package com.TinyTipsWEB;

import org.json.JSONObject;

public interface ValueCallBack <T>{

    void onSuccess(T t);
    void onFail(String msg);
}
