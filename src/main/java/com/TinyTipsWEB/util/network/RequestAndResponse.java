package com.TinyTipsWEB.util.network;

import com.TinyTipsWEB.util.JSON.JSONArrayOperation;
import com.TinyTipsWEB.util.JudgeEmpty;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 处理Servlet传入HttpServletRequest以及相对应的HttpServletResponse
 *
 * notice
 * none
 */

@Component("requestAndResponse")
public class RequestAndResponse {

    @Resource(name="jsonArrayOperation")
    private JSONArrayOperation arrayOperation;

    /**
     * 功能
     * 获取带有JSONArray的HttpServletRequest里的JSONArray
     *
     * 使用方法
     * 1.传入带有JSONArray的HttpServletRequest
     * 2.通过方法返回值获取JSONArray
     *
     * 注意
     * 1.传入的HttpServletRequest只能携带JSONArray
     *
     * @param request
     * @return
     */
    public JSONArray transRequestToArray(HttpServletRequest request){
        if(JudgeEmpty.isEmpty(request)){
            return null;
        }
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder source=new StringBuilder();
        try {
            BufferedReader reader=request.getReader();
            String temp=null;
            while((temp=reader.readLine())!=null){
                source.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray array=new JSONArray(source.toString());
        return array;
    }

    /**
     * 功能
     * 获取带有JSONObject的HttpServletRequest里的JSONObject
     *
     * 使用方法
     * 1.传入带有JSONObject的HttpServletRequest
     * 2.通过方法返回值获取JSONArray
     *
     * 注意
     * 1.传入的HttpServletRequest只能携带JSONObject
     *
     * @param request
     * @return
     */
    public JSONObject transRequestToObject(HttpServletRequest request){
        if(JudgeEmpty.isEmpty(request)){
            return null;
        }
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder source=new StringBuilder();
        try {
            BufferedReader reader=request.getReader();
            String temp=null;
            while((temp=reader.readLine())!=null){
                source.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object=new JSONObject(source.toString());
        return object;
    }

    /**
     * 功能
     * 响应客户端请求
     *
     * 使用方法
     * 1.传入JSONObject集合以及HttpServletResponse
     *
     * @param response
     * @param result
     */
    public void transArrayToResponse(HttpServletResponse response, List<JSONObject> result){
        JSONArray array=arrayOperation.setObjectToArray(result);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().append(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transObjectToResponse(HttpServletResponse response, JSONObject object){
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().append(object.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
