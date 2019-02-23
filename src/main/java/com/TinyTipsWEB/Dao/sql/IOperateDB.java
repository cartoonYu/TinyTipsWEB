package com.TinyTipsWEB.DAO.sql;

import java.sql.ResultSet;
import java.util.Map;

public interface IOperateDB {

    boolean add(String tableName, Map<String, String> data);   //插入数据

    boolean delete(String tableName, Map<String, String> condition);   //删除数据

    ResultSet query(String tableName, Map<String, String> condition);   //查询数据

    boolean update(String tableName, Map<String, String> data, Map<String, String> condition);  //更新数据
}
