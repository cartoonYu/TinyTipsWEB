package servlet;

import static java.lang.System.out;
import BaseClass.ValueCallBack;
import bean.Information;
import bean.operate.OperateInformation;
import net.sf.json.JSONObject;
import spring.GetContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Text")
public class Text extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        // TODO Auto-generated method stub
        Information information=GetContext.getContext().getBean("information",Information.class);
        information.setAccount("cartoo");
        information.setPassword("qwert12435");
        OperateInformation oi=GetContext.getContext().getBean("operateInformation",OperateInformation.class);
        oi.add(information, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                out.println(s);
            }

            @Override
            public void onFail(String code) {
                out.println(code);
            }
        });
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        JSONObject result=new JSONObject();
        result.put("result", "200");
        System.out.println(result.toString());
        response.getWriter().append(result.toString());
    }

}
