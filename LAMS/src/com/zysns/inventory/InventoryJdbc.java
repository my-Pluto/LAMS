package com.zysns.inventory;

import com.zysns.main.Book;

import java.sql.SQLException;
import java.time.LocalDate;

import static com.zysns.other.AlertBox.showalertbox;

public class InventoryJdbc extends com.zysns.main.Jdbc {
    public static void create_book(Book book) throws SQLException {
        //添加图书信息:编号，名称，作者，出版社，日期，ISBN，书架编号
        if (Ret()) {
            setRs(null);
            String sqlString = "SELECT * FROM `书架` WHERE `书架编号` = '" + book.getBbookno() + "'";
            setRs(getStmt().executeQuery(sqlString));
            if (!getRs().next()){
                showalertbox("警告", "您输入的书架编号不存在！\n请检查输入！");
                return;
            }
            else if (!getRs().getString("所属类别").equals(book.getBfamily())){
                showalertbox("警告", "您输入的书架编号和存放图书的所属类别不符！\n请检查输入！");
                return;
            }
            setRs(null);
            int i;
            try {
                sqlString = "INSERT INTO `存放`(`图书号` ,`书架号` ,`入库日期` ,`入库数量` ) VALUES ('" + book.getBno() + "', '" + book.getBbookno() +
                        "', '" + LocalDate.now() + "'," + Integer.toString(book.getBquantity()) + ")";
                i = getStmt().executeUpdate(sqlString);
            }
            catch (java.sql.SQLIntegrityConstraintViolationException e){
                showalertbox("警告", "您今天已经录入过该图书！");
                return;
            }
            setRs(null);
            sqlString = "SELECT * FROM `图书` WHERE `图书编号` = '" + book.getBno() + "'";
            setRs(getStmt().executeQuery(sqlString));
            if (getRs().next()){
                sqlString = "UPDATE `图书` SET `馆藏数量` = `馆藏数量` + " + Integer.toString(book.getBquantity()) +  "WHERE `图书编号` = '" + book.getBno() + "'";
                int j = getStmt().executeUpdate(sqlString);
                if (i == 1 && j == 1){
                    showalertbox("提示","图书入库成功！");
                    return;
                }
                else {
                    showalertbox("提示","未知错误！图书入库失败！\n请稍后重试！");
                }
            }
            else{
                setRs(null);
                sqlString = "INSERT INTO `图书`(`图书编号`,`图书名称`,`图书作者`,`出版社`,`出版时间`,`ISBN`,`书架编号`,`所属类别`,`馆藏数量` ) VALUES('" +
                        book.getBno() + "','" + book.getBname() + "','" + book.getBauthor() + "',' " + book.getBpress() + "','" +
                        book.getBdate() + "','" + book.getBisbn() + "','" + book.getBbookno() + "','" + book.getBfamily() + "'," +
                        Integer.toString(book.getBquantity()) + ")" ;
                int j = getStmt().executeUpdate(sqlString);
                if (i == 1 && j ==1){
                    showalertbox("提示","图书入库成功！");
                    return;
                }
                else {
                    showalertbox("提示","未知错误！图书入库失败！\n请稍后重试！");
                }
            }
        } else {
            showalertbox("警告", "数据库连接失败！");
        }
    }

    public static Book selectbook(String no) throws SQLException {
        if (Ret()){
            setRs(null);
            Book book = new Book();
            String sqlString = "SELECT * FROM `图书` WHERE `图书编号` = '" + no + "'";
            setRs(getStmt().executeQuery(sqlString));
            if (getRs().next()){
                showalertbox("提示", "查询成功！");
                book.setBno(getRs().getString("图书编号"));
                book.setBisbn(getRs().getString("ISBN"));
                book.setBpress(getRs().getString("出版社"));
                book.setBquantity(getRs().getInt("馆藏数量"));
                book.setBbookno(getRs().getString("图书编号"));
                book.setBname(getRs().getString("图书名称"));
                book.setBfamily(getRs().getString("所属类别"));
                book.setBbookno(getRs().getString("书架编号"));
                book.setBauthor(getRs().getString("图书作者"));
                book.setBdate(getRs().getDate("出版时间").toLocalDate());
                return book;
            }
            else {
                showalertbox("警告", "查无此书！请检查输入！");
                return null;
            }
        }
        else {
            showalertbox("警告", "数据库连接失败！");
            return null;
        }
    }
    public static void updatebook(Book book) throws SQLException {
        //修改图书信息
        if (Ret()) {
            String sqlString = "UPDATE `图书` SET `图书名称`= '" + book.getBname() + "', `图书作者`= '" + book.getBauthor() + "', `出版社`= '" + book.getBpress() +
                    "', `出版时间`= '" + book.getBdate() + "', `ISBN`= '" + book.getBisbn() + "', `书架编号`= '" + book.getBbookno() + "', `所属类别`= '" +
                    book.getBfamily() + "', `馆藏数量`= " + Integer.toString(book.getBquantity()) + " WHERE `图书编号`= '" + book.getBno() + "'";
            int i = getStmt().executeUpdate(sqlString);
            if (i == 1){
                showalertbox("提示","更新成功！");
                return;
            }
            else {
                showalertbox("提示","更新失败！");
                return;
            }
        } else {
            showalertbox("警告", "数据库连接失败！");
        }
    }
    public static void delete_book(String no) throws SQLException {
        //删除图书信息
        if (Ret()) {
            String sqlString = "DELETE FROM `图书` WHERE `图书编号` = '" + no + "'";
            int i = getStmt().executeUpdate(sqlString);
            if (i == 1){
                showalertbox("提示","删除成功！");
                return;
            }
            else {
                showalertbox("提示","删除失败！");
                return;
            }
        } else {
            showalertbox("警告", "数据库连接失败！");
            return;
        }
    }
}