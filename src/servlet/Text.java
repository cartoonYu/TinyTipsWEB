package servlet;

import BaseClass.ValueCallBack;
import bean.Information;
import bean.operate.OperateInformation;
import net.sf.json.JSONObject;
import spring.GetContext;
import sql.DBConnection;
import sql.OperateDB;
import util.CurrentTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Text")
public class Text extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Information information=GetContext.getContext().getBean("information",Information.class);
        information.setAccount("cartoon");
        information.setPassword("asd12345");
        information.setHeadPortrait("qwe");
        information.setNickName("cartoon");
        information.setSex(true);
        List<String> list=new ArrayList<>();
        list.add("asdf");
        list.add("qwer");
        information.setInterest(list);
        information.setSchool("wyu");
        information.setBackground("高中");
        information.setResume("dfesgaegw");
        OperateInformation oi=GetContext.getContext().getBean("operateInformation",OperateInformation.class);
        oi.query(information, new ValueCallBack<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                for(Information information1:information){
                    System.out.println(information1.getPassword());
                }
            }

            @Override
            public void onFail(String code) {
                System.out.println(code);
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
