package com.TinyTipsWEB.DAO.table;

import com.TinyTipsWEB.DAO.sql.IOperateDB;
import com.TinyTipsWEB.DAO.table.imp.IOperateComment;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Comment;
import com.TinyTipsWEB.ValueCallBack;
import com.TinyTipsWEB.util.CurrentTime;
import com.TinyTipsWEB.util.JudgeEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("operateComment")
public class OperateComment implements IOperateComment {

    @Value("Comment")
    private String tableName;

    @Resource(name = "operateDB")
    private IOperateDB db;

    @Resource(name = "currentTime")
    private CurrentTime currentTime;

    /**
     * 功能
     * 将传入数据写入到数据库的CommentDetails表
     *
     * 使用方法
     * 1.传入评论详情对象
     * 2.通过回调接口得到插入结果
     *
     * @param details
     */
    @Override
    public Result add(Comment details) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(details)|| JudgeEmpty.isEmpty(details.getDetails())){
            result.setOperateError();
            return result;
        }
        Map<String,String> data=changeDetailsToMap(details);
        data.put("date",currentTime.getDate("time"));
        if(db.add(tableName,data)){
            result.setSuccess();
        }
        else {
            result.setFail();
        }
        return result;
    }

    /**
     * 功能
     * 根据传入条件删除数据库的CommentDetails表中符合条件的值
     *
     * 使用方法
     * 1.传入带有条件的评论详情对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     *
     * @param comment
     */
    @Override
    public Result delete(Comment comment) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(comment)|| JudgeEmpty.isEmpty(comment.getDetails())){
            result.setOperateError();
            return result;
        }
        Map<String,String> data=changeDetailsToMap(comment);
        if(db.delete(tableName,data)){
            result.setSuccess();
        }
        else {
            result.setFail();
        }
        return result;
    }

    /**
     * 功能
     * 更新数据库中符合传入条件的数据
     *
     * 使用方法
     * 1.传入带有查询条件的评论详情对象以及修改后的值
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.查询对象内需至少含有一个值
     *
     * @param oldComment
     * @param newComment
     */
    @Override
    public Result update(Comment oldComment, Comment newComment) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(newComment)|| JudgeEmpty.isEmpty(oldComment)){
            result.setOperateError();
            return result;
        }
        Map<String,String> source=changeDetailsToMap(oldComment);
        Map<String,String> data=changeDetailsToMap(newComment);
        if(db.update(tableName,source,data)){
            result.setSuccess();
        }
        else {
            result.setFail();
        }
        return result;
    }

    /**
     * 功能
     * 根据传入条件查询数据库的CommentDetails表
     *
     * 使用方法
     * 1.传入带有查询条件的笔记评论对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 2.查询结果数据类型为List
     *
     * @param comment
     */
    @Override
    public List<Comment> query(Comment comment) {
        if(JudgeEmpty.isEmpty(comment)){
            return null;
        }
        Map<String,String> data=changeDetailsToMap(comment);
        ResultSet set=db.query(tableName,data);
        List<Comment> list=new ArrayList<>();
        try {
            while(set.next()){
                Comment temp =new Comment();
                temp.setNoteId(set.getLong("noteId"));
                temp.setUserId(set.getLong("userId"));
                temp.setDetails(set.getString("details"));
                temp.setDate(set.getString("date"));
                list.add(temp);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    private Map<String,String> changeDetailsToMap(Comment details){
        Map<String,String> data=new HashMap<>();
        if(details.getNoteId()!=0){
            data.put("noteId",Long.toString(details.getNoteId()));
        }
        if(details.getUserId()!=0){
            data.put("userId",Long.toString(details.getUserId()));
        }
        if(JudgeEmpty.isNotEmpty(details.getDetails())){
            data.put("details",details.getDetails());
        }
        if(JudgeEmpty.isNotEmpty(details.getDate())){
            data.put("date",details.getDate());
        }
        return data;
    }

}
