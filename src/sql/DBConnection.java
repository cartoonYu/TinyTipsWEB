package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String driverClass;
    private String url;
    private String user;
    private String password;

    private Connection conn;

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

    public Connection getConnect() {
        try {
            Class.forName(this.driverClass);
            conn=DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
