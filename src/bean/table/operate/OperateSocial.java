package bean.table.operate;

import BaseClass.ValueCallBack;
import bean.table.Social;
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
 * description
 * 处理点赞、转发、社交等社交操作
 *
 */
public class OperateSocial {

    private String tableName;

    private OperateDB db;

    private CurrentTime currentTime;

    public OperateSocial(){

    }

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
    public void add(Social social, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("300");
            return;
        }
        if(social.getType().equals("Like")){
            tableName="Love";
        }
        else {
            tableName=social.getType();
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
    public void delete(Social social,ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("300");
            return;
        }
        if(social.getType().equals("Like")){
            tableName="Love";
        }
        else {
            tableName=social.getType();
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

    /**
     * 功能
     * 根据传入的类型查询数据库对应表中的相应数据
     *
     * 使用方法
     * 1.传入social对象
     * 2.通过回调接口得到删除结果
     *
     * @param social
     * @param callBack
     */
    public void query(Social social,ValueCallBack<List<Social>> callBack){
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("300");
            return;
        }
        if(social.getType().equals("Like")){
            tableName="Love";
        }
        else {
            tableName=social.getType();
        }

        Map<String,String> data=changeConditionToMap(social);
        if(JudgeEmpty.isEmpty(data)){
            callBack.onFail("300");
            return;
        }
        ResultSet set=db.query(tableName,data);
        List<Social> result=new ArrayList<>();
        try {
            while (set.next()){
                Social temp=new Social();
                temp.setType(social.getType());
                temp.setNoteId(set.getLong("noteId"));
                temp.setUserId(set.getLong("userId"));
                temp.setDate(set.getString("date"));
                result.add(temp);
            }
            callBack.onSuccess(result);
        }catch (SQLException e){
            callBack.onFail("300");
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

    public void setDb(OperateDB db) {
        this.db = db;
    }

    public void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }
}
