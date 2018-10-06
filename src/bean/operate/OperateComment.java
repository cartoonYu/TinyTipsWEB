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
     * 功能
     * 将传入数据写入到数据库的Comment表
     *
     * 使用方法
     * 1.传入评论对象
     * 2.通过回调接口得到插入结果
     *
     * @param comment
     * @param callBack
     */
    public void add(Comment comment, ValueCallBack<String> callBack) {

    }

    /**
     * 功能
     * 根据传入条件删除数据库的Comment表中符合条件的值
     *
     * 使用方法
     * 1.传入带有条件的评论对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     *
     * @param comment
     * @param callBack
     */
    public void delete(Comment comment, ValueCallBack<String> callBack) {

    }

    /**
     * 功能
     * 根据传入条件查询数据库的Comment表
     *
     * 使用方法
     * 1.传入带有查询条件的评论对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     * 2.查询结果数据类型为List
     *
     * @param comment
     * @param callBack
     */
    public void query(Comment comment, ValueCallBack<String> callBack){

    }

    /**
     * 功能
     * 更新数据库中符合传入条件的数据
     *
     * 使用方法
     * 1.传入带有查询条件的评论对象以及修改后的值
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.查询对象内需至少含有一个值
     *
     * @param oldComment
     * @param newComment
     * @param callBack
     */
    public void update(Comment oldComment,Comment newComment, ValueCallBack<String> callBack){

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

}
