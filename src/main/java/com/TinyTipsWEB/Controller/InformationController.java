package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.Service.imp.IHandleInformation;
import com.TinyTipsWEB.ValueCallBack;
import com.TinyTipsWEB.util.JSON.JSONArrayOperation;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.RequestAndResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/Information")
public class InformationController {

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "jsonArrayOperation")
    private JSONArrayOperation arrayOperation;

    @Resource(name = "handleInformation")
    private IHandleInformation handleInformation;

    @Resource(name = "requestAndResponse")
    private RequestAndResponse rr;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public void information(HttpServletResponse response){
        try {
            response.getWriter().append("Information");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addInformation(HttpServletRequest request, HttpServletResponse response){
        JSONObject temp=rr.transRequestToObject(request);
        Information newUser=objectOperation.getInformationFromJSON(temp);
        handleInformation.add(newUser, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                rr.transObjectToResponse(response,objectOperation.setResultToJSON(s));
            }

            @Override
            public void onFail(String msg) {
                rr.transObjectToResponse(response,objectOperation.setResultToJSON(msg));
            }
        });
    }

}
