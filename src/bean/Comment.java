package bean;

import java.util.List;

public class Comment {

    private long noteId;    //笔记ID，外键

    private List<String> tag;   //公开标签

    private int love;    //点赞数

    private int comment;   //评论数

    private int collect;    //收藏数

    private int forward;     //转发数

    public int getCollect() {
        return collect;
    }

    public int getComment() {
        return comment;
    }

    public int getForward() {
        return forward;
    }

    public int getLike() {
        return love;
    }

    public long getNoteId() {
        return noteId;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public void setLike(int love) {
        this.love=love;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
