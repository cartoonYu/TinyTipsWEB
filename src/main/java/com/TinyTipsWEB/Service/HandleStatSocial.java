package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.view.imp.ICheckStatSocial;
import com.TinyTipsWEB.Model.view.StatSocial;
import com.TinyTipsWEB.Service.imp.IHandleStatSocial;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandleStatSocial implements IHandleStatSocial {

    @Resource(name = "checkSocial")
    private ICheckStatSocial check;

    @Override
    public List<StatSocial> getSocial(StatSocial statSocial) {
        return check.getSocial(statSocial);
    }
}
