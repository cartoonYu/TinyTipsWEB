package com.TinyTipsWEB.Model.table;

import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 评论详情bean类
 *
 * notice
 * 1.属性noteId关联笔记bean类的id
 * 2.属性headPro关联个人信息bean类的headPortrait
 * 3.属性nickName关联个人信息bean类的nickName
 * 3.属性date已通过spring注入，不需另进行赋值操作
 */

@Component("comment")
public class Comment {

    private long noteId;       //笔记编号

    private long userId;        //用户id

    private String date;        //时间

    private String details;     //评论详情

    public Comment(){

    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public long getNoteId() {
        return noteId;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
