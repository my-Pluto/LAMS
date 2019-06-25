package com.zysns.account;

import com.zysns.main.Manager;
import com.zysns.main.Reader;

public class AccountJdbc extends com.zysns.main.Jdbc{
    public static void accountone(Reader reader) throws Exception{
        if (Ret()) {
            String sqlString = "INSERT" +
                    "INTO `读者`(`读者证编码`,`密码`,`姓名`,`性别`,`出生日期`,`借阅日期`,`借阅权限`) " +
                    "VALUES(`" +reader.getRno() +"`,`" +reader.getRpassword()+"`,`" +reader.getRname()+"`,`" + reader.getRsex()+"`,`" +reader.getRbrithday()+"`," +
                    "`" +reader.getRcreate()+"`,`" +reader.getRpower()+"`)";
            setRs(getStmt().executeQuery(sqlString));
            com.zysns.other.AlertBox.showalertbox("提示","创建成功！");
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "创建失败！");
        }
    }
    public static void accounttwo(Manager manager) throws Exception{
        if (Ret()) {
            String sqlString = "INSERT" +
                    "INTO `管理员`(`工号`,`密码`,`管理员等级`,`性别`,`所属部门`,`所属领导`,`姓名`) " +
                    "VALUES(`" +manager.getMno() +"`,`" +manager.getMpassword()+"`,`" +manager.getMlevel()+"`,`" +manager.getMsex()+"`,`" +manager.getMdept()+"`," +
                    "`" +manager.getMlead()+"`,`" +manager.getMname()+"`)";
            setRs(getStmt().executeQuery(sqlString));
            com.zysns.other.AlertBox.showalertbox("提示","创建成功！");
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "创建失败！");
        }
    }
    public static void deleteone(String mno,String rno,String family) throws  Exception{
        if (Ret()) {
            if (family.equals("管理员")) {
                String sqlString = "DELETE FROM `管理员` WHERE `工号` =`"+mno+"`";
                setRs(getStmt().executeQuery(sqlString));
                com.zysns.other.AlertBox.showalertbox("提示","删除成功！");
            } else {
                String sqlString = "DELETE FROM `读者` WHERE `工号` =`"+rno+"`";
                setRs(getStmt().executeQuery(sqlString));
                com.zysns.other.AlertBox.showalertbox("提示","删除成功！");
            }
        } else {
            com.zysns.other.AlertBox.showalertbox("警告", "删除失败！");
        }

    }

}


