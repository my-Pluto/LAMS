/**
 * 数据库操作类，主要用于数据库的连接操作
 */

package com.zysns.main;

import java.sql.*;

import static com.zysns.other.AlertBox.showalertbox;

public class Jdbc {
    private static Connection conn = null;
    private static Statement stmt=null;
    private static ResultSet rs= null;
    //数据库连接，采用阿里云云数据库
    private static String url = "jdbc:mysql://rm-m5e01n6xm73k5dple4o.mysql.rds.aliyuncs.com:3306/LAMS";
    //数据库用户名
    private static String username = "lams";
    //数据库密码
    private static String password = "QUTqdlgdx2017";

    //各种getter、setter方法，用于获取指定对象
    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        Jdbc.conn = conn;
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static void setStmt(Statement stmt) {
        Jdbc.stmt = stmt;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static void setRs(ResultSet rs) {
        Jdbc.rs = rs;
    }

    //进行数据库连接
   public static void jdbcconnection(){
             //加载数据库驱动
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                showalertbox("警告", "数据库连接失败！！\n\t请重试！");
                System.exit(0);
            }
            //通过DriverManager获取数据库连接
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                showalertbox("警告", "数据库连接失败！！\n\t请重试！");
                System.exit(0);
            }
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                showalertbox("警告", "数据库连接失败！！\n\t请重试！");
                System.exit(0);
            }
        }

        //判断是否对数据库连接成功，如果成功，返回true，否则返回false
        public static boolean Ret() {
            if ((conn == null)||(stmt == null))
                return false;
            else
                return true;
        }

}




