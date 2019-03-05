package com.TinyTipsWEB.DAO.table;

import com.TinyTipsWEB.DAO.sql.IOperateDB;
import com.TinyTipsWEB.DAO.table.imp.IOperateNote;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;
import com.TinyTipsWEB.util.CollectionAndString;
import com.TinyTipsWEB.util.CurrentTime;
import com.TinyTipsWEB.util.JudgeEmpty;
import com.TinyTipsWEB.util.file.FileOperation;
import com.TinyTipsWEB.util.file.ImageConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("operateNote")
public class OperateNote implements IOperateNote {

    @Resource(name = "fileOperation")
    private FileOperation fileOperation;

    @Resource(name = "imageConstant")
    private ImageConstant imageConstant;

    @Resource(name = "currentTime")
    private CurrentTime currentTime;

    @Value("note")
    private String tableName;

    @Resource(name = "collectAndString")
    private CollectionAndString cs;

    @Resource(name = "operateDB")
    private IOperateDB db;

    /**
     * 功能
     * 将传入数据写入到数据库的Note表
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过回调接口得到插入结果
     *
     * @param note
     */
    @Override
    public Result add(Note note) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(note)){
            result.setOperateError();
            return result;
        }
        addPhoto(note);
        note.setDate(currentTime.getDate("time"));
        Map<String,String> data=changeNoteToMap(note);
        if(db.add(tableName,data)){
            result.setSuccess();
        }
        else{
            result.setFail();
        }
        return result;
    }

    /**
     * 删除数据库中的数据
     * @param note
     */
    @Override
    public Result delete(Note note) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(note)){
            result.setOperateError();
            return result;
        }
        if(deletePhoto(note)){
            if(db.delete(tableName,changeNoteToMap(note))){
                result.setSuccess();
                return result;
            }
        }
        result.setFail();
        return result;
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
     */
    @Override
    public Result update(Note oldNote, Note newNote) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(oldNote)|| JudgeEmpty.isEmpty(newNote)){
            result.setOperateError();
            return result;
        }
        Map<String,String> data=changeNoteToMap(newNote);
        Map<String,String> condition=new HashMap<>();
        condition.put("id",Long.toString(oldNote.getId()));
        if(db.update(tableName,data,condition)){
            result.setSuccess();
        }
        else {
            result.setFail();
        }
        return result;
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
     */
    @Override
    public List<Note> query(Note condition) {
        if(JudgeEmpty.isEmpty(condition)){
            return null;
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
                note.setTag(cs.changeStringToList(set.getString("tag"),"$"));
                note.setWordDetails(cs.changeStringToList(set.getString("wordDetails"),"$"));
                if(JudgeEmpty.isNotEmpty(set.getString("photoDetails"))){
                    List<String> photoName=cs.changeStringToList(set.getString("photoDetails"),"$");
                    Map<String,String> photo=new LinkedHashMap<>();
                    for(String s:photoName){
                        photo.put(s,s);
                    }
                    note.setPhoto(photo);
                    queryPhoto(note);
                }
                result.add(note);
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
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
            data.put("tag",cs.changeListToString(note.getTag(),new String("$")));
        }
        if(JudgeEmpty.isNotEmpty(note.getWordDetails())){
            data.put("wordDetails",cs.changeListToString(note.getWordDetails(),new String("$")));
        }
        if(JudgeEmpty.isNotEmpty(note.getPhoto())){
            List<String> photo=new ArrayList<>();
            note.getPhoto().forEach((key,value)->{
                photo.add(key);
            });
            data.put("photoDetails",cs.changeListToString(photo,new String("$")));
        }
        return data;
    }

    private void addPhoto(Note note){
        if(JudgeEmpty.isEmpty(note.getPhoto())){
            return;
        }
        if(note.getPhoto().isEmpty()){
            return;
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
    }

    private boolean deletePhoto(Note note){
        if(JudgeEmpty.isEmpty(note.getPhoto())){
            return false;
        }
        if(note.getPhoto().isEmpty()){
            return true;
        }
        List<String> photo=new ArrayList<>();
        for(String name:photo){
            fileOperation.deleteFile(imageConstant.getNote(),name,".jpg");
        }
        note.setPhoto(null);
        return true;
    }

    private void queryPhoto(Note note){
        if(JudgeEmpty.isEmpty(note)){
            return;
        }
        if(note.getPhoto().isEmpty()){
            return;
        }
        note.getPhoto().forEach((key,value)->{
            String photo=fileOperation.queryFile(imageConstant.getNote(),key,".jpg");
            note.getPhoto().put(key,photo);
        });
    }

}
