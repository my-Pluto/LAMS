package com.zysns.select;


import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;

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
    private TableView<?> table_view;

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
    void book() throws IOException {
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
        Parent account = FXMLLoader.load(getClass().getResource("../account/account.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    @FXML
    void exit_login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    @FXML
    void exit() {
        System.exit(0);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
