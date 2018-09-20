package servlet;

import net.sf.json.JSONObject;
import spring.GetContext;
import sql.DBConnection;
import sql.OperateDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Text")
public class Text extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        OperateDB db= GetContext.getContext().getBean("operateDB",OperateDB.class);
        //DBConnection c=GetContext.getContext().getBean("dbConnection",DBConnection.class);
        Map<String,String> data=new HashMap<>();
        data.put("author", "cartoon");
        data.put("id", "3");
        boolean b=db.add("note",data);
        response.getWriter().append(""+b).append("qwe");
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
