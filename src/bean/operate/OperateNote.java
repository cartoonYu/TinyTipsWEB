package bean.operate;

import static java.lang.System.out;
import BaseClass.ValueCallBack;
import bean.Information;
import bean.Note;
import spring.GetContext;
import sql.OperateDB;
import util.JudgeEmpty;
import util.ListAndString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆分形参传进的Note，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateNote {

    private OperateDB db;

    private String tableName;

    private ListAndString ls;

    private OperateInformation operateInformation;

    /**
     * 功能
     * 将传入数据写入到数据库的Note表
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过回调接口得到插入结果
     *
     * @param note
     * @param callBack
     */
    public void add(Note note, ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("300");
            return;
        }
        Information information= GetContext.getContext().getBean("information",Information.class);
        information.setNickName(note.getAuthor());
        note.setUserId(getUserId(information).getId());
        if(JudgeEmpty.isEmpty(note.getUserId())){
            callBack.onFail("400");
            return;
        }
        Map<String,String> data=changeNoteToMap(note);
        if(db.add(tableName,data)){
            callBack.onSuccess("200");
        }
        else{
            callBack.onFail("400");
        }
    }

    /**
     * 根据author获取userId
     * @param data
     * @return
     */
    private Information getUserId(Information data){
        operateInformation.query(data, new ValueCallBack<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                for(Information result:information){
                    data.setId(result.getId());
                }
            }

            @Override
            public void onFail(String code) {
                data.setId(0);
            }
        });
        return data;
    }

    /**
     * 功能
     * 根据传入条件删除数据库的Note表中符合条件的值
     *
     * 使用方法
     * 1.传入带有条件的笔记对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     * 2.查询结果数据类型为List
     *
     * @param note
     * @param callBack
     */
    public void delete(Note note,ValueCallBack<String> callBack){

    }

    /**
     * 功能
     * 根据传入条件查询数据库的Note表
     *
     * 使用方法
     * 1.传入带有查询条件的笔记对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     * 2.查询结果数据类型为List
     *
     * @param note
     * @param callBack
     */
    public void query(Note note, ValueCallBack<List<String>> callBack){

    }

    /**
     * 功能
     * 更新数据库中符合传入条件的数据
     *
     * 使用方法
     * 1.传入带有查询条件的个人信息对象以及修改后的值
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.查询对象内需至少含有一个值
     *
     * @param oldNote
     * @param newNote
     * @param callBack
     */
    public void update(Note oldNote,Note newNote,ValueCallBack<String> callBack){

    }

    private Map<String,String> changeNoteToMap(Note note){
        Map<String,String> data=new HashMap<>();
        data.put("userId",Long.toString(note.getUserId()));
        data.put("title",note.getTitle());
        data.put("author",note.getAuthor());
        data.put("date",note.getDate());
        data.put("tag",ls.changeListToString(note.getTag(),new String("$")));
        data.put("wordDetails",ls.changeListToString(note.getWordDetails(),new String("$")));
        data.put("photoDetails",ls.changeListToString(note.getPhotoDetails(),new String("$")));
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
    public void setTableName(String tableName){
        this.tableName=tableName;
    }

    /**
     * 将个人信息操作对象传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param information
     */
    public void setOperateInformation(OperateInformation information){
        this.operateInformation=information;
    }

    /**
     * 将List与String转换的工具类对象传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param ls
     */
    public void setLs(ListAndString ls){
        this.ls=ls;
    }

}
