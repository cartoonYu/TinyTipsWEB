package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.ICommentDetailsController;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.view.CommentDetails;
import com.TinyTipsWEB.Service.imp.IHandleCommentDetails;
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
@RequestMapping("/TinyTipsWEB/CommentDetails")
public class CommentDetailsController implements ICommentDetailsController{

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "handleCommentDetails")
    private IHandleCommentDetails handle;

    @Resource(name = "getDataFromHttp")
    private IGetDataFromHttp getData;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Override
    public Result commentDetails() {
        Result result=new Result();
        result.setSuccess();
        return result;
    }

    @RequestMapping("/query")
    @ResponseBody
    @Override
    public List<CommentDetails> getDetails(@RequestBody String data) {
        CommentDetails details=objectOperation.getCommentDetailsFromJSON(getData.getJSONObject(data));
        return handle.getCommentDetails(details);
    }

}
