package com.TinyTipsWEB.Model.view;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author cartoon
 * @version  1.0
 *
 * 对应的视图为social
 */

@Component("statSocial")
public class StatSocial {

    private long userId;        //用户id

    private String headPortrait;  //头像

    private String headPortraitName; //头像文件名

    private String nickName;  //昵称

    private long noteId;       //笔记编号

    private String title;     //标题

    private List<String> wordDetails;    //文字性内容

    private Map<String,String> photo;   //图片性内容

    private String date;       //时间

    private int numOfLove;    //点赞总数

    private List<Integer> loveList;   //点赞列表

    private int numOfComment;    //评论总数

    private List<Integer> commentList;   //评论列表

    private int numOfCollect;    //收藏总数

    private List<Integer> collectList;   //收藏列表

    private int numOfForward;    //转发总数

    private List<Integer> forwardList;   //转发列表

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setHeadPortraitName(String headPortraitName) {
        this.headPortraitName = headPortraitName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getHeadPortraitName() {
        return headPortraitName;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }

    public long getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfCollect() {
        return numOfCollect;
    }

    public List<String> getWordDetails() {
        return wordDetails;
    }

    public Map<String, String> getPhoto() {
        return photo;
    }

    public void setPhoto(Map<String, String> photo) {
        this.photo = photo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWordDetails(List<String> wordDetails) {
        this.wordDetails = wordDetails;
    }

    public int getNumOfComment() {
        return numOfComment;
    }

    public int getNumOfForward() {
        return numOfForward;
    }

    public void setNumOfLove(int numOfLove) {
        this.numOfLove = numOfLove;
    }

    public int getNumOfLove() {
        return numOfLove;
    }

    public List<Integer> getCollectList() {
        return collectList;
    }

    public List<Integer> getCommentList() {
        return commentList;
    }

    public List<Integer> getForwardList() {
        return forwardList;
    }

    public List<Integer> getLoveList() {
        return loveList;
    }

    public void setCollectList(List<Integer> collectList) {
        this.collectList = collectList;
    }

    public void setCommentList(List<Integer> commentList) {
        this.commentList = commentList;
    }

    public void setForwardList(List<Integer> forwardList) {
        this.forwardList = forwardList;
    }

    public void setLoveList(List<Integer> loveList) {
        this.loveList = loveList;
    }

    public void setNumOfCollect(int numOfCollect) {
        this.numOfCollect = numOfCollect;
    }

    public void setNumOfComment(int numOfComment) {
        this.numOfComment = numOfComment;
    }

    public void setNumOfForward(int numOfForward) {
        this.numOfForward = numOfForward;
    }

}
