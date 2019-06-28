/**
 * 账号管理主界面控制类
 * 主要用于各个界面的跳转
 */
package com.zysns.account;

import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class Account extends Window implements Initializable {

    //退出按钮
    @FXML
    private MenuItem exit_button;

    //关于按钮
    @FXML
    private MenuItem about_button;

    //删除账号跳转按钮
    @FXML
    private Button delete_button;

    //管理员创建跳转按钮
    @FXML
    private Button message_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;

    //创建读者证跳转按钮
    @FXML
    private Button readerID_button;

    //图书管理跳转按钮
    @FXML
    private Button book_button;

    //图书借还管理按钮
    @FXML
    private Button borrow_button;

    //图书查询按钮
    @FXML
    private Button select_button;

    //用户名标签
    @FXML
    private Label user;

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
        //鉴权
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
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
    //跳转到读者证创建界面
    void readerID_create() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountReader.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    //跳转到管理员创建界面
    void message_create() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountG.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    //跳转到账户信息删除界面
    void account_delete() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountDelete.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //界面初始化，此处主要用于初始化用户名位置
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
    }
}

