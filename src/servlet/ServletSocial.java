package servlet;

import BaseClass.ValueCallBack;
import bean.Social;
import bean.operate.OperateSocial;
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

public class ServletSocial extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private OperateSocial operateSocial;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("social");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        out.println("Social");
        out.println(array.toString());
        List<JSONObject> data=arrayOperation.getObjectsFromArray(array);
        List<Social> result=new ArrayList<>();
        for(JSONObject object:data){
            result.add(objectOperation.getSocialFromJSON(object));
        }
        String method=objectOperation.getStringFromJSON(data.get(0),"method");
        switch (method){
            case "add":{
                handleAddSocial(result.get(0),response);
                break;
            }
            case "delete":{
                handleDeleteSocial(result.get(0),response);
                break;
            }
            case "query":{
                handleQuerySocial(result.get(0),response);
                break;
            }
        }
    }

    private void handleAddSocial(Social social, HttpServletResponse response){
        operateSocial.add(social, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                sendResultToClient(s,response);
            }

            @Override
            public void onFail(String code) {
                sendResultToClient(code,response);
            }
        });
    }

    private void handleDeleteSocial(Social social,HttpServletResponse response){
        operateSocial.delete(social, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                sendResultToClient(s,response);
            }

            @Override
            public void onFail(String code) {
                sendResultToClient(code,response);
            }
        });
    }

    private void handleQuerySocial(Social social,HttpServletResponse response){
        operateSocial.query(social, new ValueCallBack<List<Social>>() {
            @Override
            public void onSuccess(List<Social> socials) {
                List<JSONObject> result=new ArrayList<>();
                for(Social temp:socials){
                    result.add(objectOperation.setSocialToJSON(temp));
                }
                requestAndResponse.transArrayToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                sendResultToClient(code,response);
            }
        });
    }



    private void sendResultToClient(String result,HttpServletResponse response){
        JSONObject object=objectOperation.setResultToJSON(result);
        requestAndResponse.transObjectToResponse(response,object);
    }

    public ServletSocial(){
        ApplicationContext context= GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setOperateSocial(context.getBean("operateSocial",OperateSocial.class));
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

    public void setOperateSocial(OperateSocial operateSocial) {
        this.operateSocial = operateSocial;
    }
}
