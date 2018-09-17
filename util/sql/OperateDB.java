package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import spring.GetContext;

public class OperateDB {
	
	private Connection conn;  
	
	private Statement s;
	
	public OperateDB() {
		DBConnection dbconn=GetContext.getContext().getBean("dbConnection",DBConnection.class);
		conn=dbconn.getConnect();
		try {
			s=conn.createStatement();
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
	 *   columnName(列名集合)
	 *   values(数据集合)
	 * 注意:
	 * 1.形参中columnName与values的长度必须相等
	 * 2.
	 * @param tableName
	 * @param data
	 * @return
	 */
	public boolean add(String tableName,Map<String,String> data) {
		Set<String> columns=data.keySet();
		Collection<String> values=data.values(); 
		StringBuffer sql=new StringBuffer();  //拼接的sql语句
		sql.append("insert into ").append(tableName);
		sql.append("(");
		/*sql.append("(").append(columnName).append(") ");
		sql.append("values(").append(values).append(");");*/
		
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
	public boolean delete(String tableName,String condition) {
		StringBuffer sql=new StringBuffer();  //拼接的sql语句
		sql.append("delete from ").append(tableName);
		sql.append(" where ").append(condition).append(";");
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
	public ResultSet query(String tableName,String condition) {
		StringBuffer sql=new StringBuffer();  //拼接的sql语句
		sql.append("select * from ").append(tableName);
		sql.append(" where ").append(condition);
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
	 * 	 columnName(需要修改数值的列名)
	 * 	 values(需要修改的数据)
	 *   condition(筛选条件)
	 * 2.根据返回值判断修改是否成功
	 * 注意:
	 * 1.形参中columnName与values的长度必须相等
	 * @param tableName
	 * @param columnName
	 * @param values
	 * @param condition
	 * @return
	 */
	public boolean update(String tableName,Map<String,String> data,String condition) {
		StringBuffer sql=new StringBuffer(); //拼接的sql语句
		sql.append("update ").append(tableName);
		sql.append(" set ");
		/*for(int i=0,length=columnName.size();i<length;i++) {
			sql.append(columnName.get(i)).append(" = ").append(values.get(i)).append(" ");
		}*/
		sql.append("where ").append(condition);
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

