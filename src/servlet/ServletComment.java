package servlet;

import static java.lang.System.out;

import BaseClass.ValueCallBack;
import bean.Comment;
import bean.operate.OperateComment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import spring.GetContext;
import util.JSON.JSONArrayOperation;
import util.JSON.JSONObjectOperation;
import util.ListAndString;
import util.network.RequestAndResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String method=objectOperation.getMethodFromJSON(data.get(0));
        switch (method){
            case "add":{
                handleAddComment(result.get(0),response);
                break;
            }
            case "delete":{
                handleDeleteComment(result.get(0),response);
                break;
            }
            case "query":{
                handleQueryComment(result.get(0),response);
                break;
            }
            case "update":{
                handleUpdateComment(result.get(0),result.get(1),response);
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
        out.println();
    }

    private void handleAddComment(Comment comment,HttpServletResponse response){
        operateComment.add(comment, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject object=objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    private void handleDeleteComment(Comment comment,HttpServletResponse response){
        operateComment.delete(comment, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject object=objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    private void handleQueryComment(Comment comment,HttpServletResponse response){
        operateComment.query(comment, new ValueCallBack<Comment>() {
            @Override
            public void onSuccess(Comment comment) {
                JSONObject object=objectOperation.setCommentToJSON(comment);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    private void handleUpdateComment(Comment oldComment,Comment newComment,HttpServletResponse response){
        operateComment.update(oldComment, newComment, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject object=objectOperation.setResultToJSON(s);
                requestAndResponse.transObjectToResponse(response,object);
            }

            @Override
            public void onFail(String code) {
                JSONObject object=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    public ServletComment(){
        ApplicationContext context=GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setOperateComment(context.getBean("operateComment",OperateComment.class));
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
