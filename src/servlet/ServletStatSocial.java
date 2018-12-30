package servlet;

import BaseClass.ValueCallBack;
import bean.view.StatSocial;
import bean.view.check.CheckStatSocial;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import spring.GetContext;
import util.JSON.JSONArrayOperation;
import util.JSON.JSONObjectOperation;
import util.network.RequestAndResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class ServletStatSocial extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private CheckStatSocial checkStatSocial;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().append("statSocial");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        List<JSONObject> resource=arrayOperation.getObjectsFromArray(array);
        checkStatSocial.getSocial(objectOperation.getStatSocialFromJSON(resource.get(0)), new ValueCallBack<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> statSocials) {
                List<JSONObject> result=new ArrayList<>();
                for(StatSocial temp:statSocials){
                    result.add(objectOperation.setStatSocialToJSON(temp));
                    objectOperation.displayJSON(objectOperation.setStatSocialToJSON(temp));
                }
                requestAndResponse.transArrayToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                requestAndResponse.transObjectToResponse(response,objectOperation.setResultToJSON(code));
            }
        });
    }

    public ServletStatSocial(){
        ApplicationContext context= GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setCheckStatSocial(context.getBean("checkSocial",CheckStatSocial.class));
    }

    public void setRequestAndResponse(RequestAndResponse requestAndResponse) {
        this.requestAndResponse = requestAndResponse;
    }

    public void setArrayOperation(JSONArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }

    public void setObjectOperation(JSONObjectOperation objectOperation) {
        this.objectOperation = objectOperation;
    }

    public void setCheckStatSocial(CheckStatSocial checkStatSocial) {
        this.checkStatSocial = checkStatSocial;
    }
}
