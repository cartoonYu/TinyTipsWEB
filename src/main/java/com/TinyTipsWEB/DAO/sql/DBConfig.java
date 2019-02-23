package com.TinyTipsWEB.DAO.sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("dbConfig")
public class DBConfig {

    @Value("${dbConnection.driverClass}")
    private String driverClass;

    @Value("${dbConnection.url}")
    private String url;

    @Value("${dbConnection.user}")
    private String user;

    @Value("${dbConnection.password}")
    private String password;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
