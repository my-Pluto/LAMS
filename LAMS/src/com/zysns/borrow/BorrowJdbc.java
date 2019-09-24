/**
 * 图书借还界面数据库操作类
 * 主要完成在图书借还方面对数据库的操作
 */
package com.zysns.borrow;

import com.zysns.main.Borrow_Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

import static com.zysns.other.AlertBox.showalertbox;

public class BorrowJdbc extends com.zysns.main.Jdbc {

    //进行图书借阅工作
    //重要提示！！！一本书一天只能借阅一次，无论当天是否归还，改天均不能再借阅该书！！！
    public static void borrow(String Rno, String Bno) throws Exception{
        //清空结果集
        setRs(null);

        if (Ret()) {
            //首先判定是否存在该用户
            String sqlString = "SELECT * FROM `读者` WHERE `读者证编号` = '" + Rno + "'";
            setRs(getStmt().executeQuery(sqlString));

            //如果不存在该用户，输出错误，返回
            if (!getRs().next()) {
                showalertbox("警告", "查无此人！图书借阅失败！");
                return;
            }

            setRs(null);
            //判定该用户是否存在超期图书，如果存在，输出错误，返回
            sqlString = "SELECT * FROM `借还` WHERE `读者证编号` = '" + Rno + "'";
            setRs(getStmt().executeQuery(sqlString));

            while (getRs().next()) {
                //获取归还日期
                LocalDate date1 = getRs().getDate("归还日期").toLocalDate();
                //获取当前时间
                LocalDate date2 = LocalDate.now();
                //进行判断
                if ((LocalDate.now().toEpochDay() - getRs().getDate("归还日期").toLocalDate().toEpochDay() > 0)
                        && (getRs().getString("是否归还").equals("否"))){
                    showalertbox("警告", "您有超期图书！\n借阅失败！");
                    return;
                }
            }

            //查询今天是否借阅该图书
            setRs(null);
            sqlString = "SELECT * FROM `借还` WHERE `读者证编号` = '" + Rno + "' AND `图书编号` = '" +
                    Bno + "'";
            setRs(getStmt().executeQuery(sqlString));
            //如果已经借阅，输出错误提示
            if (getRs().next()){
                showalertbox("警告", "该书您今天已借阅该图书！\n借阅失败！");
                return;
            }

            setRs(null);
            //查询借阅的图书是否存在
            sqlString = "SELECT * FROM `图书` WHERE `图书编号` = '" + Bno + "'";
            setRs(getStmt().executeQuery(sqlString));
            //如果不存在，输出错误提示
            if (!getRs().next()){
                showalertbox("警告", "查无此书！\n借阅失败！");
                return;
            }

            //查询借阅的图书是否存在在馆数量
            int i = getRs().getInt("馆藏数量");
            //如果不在馆，输出错误提示
            if (i == 0){
                showalertbox("警告", "该书已全部借出！\n借阅失败！");
                return;
            }
            else{
                //如果存在在馆数量，进行借阅操作
                setRs(null);

                //将图书的在馆数量减一
                sqlString = "UPDATE `图书` SET `馆藏数量` = `馆藏数量` - 1 WHERE `图书编号` = '" + Bno + "'";
                int j = getStmt().executeUpdate(sqlString);

                //如操作成功
                if (j == 1) {
                    //将借阅信息插入“借还”表中
                    sqlString = "INSERT INTO `借还`(`读者证编号` ,`图书编号` ,`借阅日期` ,`归还日期` ,`是否归还` ) VALUES('" + Rno + "','"
                            + Bno + "','" + LocalDate.now() + "','" + LocalDate.now().plusDays(30) + "','否')";
                    int k = getStmt().executeUpdate(sqlString);

                    //借阅成功输出提示
                    if (k > 0){
                        showalertbox("提示", "借阅成功！");
                    }
                    else {
                        //否则输出提示，并恢复馆藏数量
                        showalertbox("提示", "借阅失败！");
                        sqlString = "UPDATE `图书` SET `馆藏数量` = `馆藏数量` + 1 WHERE `图书编号` = '" + Bno + "'";
                    }
                }
                else {
                    showalertbox("提示", "未知错误！借阅失败！");
                }
            }
        } else {
            showalertbox("警告", "数据库连接失败！请稍后重试！");
        }
    }

    //进行图书归还操作
    public static void book_still(Borrow_Book borrow_book) throws Exception {
        if (Ret()) {
            //结果集清空
            setRs(null);

            //进行图书归还操作
            String sqlString = "UPDATE `借还` SET `是否归还` = '是' WHERE `读者证编号` = '" + borrow_book.getRno() +
                    "' AND `图书编号` = '" + borrow_book.getBno() + "' AND `借阅日期` = '" + borrow_book.getBorrow_date() + "' AND `是否归还` = '否'";
            int i = getStmt().executeUpdate(sqlString);

            //判断是否归还成功
            if (i == 0){
                showalertbox("警告" , "归还失败！请重试！");
                return;
            }

            //将库存加一
            sqlString = "UPDATE `图书` SET `馆藏数量` = `馆藏数量` + 1 WHERE `图书编号` = '" + borrow_book.getBno() + "'";
            int j = getStmt().executeUpdate(sqlString);
            if (j == 0){
                showalertbox("警告" , "归还失败！请重试！");
                return;
            }
            showalertbox("提示" , "归还成功");

        } else {
            showalertbox("警告", "数据库连接失败！");
        }
    }

