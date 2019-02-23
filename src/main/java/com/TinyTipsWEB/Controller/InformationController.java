package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.Service.imp.IHandleInformation;
import com.TinyTipsWEB.ValueCallBack;
import com.TinyTipsWEB.util.JSON.JSONArrayOperation;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.RequestAndResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/TinyTipsWEB/Information")
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
    public void addInformation(HttpServletRequest request, final HttpServletResponse response){
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

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void deleteInformation(HttpServletRequest request, final HttpServletResponse response){
        final JSONObject object=rr.transRequestToObject(request);
        Information user=objectOperation.getInformationFromJSON(object);
        handleInformation.delete(user, new ValueCallBack<String>() {
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

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public void queryInformation(HttpServletRequest request, final HttpServletResponse response){
        JSONObject object=rr.transRequestToObject(request);
        Information condition=objectOperation.getInformationFromJSON(object);
        handleInformation.query(condition, new ValueCallBack<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                List<JSONObject> list=new ArrayList<>();
                for(Information temp:information){
                    list.add(objectOperation.setInformationToJSON(temp));
                }
                rr.transArrayToResponse(response,list);
            }

            @Override
            public void onFail(String msg) {
                rr.transObjectToResponse(response,objectOperation.setResultToJSON(msg));
            }
        });
    }

    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public void updateInformation(HttpServletRequest request, final HttpServletResponse response){
        JSONArray array=rr.transRequestToArray(request);
        Information condition=objectOperation.getInformationFromJSON(array.getJSONObject(0));
        Information data=objectOperation.getInformationFromJSON(array.getJSONObject(1));
        handleInformation.update(data, condition, new ValueCallBack<String>() {
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
