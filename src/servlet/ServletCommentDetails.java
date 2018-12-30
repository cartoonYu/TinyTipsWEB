package servlet;

import BaseClass.ValueCallBack;
import bean.table.Comment;
import bean.view.CommentDetails;
import bean.view.check.CheckCommentDetails;
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

public class ServletCommentDetails extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private CheckCommentDetails checkCommentDetails;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().append("CommentDetails");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        List<JSONObject> resource=arrayOperation.getObjectsFromArray(array);
        checkCommentDetails.getCommentDetails(objectOperation.getCommentDetailsFromJSON(resource.get(0)), new ValueCallBack<List<CommentDetails>>() {
            @Override
            public void onSuccess(List<CommentDetails> commentDetails) {
                List<JSONObject> result=new ArrayList<>();
                for(CommentDetails temp:commentDetails){
                    JSONObject object=objectOperation.setCommentDetailsToJSON(temp);
                    objectOperation.displayJSON(object);
                    result.add(object);
                }
                requestAndResponse.transArrayToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject object=objectOperation.setResultToJSON(code);
                objectOperation.displayJSON(object);
                requestAndResponse.transObjectToResponse(response,object);
            }
        });
    }

    public ServletCommentDetails(){
        ApplicationContext context= GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setCheckCommentDetails(context.getBean("checkCommentDetails",CheckCommentDetails.class));
    }

    public void setArrayOperation(JSONArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }

    public void setObjectOperation(JSONObjectOperation objectOperation) {
        this.objectOperation = objectOperation;
    }

    public void setRequestAndResponse(RequestAndResponse requestAndResponse) {
        this.requestAndResponse = requestAndResponse;
    }

    public void setCheckCommentDetails(CheckCommentDetails checkCommentDetails) {
        this.checkCommentDetails = checkCommentDetails;
    }
}
