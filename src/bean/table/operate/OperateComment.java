package bean.table.operate;

import BaseClass.ValueCallBack;
import bean.table.Comment;
import sql.OperateDB;
import util.CurrentTime;
import util.JudgeEmpty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆分形参传进的InformationDetails，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateComment {

    private String tableName;

    private OperateDB db;

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
     * @param callBack
     */
    public void add(Comment details, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(details)||JudgeEmpty.isEmpty(details.getDetails())){
            callBack.onFail("300");
            return;
        }
        Map<String,String> data=changeDetailsToMap(details);
        data.put("date",currentTime.getDate("time"));
        if(db.add(tableName,data)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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
     * @param details
     * @param callBack
     */
    public void delete(Comment details, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(details)||JudgeEmpty.isEmpty(details.getDetails())){
            callBack.onFail("300");
            return;
        }
        Map<String,String> data=changeDetailsToMap(details);
        if(db.delete(tableName,data)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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
     * @param details
     * @param callBack
     */
    public void query(Comment details, ValueCallBack<List<Comment>> callBack){
        if(JudgeEmpty.isEmpty(details)){
            callBack.onFail("300");
            return;
        }
        Map<String,String> data=changeDetailsToMap(details);
        ResultSet set=db.query(tableName,data);
        try {
            List<Comment> list=new ArrayList<>();
            while(set.next()){
                Comment comment =new Comment();
                comment.setNoteId(set.getLong("noteId"));
                comment.setUserId(set.getLong("userId"));
                comment.setDetails(set.getString("details"));
                comment.setDate(set.getString("date"));
                list.add(comment);
            }
            callBack.onSuccess(list);
        }catch (SQLException e){
            e.printStackTrace();
            callBack.onFail("400");
        }

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
     * @param oldDetails
     * @param newDetails
     * @param callBack
     */
    public void update(Comment oldDetails, Comment newDetails, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(newDetails)||JudgeEmpty.isEmpty(oldDetails)){
            callBack.onFail("300");
            return;
        }
        Map<String,String> source=changeDetailsToMap(oldDetails);
        Map<String,String> result=changeDetailsToMap(newDetails);
        if(db.update(tableName,source,result)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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

    /**
     * 将数据库操作类对象传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param db
     */
    public void setOperateDB(OperateDB db){
        this.db=db;
    }

    /**
     * 将表名传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }
}