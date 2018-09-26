package bean.operate;

import BaseClass.ValueCallBack;
import bean.Comment;
import sql.OperateDB;

import java.util.*;

/**
 * 拆分形参传进的Comment，传递给数据库操作层
 * @author cartoon
 * @version 1.1
 */

public class OperateComment {

    private String tableName;

    private OperateDB db;

    public OperateComment(){

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

    /**
     * 功能：将Comment分拆并插入数据库
     * 使用方法
     * 1.传入Comment对象
     * 2.使用回调接口获取插入结果
     * 注意
     * 1.传入Comment对象数据应严格按照bean包中Comment的要求
     * @param comment
     * @param callBack
     */
    public void add(Comment comment, ValueCallBack<String> callBack) {
        Map<String,String> data=new HashMap<>();
        data.put("like",Integer.toString(comment.getLike()));
        data.put("comment",Integer.toString(comment.getComment()));
        data.put("collect",Integer.toString(comment.getCollect()));
        data.put("forward",Integer.toString(comment.getForward()));
        StringBuilder tag=new StringBuilder();
        Iterator tIt=comment.getTag().iterator();
        while(tIt.hasNext()){
            tag.append(tIt.next()).append("&");
        }
        data.put("tag",tag.substring(0,tag.length()-1));
        if(db.add(tableName,data)){
            callBack.onSuccess("200");
        }
        else{
            callBack.onFail("300");
        }
    }

    public void delete(Comment comment, ValueCallBack<String> callBack) {

    }

    /**
     * 根据传入的comment对象进行查询
     * @param comment
     * @return
     */
    public void query(Comment comment, ValueCallBack<String> callBack){

    }


    public void update(Comment comment, ValueCallBack<String> callBack){

    }
}
