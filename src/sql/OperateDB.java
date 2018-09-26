package sql;

import util.JudgeEmpty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * pack sql statement and operate database
 * @author cartoon
 * @version 1.5 in 2018.9.26
 * compare to last version
 * 1.remove some suggestive statements
 *
 * still to prefect
 * 1.use prepareStatement replace statement
 * 2.according to point one,still have some place to prefect
 * 3.all function except add need to use data type map to replace string in condition
 */
public class OperateDB {

    private Connection conn;

    private Statement s;

    public OperateDB() {

    }

    public void setConnection(DBConnection db) {
        this.conn=db.getConnect();
        try {
            this.s=this.conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 功能:根据传入的值插入数据到数据库中
     * 使用方法
     * 1.传入相应的形参
     *   tableName(表名)
     *   data(数据以及对应的属性)
     * 2.根据方法返回的boolean判断插入是否成功
     * 注意:
     * 1.每次只能插入一个对象
     * 2.data的key为数据的属性，value为数据本身
     * 3.data里的所有value都转换成String类型再传入
     * @param tableName
     * @param data
     * @return
     */
    public boolean add(String tableName,Map<String,String> data) {
        StringBuilder sql=new StringBuilder();  //拼接的sql语句
        StringBuilder columns=new StringBuilder();
        StringBuilder values=new StringBuilder();
        data.forEach((key,value)->{
            columns.append(key).append(",");
            values.append("\"").append(value).append("\"").append(",");
        });
        sql.append("insert into ").append(tableName).append("(");
        sql.append(columns.substring(0, columns.length()-1)).append(")");
        sql.append(" values(");
        sql.append(values.substring(0, values.length()-1)).append(");");
        int result=-1;
        try {
            result=s.executeUpdate(sql.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(result==-1) {
            return false;
        }else {
            return true;
        }
    }


    /**
     * 功能：根据条件查询数据库的符合条件的数据
     * 使用方法
     * 1.传入相对应的形参
     * 	 tableName(表名)
     * 	 condition(筛选条件)
     * 2.根据返回值判断修改是否成功
     * @param tableName
     * @param condition
     * @return
     */
    public boolean delete(String tableName,Map<String,String> condition) {
        StringBuilder sql=new StringBuilder();  //拼接的sql语句
        StringBuilder tCondition=new StringBuilder();
        sql.append("delete from ").append(tableName).append(" where ");
        condition.forEach((key,value)->{
            if(JudgeEmpty.isNotEmpty(value)){
                tCondition.append(key).append("=").append("\"").append(value).append("\"");
                tCondition.append(" and ");
            }
        });
        sql.append(tCondition.substring(0,tCondition.length()-5)).append(";");
        int result=-1;
        try {
            result=s.executeUpdate(sql.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(result==-1) {
            return false;
        }else {
            return true;
        }
    }


    /**
     * 功能：根据条件查询数据库的符合条件的数据
     * 使用方法
     * 1.传入相对应的形参
     * 	 tableName(表名)
     * 	 condition(筛选条件)
     * 2.根据列名解析返回的ResultSet得出相对应的值
     * 注意
     * 1.应先判断返回的ResultSet是否为null再进行操作
     * @param tableName
     * @param condition
     * @return
     */
    public ResultSet query(String tableName,Map<String,String> condition) {
        StringBuilder sql=new StringBuilder();  //拼接的sql语句
        StringBuilder tCondition=new StringBuilder();
        sql.append("select * from ").append(tableName).append(" where ");
        condition.forEach((key,value)->{
            if(JudgeEmpty.isNotEmpty(value)){
                tCondition.append(key).append("=").append("\"").append(value).append("\"");
                tCondition.append(" and ");
            }
        });
        sql.append(tCondition.substring(0,tCondition.length()-5)).append(";");
        ResultSet result=null;
        try {
            result=s.executeQuery(sql.toString());
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 功能:修改数据库中符合筛选条件的数据
     * 使用方法
     * 1.传入相对应的形参
     * 	 tableName(表名)
     * 	 data(数据以及对应的属性)
     *   condition(筛选条件)
     * 2.根据返回值判断修改是否成功
     * @param tableName
     * @param data
     * @param condition
     * @return
     */
    public boolean update(String tableName,Map<String,String> data,Map<String,String> condition) {
        StringBuilder sql=new StringBuilder(); //拼接的sql语句
        StringBuilder rData=new StringBuilder();  //修改后的数据
        data.forEach((key,value)->{
            rData.append(key).append("=").append("\"").append(value).append("\"").append(",");
        });
        sql.append("update ").append(tableName).append(" where ");
        sql.append(" set ").append(rData.substring(0, rData.length()-1));
        StringBuilder tCondition=new StringBuilder();
        condition.forEach((key,value)->{
            if(JudgeEmpty.isNotEmpty(value)){
                tCondition.append(key).append("=").append("\"").append(value).append("\"");
                tCondition.append(" and ");
            }
        });
        sql.append(tCondition.substring(0,tCondition.length()-5)).append(";");
        int result=-1;
        try {
            result=s.executeUpdate(sql.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(result==-1) {
            return false;
        }else {
            return true;
        }
    }
}
