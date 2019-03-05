package com.TinyTipsWEB.DAO.table;

import com.TinyTipsWEB.DAO.sql.IOperateDB;
import com.TinyTipsWEB.DAO.table.imp.IOperateSocial;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Social;
import com.TinyTipsWEB.util.CurrentTime;
import com.TinyTipsWEB.util.JudgeEmpty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository("operateSocial")
public class OperateSocial implements IOperateSocial {


    @Resource(name = "operateDB")
    private IOperateDB db;

    @Resource(name = "currentTime")
    private CurrentTime currentTime;

    private String tableName;

    /**
     * 功能
     * 根据传入的类型将传入数据写入到数据库对应表中
     *
     * 使用方法
     * 1.传入类型、笔记ID以及用户ID
     * 2.通过回调接口得到插入结果
     *
     * @param social
     */
    @Override
    public Result add(Social social) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(social)){
            result.setOperateError();
            return result;
        }
        if(social.getType().equals("Like")){
            setTableName("love");
        }
        else {
            setTableName(social.getType().toLowerCase());
        }
        Map<String,String> data=changeConditionToMap(social);
        data.put("date",currentTime.getDate("time"));
        if(JudgeEmpty.isEmpty(data)){
            result.setOperateError();
            return result;
        }
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
     * 根据传入的类型删除数据库对应表中的相应数据
     *
     * 使用方法
     * 1.传入类型、笔记ID以及用户ID
     * 2.通过回调接口得到删除结果
     *
     * @param social
     */
    @Override
    public Result delete(Social social) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(social)){
            result.setOperateError();
            return result;
        }
        if(social.getType().equals("Like")){
            setTableName("love");
        }
        else {
            setTableName(social.getType().toLowerCase());
        }
        Map<String,String> data=changeConditionToMap(social);

        if(JudgeEmpty.isEmpty(data)){
            result.setOperateError();
            return result;
        }
        if(db.delete(tableName,data)){
            result.setSuccess();
        }
        else {
            result.setFail();
        }
        return result;
    }

    private Map<String,String> changeConditionToMap(Social social){
        if(social.getNoteId()==0&&social.getUserId()==0){
            return null;
        }
        Map<String,String> data=new HashMap<>();
        if(social.getNoteId()!=0){
            data.put("noteId",Long.toString(social.getNoteId()));
        }
        if(social.getUserId()!=0){
            data.put("userId",Long.toString(social.getUserId()));
        }
        return data;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
