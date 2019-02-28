package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.table.imp.IOperateComment;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Comment;
import com.TinyTipsWEB.Service.imp.IHandleComment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandleComment implements IHandleComment {

    @Resource(name = "operateComment")
    private IOperateComment operateComment;

    @Override
    public Result add(Comment comment) {
        return operateComment.add(comment);
    }

    @Override
    public Result delete(Comment comment) {
        return operateComment.delete(comment);
    }

    @Override
    public Result update(Comment oldComment, Comment newComment) {
        return operateComment.update(oldComment, newComment);
    }

    @Override
    public List<Comment> query(Comment comment) {
        return operateComment.query(comment);
    }
}
