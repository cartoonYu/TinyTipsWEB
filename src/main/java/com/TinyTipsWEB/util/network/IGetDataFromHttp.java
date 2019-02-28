package com.TinyTipsWEB.util.network;


import org.json.JSONArray;
import org.json.JSONObject;

public interface IGetDataFromHttp {

    JSONObject getJSONObject(String data);

    JSONArray getJSONArray(String data);
}
