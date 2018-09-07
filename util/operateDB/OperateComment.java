package operateDB;

import java.sql.Connection;

import bean.Comment;
import sql.DBConnection;

public class OperateComment {
	
	private static DBConnection db=new DBConnection();
	
	private static Connection connect=db.getConnect();
	
	public static boolean add(Comment comment) {
		
		return false;
	}
	
	public static boolean delete(Comment comment) {
		return false;
	}
}
