package com.zysns.main;

import java.sql.*;

public class Jdbc {
    private static Connection conn = null;
    private static Statement stmt=null;
    private static ResultSet rs= null;

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

    static {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //通过DriverManager获取数据库连接
            String url = "jdbc:mysql://---rm-m5e01n6xm73k5dple.mysql.rds.aliyuncs.com:3306/LAMS";
            String username = "lams";
            String password = "QUTqdlgdx2017";
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static boolean Ret() {
            if ((conn == null)||(stmt == null))
                return false;
                else
                return true;
        }

}




