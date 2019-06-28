/**
 * 还书界面控制类
 * 对图书归还进行操作
 * 主要进行界面跳转
 * 以及各个事件的响应
 */

package com.zysns.borrow;

import com.zysns.main.Borrow_Book;
import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.zysns.borrow.BorrowJdbc.*;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class BorrowJh extends Window implements Initializable {

    //账号管理跳转按钮
    @FXML
    private Button account_button;

    //退出按钮
    @FXML
    private MenuItem exit_button;

    //借阅信息查询按钮
    @FXML
    private Button message_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;


    @FXML
    private Button book_button;

    @FXML
    private TextField readerID;

    @FXML
    private Button huan_button;

    @FXML
    private Button select_button;

    @FXML
    private MenuItem about_button;

    @FXML
    private Button select_h_button;

    @FXML
    private Button borrow_book_button;

    @FXML
    private TableView<Borrow_Book> tableview;

    @FXML
    private Label user;

    @FXML
    private Button urge_book_button;

    @FXML
    private TableColumn<Borrow_Book, String> book_name;

    @FXML
    private TableColumn<Borrow_Book, String> book_no;

    @FXML
    private TableColumn<Borrow_Book, LocalDate> borroe_date;

    @FXML
    private TableColumn<Borrow_Book, LocalDate> still_date;

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

    //跳转到图书借阅界面
    @FXML
    void borrow_book() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/BorrowJJ.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
    }

    //跳转到借阅信息界面
    @FXML
    void message() throws IOException {
        Parent message = FXMLLoader.load(getClass().getResource("../borrow/BorrowJc.fxml"));
        getWindow().setScene(new Scene(message, 1280, 800));
    }

    //还书事件响应
    @FXML
    void still_book() throws Exception {
        //对显示的信息进行选择
        Borrow_Book borrow_book = tableview.getSelectionModel().getSelectedItem();

        //进行还书操作
        book_still(borrow_book);

        //刷新界面
        select_h();
    }

    //借书信息查询
    @FXML
    void select_h() throws Exception {
        //绑定属性列
        borroe_date.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
        book_no.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        still_date.setCellValueFactory(new PropertyValueFactory<>("still_date"));

        //信息查询
        if (getW_manager() == null) {
            borrowselect(getW_reader().getRno());
        }
        else {
            borrowselect(readerID.getText());
        }

        //显示查询结果
        tableview.setItems(getstill());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化user标签
        //鉴权，管理员账号允许输入各个账号信息查询，普通用户输入框不可以，只能查询本人账号信息
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
