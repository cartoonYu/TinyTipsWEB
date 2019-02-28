package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.table.imp.IOperateInformation;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Service.imp.IHandleInformation;
import com.TinyTipsWEB.Model.table.Information;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandleInformation implements IHandleInformation {

    @Resource(name = "operateInformation")
    private IOperateInformation op;

    @Override
    public Result add(Information information) {
        return op.add(information);
    }

    @Override
    public Result delete(Information information) {
        return op.delete(information);
    }

    @Override
    public Result update(Information oldInformation, Information newInformation) {
        return op.update(oldInformation, newInformation);
    }

    @Override
    public List<Information> query(Information information) {
        return op.query(information);
    }
}
