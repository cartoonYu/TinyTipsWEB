package com.TinyTipsWEB.Service.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Comment;

import java.util.List;

public interface IHandleComment {

    Result add(Comment comment);  //增加评论

    Result delete(Comment comment);  //删除评论

    Result update(Comment oldComment, Comment newComment);   //更新评论

    List<Comment> query(Comment comment);   //查询评论
}
