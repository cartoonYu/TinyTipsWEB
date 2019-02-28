package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.IInformationController;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.Service.imp.IHandleInformation;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.IGetDataFromHttp;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/TinyTipsWEB/Information")
public class InformationController implements IInformationController {

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "handleInformation")
    private IHandleInformation handleInformation;

    @Resource(name = "getDataFromHttp")
    private IGetDataFromHttp getData;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    @Override
    public Result information(){
        Result result=new Result();
        result.setSuccess();
        return result;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    @Override
    public Result addInformation(@RequestBody String data){
        Information user=objectOperation.getInformationFromJSON(getData.getJSONObject(data));
        Result result=handleInformation.add(user);
        System.out.println(result.getResult());
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Override
    public Result deleteInformation(@RequestBody String data){
        Information user=objectOperation.getInformationFromJSON(getData.getJSONObject(data));
        return handleInformation.delete(user);
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    @Override
    public Object queryInformation(@RequestBody String data){
        Information condition=objectOperation.getInformationFromJSON(getData.getJSONObject(data));
        return handleInformation.query(condition);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    @Override
    public Object updateInformation(@RequestBody String data){
        JSONArray array=getData.getJSONArray(data);
        Information condition=objectOperation.getInformationFromJSON(array.getJSONObject(0));
        Information information=objectOperation.getInformationFromJSON(array.getJSONObject(1));
        return handleInformation.update(condition,information);
    }

}
