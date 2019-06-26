/**
 * 借还关系类
 * 主要用于存储借还关系以及在TableView中显示借还信息用
 */
package com.zysns.main;

import java.time.LocalDate;

public class Borrow_Book {
    private String Rno;    //读者证编号
    private String Bno;    //图书编号
    private LocalDate borrow_date;    //借阅时间
    private LocalDate still_date;    //归还时间
    private String yes_or_no;    //是否归还
    private String book_name; //图书名称


    //无参构造
    public Borrow_Book() {
    }

    //有参构造
    public Borrow_Book(String rno, String bno, LocalDate borrow_date, LocalDate still_date, String yes_or_no, String book_name) {
        this.Rno = rno;
        this.Bno = bno;
        this.borrow_date = borrow_date;
        this.still_date = still_date;
        this.yes_or_no = yes_or_no;
        this.book_name = book_name;
    }

    //getter、setter方法
    public String getRno() {
        return Rno;
    }

    public void setRno(String rno) {
        Rno = rno;
    }

    public String getBno() {
        return Bno;
    }

    public void setBno(String bno) {
        Bno = bno;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getStill_date() {
        return still_date;
    }

    public void setStill_date(LocalDate still_date) {
        this.still_date = still_date;
    }

    public String getYes_or_no() {
        return yes_or_no;
    }

    public void setYes_or_no(String yes_or_no) {
        this.yes_or_no = yes_or_no;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
