package com.TinyTipsWEB.DAO.view.imp;

import com.TinyTipsWEB.Model.view.CommentDetails;

import java.util.List;

public interface ICheckCommentDetails {

    List<CommentDetails> getCommentDetails(CommentDetails details);  //获取评论详情

}
