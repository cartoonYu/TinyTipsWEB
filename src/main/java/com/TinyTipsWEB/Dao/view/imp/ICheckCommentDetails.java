package com.TinyTipsWEB.DAO.view.imp;

import com.TinyTipsWEB.Model.view.CommentDetails;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface ICheckCommentDetails {

    void getCommentDetails(CommentDetails details, ValueCallBack<List<CommentDetails>> callBack);  //获取评论详情

}
