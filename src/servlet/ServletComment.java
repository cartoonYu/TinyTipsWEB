package servlet;

import BaseClass.ValueCallBack;
import bean.table.Comment;
import bean.table.operate.OperateComment;
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

public class ServletComment extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private OperateComment operateComment;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("comment");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        out.println("Comment");
        out.println(array.toString());
        List<JSONObject> data=arrayOperation.getObjectsFromArray(array);
        List<Comment> result=new ArrayList<>();
        for(JSONObject object:data){
            result.add(objectOperation.getCommentFromJSON(object));
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

    public void handleAddDetails(Comment details, HttpServletResponse response){
        operateComment.add(details, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject result=objectOperation.setResultToJSON(s);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public void handleDeleteDetails(Comment details, HttpServletResponse response){
        operateComment.delete(details, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject result=objectOperation.setResultToJSON(s);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public void handleQueryDetails(Comment details, HttpServletResponse response){
        operateComment.query(details, new ValueCallBack<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> commentDetails) {
                List<JSONObject> list=new ArrayList<>();
                for(Comment temp:commentDetails){
                    JSONObject result=objectOperation.setCommentToJSON(temp);
                    list.add(result);
                    objectOperation.displayJSON(result);
                }
                requestAndResponse.transArrayToResponse(response,list);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public void handleUpdateDetails(Comment source, Comment result, HttpServletResponse response){
        operateComment.update(source, result, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject result=objectOperation.setResultToJSON(s);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                objectOperation.displayJSON(result);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    public ServletComment(){
        ApplicationContext context= GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setOperateComment(context.getBean("operateComment", OperateComment.class));
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

    public void setOperateComment(OperateComment operateComment) {
        this.operateComment = operateComment;
    }
}
