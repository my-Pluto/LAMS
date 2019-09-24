/**
 * 催还信息控制类
 * 查询账号是否存在超期图书
 * 主要用于各个界面之间的跳转
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

import static com.zysns.borrow.BorrowJdbc.borrowselect;
import static com.zysns.borrow.BorrowJdbc.geturge;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class BorrowJch extends Window implements Initializable {

    //账号管理界面跳转按钮
    @FXML
    private Button account_button;

    //退出系统按钮
    @FXML
    private MenuItem exit_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;

    //图书管理跳转按钮
    @FXML
    private Button book_button;

    //关于界面
    @FXML
    private MenuItem about;

    //读者证号输入框
    @FXML
    private TextField readerID;

    //借阅信息查询界面
    @FXML
    private Button message;

    //图书查询跳转按钮
    @FXML
    private Button select_button;

    //催还信息查询按钮
    @FXML
    private Button select_urge_button;

    //图书借阅跳转按钮
    @FXML
    private Button borrow_book_button;

    //催还信息显示框
    @FXML
    private TableView<Borrow_Book> Table_view;

    //还书界面跳转按钮
    @FXML
    private Button still_book_button;

    //用户名标签
    @FXML
    private Label user;

    //显示框属性列
    //借阅时间
    @FXML
    private TableColumn<Borrow_Book, LocalDate> borrow_date;

    //读者证号
    @FXML
    private TableColumn<Borrow_Book, String> reader_ID;

    //图书编号
    @FXML
    private TableColumn<Borrow_Book, String> book_no;

    //还书时间
    @FXML
    private TableColumn<Borrow_Book, LocalDate> still_date;

    //图书名称
    @FXML
    private TableColumn<Borrow_Book, String> book_name;

    //跳转到图书管理
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

    //退出程序
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

    //跳转到图书借阅
    @FXML
    void borrow_book() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/BorrowJJ.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
    }

    //跳转到图书归还
    @FXML
    void still_book() throws IOException {
        Parent still = FXMLLoader.load(getClass().getResource("../borrow/BorrowJh.fxml"));
        getWindow().setScene(new Scene(still, 1280, 800));
    }

    //跳转到借阅信息查询
    @FXML
    void message() throws IOException {
        Parent message = FXMLLoader.load(getClass().getResource("../borrow/BorrowJc.fxml"));
        getWindow().setScene(new Scene(message, 1280, 800));
    }

    //催还信息查询事件响应
    @FXML
    void select_urge() throws Exception {
        boolean answer = false;
        //绑定属性列
        borrow_date.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
        reader_ID.setCellValueFactory(new PropertyValueFactory<>("Rno"));
        book_no.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        still_date.setCellValueFactory(new PropertyValueFactory<>("still_date"));

        //进行查询操作，调用jdbc中的方法，需要区分账户属性
        if (getW_manager() == null) {
            answer = borrowselect(getW_reader().getRno());
        }
        else {
            answer = borrowselect(readerID.getText());
        }

        //查询结果显示
        if (answer)
        Table_view.setItems(geturge());
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

