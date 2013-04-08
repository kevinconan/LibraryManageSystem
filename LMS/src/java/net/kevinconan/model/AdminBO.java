/*
 * BO means Bean Operate
 * This class using AdminBean
 */
package net.kevinconan.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kevinconan.tools.ToSHA_1;

/**
 *
 * @author Kevin
 */
public class AdminBO {

    /**
     * 检查输入的用户名和密码是否存在于数据库 数据库中的密码需要用SHA-1保存
     * <p/>
     * @param u 用户名
     * @param p 密码
     * <p/>
     * @return boolean
     */
    public boolean checkAdmin(String u, String p) {
        boolean b = false;
        ConnDB cdb = new ConnDB();
        String sql = "select APASSWD from admin where ADID='" + u + "'";
        String pwdb = null;

        cdb.connect();
        cdb.setSqlStatement(sql);
        cdb.execQuery();
        try {
            if (cdb.getResultSet().next()) {
                //从数据库直接读取byte数组，这里要确定数据库里面保存的是20个字节的数据
                //SHA-1算法的结果是20字节
                //这里没有选择保存20字节的二进制数据，太不优雅了，把它变成40个字节的字符串保存的，内容是十六进制数字
                pwdb = cdb.getResultSet().getString(1);
            } else {
                return false;//既然用户都不存在，就没必要继续了
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cdb.close();
        }

        ToSHA_1 ts = new ToSHA_1(p);
        if (ts.getSHA_1String().equals(pwdb)) {
            b = true;
        }

        return b;
    }

    /**
     * 创建一个用户，用字符串保存用户名和SHA-1密码
     * <p/>
     * @param u 用户名
     * @param p 密码
     * <p/>
     * @return boolean
     */
    public boolean createAdmin(String u, String p) {
        boolean b = false;
        ConnDB cdb = new ConnDB();
        String sql = "select * from admin where ADID='" + u + "'";

        cdb.connect();
        cdb.setSqlStatement(sql);
        cdb.execQuery();

        try {
            //如果用户名存在，直接返回false
            if (cdb.getResultSet().next()) {
                return false;
            }
            //程序执行到这里，说明用户名不存在，可以创建

            ToSHA_1 ts = new ToSHA_1(p);
            String pws = ts.getSHA_1String();



            if (pws.isEmpty()) {
                return false;//这里为空字符串的话，显然前面异常了，那么就直接返回好了
            }
            //写入数据库
            String sql2 = "insert into admin(ADID,APASSWD) values('" + u + "','" + pws
                    + "')";

            cdb.setSqlStatement(sql2);
            cdb.execUpdate();
            //检查创建成功了没有
            if (cdb.getExecUpdateNum() != 0) {
                b = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cdb.close();
        }





        return b;

    }
}
