package com.TinyTipsWEB.DAO.table;

import com.TinyTipsWEB.DAO.sql.IOperateDB;
import com.TinyTipsWEB.DAO.table.imp.IOperateInformation;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Information;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("operateInformation")
public class OperateInformation implements IOperateInformation {

    @Resource(name = "fileOperation")
    private FileOperation fileOperation;

    @Resource(name = "imageConstant")
    private ImageConstant imageConstant;

    @Resource(name = "currentTime")
    private CurrentTime currentTime;

    @Value("Information")
    private String tableName;

    @Resource(name = "operateDB")
    private IOperateDB db;

    @Resource(name = "collectAndString")
    private CollectionAndString cs;

    /**
     * 功能
     * 将传入数据写入到数据库的Information表
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过回调接口得到插入结果
     *
     * @param information
     */
    @Override
    public Result add(Information information) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(information)){
            result.setOperateError();
        }
        if(JudgeEmpty.isNotEmpty(information.getHeadPortrait())){
            String headPortrait=information.getHeadPortrait();
            String headPortraitName=fileOperation.addFile(headPortrait,imageConstant.getInformation(),".jpg");
            information.setHeadPortraitName(headPortraitName);
        }
        information.setDate(currentTime.getDate("day"));
        Map<String,String> data=changeInformationToMap(information);
        if(db.add(tableName,data)){
            result.setSuccess();
        }
        else{
            result.setFail();
        }
        return result;
    }

    /**
     * 功能
     * 根据传入条件删除数据库的information表中符合条件的值
     *
     * 使用方法
     * 1.传入带有条件的个人信息对象
     * 2.通过回调接口得到删除结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     *
     * @param information
     */
    @Override
    public Result delete(Information information) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(information)){
            result.setOperateError();
        }
        if(JudgeEmpty.isNotEmpty(information.getHeadPortraitName())){
            fileOperation.deleteFile(imageConstant.getInformation(),information.getHeadPortraitName(),".jpg");
        }
        Map<String,String> condition=changeInformationToMap(information);
        if(db.delete(tableName,condition)){
            result.setSuccess();
        }
        else{
            result.setFail();
        }
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
     * @param oldInformation
     * @param newInformation
     */
    @Override
    public Result update(Information oldInformation, Information newInformation) {
        Result result=new Result();
        if(JudgeEmpty.isEmpty(oldInformation)|| JudgeEmpty.isEmpty(newInformation)){
            result.setOperateError();
        }
        if(JudgeEmpty.isNotEmpty(oldInformation.getHeadPortraitName())){
            String headPortraitName=oldInformation.getHeadPortraitName();
            fileOperation.deleteFile(imageConstant.getInformation(),headPortraitName,".jpg");
        }
        if(JudgeEmpty.isNotEmpty(newInformation.getHeadPortrait())){
            String headPortrait=newInformation.getHeadPortrait();
            String headPortraitName=fileOperation.addFile(headPortrait,imageConstant.getInformation(),".jpg");
            newInformation.setHeadPortraitName(headPortraitName);
        }
        Map<String,String> condition=new HashMap<>();
        condition.put("id",Long.toString(oldInformation.getId()));
        Map<String,String> data=changeInformationToMap(newInformation);
        if(db.update(tableName,data,condition)){
            result.setSuccess();
        }
        else{
            result.setFail();
        }
        return result;
    }

    /**
     * 功能
     * 根据传入条件查询数据库的information表
     *
     * 使用方法
     * 1.传入带有查询条件的个人信息对象
     * 2.通过回调接口得到查询结果
     *
     * 注意
     * 1.对象内需至少含有一个值
     * 2.查询结果数据类型为List
     *
     * @param information
     */
    @Override
    public List<Information> query(Information information) {
        if(JudgeEmpty.isEmpty(information)){
            return null;
        }
        Map<String,String> data=changeInformationToMap(information);
        List<Information> list=new ArrayList<>();
        try{
            ResultSet set=db.query(tableName,data);
            while(set.next()){
                Information result=new Information();
                result.setId(set.getLong("id"));
                result.setAccount(set.getString("account"));
                result.setPassword(set.getString("password"));
                result.setDate(set.getString("date"));
                result.setHeadPortraitName(set.getString("headPortrait"));
                result.setNickName(set.getString("nickName"));
                result.setSex(changeSexToBoolean(set.getString("sex")));
                result.setInterest(cs.changeStringToList(set.getString("interest"),new String("$")));
                result.setSchool(set.getString("school"));
                result.setMajor(set.getString("major"));
                result.setBackground(set.getString("background"));
                result.setResume(set.getString("resume"));
                if(JudgeEmpty.isNotEmpty(result.getHeadPortraitName())){
                    String headPortraitName=result.getHeadPortraitName();
                    String file=fileOperation.queryFile(imageConstant.getInformation(),headPortraitName,".jpg");
                    result.setHeadPortrait(file);
                }
                list.add(result);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    /**
     * 将个人信息的对象转换成Map
     * 该Map用于写入到数据库
     * @param information
     * @return
     */
    private Map<String,String> changeInformationToMap(Information information){
        Map<String,String> data=new HashMap<>();
        if(information.getId()!=0){
            data.put("id",Long.toString(information.getId()));
        }
        if(JudgeEmpty.isNotEmpty(information.getAccount())){
            data.put("account",information.getAccount());
        }
        if(JudgeEmpty.isNotEmpty(information.getPassword())){
            data.put("password",information.getPassword());
        }
        if(JudgeEmpty.isNotEmpty(information.getDate())){
            data.put("date",information.getDate());
        }
        if(JudgeEmpty.isNotEmpty(information.getHeadPortraitName())){
            data.put("headPortrait",information.getHeadPortraitName());
        }
        if(JudgeEmpty.isNotEmpty(information.getNickName())){
            data.put("nickName",information.getNickName());
        }
        if(JudgeEmpty.isNotEmpty(information.isSex())){
            data.put("sex",changeSexToString(information.isSex()));
        }
        if(JudgeEmpty.isNotEmpty(information.getInterest())){
            data.put("interest",cs.changeListToString(information.getInterest(),new String("$")));
        }
        if(JudgeEmpty.isNotEmpty(information.getSchool())){
            data.put("school",information.getSchool());
        }
        if(JudgeEmpty.isNotEmpty(information.getMajor())){
            data.put("major",information.getMajor());
        }
        if(JudgeEmpty.isNotEmpty(information.getBackground())){
            data.put("background",information.getBackground());
        }
        if(JudgeEmpty.isNotEmpty(information.getResume())){
            data.put("resume",information.getResume());
        }
        return data;
    }

    /**
     * 将个人信息中的性别的数据类型从boolean转换成String
     * 该String用于写入到数据库
     * @param sex
     * @return
     */
    private String changeSexToString(boolean sex){
        if(sex){
            return new String("男");
        }
        else {
            return new String("女");
        }
    }

    /**
     * 将个人信息中的性别的数据类型从String转换成boolean
     * 该boolean用于上层再利用
     * @param sex
     * @return
     */
    private boolean changeSexToBoolean(String sex){
        if(JudgeEmpty.isEmpty(sex)){
            return true;
        }
        if(sex.equals(new String("男"))){
            return true;
        }
        else{
            return false;
        }
    }

}
