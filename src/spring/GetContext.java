package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetContext {

    private static String profileName=new String("/spring/beans.xml");

    public static ApplicationContext getContext() {
        return new ClassPathXmlApplicationContext(profileName);
    }
}
