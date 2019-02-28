package com.TinyTipsWEB.Model;

import java.io.Serializable;

public class Result{

    private String result;

    public String getResult() {
        return result;
    }

    public void setSuccess(){
        result="200";
    }

    public void setOperateError(){
        result="300";
    }

    public void setFail(){
        result="400";
    }
}
