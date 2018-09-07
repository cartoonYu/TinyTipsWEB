package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sql.DBConnection;

public class GetContext {
	
	public static ApplicationContext getContext() {
		String profileName=new String("beans.xml");
		return new ClassPathXmlApplicationContext(profileName);
	}
}
