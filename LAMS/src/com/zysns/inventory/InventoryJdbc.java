package com.zysns.inventory;

import com.zysns.main.Book;

import java.sql.SQLException;

public class InventoryJdbc extends com.zysns.main.Jdbc {
    public static void inventoryone(Book book) throws SQLException {
        //添加图书信息:编号，名称，作者，出版社，日期，ISBN，书架编号
        if (Ret()) {
            String sqlString = "INSERT" +
                    "INTO `图书`(`图书编号`,`图书名称`,`图书作者`,`出版社`,`出版时间`,`ISBN`,`书架编号`,`所属类别`,`馆藏数量` )" +
                    "VALUES(`" + book.getBno()+ "`,`" + book.getBname() + "`,`" + book.getBauthor() + "`,`" + book.getBpress() + "`,`" +book.getBdate() + "`,`" + book.getBisbn() + "`," +
                    "`" + book.getBbookno()+ "`," + "`" + book.getBfamily() + "`,`" + book.getBquantity() + "`)";
            setRs(getStmt().executeQuery(sqlString));
            com.zysns.other.AlertBox.showalertbox("提示","数据库连接成功！");
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
        }
    }
    public static void inventorytwo(Book book) throws SQLException {
        //修改图书信息
        if(getRs().next()) {
            if (Ret()) {
                String sqlString = "UPDATE `图书`" +
                        "SET  `图书名称`=`" + book.getBname() + "`, `图书作者`=`" +book.getBauthor() + ", `出版社`=`" + book.getBpress()+ "`, `出版时间`=`" + book.getBdate()+ "`, " +
                        "`ISBN`=`" +  book.getBisbn() + "`, `书架编号` =`" +  book.getBbookno()+ "`,`所属类别`=`" +  book.getBfamily() + "`,`馆藏数量=`"+book.getBquantity()  + "`" +
                        "WHERE `图书编号` =`" +  book.getBno() + "`";
                setRs(getStmt().executeQuery(sqlString));
                com.zysns.other.AlertBox.showalertbox("提示","更新成功！");
            } else {
                com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
            }
        }
            else{
                com.zysns.other.AlertBox.showalertbox("提示","更新失败！");
            }
    }
    public static void inventorythree(Book book) throws SQLException {
        //删除图书信息
        if(getRs().next()) {
            if (Ret()) {
                String sqlString = "DELETE FROM `图书` WHERE `图书编号` =`" + book.getBno()+ "`";
                setRs(getStmt().executeQuery(sqlString));
                com.zysns.other.AlertBox.showalertbox("提示","删除成功！");
            } else {
                com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
            }
        }
        else{
            com.zysns.other.AlertBox.showalertbox("提示","删除失败！");
        }
    }
}