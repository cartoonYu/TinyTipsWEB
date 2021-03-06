package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.ICommentController;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Comment;
import com.TinyTipsWEB.Service.imp.IHandleComment;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.IGetDataFromHttp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/TinyTipsWEB/Comment")
public class CommentController implements ICommentController {

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "handleComment")
    private IHandleComment handleComment;

    @Resource(name = "getDataFromHttp")
    private IGetDataFromHttp getData;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Override
    public Result comment() {
        Result result=new Result();
        result.setSuccess();
        return result;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    @Override
    public Result addComment(@RequestBody String data) {
        Comment comment=objectOperation.getCommentFromJSON(getData.getJSONObject(data));
        return handleComment.add(comment);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Override
    public Result deleteComment(String data) {
        Comment comment=objectOperation.getCommentFromJSON(getData.getJSONObject(data));
        return handleComment.delete(comment);
    }

}
