package servlet;

import static java.lang.System.out;

import BaseClass.ValueCallBack;
import bean.Note;
import bean.operate.OperateInformation;
import bean.operate.OperateNote;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServletNote extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private OperateNote operateNote;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("note");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        out.println(array.toString());
        List<JSONObject> data=arrayOperation.getObjectsFromArray(array);
        List<Note> result=new ArrayList<>();
        for(JSONObject object:data){
            result.add(objectOperation.getNoteFromJSON(object));
        }
        String method=objectOperation.getMethodFromJSON(data.get(0));
        switch (method){
            case "add":{
                handleAddNote(result.get(0),response);
                break;
            }
            case "delete":{
                handleDeleteNote(result.get(0),response);
                break;
            }
            case "query":{
                handleQueryNote(result.get(0),response);
                break;
            }
            case "update":{
                handleUpdateNote(result.get(0),result.get(1),response);
                break;
            }
        }
    }

    private void handleAddNote(Note data,HttpServletResponse response) {
        operateNote.add(data, new ValueCallBack<String>() {
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

    private void handleDeleteNote(Note condition, HttpServletResponse response) {
        operateNote.delete(condition, new ValueCallBack<String>() {
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

    private void handleQueryNote(Note condition, HttpServletResponse response) {
        operateNote.query(condition, new ValueCallBack<List<Note>>() {
            @Override
            public void onSuccess(List<Note> notes) {
                List<JSONObject> result=new ArrayList<>();
                for(Note note:notes){
                    result.add(objectOperation.setNoteToJSON(note));
                }
                requestAndResponse.transArrayToResponse(response,result);
            }

            @Override
            public void onFail(String code) {
                JSONObject result=objectOperation.setResultToJSON(code);
                requestAndResponse.transObjectToResponse(response,result);
            }
        });
    }

    private void handleUpdateNote(Note oldNote,Note newNote, HttpServletResponse response) {
        operateNote.update(oldNote, newNote, new ValueCallBack<String>() {
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

    public ServletNote(){
        ApplicationContext context= GetContext.getContext();
        setRequestAndResponse(context.getBean("requestAndResponse",RequestAndResponse.class));
        setArrayOperation(context.getBean("jsonArrayOperation",JSONArrayOperation.class));
        setObjectOperation(context.getBean("jsonObjectOperation",JSONObjectOperation.class));
        setOperateNote(context.getBean("operateNote",OperateNote.class));
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

    private void setOperateNote(OperateNote operateNote) {
        this.operateNote=operateNote;
    }
}
