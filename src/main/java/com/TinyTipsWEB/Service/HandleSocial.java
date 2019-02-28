package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.table.imp.IOperateSocial;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Social;
import com.TinyTipsWEB.Service.imp.IHandleSocial;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HandleSocial implements IHandleSocial {

    @Resource(name = "operateSocial")
    private IOperateSocial operateSocial;

    @Override
    public Result add(Social social) {
        return operateSocial.add(social);
    }

    @Override
    public Result delete(Social social) {
        return operateSocial.delete(social);
    }
}
