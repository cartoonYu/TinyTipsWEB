package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private String driverClsss;
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	
	public void setDriverClass(String driverClass) {
		this.driverClsss=driverClass;
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public void setUser(String user) {
		this.user=user;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public Connection getConnect() {
		try {
			Class.forName(this.driverClsss);
			conn=(Connection)DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}