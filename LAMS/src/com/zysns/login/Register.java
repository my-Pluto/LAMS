package com.zysns.login;

import com.zysns.main.Book;
import com.zysns.main.Reader;
import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static com.zysns.login.LoginJdbc.registerjdbc;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.other.SHA.getResult;

public class Register extends Window implements Initializable {

    //系统推退出
    @FXML
    private MenuItem exit_button;

    //系统关于
    @FXML
    private MenuItem about_button;

    //读者年龄
    @FXML
    private TextField age_text;

    //读者等级下列菜单
    @FXML
    private ChoiceBox<String> reader_grade;

    //读者性别
    @FXML
    private ChoiceBox<String> sex;

    //读者生日选择框
    @FXML
    private DatePicker birthday_date;

    //读者姓名
    @FXML
    private TextField name_text;

    //注册按钮
    @FXML
    private Button register_button;

    //密码输入框
    @FXML
    private TextField password_text;

    //读者证号输入框
    @FXML
    private TextField card_id;

    //返回按钮
    @FXML
    private Button back_button;


    //注册按钮事件
    @FXML
    void register() throws Exception {
        Reader reader = new Reader();
        //获取输入的读者证号
        reader.setRno(card_id.getText());
        //获取读者输入的密码，并检验是否小于6位，如小于，要求注册者重新输入
        String passward = password_text.getText();
        if (passward.length() < 6) {
            showalertbox("警告", "输入的密码小于6位\n请重新输入");
            return;
        }
        //对读者设置的密码进行加密，保证在服务器端不会存储用户的明文密码，已保证安全，采用SHA加密的方法
        reader.setRpassword(getResult(passward));
        //获取读者输入的姓名，并对读者输入的姓名进行检验，如果存在明显错误，则要求注册者重新输入
        String name = name_text.getText();
        if ((name.length() < 2) || (name.length() > 6)) {
            showalertbox("警告", "您输入的姓名过长或过短\n请检查您的输入或联系管理员");
            return;
        }
        reader.setRname(name);
        //获取读者的性别
        reader.setRsex(sex.getValue());
        //获取读者的生日，对读者输入的生日进行检验，如果存在明显错误，则要求读者重新输入
        LocalDate date = birthday_date.getValue();
        int days = Period.between(date, LocalDate.now()).getDays();
        if ((days <= 0) || days >= 36500) {
            showalertbox("警告", "您输入的年龄有误\n请检查您的输入或联系管理员");
            return;
        }
        reader.setRbrithday(date);
        //获取读者注册时间，即当前时间
        reader.setRcreate(LocalDate.now());
        //获取读者等级
        reader.setRpower(reader_grade.getValue());
        //在一切检查完毕后，调用对数据库的操作，将读者的注册信息添加到数据库中
        boolean answer = registerjdbc(reader);
        //待注册完成后，如果成功，则跳转到登录页面，要求读者登录
        if (answer)
            back();
            }

    //退出
    @FXML
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    //系统关于
    @FXML
    void about() {
        showabout();
    }

    //返回登录界面
    @FXML
    void back() throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
        getWindow().setScene(new Scene(login, 1280, 800));
    }

    //界面初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sex.getItems().addAll("男", "女");
        reader_grade.getItems().addAll("儿童读者", "成人读者");
    }
}
