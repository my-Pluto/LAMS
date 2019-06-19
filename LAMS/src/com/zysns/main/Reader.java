package com.zysns.main;

import java.util.Date;

public class Reader {
    private double rno;    //读者编号
    private String rpassword;    //读者密码
    private String rname;    //读者姓名
    private String rsex;    //读者性别
    private Date rbrithday;    //读者出生日期
    private Date rcreate;    //读者证创建日期
    private int rpower;    //读者等级

    public Reader() {
    }

    public Reader(double rno, String rpassword, String rname, String rsex, Date rbrithday, Date rcreate, int rpower) {
        this.rno = rno;
        this.rpassword = rpassword;
        this.rname = rname;
        this.rsex = rsex;
        this.rbrithday = rbrithday;
        this.rcreate = rcreate;
        this.rpower = rpower;
    }

    public double getRno() {
        return rno;
    }

    public void setRno(double rno) {
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

    public Date getRbrithday() {
        return rbrithday;
    }

    public void setRbrithday(Date rbrithday) {
        this.rbrithday = rbrithday;
    }

    public Date getRcreate() {
        return rcreate;
    }

    public void setRcreate(Date rcreate) {
        this.rcreate = rcreate;
    }

    public int getRpower() {
        return rpower;
    }

    public void setRpower(int rpower) {
        this.rpower = rpower;
    }
}
