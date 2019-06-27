package com.zysns.select;


import com.zysns.main.High_Book;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.select.SelectJdbc.gethigh_book;
import static com.zysns.select.SelectJdbc.high_book;

public class SelectR extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private Button reader_message_button;

    @FXML
    private MenuItem about_button;

    @FXML
    private TableView<High_Book> table_view;

    @FXML
    private Button book_select_button;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private Button borrow_button;

    @FXML
    private Label user;

    @FXML
    private TableColumn<High_Book, String> book_number;

    @FXML
    private TableColumn<High_Book, String> book_author;

    @FXML
    private TableColumn<High_Book, String> book_name;

    @FXML
    private TableColumn<High_Book, String> book_family;

    @FXML
    private TableColumn<High_Book, String> book_bno;

    @FXML
    private TableColumn<High_Book, String> book_no;

    @FXML
    private TableColumn<High_Book, Integer> book_quantity;

    @FXML
    void book() throws IOException {
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent book = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(book, 1280, 800));
    }

    @FXML
    void borrow_book() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
    }

    @FXML
    void account() throws IOException {
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent account = FXMLLoader.load(getClass().getResource("../account/account.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

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

    @FXML
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    @FXML
    void about() {
        showabout();
    }

    @FXML
    void reader_message() throws IOException {
        Parent reader_message = FXMLLoader.load(getClass().getResource("../select/SelectPerson.fxml"));
        getWindow().setScene(new Scene(reader_message, 1280, 800));
    }

    @FXML
    void book_select() throws IOException {
        Parent book_message = FXMLLoader.load(getClass().getResource("../select/SelectBook.fxml"));
        getWindow().setScene(new Scene(book_message, 1280, 800));
    }

    void set_combobox(){
        book_number.setCellValueFactory(new PropertyValueFactory<>("book_number"));
        book_author.setCellValueFactory(new PropertyValueFactory<>("book_author"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        book_family.setCellValueFactory(new PropertyValueFactory<>("book_family"));
        book_bno.setCellValueFactory(new PropertyValueFactory<>("book_bno"));
        book_no.setCellValueFactory(new PropertyValueFactory<>("book_no"));
        book_quantity.setCellValueFactory(new PropertyValueFactory<>("book_quantity"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
        set_combobox();
        try {
            high_book();
            table_view.setItems(gethigh_book());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
