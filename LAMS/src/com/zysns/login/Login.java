/**
 * 登录界面控制类
 * 进行登录以及注册界面跳转
 */
package com.zysns.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.zysns.main.Window;

import static com.zysns.login.LoginJdbc.*;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.other.SHA.getResult;

public class Login extends Window implements Initializable {

    //关于按钮
    @FXML
    private MenuItem about_menu;

    //登录按钮
    @FXML
    private Button loginbuttion;

    //登录类型选择
    @FXML
    private ComboBox<String> select_user_or_admin;

    //密码输入框
    @FXML
    private PasswordField password_text;

    //退出按钮
    @FXML
    private MenuItem exit_menu;

    //用户名输入框
    @FXML
    private TextField user_text;

    //注册按钮
    @FXML
    private Button registerbutton;

    //跳转到注册界面
    @FXML
    void register() throws IOException {
        Parent regiser = FXMLLoader.load(getClass().getResource("../login/Register.fxml"));
        getWindow().setScene(new Scene(regiser, 1280, 800));
    }

    //进行登录操作，如果成功，跳转到登录后的界面，否则要求登录者重试
    @FXML
    void login() throws Exception {

        //获取输入的登录信息
        String user = user_text.getText();
        String family = select_user_or_admin.getValue();
        //此处需要对密码进行和注册时相同的加密处理，以便于对密码进行和比对，同时保证密码在传输过程中的安全性
        String password = getResult(password_text.getText());
        //先验证是否将所有的信息输入
        if (user == null || family == null || password == null) {
            showalertbox("警告", "登录失败！请检查您输入的账号或密码！");
            return;
        }

        //进行登录操作，和数据库中保存的账号、密码进行比对，确认是否登录成功
        boolean answer = Login(user,password,family);

        //如果登录成功，则跳转的登录后的主界面
        if (answer) {
            //设置当前登录用户的信息
            if (family.equals("管理员")) {
                setW_manager(return_manager(user));
            }
            else{
                setW_reader(return_reader(user));
            }

            //界面跳转
            Parent view = FXMLLoader.load(getClass().getResource("../login/LoginView.fxml"));
            getWindow().setScene(new Scene(view, 1280, 800));
        }
    }

    //退出
    @FXML
    void exit(){
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    //显示关于界面
    @FXML
    void about() {
        showabout();
    }

    //界面初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        select_user_or_admin.getItems().addAll("管理员", "普通用户");
    }
}

