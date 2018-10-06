package util.JSON;

import org.json.JSONArray;
import org.json.JSONObject;
import util.network.RequestAndResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * JSONObject与JSONArray的互相转化
 *
 * notice
 * none
 */
public class JSONArrayOperation {

    /**
     * 功能
     * 将JSONArray转化成JSONObject
     *
     * 使用方法
     * 1.传入JSONArray对象
     * 2.通过返回值获取JSONObject的集合
     *
     * @param source
     * @return
     */
    public List<JSONObject> getObjectsFromArray(JSONArray source){
        List<JSONObject> result=new ArrayList<>();
        for(int i=0;i<source.length();i++){
            result.add(source.getJSONObject(i));
        }
        return result;
    }

    /**
     * 功能
     * 将JSONObject转化成JSONArray
     *
     * 使用方法
     * 1.传入JSONObject集合
     * 2.通过返回值获取JSONArray对象
     *
     * @param source
     * @return
     */
    public JSONArray setObjectToArray(List<JSONObject> source){
        JSONArray result=new JSONArray();
        for(JSONObject object:source){
            result.put(object);
        }
        return result;
    }

}
