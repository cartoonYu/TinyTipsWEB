package com.TinyTipsWEB.DAO.sql; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* OperateDB Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 23, 2019</pre> 
* @version 1.0 
*/ 
public class OperateDBTest { 

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

        /** 
    * 
    * Method: add(String tableName, Map<String, String> data) 
    * 
    */ 
    @Test
    public void testAdd() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: delete(String tableName, Map<String, String> condition) 
    * 
    */ 
    @Test
    public void testDelete() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: query(String tableName, Map<String, String> condition) 
    * 
    */ 
    @Test
    public void testQuery() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: update(String tableName, Map<String, String> data, Map<String, String> condition) 
    * 
    */ 
    @Test
    public void testUpdate() throws Exception { 
        //TODO: Test goes here... 
    } 

    
        /** 
    * 
    * Method: getAddSQL(String tableName, List<String> columns) 
    * 
    */ 
    @Test
    public void testGetAddSQL() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getAddSQL", String.class, List<String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: getDeleteSQL(String tableName, List<String> columns) 
    * 
    */ 
    @Test
    public void testGetDeleteSQL() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getDeleteSQL", String.class, List<String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: getQuerySQL(String tableName, List<String> columns) 
    * 
    */ 
    @Test
    public void testGetQuerySQL() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getQuerySQL", String.class, List<String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: getUpdateSQL(String tableName, List<String> oldColumns, List<String> newColumns) 
    * 
    */ 
    @Test
    public void testGetUpdateSQL() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getUpdateSQL", String.class, List<String>.class, List<String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: getColumns(Map<String,String> data) 
    * 
    */ 
    @Test
    public void testGetColumns() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getColumns", Map<String,String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: getPreparedStatement(String sql, List<String> columns, Map<String,String> data) 
    * 
    */ 
    @Test
    public void testGetPreparedStatementForSqlColumnsData() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getPreparedStatement", String.class, List<String>.class, Map<String,String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: getPreparedStatement(String sql, List<String> oldColumns, Map<String,String> condition, List<String> newColumns, Map<String,String> data) 
    * 
    */ 
    @Test
    public void testGetPreparedStatementForSqlOldColumnsConditionNewColumnsData() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("getPreparedStatement", String.class, List<String>.class, Map<String,String>.class, List<String>.class, Map<String,String>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
    /** 
    * 
    * Method: updateData(PreparedStatement pst) 
    * 
    */ 
    @Test
    public void testUpdateData() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OperateDB.getClass().getMethod("updateData", PreparedStatement.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 
}
