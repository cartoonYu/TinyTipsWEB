package com.TinyTipsWEB.util.network;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author cartoon
 * @version 1.0
 *
 * 1.将http传输的数据解码
 * 2.将解码后的数据转换成JSON输出
 */

@Component("getDataFromHttp")
public class GetDataFromHttp implements IGetDataFromHttp{

    private Logger log;

    @Override
    public JSONObject getJSONObject(String data){
        String temp=decode(data);
        log.info(temp);
        JSONObject object=new JSONObject(temp);
        return object;
    }

    @Override
    public JSONArray getJSONArray(String data) {
        String temp=decode(data);
        System.out.println(temp);
        JSONArray array=new JSONArray(temp);
        return array;
    }

    private String decode(String data){
        String temp=null;
        try {
            temp= URLDecoder.decode(data,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return temp.substring(0,temp.length()-1);
    }

    @PostConstruct
    private void init(){
        log=Logger.getLogger(this.getClass());
    }
}
