package com.zysns.account;

import com.zysns.main.Manager;
import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static com.zysns.account.AccountJdbc.manager_create;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.other.SHA.getResult;

public class AccountG extends Window implements Initializable {

    //退出按钮
    @FXML
    private MenuItem exit_button;

    //账号删除页面跳转按钮
    @FXML
    private Button delete_button;

    //管理员等级选择
    @FXML
    private ComboBox<String> message_grade_combobox;

    //所属领导
    @FXML
    private TextField leader_text;

    //退出登录按钮
    @FXML
    private Button exit_login_button;

    //所属部门输入
    @FXML
    private TextField dept_text;

    //图书管理跳转按钮
    @FXML
    private Button book_button;

    //姓名输入框
    @FXML
    private TextField name_text;

    //性别选择框
    @FXML
    private ComboBox<String> sex_combobox;

    //图书查询按钮
    @FXML
    private Button select_button;

    //关于按钮
    @FXML
    private MenuItem about_button;

    //ID输入框
    @FXML
    private TextField ID_texxt;

    //管理员账号创建按钮
    @FXML
    private Button create_button;

    //生日选择框
    @FXML
    private DatePicker birthday_date;

    //密码输入框
    @FXML
    private TextField password_text;

    //读者账号创建跳转按钮
    @FXML
    private Button readerID_create_button;

    //用户名标签
    @FXML
    private Label user;

    //图书借还跳转按钮
    @FXML
    private Button borroe_button;

    @FXML
        //显示图书借还界面
    void borrow() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
    }

    @FXML
        //显示查询界面
    void select() throws IOException {
        Parent select = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(select, 1280, 800));
    }

    @FXML
        //显示图书管理界面
    void book() throws IOException {
        Parent book = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(book, 1280, 800));
    }

    @FXML
        //点击退出后返回登录界面
    void exit_login() throws IOException {
        boolean answer = showexitbox("提示", "您是否真的要退出当前登录的账号？");
        if (answer){
            //将当前用户信息清除
            setW_manager(null);
            setW_reader(null);
            //跳转到登录界面
            Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
            getWindow().setScene(new Scene(root, 1280, 800));
        }
    }

    @FXML
        //点击exit后退出系统
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    @FXML
        //显示About信息
    void about() {
        showabout();
    }

    @FXML
    void readerID_create() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountReader.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    void account_delete() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountDelete.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    void create() throws Exception {
        Manager manager = new Manager();
        //获取输入的工号
        manager.setMno(ID_texxt.getText());
        //获取输入的密码，并检验是否小于6位，如小于，要求注册者重新输入
        String passward = password_text.getText();
        if (passward.length() < 6) {
            showalertbox("警告", "输入的密码小于6位\n请重新输入");
            return;
        }
        //对设置的密码进行加密，保证在服务器端不会存储用户的明文密码，已保证安全，采用SHA加密的方法
        manager.setMpassword(getResult(passward));
        //获取输入的姓名，并对输入的姓名进行检验，如果存在明显错误，则要求注册者重新输入
        String name = name_text.getText();
        if ((name.length() < 2) || (name.length() > 6)) {
            showalertbox("警告", "您输入的姓名过长或过短\n请检查您的输入或联系管理员");
            return;
        }
        manager.setMname(name);
        //获取性别
        manager.setMsex(sex_combobox.getValue());
        //获取生日，对输入的生日进行检验，如果存在明显错误，则要求重新输入
        LocalDate date = birthday_date.getValue();
        int days = Period.between(date, LocalDate.now()).getYears();
        if ((days <= 17) || days >= 66) {
            showalertbox("警告", "您输入的年龄有误\n请检查您的输入");
            return;
        }
        manager.setMdirthday(date);
        //获取管理员等级
        if (Integer.parseInt(message_grade_combobox.getValue()) <= Integer.parseInt(getW_manager().getMlevel())){
            showalertbox("警告", "对不起，您创建的账号高于您的权限，创建失败！");
            return;
        }
        manager.setMlevel(message_grade_combobox.getValue());
        //设置管理员所在部门
        manager.setMdept(dept_text.getText());
        //获取管理员所属领导（即创建该账号的账号）
        manager.setMlead(getW_manager().getMno());
        //在一切检查完毕后，调用对数据库的操作，将读者的注册信息添加到数据库中
        manager_create(manager);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //界面初始化，此处主要用于初始化用户名位置信息
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
        leader_text.setText(getW_manager().getMno());
        sex_combobox.getItems().addAll("男", "女");
        message_grade_combobox.getItems().addAll("1", "2", "3", "4", "5");
    }
}

