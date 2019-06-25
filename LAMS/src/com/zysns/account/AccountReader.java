package com.zysns.account;

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
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;

public class AccountReader extends Window implements Initializable {

    @FXML
    private MenuItem exit_button;

    @FXML
    private TextField name_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button message_create_button;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private ComboBox<?> sex_combobox;

    @FXML
    private Button select_button;

    @FXML
    private Button creage_button;

    @FXML
    private TextField ID_text;

    @FXML
    private MenuItem about_button;

    @FXML
    private TextField age_text;

    @FXML
    private DatePicker birthday_date;

    @FXML
    private Button borrow_button;

    @FXML
    private Label user;

    @FXML
    private ComboBox<?> grade_combobox;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

