package servlet;

import BaseClass.ValueCallBack;
import bean.CommentDetails;
import bean.operate.OperateCommentDetails;
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

public class ServletCommentDetails extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private OperateCommentDetails operateCommentDetails;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("commentDetails");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        out.println("CommentDetails");
        out.println(array.toString());
        List<JSONObject> data=arrayOperation.getObjectsFromArray(array);
        List<CommentDetails> result=new ArrayList<>();
        for(JSONObject object:data){
            result.add(objectOperation.getCommentDetailsFromJSON(object));
        }
        String method=objectOperation.getStringFromJSON(data.get(0),"method");
        switch (method){
            case "add":{
                handleAddDetails(result.get(0),response);
                break;
            }
            case "delete":{
                handleDeleteDetails(result.get(0),response);
                break;
            }
            case "query":{
                handleQueryDetails(result.get(0),response);
                break;
            }
            case "update":{
                handleUpdateDetails(result.get(0),result.get(1),response);
                break;
            }
        }
        out.println();
    }

    public void handleAddDetails(CommentDetails details,HttpServletResponse response){
        operateCommentDetails.add(details, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject result=objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public void handleDeleteDetails(CommentDetails details,HttpServletResponse response){
        operateCommentDetails.delete(details, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject result=objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public void handleQueryDetails(CommentDetails details,HttpServletResponse response){
        operateCommentDetails.query(details, new ValueCallBack<List<CommentDetails>>() {
            @Override
            public void onSuccess(List<CommentDetails> commentDetails) {
                List<JSONObject> list=new ArrayList<>();
                for(CommentDetails temp:commentDetails){
                    list.add(objectOperation.setCommentDetailsToJSON(temp));
                }
                requestAndResponse.transArrayToResponse(response,list);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public void handleUpdateDetails(CommentDetails source,CommentDetails result,HttpServletResponse response){
        operateCommentDetails.update(source, result, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject result=objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public ServletCommentDetails(){
        ApplicationContext context= GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setOperateCommentDetails(context.getBean("operateCommentDetails",OperateCommentDetails.class));
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

    public void setOperateCommentDetails(OperateCommentDetails operateCommentDetails) {
        this.operateCommentDetails = operateCommentDetails;
    }
}
