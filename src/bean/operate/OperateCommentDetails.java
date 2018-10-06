package bean.operate;

import BaseClass.ValueCallBack;
import bean.CommentDetails;
import sql.OperateDB;
import util.ListAndString;

import java.util.List;

/**
 * 拆分形参传进的InformationDetails，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateCommentDetails {

    private String tableName;

    private OperateDB db;

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
    public void add(CommentDetails details, ValueCallBack<String> callBack){

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
    public void delete(CommentDetails details,ValueCallBack<String> callBack){

    }

    /**
     * 功能
     * 根据传入条件查询数据库的CommentDetails表
     *
     * 使用方法
     * 1.传入带有查询条件的个人信息对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     * 2.查询结果数据类型为List
     *
     * @param details
     * @param callBack
     */
    public void query(CommentDetails details, ValueCallBack<List<CommentDetails>> callBack){

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
    public void update(CommentDetails oldDetails,CommentDetails newDetails,ValueCallBack<String> callBack){

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
