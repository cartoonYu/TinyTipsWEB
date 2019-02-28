package com.TinyTipsWEB.Controller.imp;

import com.TinyTipsWEB.Model.Result;

public interface ICommentController {

    Result comment();

    Result addComment(String data);

    Result deleteComment(String data);

}
