package com.TinyTipsWEB.Service.imp;


import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Social;
import org.springframework.stereotype.Service;

public interface IHandleSocial {

    Result add(Social social);  //增加社交信息

    Result delete(Social social);  //删除社交信息
}
