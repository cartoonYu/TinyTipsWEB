package com.TinyTipsWEB.DAO.table.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Social;

public interface IOperateSocial {

    Result add(Social social);  //增加社交信息

    Result delete(Social social);  //删除社交信息

}
