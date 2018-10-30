package servlet;

import static java.lang.System.out;

import bean.Note;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServletNote extends HttpServlet {

    private RequestAndResponse requestAndResponse;

    private JSONArrayOperation arrayOperation;

    private JSONObjectOperation objectOperation;

    private OperateInformation operateInformation;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("note");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        JSONArray array=requestAndResponse.transRequestToArray(request);
        out.println(array.toString());
        Note note=new Note();
        note.setId(12);
        note.setUserId(23);
        note.setTitle("java学习指南");
        List<String> word=new ArrayList<>();
        word.add("asd");
        word.add("qwe");
        word.add("zxc");
        note.setWordDetails(word);
        Map<String,String> map=new LinkedHashMap<>();
        map.put("asd","asd");
        map.put("qwe","qwe");
        map.put("zxc","zxc");
        note.setPhoto(map);
        note.setAuthor("cartoon");
        note.setDate("2018-05-06");
        List<String> tag=new ArrayList<>();
        tag.add("asd");
        tag.add("zxc");
        tag.add("qwe");
        note.setTag(tag);
        JSONObject object=objectOperation.setNoteToJSON(note);
        requestAndResponse.transObjectToResponse(response,object);
    }

    public ServletNote(){
        ApplicationContext context= GetContext.getContext();
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
