/**
 *借阅信息查询控制类
 * 进行借阅信息的查询
 * 主要用于各个界面的跳转
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
import static com.zysns.borrow.BorrowJdbc.getborrow;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class BorrowJc extends Window implements Initializable {

    //账户管理跳转按钮
    @FXML
    private Button account_button;

    //退出按钮
    @FXML
    private MenuItem exit_button;

    //借阅信息查询按钮
    @FXML
    private Button select_c_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;

    //图书管理按钮
    @FXML
    private Button book_button;

    //关于按钮
    @FXML
    private MenuItem about;

    //读者证号输入框
    @FXML
    private TextField readerID;

    //查询系统跳转按钮
    @FXML
    private Button select_button;

    //借书界面跳转按钮
    @FXML
    private Button borrow_book_button;

    //借阅信息显示框
    @FXML
    private TableView<Borrow_Book> tableview;

    //还书界面跳转按钮
    @FXML
    private Button still_book_button;

    //用户名标签
    @FXML
    private Label user;

    //催还信息跳转按钮
    @FXML
    private Button urge_button;

    //借阅信息显示各个属性列
    //借阅时间
    @FXML
    private TableColumn<Borrow_Book, LocalDate> borrow_date;

    //图书编号
    @FXML
    private TableColumn<Borrow_Book, String> book_ID;

    //图书名称
    @FXML
    private TableColumn<Borrow_Book, String> book_name;

    //是否归还图书
    @FXML
    private TableColumn<Borrow_Book, String> yes_or_no;

    //归还时间，如果为归还，则为最晚归还时间
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

    //退出程序
    @FXML
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        };
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

    //跳转到图书归还界面
    @FXML
    void still_book() throws IOException {
        Parent still = FXMLLoader.load(getClass().getResource("../borrow/BorrowJh.fxml"));
        getWindow().setScene(new Scene(still, 1280, 800));
    }

    //借阅信息查询事件响应
    @FXML
    void select_c() throws Exception {
        //将各个属性列和类内的各个属性进行绑定
        borrow_date.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
        book_ID.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        yes_or_no.setCellValueFactory(new PropertyValueFactory<>("yes_or_no"));
        still_date.setCellValueFactory(new PropertyValueFactory<>("still_date"));

        //进行查询操作
        if (getW_manager() == null) {
            borrowselect(getW_reader().getRno());
        }
        else {
            String id = readerID.getText();
            if (id == null || id.equals("")){
                showalertbox("警告", "您输入的信息不全！请检查后重试！");
                return;
            }
            borrowselect(id);
        }

        //将查询操作结果显示在TableView中
        tableview.setItems(getborrow());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化用户名标签
        //鉴权，如果是普通账号，则仅允许查询本人借阅信息，即读者证号输入框不可用，如果为管理员账号，则可随意输入
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
