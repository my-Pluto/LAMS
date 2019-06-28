/**
 *借书界面控制类
 * 进行借书操作
 * 主要用于页面跳转
 * 以及各个事件的响应
 */

package com.zysns.borrow;

import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zysns.borrow.BorrowJdbc.borrow;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class BorrowJJ extends Window implements Initializable {

    //跳转到账号管理界面按钮
    @FXML
    private Button account_button;

    //退出系统按钮
    @FXML
    private MenuItem exit_button;

    //图书编号输入框
    @FXML
    private TextField bookno;

    //借阅信息查询按钮
    @FXML
    private Button message_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;
    //跳转到图书管理界面按钮
    @FXML
    private Button book_button;

    //读者证号输入框
    @FXML
    private TextField readerID;

    //跳转到图书查询界面按钮
    @FXML
    private Button select_button;

    //关于按钮
    @FXML
    private MenuItem about_button;

    //图书借阅按钮
    @FXML
    private Button borrow_book_button;

    //跳转到图书归还界面按钮
    @FXML
    private Button still_book_button;

    //用户名标签
    @FXML
    private Label user;

    //跳转到图书催还界面按钮
    @FXML
    private Button urge_book_button;

    //跳转到图书管理界面
    @FXML
    void book() throws IOException {
        //鉴权
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent book = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(book, 1280, 800));
    }

    //跳转到图书查询界面
    @FXML
    void select() throws IOException {
        Parent select = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(select, 1280, 800));
    }

    //跳转到账号管理界面
    @FXML
    void account() throws IOException {
        //鉴权
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent account = FXMLLoader.load(getClass().getResource("../account/account.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    //退出登录
    @FXML
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

    //退出系统
    @FXML
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    //关于界面
    @FXML
    void about() {
        showabout();
    }

    //跳转到图书催还界面
    @FXML
    void urge_book() throws IOException {
        Parent urge = FXMLLoader.load(getClass().getResource("../borrow/BorrowJch.fxml"));
        getWindow().setScene(new Scene(urge, 1280, 800));
    }

    //跳转到还书界面
    @FXML
    void still_book() throws IOException {
        Parent still = FXMLLoader.load(getClass().getResource("../borrow/BorrowJh.fxml"));
        getWindow().setScene(new Scene(still, 1280, 800));
    }

    //跳转到借阅信息界面
    @FXML
    void message() throws IOException {
        Parent message = FXMLLoader.load(getClass().getResource("../borrow/BorrowJc.fxml"));
        getWindow().setScene(new Scene(message, 1280, 800));
    }

    //图书借阅事件响应
    @FXML
    void borrow_book() throws Exception {
        //获取输入的读者证号和图书编号
        String Bno = bookno.getText();
        String Rno = readerID.getText();

        //进行输入结果是否为空检验
        if (Bno == null || Bno.equals("") || Rno == null || Rno.equals("")){
            showalertbox("警告", "您输入的信息不全！\n请检查输入！");
        }

        //进行图书借阅操作
        borrow(Rno,Bno);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化user标签
        //鉴权，管理员账号允许输入各个账号信息，普通用户输入框不可以，只能使用本人账号信息
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
            readerID.setText(getW_reader().getRno());
            readerID.setDisable(true);
        }
    }
}

