package com.TinyTipsWEB.DAO.sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("dbConnection")
public class DBConnection {

    @Resource(name = "dbConfig")
    private DBConfig dbConfig;

    private Connection connection;

    /**
     * 获取数据库连接
     * @return
     */
    @Bean(name = "connection")
    public Connection getConnection() {
        try {
            Class.forName(dbConfig.getDriverClass());
            connection=DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
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
