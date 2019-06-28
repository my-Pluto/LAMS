/**
 * 图书热度类
 * 用于存储图书热度的信息
 */
package com.zysns.main;

public class High_Book {
    private String book_number;    //图书借阅次数
    private String book_author;    //图书作者
    private String bookname;    //图书名称
    private String book_family;    //图书类别
    private String book_bno;    //图书书架编号
    private String book_no;    //图书编号
    private int book_quantity;    //图书在馆数量

    //有参构造
    public High_Book(String book_number, String book_author, String bookname, String book_family, String book_bno, String book_no, int book_quantity) {
        this.book_number = book_number;
        this.book_author = book_author;
        this.bookname = bookname;
        this.book_family = book_family;
        this.book_bno = book_bno;
        this.book_no = book_no;
        this.book_quantity = book_quantity;
    }

    //无参构造
    public High_Book() {
    }

    //setter、getter方法
    public String getBook_number() {
        return book_number;
    }

    public void setBook_number(String book_number) {
        this.book_number = book_number;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBook_family() {
        return book_family;
    }

    public void setBook_family(String book_family) {
        this.book_family = book_family;
    }

    public String getBook_bno() {
        return book_bno;
    }

    public void setBook_bno(String book_bno) {
        this.book_bno = book_bno;
    }

    public String getBook_no() {
        return book_no;
    }

    public void setBook_no(String book_no) {
        this.book_no = book_no;
    }

    public int getBook_quantity() {
        return book_quantity;
    }

    public void setBook_quantity(int book_quantity) {
        this.book_quantity = book_quantity;
    }
}
