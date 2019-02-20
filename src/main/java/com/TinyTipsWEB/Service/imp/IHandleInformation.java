package com.TinyTipsWEB.Service.imp;

import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface IHandleInformation {

    void add(Information information, ValueCallBack<String> callBack);

    void delete(Information information,ValueCallBack<String> callBack);

    void query(Information information, ValueCallBack<List<Information>> callBack);

    void update(Information data,Information condition,ValueCallBack<String> callBack);
}
