package com.zysns.select;

import com.zysns.main.Book;
import com.zysns.main.High_Book;
import com.zysns.main.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.zysns.other.AlertBox.showalertbox;

public class SelectJdbc extends com.zysns.main.Jdbc {
    public static Reader select_reader_message (String no) throws SQLException {
        //个人信息查询
        if (Ret()) {
            setRs(null);
            String sqlString = "SELECT * FROM `读者` WHERE `读者证编号` = '" + no + "'";
            setRs(getStmt().executeQuery(sqlString));
            if (getRs().next()){
                Reader reader = new Reader();
                reader.setRno(getRs().getString("读者证编号"));
                reader.setRname(getRs().getString("姓名"));
                reader.setRsex(getRs().getString("性别"));
                reader.setRbrithday(getRs().getDate("出生日期").toLocalDate());
                reader.setRcreate(getRs().getDate("创建日期").toLocalDate());
                reader.setRpower(getRs().getString("借阅权限"));
                return reader;
            }
            else {
                showalertbox("警告", "查无此人！请检查输入数据！");
                return null;
            }
        } else {
            showalertbox("警告", "数据库连接失败！");
            return null;
        }
    }

    public static void selectbook(String methodString ,String keyString) throws SQLException{
        //图书查询（分类查询，用关键字查询）
        if (Ret()){
            setRs(null);
            String sqlString = "SELECT * FROM 图书 WHERE `" + methodString + "` LIKE '%" + keyString +"%'";
            setRs(getStmt().executeQuery(sqlString));
        }else{
            showalertbox("警告","连接数据库失败！");
        }
    }


    public static ObservableList<Book> getbook() throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();
        if (Ret()) {
            double i = 0;
            while (getRs().next()) {
                books.add(new Book(getRs().getString("图书编号"),getRs().getString("图书名称"),
                        getRs().getString("图书作者"),getRs().getString("出版社"),getRs().getDate("出版时间").toLocalDate(),
                        getRs().getString("ISBN"),getRs().getString("书架编号"), getRs().getInt("馆藏数量"),
                        getRs().getString("所属类别")));
                i++;
            }
            if (i != 0){
                showalertbox("提示", "查询成功！");
                return books;
            }
            else{
                showalertbox("提示", "没有该书存在！");
                return null;
            }
        }
        else{
            showalertbox("警告", "数据库连接失败！请稍后重试！");
            return null;
        }

    }

    public static void high_book() throws SQLException{
        //热度查询
        if (Ret()) {
            setRs(null);
            String sqlString ="SELECT `图书`.`图书编号`, `图书`.`图书名称`, `图书`.`图书作者`, `图书`.`所属类别`, `图书`.`馆藏数量`," +
                    "`图书`.`书架编号`, COUNT(`借还`.`图书编号`) '借阅次数' FROM `借还`, `图书` WHERE `图书` .`图书编号`= `借还` .`图书编号`" +
                    "GROUP BY `图书` .`图书编号` ORDER BY `借阅次数` DESC";
            setRs(getStmt().executeQuery(sqlString));
        } else {
            showalertbox("警告", "数据库连接失败！");
        }
    }

    public static ObservableList<High_Book> gethigh_book() throws SQLException {
        ObservableList<High_Book> high_books = FXCollections.observableArrayList();
        if (Ret()) {
            double i = 0;
            while (getRs().next()) {
                high_books.add(new High_Book(Integer.toString(getRs().getInt("借阅次数")), getRs().getString("图书作者"),
                        getRs().getString("图书名称"), getRs().getString("所属类别"), getRs().getString("书架编号"),
                        getRs().getString("图书编号"),getRs().getInt("馆藏数量")));
                i++;
            }
            if (i != 0){
                showalertbox("提示", "查询成功！");
                return high_books;
            }
            else{
                showalertbox("提示", "未知错误！查询失败！");
                return null;
            }
        }
        else{
            showalertbox("警告", "数据库连接失败！请稍后重试！");
            return null;
        }

    }
}


