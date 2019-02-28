package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.view.imp.ICheckCommentDetails;
import com.TinyTipsWEB.Model.view.CommentDetails;
import com.TinyTipsWEB.Service.imp.IHandleCommentDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandleCommentDetails implements IHandleCommentDetails {

    @Resource(name = "checkCommentDetails")
    private ICheckCommentDetails check;

    @Override
    public List<CommentDetails> getCommentDetails(CommentDetails details) {
        return check.getCommentDetails(details);
    }
}
