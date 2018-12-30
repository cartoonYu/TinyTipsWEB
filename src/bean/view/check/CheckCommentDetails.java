package bean.view.check;

import BaseClass.ValueCallBack;
import bean.view.CommentDetails;
import sql.OperateDB;
import util.JudgeEmpty;
import util.file.FileOperation;
import util.file.ImageConstant;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckCommentDetails {

    private FileOperation fileOperation;

    private ImageConstant imageConstant;

    private String viewName;

    private OperateDB db;

    public void getCommentDetails(CommentDetails details, ValueCallBack<List<CommentDetails>> callBack){
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


    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setImageConstant(ImageConstant imageConstant) {
        this.imageConstant = imageConstant;
    }

    public void setFileOperation(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public void setDb(OperateDB db) {
        this.db = db;
    }
}
