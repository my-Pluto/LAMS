package com.zysns.borrow;

import java.sql.Date;
import java.sql.SQLException;

public class BorrowJdbc extends com.zysns.main.Jdbc {
    public static void borrow(String rno) throws Exception{
        if (Ret()) {
            String sqlString = "SELECT 借阅日期,归还日期" +
                    "FROM `借还` " +
                    "WHERE `读者编号` =`"+rno+"`";
            setRs(getStmt().executeQuery(sqlString));
            while (getRs().next()) {
                Date date1 = getRs().getDate("借阅日期");
                Date date2 = getRs().getDate("归还日期");
                int i = getRs().getInt("是否归还");
                if(i == 1)
                    //i=1已归还
                    continue;
                if (date2.getYear() - date1.getYear() >0 )
                    return;
                int month = date2.getMonth() - date1.getMonth();
                if (month >=2)
                    return;
            }
            com.zysns.other.AlertBox.showalertbox("提示", "借阅成功！");
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "借阅失败！");
        }
    }
    public static void back(String rno) throws Exception {
        if (Ret()) {
            String sqlString = "SELECT 是否归还" +
                    "FROM `借还` " +
                    "WHERE `读者编号` =`" + rno + "`";
            setRs(getStmt().executeQuery(sqlString));


        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
        }
    }
        public static void borrowselect(String rno) throws Exception{
            if (Ret()) {
                String sqlString = "SELECT 读者证编号,姓名,性别,出生日期,创建日期,借阅权限" +
                                    "FROM `借还` " +
                                   "WHERE `读者编号` =`"+rno+"`";
                setRs(getStmt().executeQuery(sqlString));
            } else {
                com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
            }
    }


}
