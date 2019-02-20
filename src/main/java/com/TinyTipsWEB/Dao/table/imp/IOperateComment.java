package com.TinyTipsWEB.Dao.table.imp;

import com.TinyTipsWEB.Model.table.Comment;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface IOperateComment {

    void add(Comment comment, ValueCallBack<String> callBack);  //增加评论

    void delete(Comment comment,ValueCallBack<String> callBack);  //删除评论

    void update(Comment oldComment,Comment newComment,ValueCallBack<String> callBack);   //更新评论

    void query(Comment comment,ValueCallBack<List<Comment>> callBack);   //查询评论

}
