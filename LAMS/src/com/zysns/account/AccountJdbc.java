/**
 * 账号管理数据库操作类
 * 主要用于程序对数据库的操作
 * 所有对外调用的方法均为static方法
 */

package com.zysns.account;

import com.zysns.main.Manager;
import com.zysns.main.Reader;

import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class AccountJdbc extends com.zysns.main.Jdbc{

    //读者证号创建，参数为Reader类的实例
    public static void reader_create(Reader reader) throws Exception{
        if (Ret()) {    //首先判断数据库是否在连接状态

            //结果集清空
            setRs(null);
            //用于判断当前注册的账号是否存在
            String sql = "SELECT * FROM `读者` WHERE `读者`.`读者证编号` = '" + reader.getRno() + "'";
            setRs(getStmt().executeQuery(sql));

            //如果不存在
            if (!getRs().next()) {
                //进行账号注册
                String sqlString = "INSERT INTO `读者`(`读者证编号`,`密码`,`姓名`,`性别`,`出生日期`,`创建日期`,`借阅权限`)" +
                        "VALUES('" + reader.getRno() + "', '" + reader.getRpassword() + "', '" + reader.getRname() +
                        "', '" + reader.getRsex() + "', '" + reader.getRbrithday() + "', '" + reader.getRcreate() + "', '" + reader.getRpower() + "')";
                int i = getStmt().executeUpdate(sqlString);
                //update返回值为受影响的行数
                //进行注册结果检验
                if (i != 0){
                    showalertbox("提示", "注册成功！");
                    return;
                }
                else{
                    showalertbox("警告", "注册失败！");
                    return;
                }
            }
            //如果存在，输出错误
            else {
                showalertbox("警告", "您注册的账号已存在！\n请检查您输入的读者证号，或联系管理员确认");
                return;
            }
        } else {
            showalertbox("警告", "数据库连接失败！请重试！");
            return;
        }
    }

    //管理员账号创建，参数为一个Manager类的实例
    public static void manager_create(Manager manager) throws Exception{
        //判断数据库是否连接
        if (Ret()) {
            //结果集清空
            setRs(null);

            //首先确认需要创建的工号是否存在
            String sqlString = "SELECT * FROM `管理员` WHERE `工号` = '" + manager.getMno() + "'";
            setRs(getStmt().executeQuery(sqlString));

            //如果该工号不存在，进行创建工作
            if (!getRs().next()){
                sqlString = "INSERT INTO `管理员`(`工号`,`密码`,`管理员等级`,`性别`,`所属部门`,`所属领导`,`姓名`, `出生日期` )" +
               " VALUES('" + manager.getMno() + "','" + manager.getMpassword() + "','" + manager.getMlevel() + "','" +
                        manager.getMsex() + "','" + manager.getMdept()+"','" + manager.getMlead() + "','" + manager.getMname() + "','" + manager.getMdirthday() + "')";
                int i = getStmt().executeUpdate(sqlString);
                //对创建结果进行检验
                if (i == 1)
                    showalertbox("提示","创建成功！");
                else
                    showalertbox("提示","创建失败！");
            }
            else
                showalertbox("警告", "该账号已存在！请检查您的输入！");

        } else {
            showalertbox("警告", "数据库连接失败！请稍后重试！");
        }
    }

    //账号删除，参数no为账号编号，family为删除类型，leader为进行删除操作的用户
    public static void delete_account(String no, String family, String leader) throws  Exception{
        //首先检查数据库是否连接成功
        if (Ret()) {
            //将结果集清空
            setRs(null);

            //判断删除类别
            if (family.equals("管理员")) {
                //进行管理员账号删除操作
                //首先检查是否存在该账号
                String sqlString = new String();
                sqlString = "SELECT * FROM `管理员` WHERE `工号` = '" + no + "'";
                setRs(getStmt().executeQuery(sqlString));

                if (getRs().next()){
                    //存在该账号，进行删除权限检查
                    String lead = getRs().getString("所属领导");
                    if (!lead.equals(leader)){
                        showalertbox("警告", "对不起，您没有权限删除该账号！");
                        return;
                    }

                    //进行一次删除提示，保证没有误操作
                    boolean answer = showexitbox("警告", "请确认您是否真的要删除该账号？");

                    //如果存在该账号，进行删除操作
                    if (answer){
                        sqlString = "DELETE FROM `管理员` WHERE `工号` ='"+no+"'";
                        int i = getStmt().executeUpdate(sqlString);

                        //删除语句结果返回受影响的行数，通过受影响的行数来判断是否删除成功
                        if (i == 1)
                            showalertbox("提示","删除成功！");
                        else
                            showalertbox("提示","删除失败！请重试！");
                    }
                }
                else
                    showalertbox("提示", "查无此人，请检查您输入的工号！");

            } else {
                //进行读者账号删除操作
                //首先判断是否存在该账号
                setRs(null);
                String sqlString = new String();
                sqlString = "SELECT * FROM `读者` WHERE `读者证编号` = '" + no + "'";
                setRs(getStmt().executeQuery(sqlString));

                //如果存在该账号，进行删除操作
                if (getRs().next()){
                    //进行一次删除提示，保证没有误操作
                    boolean answer = showexitbox("警告", "请确认您是否真的要删除该账号？");
                    if (answer){
                        sqlString = "DELETE FROM `读者` WHERE `读者证编号` = '"+ no +"'";
                        int i = getStmt().executeUpdate(sqlString);
                        if (i == 1)
                            showalertbox("提示","删除成功！");
                        else
                            showalertbox("提示","删除失败！请重试！");
                    }
                }
                else
                    showalertbox("提示","查无此人，请检查您输入的读者证编号！");
            }
        } else {
            showalertbox("警告", "数据库连接失败，请稍后重试！");
        }

    }

}
