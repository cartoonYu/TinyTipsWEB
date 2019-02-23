package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.table.imp.IOperateInformation;
import com.TinyTipsWEB.Service.imp.IHandleInformation;
import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.ValueCallBack;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandleInformation implements IHandleInformation {

    @Resource(name = "operateInformation")
    private IOperateInformation op;

    @Override
    public void add(Information information, final ValueCallBack<String> callBack) {
        op.add(information, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                callBack.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void delete(Information information, final ValueCallBack<String> callBack) {
        op.delete(information, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                callBack.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void query(Information information, final ValueCallBack<List<Information>> callBack) {
        op.query(information, new ValueCallBack<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                callBack.onSuccess(information);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void update(Information data, Information condition, final ValueCallBack<String> callBack) {
        op.update(condition, data, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                callBack.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

}
