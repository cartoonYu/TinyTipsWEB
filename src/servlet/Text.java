package servlet;

import BaseClass.ValueCallBack;
import bean.Information;
import bean.Note;
import bean.operate.OperateNote;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import spring.GetContext;
import util.JSON.JSONArrayOperation;
import util.JSON.JSONObjectOperation;
import util.file.FileOperation;
import util.network.RequestAndResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class Text extends HttpServlet {

    private ApplicationContext context;

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private FileOperation fileOperation;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        // TODO Auto-generated method stub
        Note note=GetContext.getContext().getBean("note",Note.class);
        note.setAuthor("cartoon");
        note.setTitle("w");
        OperateNote on=GetContext.getContext().getBean("operateNote",OperateNote.class);
        on.add(note, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                try {
                    response.getWriter().append(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String code) {
                try {
                    response.getWriter().append(code);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        JSONArray array=requestAndResponse.transRequestToArray(request);
        out.println(array);
        List<JSONObject> objects=arrayOperation.getObjectsFromArray(array);
        Information information=objectOperation.getInformationFromJSON(objects.get(0));
        out.println(information.getHeadPortraitName());
        JSONObject result=objectOperation.setResultToJSON(information.getHeadPortraitName());
        requestAndResponse.transObjectToResponse(response,result);
    }

    public Text(){
        context=GetContext.getContext();
        requestAndResponse=context.getBean("requestAndResponse", RequestAndResponse.class);
        arrayOperation=context.getBean("jsonArrayOperation", JSONArrayOperation.class);
        objectOperation=context.getBean("jsonObjectOperation", JSONObjectOperation.class);
        fileOperation=context.getBean("fileOperation",FileOperation.class);
    }

}
