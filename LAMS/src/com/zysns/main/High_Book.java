package com.zysns.main;

public class High_Book {
    private String book_number;
    private String book_author;
    private String bookname;
    private String book_family;
    private String book_bno;
    private String book_no;
    private int book_quantity;

    public High_Book(String book_number, String book_author, String bookname, String book_family, String book_bno, String book_no, int book_quantity) {
        this.book_number = book_number;
        this.book_author = book_author;
        this.bookname = bookname;
        this.book_family = book_family;
        this.book_bno = book_bno;
        this.book_no = book_no;
        this.book_quantity = book_quantity;
    }

    public High_Book() {
    }

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
