package com.TinyTipsWEB.Dao.sql;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("dbConnection")
public class DBConnection {

    private String driverClass;

    private String url;

    private String user;

    private String password;

    private Connection connection;

    public void setDriverClass(String driverClass) {
        this.driverClass=driverClass;
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

    /**
     * 获取数据库连接
     * @return
     */
    @Bean(name = "connection")
    public Connection getConnection() {
        try {
            Class.forName(driverClass);
            connection=DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 断开数据库连接
     * @return
     */
    @PreDestroy
    public void disConnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
