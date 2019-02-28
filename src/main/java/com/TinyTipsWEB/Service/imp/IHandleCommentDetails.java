package com.TinyTipsWEB.Service.imp;

import com.TinyTipsWEB.Model.view.CommentDetails;

import java.util.List;

public interface IHandleCommentDetails {

    List<CommentDetails> getCommentDetails(CommentDetails details);  //获取评论详情

}
