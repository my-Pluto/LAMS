package com.zysns.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectJdbc extends com.zysns.main.Jdbc {
    public static void selectone(String snoString) throws SQLException {
        //个人信息查询
        if (Ret()) {
            String sqlString = "SELECT 读者证编号 FROM 读者 WHERE 读者.读者编号 =" + snoString;
            setRs(getStmt().executeQuery(sqlString));
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
        }
    }
    public static void selecttwo(String methodString ,String keyString) throws SQLException{
        //图书查询（分类查询，用关键字查询）
        if (Ret()){
            String sqlString = "SELECT * FROM 图书 WHERE"+ "'图书'." + methodString + " = '%" + keyString + "%'";
            setRs(getStmt().executeQuery(sqlString));
        }else{
            com.zysns.other.AlertBox.showalertbox("警告","连接数据库失败！");
        }
    }
    public static void selectthree(String rotString) throws SQLException{
        //热度查询
        if (Ret()) {
            String sqlString = "SELECT `图书`.图书编号,`图书`.图书名称,`图书`.图书作者,`图书`.所属类别,`图书`.书架编号,`图书`.馆藏数量,COUNT(`借还`.图书编号)" +
                    "FROM `图书` ,`借还`" +
                    "WHERE `图书`.图书编号=`借还`.图书编号" +
                    "GROUP BY `图书编号` ";
            setRs(getStmt().executeQuery(sqlString));
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "数据库连接失败！");
        }
    }
}


