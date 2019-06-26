package com.zysns.login;


import com.zysns.main.Book;
import com.zysns.main.Manager;
import com.zysns.main.Reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zysns.other.AlertBox.showalertbox;


public class LoginJdbc extends com.zysns.main.Jdbc {

    //登录，登录需要区分管理员和普通用户，如果登录成功，则返回true，否则返回false
    public static boolean Login(String user, String keyword, String family) throws Exception {
        //登录-用户，密码，类别
        String sqlString;
        setRs(null);
        if (Ret()) {
            if (family.equals("管理员")) {
                sqlString = "SELECT * FROM `管理员` WHERE `管理员`.`工号` = '" + user + "' AND `管理员`.`密码` = '" + keyword + "'";
                setRs(getStmt().executeQuery(sqlString));
            } else {
                sqlString = "SELECT * FROM `读者` WHERE `读者`.`读者证编号` = '" + user + "' AND `读者`.`密码` = '" + keyword + "'";
                setRs(getStmt().executeQuery(sqlString));
            }
            if (!getRs().next()){
                showalertbox("警告", "登录失败！\n请检查您输入的账号、密码以及登录类型!");
                return false;
            }
            else {
                return true;
            }

        } else {
            showalertbox("警告", "数据库连接失败！请重试!");
            return false;
        }
    }

    //读者信息注册，如果注册成功，返回true，否则返回false
    public static boolean registerjdbc(Reader reader) throws Exception {

        if (Ret()) {    //首先判断数据库是否在连接状态

            //用于判断当前注册的账号是否存在
            String sql = "SELECT * FROM `读者` WHERE `读者`.`读者证编号` = '" + reader.getRno() + "'";
            setRs(getStmt().executeQuery(sql));

            //如果不存在
            if (!getRs().next()) {

                //进行账号注册
                String sqlString = "INSERT INTO `读者`(`读者证编号`,`密码`,`姓名`,`性别`,`出生日期`,`创建日期`,`借阅权限`)" +
                        "VALUES('" + reader.getRno() + "', '" + reader.getRpassword() + "', '" + reader.getRname() +
                        "', '" + reader.getRsex() + "', '" + reader.getRbrithday() + "', '" + reader.getRcreate() + "', '" + reader.getRpower() + "')";
                int i = getStmt().executeUpdate(sqlString);

                //update返回值为受影响的行数
                if (i != 0){
                    showalertbox("提示", "注册成功！");
                    return true;
                }
                else{
                    showalertbox("警告", "注册失败！");
                    return false;
                }
            }
            else {
                showalertbox("警告", "您注册的账号已存在！\n请检查您输入的读者证号，或联系管理员确认");
                return false;
            }
        } else {
            showalertbox("警告", "数据库连接失败！请重试！");
            return false;
        }
    }

    //用于在登录成功后，获取当前登录用户的信息，不包括密码
    public static Manager return_manager (String user) throws SQLException {
        Manager manager = new Manager();
        setRs(null);
        String sqlString = "SELECT * FROM `管理员` WHERE `管理员`.`工号` = '" + user + "'";
        setRs(getStmt().executeQuery(sqlString));
        while (getRs().next()){
            manager.setMno(getRs().getString("工号"));
            manager.setMname(getRs().getString("姓名"));
            manager.setMsex(getRs().getString("性别"));
            manager.setMdept(getRs().getString("所属部门"));
            manager.setMdirthday(getRs().getDate("出生日期").toLocalDate());
            manager.setMlead(getRs().getString("所属领导"));
            manager.setMlevel(getRs().getString("管理员等级"));
        }
        return manager;
    }

    //用于在登录成功后，获取当前登录用户的信息，不包括密码
    public static Reader return_reader(String user) throws SQLException {
        Reader reader = new Reader();
        setRs(null);
        String sqlString = "SELECT * FROM `读者` WHERE `读者`.`读者证编号` = '" + user + "'";
        setRs(getStmt().executeQuery(sqlString));
        while (getRs().next()){
            reader.setRno(getRs().getString("读者证编号"));
            reader.setRname(getRs().getString("姓名"));
            reader.setRsex(getRs().getString("性别"));
            reader.setRbrithday(getRs().getDate("出生日期").toLocalDate());
            reader.setRcreate(getRs().getDate("创建日期").toLocalDate());
            reader.setRpower(getRs().getString("借阅权限"));
        }
        return reader;
    }
    }




