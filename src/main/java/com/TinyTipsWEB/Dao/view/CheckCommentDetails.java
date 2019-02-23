package com.TinyTipsWEB.DAO.view;

import com.TinyTipsWEB.DAO.sql.IOperateDB;
import com.TinyTipsWEB.DAO.view.imp.ICheckCommentDetails;
import com.TinyTipsWEB.Model.view.CommentDetails;
import com.TinyTipsWEB.ValueCallBack;
import com.TinyTipsWEB.util.JudgeEmpty;
import com.TinyTipsWEB.util.file.FileOperation;
import com.TinyTipsWEB.util.file.ImageConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("checkCommentDetails")
public class CheckCommentDetails implements ICheckCommentDetails {

    @Resource(name = "fileOperation")
    private FileOperation fileOperation;

    @Resource(name = "imageConstant")
    private ImageConstant imageConstant;

    @Value("CommentDetails")
    private String viewName;

    @Resource(name = "operateDB")
    private IOperateDB db;

    /**
     * 获取评论列表
     * @param details
     * @param callBack
     */
    @Override
    public void getCommentDetails(CommentDetails details, ValueCallBack<List<CommentDetails>> callBack) {
        List<CommentDetails> data=new ArrayList<>();
        if(JudgeEmpty.isEmpty(details)){
            callBack.onFail("300");
            return;
        }
        try {
            ResultSet set=db.query(viewName,changeDetailsToMap(details));
            while (set.next()){
                CommentDetails temp=new CommentDetails();
                temp.setUserId(set.getLong("userId"));
                temp.setNoteId(set.getLong("noteId"));
                temp.setDate(set.getString("date"));
                temp.setDetails(set.getString("details"));
                temp.setNickName(set.getString("nickName"));
                temp.setHeadPortraitName(set.getString("headPortrait"));
                if(JudgeEmpty.isNotEmpty(temp.getHeadPortraitName())){
                    temp.setHeadPortrait(fileOperation.queryFile(imageConstant.getInformation(),temp.getHeadPortraitName(),".jpg"));
                }
                data.add(temp);
            }
            callBack.onSuccess(data);
        }catch (SQLException e){
            callBack.onFail("400");
        }
    }

    private Map<String,String> changeDetailsToMap(CommentDetails details){
        Map<String,String> data=new HashMap<>();
        if(details.getUserId()!=0){
            data.put("userId",Long.toString(details.getUserId()));
        }
        if(details.getNoteId()!=0){
            data.put("noteId",Long.toString(details.getNoteId()));
        }
        if(JudgeEmpty.isNotEmpty(details.getDate())){
            data.put("date",details.getDate());
        }
        if(JudgeEmpty.isNotEmpty(details.getNickName())){
            data.put("nickName",details.getNickName());
        }
        if(JudgeEmpty.isNotEmpty(details.getDetails())){
            data.put("details",details.getDetails());
        }
        return data;
    }

}
