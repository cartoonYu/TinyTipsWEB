package com.TinyTipsWEB;

public interface ValueCallBack<T>{

    void onSuccess(T t);
    void onFail(String msg);
}
