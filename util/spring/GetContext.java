package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sql.DBConnection;
import util.JudgeEmpty;

public class GetContext {
	
	private static String profileName=new String("beans.xml");
	
	public static ApplicationContext getContext() {
		return new ClassPathXmlApplicationContext(profileName);
	}
	
}
