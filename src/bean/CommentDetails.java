package bean;

import util.CurrentTime;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 评论详情bean类
 *
 * notice
 * 1.属性noteId关联笔记bean类的id
 * 2.属性headPro关联个人信息bena类的headPortrait
 * 3.属性nickName关联个人信息bena类的nickName
 * 3.属性date已通过spring注入，不需另进行赋值操作
 */

public class CommentDetails {

    private long noteId;       //笔记编号

    private String date;        //时间，主键

    private String headPro;     //头像

    private String nickName;    //昵称，主键

    private String details;     //评论详情

    public CommentDetails(){
    }

    public String getNickName() {
        return nickName;
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

    public String getHeadPro() {
        return headPro;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setDate(CurrentTime date) {
        this.date = date.getDate();
}

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setHeadPro(String headPro) {
        this.headPro = headPro;
    }
}
