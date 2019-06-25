/**
 * 系统图书类
 */
package com.zysns.main;

import java.util.Date;

public class Book {
    private String bno;  //图书编号
    private String bname;    //图书名称
    private String bauthor;    //图书作者
    private String bpress;    //图书出版社
    private Date bdate;    //图书出版时间
    private String bisbn;    //图书ISBN
    private String bbookno;   //图书所在书架编号
    private int bquantity;    //图书馆藏数量
    private String bfamily;    //图书所属类别

    public String getBfamily() {
        return bfamily;
    }

    public void setBfamily(String bfamily) {
        this.bfamily = bfamily;
    }

    public Book() { }    //无参构造

    public Book(String bno, String bname, String bauthor, String bpress, Date bdate, String bisbn, String bbookno, int bquantity) {  //有参构造
        this.bno = bno;
        this.bname = bname;
        this.bauthor = bauthor;
        this.bpress = bpress;
        this.bdate = bdate;
        this.bisbn = bisbn;
        this.bbookno = bbookno;
        this.bquantity = bquantity;
    }

    /*getter、setter函数*/

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public String getBpress() {
        return bpress;
    }

    public void setBpress(String bpress) {
        this.bpress = bpress;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public String getBisbn() {
        return bisbn;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public String getBbookno() {
        return bbookno;
    }

    public void setBbookno(String bbookno) {
        this.bbookno = bbookno;
    }

    public int getBquantity() {
        return bquantity;
    }

    public void setBquantity(int bquantity) {
        this.bquantity = bquantity;
    }
}
