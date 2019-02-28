package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.ISocialController;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Social;
import com.TinyTipsWEB.Service.imp.IHandleSocial;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.IGetDataFromHttp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/TinyTipsWEB/Social")
public class SocialController implements ISocialController{

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "handleSocial")
    private IHandleSocial handle;

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
    @RequestMapping("/add")
    @Override
    public Result addSocial(@RequestBody String data) {
        Social social=objectOperation.getSocialFromJSON(getData.getJSONObject(data));
        return handle.add(social);
    }

    @ResponseBody
    @RequestMapping("/delete")
    @Override
    public Result deleteSocial(@RequestBody String data) {
        Social social=objectOperation.getSocialFromJSON(getData.getJSONObject(data));
        return handle.delete(social);
    }
}
