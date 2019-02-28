package com.TinyTipsWEB.Controller.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.view.CommentDetails;

import java.util.List;

public interface ICommentDetailsController {

    Result commentDetails();

    List<CommentDetails> getDetails(String data);

}
