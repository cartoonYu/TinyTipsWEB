package com.TinyTipsWEB.Dao.table.imp;

import com.TinyTipsWEB.Model.table.Social;
import com.TinyTipsWEB.ValueCallBack;

public interface IOperateSocial {

    void add(Social social, ValueCallBack<String> callBack);  //增加社交信息

    void delete(Social social,ValueCallBack<String> callBack);  //删除社交信息

}
