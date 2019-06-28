/**
 * 读者类
 * 主要用于对系统中读者的操作
 */
package com.zysns.main;

import java.time.LocalDate;

public class Reader {
    private String rno;    //读者编号
    private String rpassword;    //读者密码
    private String rname;    //读者姓名
    private String rsex;    //读者性别
    private LocalDate rbrithday;    //读者出生日期
    private LocalDate rcreate;    //读者证创建日期
    private String rpower;    //借阅权限，主要分为成人读者1、儿童读者0等
    private String rage; //读者年龄

    //无参构造
    public Reader() {
    }

    //有参构造
    public Reader(String rno, String rpassword, String rname, String rsex, LocalDate rbrithday, LocalDate rcreate, String rpower, String rage) {
        this.rno = rno;
        this.rpassword = rpassword;
        this.rname = rname;
        this.rsex = rsex;
        this.rbrithday = rbrithday;
        this.rcreate = rcreate;
        this.rpower = rpower;
        this.rage = rage;
    }

    //getter、setter
    public String getRage() {
        return rage;
    }

    public void setRage(String rage) {
        this.rage = rage;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRsex() {
        return rsex;
    }

    public void setRsex(String rsex) {
        this.rsex = rsex;
    }

    public LocalDate getRbrithday() {
        return rbrithday;
    }

    public void setRbrithday(LocalDate rbrithday) {
        this.rbrithday = rbrithday;
    }

    public LocalDate getRcreate() {
        return rcreate;
    }

    public void setRcreate(LocalDate rcreate) {
        this.rcreate = rcreate;
    }

    public String getRpower() {
        return rpower;
    }

    public void setRpower(String rpower) {
        this.rpower = rpower;
    }
}
