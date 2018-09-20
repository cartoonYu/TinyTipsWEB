package bean.operate;

import sql.OperateDB;

/**
 * 拆分形参传进的Note，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateNote {

    private OperateDB db;

    /**
     * 将数据库操作类对象传进本类
     * 注：已经通过Spring注入对象，不需在后续操作显式传入
     * @param db
     */
    public void setOperateDB(OperateDB db){
        this.db=db;
    }

}