    //图书借阅信息查询
    public static boolean borrowselect(String no) throws Exception{

        if (Ret()) {
            setRs(null);
            String sqlString = "SELECT * FROM `读者` WHERE `读者证编号` = '" + no + "'";
            setRs(getStmt().executeQuery(sqlString));

            //如果不存在该用户，输出错误，返回
            if (!getRs().next()) {
                showalertbox("警告", "查无此人！信息查询失败！");
                return false;
            }

            //查询操作
            setRs(null);
            sqlString = "SELECT * FROM `借还`, `图书` WHERE `读者证编号` = '" + no + "' AND `借还`.`图书编号` = `图书`.`图书编号` ORDER BY `是否归还` DESC ";
            setRs(getStmt().executeQuery(sqlString));
            return true;
        } else {
            showalertbox("警告", "数据库连接失败！");
            return false;
        }
    }

    //将图书借阅记录的查询结果进行处理，以便于显示在TableView中
    public static ObservableList<Borrow_Book> getborrow() throws SQLException {
        ObservableList<Borrow_Book> borrow_books = FXCollections.observableArrayList();
        if (Ret()) {
            double i = 0;
            while (getRs().next()) {
                borrow_books.add(new Borrow_Book(getRs().getString("读者证编号"), getRs().getString("图书编号"),
                        getRs().getDate("借阅日期").toLocalDate(), getRs().getDate("归还日期").toLocalDate(),
                        getRs().getString("是否归还"), getRs().getString("图书名称")));
                i++;
            }
            if (i != 0){
                showalertbox("提示", "查询成功！");
                return borrow_books;
            }
            else{
                showalertbox("提示", "该用户没有借阅记录存在！");
                return null;
            }
        }
        else{
            showalertbox("警告", "数据库连接失败！请稍后重试！");
            return null;
        }

    }

    //将图书超期记录的查询结果进行处理，以便于显示在TableView中
    public static ObservableList<Borrow_Book> geturge() throws SQLException {
        //创建用于存储结果的容器
        ObservableList<Borrow_Book> urge_books = FXCollections.observableArrayList();

        if (Ret()) {
            //i用于计数操作
            double i = 0;

            if (getRs() != null){
                while (getRs().next()){
                    //循环将结果注入容器
                    if ((LocalDate.now().toEpochDay() - getRs().getDate("归还日期").toLocalDate().toEpochDay() > 0)
                             && getRs().getString("是否归还").equals("否")){
                        urge_books.add(new Borrow_Book(getRs().getString("读者证编号"), getRs().getString("图书编号"),
                                getRs().getDate("借阅日期").toLocalDate(), getRs().getDate("归还日期").toLocalDate() ,
                                getRs().getString("是否归还"), getRs().getString("图书名称")));
                        i++;
                    }
                }

                //判断是否有超期图书存在
                if (i == 0){
                    showalertbox("提示", "该用户没有超期图书存在！");
                    return null;
                }
                else {
                    showalertbox("提示", "查询成功！");
                    return urge_books;
                }
            }
            else {
                showalertbox("提示", "该用户没有超期图书存在！");
                return null;
            }
        }
        else{
            showalertbox("警告", "数据库连接失败！请稍后重试！");
            return null;
        }
    }

    //将图书借阅且未归还记录的查询结果进行处理，以便于显示在TableView中
    public static  ObservableList<Borrow_Book> getstill() throws SQLException {
        //创建结果储存容器
        ObservableList<Borrow_Book> still_books = FXCollections.observableArrayList();

        if (Ret()) {
            //i用于计数
            double i = 0;

            if (getRs() != null){
                while (getRs().next()){
                    //循环将结果注入容器
                    if (getRs().getString("是否归还").equals("否")){
                        still_books.add(new Borrow_Book(getRs().getString("读者证编号"), getRs().getString("图书编号"),
                                getRs().getDate("借阅日期").toLocalDate(), getRs().getDate("归还日期").toLocalDate() ,
                                getRs().getString("是否归还"), getRs().getString("图书名称")));
                        i++;
                    }
                }

                //判断是否存在在借图书
                if (i == 0){
                    showalertbox("提示", "该用户没有在借图书存在！");
                    return null;
                }
                else {
                    showalertbox("提示", "查询成功！");
                    return still_books;
                }
            }
            else {
                showalertbox("提示", "该用户没有在借图书存在！");
                return null;
            }
        }
        else{
            showalertbox("警告", "数据库连接失败！请稍后重试！");
            return null;
        }
    }
}
