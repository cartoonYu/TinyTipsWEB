package com.TinyTipsWEB.Model.table;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 个人信息bean类
 *
 * notice
 * 1.属性id为数据库自增长，不需另赋值
 * 2.属性sex，true为男，false为女
 * 3.属性interest转换成String以符号$进行分隔
 */

@Component("information")
public class Information {

    private long id;      //主键，个人信息的唯一标识，自增长

    private String account;   //主键，账号

    private String password;  //密码

    private String date;     //注册时间

    private String headPortrait;  //头像

    private String headPortraitName; //头像文件名

    private String nickName;  //昵称

    private boolean sex;     //性别

    private List<String> interest;   //兴趣

    private String school;    //高校

    private String major;     //专业

    private String background;   //学历

    private String resume;    //个人简介

    public Information(){
        setSex(true);
    }

    public long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public boolean isSex() {
        return sex;
    }

    public List<String> getInterest() {
        return interest;
    }

    public String getAccount() {
        return account;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getHeadPortraitName() {
        return headPortraitName;
    }

    public String getPassword() {
        return password;
    }

    public String getMajor() {
        return major;
    }

    public String getBackground() {
        return background;
    }

    public String getSchool() {
        return school;
    }

    public String getResume() {
        return resume;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setHeadPortraitName(String headPortraitName) {
        this.headPortraitName = headPortraitName;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
