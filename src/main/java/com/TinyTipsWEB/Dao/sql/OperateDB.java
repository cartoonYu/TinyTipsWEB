package com.TinyTipsWEB.DAO.sql;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

@Component("operateDB")
public class OperateDB implements IOperateDB {

    @Resource(name = "connection")
    private Connection connection;

    private Logger log;

    /**
     * 插入数据
     * @param tableName
     * @param data
     * @return
     */
    @Override
    public boolean add(String tableName, Map<String, String> data) {
        List<String> columns=getColumns(data);
        String sql=getAddSQL(tableName,columns);
        PreparedStatement pst=getPreparedStatement(sql,columns,data);
        return updateData(pst);
    }

    /**
     * 将Map整合成插入型的sql语句
     * @param tableName
     * @param columns
     * @return
     */
    private String getAddSQL(String tableName,List<String> columns){
        StringBuilder sql=new StringBuilder("insert into ").append(tableName);
        StringBuilder columnsBuilder=new StringBuilder("(");
        StringBuilder phBuilder=new StringBuilder("(");
        for(int i=0,size=columns.size();i<size;i++){
            columnsBuilder.append(columns.get(i)).append(",");
            phBuilder.append("?,");
        }
        sql.append(columnsBuilder.substring(0,columnsBuilder.length()-1)).append(") ");
        sql.append("values").append(phBuilder.substring(0,phBuilder.length()-1)).append(");");
        return sql.toString();
    }

    /**
     * 删除数据
     * @param tableName
     * @param condition
     * @return
     */
    @Override
    public boolean delete(String tableName, Map<String, String> condition) {
        List<String> columns=getColumns(condition);
        String sql=getDeleteSQL(tableName,columns);
        PreparedStatement pst=getPreparedStatement(sql,columns,condition);
        return updateData(pst);
    }

    /**
     * 拼接删除型的sql语句
     * @param tableName
     * @param columns
     * @return
     */
    private String getDeleteSQL(String tableName,List<String> columns){
        StringBuilder sql=new StringBuilder("delete from ").append(tableName).append(" where ");
        if(columns.isEmpty()){
            return sql.append(";").toString();
        }
        for(String temp:columns){
            sql.append(temp).append("= ? and ");
        }
        return sql.substring(0,sql.length()-4).concat(";");
    }

    /**
     * 更新数据
     * @param tableName
     * @param condition
     * @return
     */
    @Override
    public ResultSet query(String tableName, Map<String, String> condition) {
        List<String> columns=getColumns(condition);
        String sql=getQuerySQL(tableName,columns);
        PreparedStatement pst=getPreparedStatement(sql,columns,condition);
        log.info(pst);
        ResultSet result=null;
        try {
            result=pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("query result is"+result!=null);
        return result;
    }

    /**
     * 拼接查询型sql语句
     * @param tableName
     * @param columns
     * @return
     */
    private String getQuerySQL(String tableName,List<String> columns){
        StringBuilder sql=new StringBuilder("select * from ").append(tableName);
        if(columns.isEmpty()){
            return sql.append(";").toString();
        }
        sql.append(" where ");
        for(String temp:columns){
            sql.append(temp).append("= ? and ");
        }
        return sql.substring(0,sql.length()-4).concat(";");
    }

    /**
     * 更新数据
     * @param tableName
     * @param data 修改后的数据
     * @param condition
     * @return
     */
    @Override
    public boolean update(String tableName, Map<String, String> data, Map<String, String> condition) {
        List<String> oldColumns=getColumns(condition);
        List<String> newColumns=getColumns(data);
        String sql=getUpdateSQL(tableName,oldColumns,newColumns);
        PreparedStatement pst=getPreparedStatement(sql,oldColumns,condition,newColumns,data);
        return updateData(pst);
    }

    /**
     * 拼接更新型sql语句
     * @param tableName
     * @param oldColumns
     * @param newColumns
     * @return
     */
    private String getUpdateSQL(String tableName,List<String> oldColumns,List<String> newColumns){
        StringBuilder sql=new StringBuilder("update ").append(tableName).append(" set ");
        StringBuilder oldBuilder=new StringBuilder();
        for(String temp:oldColumns){
            oldBuilder.append(temp).append("= ? and ");
        }
        StringBuilder newBuilder=new StringBuilder();
        for(String temp:newColumns){
            newBuilder.append(temp).append("= ?,");
        }
        sql.append(newBuilder.substring(0,newBuilder.length()-1));
        sql.append(" where ").append(oldBuilder.substring(0,oldBuilder.length()-4)).append(";");
        return sql.toString();
    }

    /**
     * 将Map的key顺序化
     * @param data
     */
    private List<String> getColumns(Map<String,String> data){
        List<String> result=new ArrayList<>();
        Set<String> temp=data.keySet();
        temp.forEach(value->{
            result.add(value);
        });
        return result;
    }

    /**
     * 获取预编译的statement
     * @param sql 包含占位符的sql语句
     * @param columns  列名
     * @param data     具体数据
     * @return
     */
    private PreparedStatement getPreparedStatement(String sql, List<String> columns,Map<String,String> data){
        PreparedStatement pst=null;
        try {
            pst=connection.prepareStatement(sql);
            for(int i=0,size=columns.size();i<size;i++){
                pst.setString(i+1,data.get(columns.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return pst;
        }
    }

    /**
     * 获取预编译的statement
     * @param sql 包含占位符的sql语句
     * @param oldColumns 条件列名
     * @param condition  条件数据
     * @param newColumns 数据列名
     * @param data       数据
     * @return
     */
    private PreparedStatement getPreparedStatement(String sql,List<String> oldColumns,Map<String,String> condition, List<String> newColumns,Map<String,String> data){
        out.println("sql:"+sql);
        PreparedStatement pst=null;
        int newColumnsSize=newColumns.size();
        try {
            pst=connection.prepareStatement(sql);
            for(int i=0;i<newColumnsSize;i++){
                pst.setString(i+1,data.get(newColumns.get(i)));
            }
            for(int i=0,size=oldColumns.size();i<size;i++){
                pst.setString(i+newColumnsSize+1,condition.get(oldColumns.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return pst;
        }
    }

    /**
     * 执行更新数据库的操作，包括
     * 1.增加数据
     * 2.删除数据
     * 3.更新数据
     *
     * @param pst
     * @return
     */
    private boolean updateData(PreparedStatement pst){
        log.info(pst);
        int result=0;
        try {
            result=pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("effect row:"+result);
        if(result==0){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 初始化成员变量
     */
    @PostConstruct
    public void init(){
        log=Logger.getLogger(this.getClass());
    }

}
