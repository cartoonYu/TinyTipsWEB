package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.ICommentController;
import com.TinyTipsWEB.Model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/TinyTips/Comment")
public class CommentController implements ICommentController {


    @RequestMapping(value = "",method = RequestMethod.GET)
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
        return null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Override
    public Result deleteComment(String data) {
        return null;
    }

}
