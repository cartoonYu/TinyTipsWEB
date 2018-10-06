package servlet;

import BaseClass.ValueCallBack;
import bean.Note;
import bean.operate.OperateNote;
import org.json.JSONObject;
import spring.GetContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Text extends HttpServlet {

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
        JSONObject result=new JSONObject();
        result.put("result", "200");
        response.getWriter().append(result.toString());
    }

}
