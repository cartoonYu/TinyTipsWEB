package com.TinyTipsWEB.Dao.table.imp;

import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface IOperateInformation {

    void add(Information information, ValueCallBack<String> callBack);    //插入个人信息

    void delete(Information information,ValueCallBack<String> callBack);  //删除个人信息

    void update(Information oldInformation,Information newInformation,ValueCallBack<String> callBack);  //更新个人信息

    void query(Information information, ValueCallBack<List<Information>> callBack);   //查询个人信息

}
