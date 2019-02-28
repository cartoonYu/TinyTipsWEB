package com.TinyTipsWEB.DAO.table.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Comment;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface IOperateComment {

    Result add(Comment comment);  //增加评论

    Result delete(Comment comment);  //删除评论

    Result update(Comment oldComment, Comment newComment);   //更新评论

    List<Comment> query(Comment comment);   //查询评论

}
