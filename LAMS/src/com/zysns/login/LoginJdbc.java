package com.zysns.login;


import com.zysns.main.Manager;
import com.zysns.main.Reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginJdbc extends com.zysns.main.Jdbc {
    public static void Login(String user, String keyword, String family) throws Exception {
        //登录-用户，密码，类别
        if (Ret()) {
            if (family.equals("管理员")) {
                String sqlString = "SELECT 工号" +
                        "FROM 管理员" +
                        "WHERE 管理员.工号=" + user + " AND 管理员.密码='" + keyword + "'";
                setRs(getStmt().executeQuery(sqlString));
            } else {
                String sqlString = "SELECT 读者证编号" +
                        "FROM 读者" +
                        "WHERE 读者.读者证编号=" + user + " AND 读者.密码='" + keyword + "'";
                setRs(getStmt().executeQuery(sqlString));
            }
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "登陆失败！");
        }
    }

    public static void register(Reader reader) throws Exception {
        //注册-读者证编号，密码，姓名，性别，出生日期，借阅权限
        if (Ret()) {
            String sqlString = "INSERT" +
                    "INTO `读者`(`读者证编码`,`密码`,`姓名`,`性别`,`出生日期`,`借阅日期`,`借阅权限`) " +
                    "VALUES(`" +reader.getRno() +"`,`" +reader.getRpassword()+"`,`" +reader.getRname()+"`,`" + reader.getRsex()+"`,`" +reader.getRbrithday()+"`," +
                    "`" +reader.getRcreate()+"`,`" +reader.getRpower()+"`)";
            setRs(getStmt().executeQuery(sqlString));
            com.zysns.other.AlertBox.showalertbox("提示","注册成功！");
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "注册失败！");
        }
    }
    }




