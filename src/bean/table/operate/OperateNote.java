package bean.table.operate;

import BaseClass.ValueCallBack;
import bean.table.Note;
import sql.OperateDB;
import util.CurrentTime;
import util.JudgeEmpty;
import util.ListAndString;
import util.file.FileOperation;
import util.file.ImageConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 拆分形参传进的Note，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateNote {

    private OperateDB db;

    private String tableName;

    private ListAndString ls;

    private FileOperation fileOperation;

    private ImageConstant imageConstant;

    private CurrentTime currentTime;

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
        note=addPhoto(note);
        note.setDate(currentTime.getDate("time"));
        Map<String,String> data=changeNoteToMap(note);
        if(db.add(tableName,data)){
            callBack.onSuccess("200");
        }
        else{
            callBack.onFail("400");
        }
    }

    /**
     * 功能
     * 根据传入条件删除数据库的Note表中符合条件的值
     *
     * 使用方法
     * 1.传入带有条件的笔记对象
     * 2.通过回调接口得到删除结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     *
     * @param condition
     * @param callBack
     */
    public void delete(Note condition,ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(condition)){
            callBack.onFail("300");
            return;
        }
        condition=deletePhoto(condition);
        if(db.delete(tableName,changeNoteToMap(condition))){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
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
     * @param condition
     * @param callBack
     */
    public void query(Note condition, ValueCallBack<List<Note>> callBack){
        if(JudgeEmpty.isEmpty(condition)){
            callBack.onFail("300");
            return;
        }
        try{
            ResultSet set=db.query(tableName,changeNoteToMap(condition));
            List<Note> result=new ArrayList<>();
            while(set.next()){
                Note note=new Note();
                note.setId(set.getLong("id"));
                note.setUserId(set.getLong("userId"));
                note.setTitle(set.getString("title"));
                note.setAuthor(set.getString("author"));
                note.setDate(set.getString("date"));
                note.setTag(ls.changeStringToList(set.getString("tag"),"$"));
                note.setWordDetails(ls.changeStringToList(set.getString("wordDetails"),"$"));
                if(JudgeEmpty.isNotEmpty(set.getString("photoDetails"))){
                    List<String> photoName=ls.changeStringToList(set.getString("photoDetails"),"$");
                    Map<String,String> photo=new LinkedHashMap<>();
                    for(String s:photoName){
                        photo.put(s,s);
                    }
                    note.setPhoto(photo);
                    note=queryPhoto(note);
                }
                result.add(note);
            }
            callBack.onSuccess(result);
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
        if(JudgeEmpty.isEmpty(oldNote)||JudgeEmpty.isEmpty(newNote)){
            callBack.onFail("300");
        }
        if(db.update(tableName,changeNoteToMap(newNote),changeNoteToMap(oldNote))){
            callBack.onSuccess("200");
        }
        else {
            callBack.onFail("400");
        }
    }

    private Note addPhoto(Note note){
        if(JudgeEmpty.isEmpty(note.getPhoto())){
            return note;
        }
        if(note.getPhoto().isEmpty()){
            return note;
        }
        List<String> photo=new ArrayList<>();   //图片列表
        note.getPhoto().forEach((key,value)->{
            //由于图片名不确定，所以先把图片获取出来
            photo.add(value);
        });
        Map<String,String> map=new LinkedHashMap<>();
        for(String temp:photo){
            map.put(fileOperation.addFile(temp,imageConstant.getNote(),".jpg"),temp);
        }
        note.setPhoto(map);
        return note;
    }

    private Note deletePhoto(Note note){
        if(JudgeEmpty.isEmpty(note.getPhoto())){
            return note;
        }
        if(note.getPhoto().isEmpty()){
            return note;
        }
        List<String> photo=new ArrayList<>();
        for(String name:photo){
            fileOperation.deleteFile(imageConstant.getNote(),name,".jpg");
        }
        note.setPhoto(null);
        return note;
    }

    private Note queryPhoto(Note note){
        if(JudgeEmpty.isEmpty(note)){
            return note;
        }
        if(note.getPhoto().isEmpty()){
            return note;
        }
        note.getPhoto().forEach((key,value)->{
            String photo=fileOperation.queryFile(imageConstant.getNote(),key,".jpg");
            note.getPhoto().put(key,photo);
        });
        return note;
    }

    private Map<String,String> changeNoteToMap(Note note){
        Map<String,String> data=new HashMap<>();
        if(note.getId()!=0){
            data.put("id",Long.toString(note.getId()));
        }
        if(note.getUserId()!=0){
            data.put("userId",Long.toString(note.getUserId()));
        }
        if(JudgeEmpty.isNotEmpty(note.getTitle())){
            data.put("title",note.getTitle());
        }
        if(JudgeEmpty.isNotEmpty(note.getAuthor())){
            data.put("author",note.getAuthor());
        }
        if(JudgeEmpty.isNotEmpty(note.getDate())){
            data.put("date",note.getDate());
        }
        if(JudgeEmpty.isNotEmpty(note.getTag())){
            data.put("tag",ls.changeListToString(note.getTag(),new String("$")));
        }
        if(JudgeEmpty.isNotEmpty(note.getWordDetails())){
            data.put("wordDetails",ls.changeListToString(note.getWordDetails(),new String("$")));
        }
        if(JudgeEmpty.isNotEmpty(note.getPhoto())){
            List<String> photo=new ArrayList<>();
            note.getPhoto().forEach((key,value)->{
                photo.add(key);
            });
            data.put("photoDetails",ls.changeListToString(photo,new String("$")));
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
    public void setTableName(String tableName){
        this.tableName=tableName;
    }

    /**
     * 将个人信息操作对象传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param fileOperation
     */
    public void setFileOperation(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    /**
     * 将List与String转换的工具类对象传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param ls
     */
    public void setLs(ListAndString ls){
        this.ls=ls;
    }

    public void setImageConstant(ImageConstant imageConstant) {
        this.imageConstant = imageConstant;
    }

    public void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

}
