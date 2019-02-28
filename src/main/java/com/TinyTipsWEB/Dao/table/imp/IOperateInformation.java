package com.TinyTipsWEB.DAO.table.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Information;

import java.util.List;

public interface IOperateInformation {

    Result add(Information information);    //插入个人信息

    Result delete(Information information);  //删除个人信息

    Result update(Information oldInformation, Information newInformation);  //更新个人信息

    List<Information> query(Information information);   //查询个人信息

}
