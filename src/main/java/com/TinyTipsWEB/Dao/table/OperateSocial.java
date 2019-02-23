package com.TinyTipsWEB.DAO.table;

import com.TinyTipsWEB.DAO.sql.IOperateDB;
import com.TinyTipsWEB.DAO.table.imp.IOperateSocial;
import com.TinyTipsWEB.Model.table.Social;
import com.TinyTipsWEB.ValueCallBack;
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
     * @param callBack
     */
    @Override
    public void add(Social social, ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("300");
            return;
        }
        if(social.getType().equals("Like")){
            setTableName("Love");
        }
        else {
            setTableName(social.getType());
        }
        Map<String,String> data=changeConditionToMap(social);
        data.put("date",currentTime.getDate("time"));
        if(JudgeEmpty.isEmpty(data)){
            callBack.onFail("300");
            return;
        }
        if(db.add(tableName,data)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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
     * @param callBack
     */
    @Override
    public void delete(Social social, ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("300");
            return;
        }
        if(social.getType().equals("Like")){
            setTableName("Love");
        }
        else {
            setTableName(social.getType());
        }
        Map<String,String> data=changeConditionToMap(social);

        if(JudgeEmpty.isEmpty(data)){
            callBack.onFail("300");
            return;
        }
        if(db.delete(tableName,data)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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
