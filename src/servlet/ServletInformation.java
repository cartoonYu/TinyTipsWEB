package servlet;

import static java.lang.System.out;
import BaseClass.ValueCallBack;
import bean.Information;
import bean.operate.OperateInformation;
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

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 接收响应客户端对于Information的操作
 *
 * notice
 * 1.传入的HttpServletRequest携带的数据必须是JSONArray形式
 * 2.响应的HttpServletResponse携带的数据形式为JSONArray
 */

public class ServletInformation extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private OperateInformation operateInformation;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        List<JSONObject> data=arrayOperation.getObjectsFromArray(array);
        List<Information> result=new ArrayList<>();
        for(JSONObject object:data){
            result.add(objectOperation.getInformationFromJSON(object));
        }
        String method=objectOperation.getMethodFromJSON(data.get(0));
        switch (method){
            case "add":{
                handleAddInformation(result.get(0),response);
                break;
            }
            case "delete":{
                handleDeleteInformation(result.get(0),response);
            }
            case "query":{
                handleQueryInformation(result.get(0),response);
                break;
            }
            case "update":{
                handleUpdateInformation(result.get(0),result.get(1),response);
                break;
            }
            default:{
                JSONObject object= objectOperation.setResultToJSON("400");
                try {
                    response.getWriter().append(object.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void handleAddInformation(Information data,HttpServletResponse response){
        operateInformation.add(data, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject object= objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object= objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    private void handleDeleteInformation(Information data,HttpServletResponse response){

        operateInformation.delete(data, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject object= objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object= objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    private void handleQueryInformation(Information condition,HttpServletResponse response){
        operateInformation.query(condition, new ValueCallBack<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                List<JSONObject> result=new ArrayList<>();
                for(Information temp:information){
                    result.add(objectOperation.setInformationToJSON(temp));
                }
                requestAndResponse.transArrayToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject object= objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    private void handleUpdateInformation(Information oldInformation,Information newInformation,HttpServletResponse response){
        operateInformation.update(oldInformation, newInformation, new ValueCallBack<String>() {
            @Override

            public void onSuccess(String s) {
                JSONObject object= objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object= objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    public ServletInformation(){
        ApplicationContext context=GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setOperateInformation(context.getBean("operateInformation",OperateInformation.class));
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

    public void setOperateInformation(OperateInformation operateInformation) {
        this.operateInformation = operateInformation;
    }
}
