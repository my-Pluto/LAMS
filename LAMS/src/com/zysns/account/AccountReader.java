package com.zysns.account;

import com.zysns.main.Reader;
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

import static com.zysns.account.AccountJdbc.reader_create;
import static com.zysns.login.LoginJdbc.registerjdbc;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.SHA.getResult;

public class AccountReader extends Window implements Initializable {

    //退出按钮
    @FXML
    private MenuItem exit_button;

    //姓名输入框
    @FXML
    private TextField name_button;

    //删除账号跳转按钮
    @FXML
    private Button delete_button;

    //管理员账号创建跳转按钮
    @FXML
    private Button message_create_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;

    //图书管理跳转按钮
    @FXML
    private Button book_button;

    //性别选择
    @FXML
    private ComboBox<String> sex_combobox;

    //图书查询按钮
    @FXML
    private Button select_button;

    //账号创建按钮
    @FXML
    private Button creage_button;

    //读者证号输入框
    @FXML
    private TextField ID_text;

    //关于按钮
    @FXML
    private MenuItem about_button;

    //年龄输入框
    @FXML
    private TextField age_text;

    //生日输入
    @FXML
    private DatePicker birthday_date;

    //图书借还按钮
    @FXML
    private Button borrow_button;

    //用户名标签
    @FXML
    private Label user;

    //读者借阅权限选择
    @FXML
    private ComboBox<String> grade_combobox;

    //密码输入框
    @FXML
    private TextField password_text;

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
        Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
        //点击exit后退出系统
    void exit() {
        System.exit(0);
    }

    @FXML
        //显示About信息
    void about() {
        showabout();
    }

    @FXML
    void message_create() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountG.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    void account_delete() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountDelete.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    void create_reader() throws Exception {
        Reader reader = new Reader();
        //获取输入的读者证号
        String ID = ID_text.getText();
        if (ID == null || ID.equals("")){
            showalertbox("警告", "您输入的信息不全");
            return;
        }
        reader.setRno(ID);
        //获取读者输入的密码，并检验是否小于6位，如小于，要求注册者重新输入
        String passward = password_text.getText();
        if (passward.length() < 6) {
            showalertbox("警告", "输入的密码小于6位\n请重新输入");
            return;
        }
        //对读者设置的密码进行加密，保证在服务器端不会存储用户的明文密码，已保证安全，采用SHA加密的方法
        reader.setRpassword(getResult(passward));
        //获取读者输入的姓名，并对读者输入的姓名进行检验，如果存在明显错误，则要求注册者重新输入
        String name = name_button.getText();
        if ((name.length() < 2) || (name.length() > 6)) {
            showalertbox("警告", "您输入的姓名过长或过短\n请检查您的输入或联系管理员");
            return;
        }
        reader.setRname(name);
        //获取读者的性别
        String sex = sex_combobox.getValue();
        if (sex == null || sex.equals("")){
            showalertbox("警告", "您输入的信息不全");
            return;
        }
        reader.setRsex(sex);
        //获取读者的生日，对读者输入的生日进行检验，如果存在明显错误，则要求读者重新输入
        LocalDate date = birthday_date.getValue();
        if (date == null){
            showalertbox("警告", "您输入的信息不全");
            return;
        }

        String age = age_text.getText();
        if (age == null ||age.equals("")){
            showalertbox("警告", "您输入的信息不全");
            return;
        }
        int years = Period.between(date, LocalDate.now()).getYears();
        if ((years <= 0) || years >= 100 || years != Integer.parseInt(age_text.getText())) {
            showalertbox("警告", "您输入的年龄或生日有误\n请检查您的输入或联系管理员");
            return;
        }
        reader.setRbrithday(date);
        //获取读者注册时间，即当前时间
        reader.setRcreate(LocalDate.now());
        //获取读者等级
        String grade = grade_combobox.getValue();
        if (grade == null || grade.equals("")){
            showalertbox("警告", "您输入的信息不全");
            return;
        }
        reader.setRpower(grade);
        //在一切检查完毕后，调用对数据库的操作，将读者的注册信息添加到数据库中
        reader_create(reader);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sex_combobox.getItems().addAll("男", "女");
        grade_combobox.getItems().addAll("儿童读者", "成人读者");
        //界面初始化，此处主要用于初始化用户名位置信息
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
    }
}

