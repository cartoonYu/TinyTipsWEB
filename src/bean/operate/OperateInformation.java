package bean.operate;

import BaseClass.ValueCallBack;
import bean.Information;
import sql.OperateDB;
import util.JudgeEmpty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆分形参传进的Information，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateInformation {

    private String tableName;

    private OperateDB db;

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

    /**
     * 功能
     * 将传入数据写入到数据库的information表
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过回调接口得到插入结果
     *
     * @param information
     * @param callBack
     */
    public void add(Information information, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(information)){
            callBack.onFail(new String("300"));
            return;
        }
        Map<String,String> data=changeInformationToMap(information);
        if(db.add(tableName,data)){
            callBack.onSuccess("200");
        }
        else{
            callBack.onFail("300");
        }
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
     * @param callBack
     */
    public void query(Information information,ValueCallBack<List<Information>> callBack){
        if(JudgeEmpty.isEmpty(information)){
            callBack.onFail(new String("300"));
            return;
        }
        Map<String,String> data=changeInformationToMap(information);
        try{
            ResultSet set=db.query(tableName,data);
            List<Information> list=new ArrayList<>();
            while(set.next()){
                Information result=new Information();
                result.setAccount(set.getString("account"));
                result.setPassword(set.getString("password"));
                result.setHeadPortrait(set.getString("headPortrait"));
                result.setNickName(set.getString("nickName"));
                result.setSex(changeSexToBoolean(set.getString("sex")));
                result.setInterest(changeInterestToList(set.getString("interest")));
                result.setSchool(set.getString("school"));
                result.setMajor(set.getString("major"));
                result.setBackground(set.getString("background"));
                result.setResume(set.getString("resume"));
                list.add(result);
            }
            callBack.onSuccess(list);
        }catch (SQLException e){
            callBack.onFail(new String("400"));
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
        data.put("id",Integer.toString(1));
        data.put("account",information.getAccount());
        data.put("password",information.getPassword());
        data.put("headPortrait",information.getHeadPortrait());
        data.put("nickName",information.getNickName());
        data.put("sex",changeSexToString(information.isSex()));
        data.put("interest",changeInterestToString(information.getInterest()));
        data.put("school",information.getSchool());
        data.put("major",information.getMajor());
        data.put("background",information.getBackground());
        data.put("resume",information.getResume());
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

    /**
     * 将个人信息中的兴趣的数据类型从List转换成String
     * 该String用于写入数据库
     * @param list
     * @return
     */
    private String changeInterestToString(List<String> list){
        if(JudgeEmpty.isEmpty(list)){
            return null;
        }
        StringBuilder interest=new StringBuilder();
        for(String i:list){
            interest.append(i).append(new String("$"));
        }
        return interest.toString();
    }

    /**
     * 将个人信息中的兴趣的数据类型从String转换成List
     * 该List用于上层再利用
     * @param interest
     * @return
     */
    private List<String> changeInterestToList(String interest){
        List<String> list=new ArrayList<>();
        if(JudgeEmpty.isEmpty(interest)){
            return list;
        }
        int index=interest.indexOf("$");
        int length;
        while(index!=-1){
            list.add(interest.substring(0,index));
            length=interest.length();
            interest=interest.substring(index+1,length);
            index=interest.indexOf("$");
        }
        return list;
    }
}
