package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.IStatSocialController;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.view.StatSocial;
import com.TinyTipsWEB.Service.imp.IHandleStatSocial;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.IGetDataFromHttp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/TinyTipsWEB/StatSocial")
public class StatSocialController implements IStatSocialController {

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "handleStatSocial")
    private IHandleStatSocial handle;

    @Resource(name = "getDataFromHttp")
    private IGetDataFromHttp getData;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public Result social() {
        Result result=new Result();
        result.setSuccess();
        return result;
    }

    @ResponseBody
    @RequestMapping("/query")
    @Override
    public List<StatSocial> getSocial(@RequestBody String data) {
        StatSocial social=objectOperation.getStatSocialFromJSON(getData.getJSONObject(data));
        return handle.getSocial(social);
    }
}
