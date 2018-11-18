package bean.operate;

import BaseClass.ValueCallBack;
import bean.Comment;
import sql.OperateDB;
import util.JudgeEmpty;
import util.ListAndString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * 拆分形参传进的Comment，传递给数据库操作层
 * @author cartoon
 * @version 1.1
 */

public class OperateComment {

    private String tableName;

    private OperateDB db;

    private ListAndString listAndString;

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
        if(JudgeEmpty.isEmpty(comment)){
            callBack.onFail("300");
            return;
        }
        Map<String,String> data=changeCommentToMap(comment);
        if(db.add(tableName,data)){

            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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
        if(JudgeEmpty.isEmpty(comment)){
            callBack.onFail("300");
            return;
        }
        Map<String,String> data=changeCommentToMap(comment);
        if(db.delete(tableName,data)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("300");
        }
    }

    /**
     * 功能
     * 根据传入条件查询数据库的Comment表
     *
     * 使用方法
     * 1.传入带有笔记ID的评论对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.查询结果数据类型为Comment
     *
     * @param comment
     * @param callBack
     */
    public void query(Comment comment, ValueCallBack<Comment> callBack){
        if(JudgeEmpty.isEmpty(comment)){
            callBack.onFail("300");
            return;
        }
        Map<String,String> data=changeCommentToMap(comment);
        try {
            List<Comment> list=new ArrayList<>();
            ResultSet set=db.query(tableName,data);
            while (set.next()){
                Comment temp=new Comment();
                temp.setNoteId(set.getLong("noteId"));
                temp.setTag(listAndString.changeStringToList(set.getString("tag"),"$"));
                temp.setLike(set.getInt("love"));
                temp.setComment(set.getInt("comment"));
                temp.setCollect(set.getInt("collect"));
                temp.setForward(set.getInt("forward"));
                list.add(temp);
            }
            if(list.size()>1){
                callBack.onSuccess(list.get(0));
            }
            else {
                callBack.onFail("400");
            }
        }catch (SQLException e){
            callBack.onFail("400");
        }

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
        if(JudgeEmpty.isEmpty(oldComment)||JudgeEmpty.isEmpty(newComment)){
            callBack.onFail("300");
            return;
        }
        if(oldComment.getNoteId()!=newComment.getNoteId()){
            callBack.onFail("400");
            return;
        }
        Map<String,String> source=changeCommentToMap(oldComment);
        Map<String,String> result=changeCommentToMap(newComment);
        if(db.update(tableName,result,source)){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
    }

    private Map<String,String> changeCommentToMap(Comment comment){
        Map<String,String> data=new HashMap<>();
        data.put("noteId",Long.toString(comment.getNoteId()));
        if(JudgeEmpty.isNotEmpty(comment.getTag())){
            data.put("tag",listAndString.changeListToString(comment.getTag(),"$"));
        }
        data.put("love",Integer.toString(comment.getLike()));
        data.put("comment",Integer.toString(comment.getComment()));
        data.put("collect",Integer.toString(comment.getCollect()));
        data.put("forward",Integer.toString(comment.getForward()));
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

    public void setListAndString(ListAndString listAndString) {
        this.listAndString = listAndString;
    }
}
