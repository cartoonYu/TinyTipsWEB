package util.network;

import static java.lang.System.out;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JSON.JSONArrayOperation;
import util.JudgeEmpty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
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
public class RequestAndResponse {

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
    public JSONArray transRequest(HttpServletRequest request){
        if(JudgeEmpty.isEmpty(request)){
            return null;
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
     * 响应客户端请求
     *
     * 使用方法
     * 1.传入JSONObject集合以及HttpServletResponse
     *
     * @param response
     * @param result
     */
    public void transResponse(HttpServletResponse response, List<JSONObject> result){
        JSONArray array=arrayOperation.setObjectToArray(result);
        try {
            response.getWriter().append(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将JSONArray自定义工具类对象传进本类
     * 注：已经通过Spring注入对象，无需在后续操作显式传入
     * @param arrayOperation
     */
    public void setArrayOperation(JSONArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }
}
