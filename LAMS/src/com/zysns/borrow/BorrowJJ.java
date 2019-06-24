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

import static com.zysns.other.About.showabout;

public class BorrowJJ extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private TextField bookno;

    @FXML
    private Button message_button;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private TextField readerID;

    @FXML
    private Button select_button;

    @FXML
    private MenuItem about_button;

    @FXML
    private Button borrow_book_button;

    @FXML
    private Button still_book_button;

    @FXML
    private Label user;

    @FXML
    private Button urge_book_button;

    @FXML
    void book() throws IOException {
        Parent book = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(book, 1280, 800));
    }

    @FXML
    void select() throws IOException {
        Parent select = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(select, 1280, 800));
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
    void urge_book() throws IOException {
        Parent urge = FXMLLoader.load(getClass().getResource("../borrow/BorrowJch.fxml"));
        getWindow().setScene(new Scene(urge, 1280, 800));
    }

    @FXML
    void still_book() throws IOException {
        Parent still = FXMLLoader.load(getClass().getResource("../borrow/BorrowJh.fxml"));
        getWindow().setScene(new Scene(still, 1280, 800));
    }

    @FXML
    void message() throws IOException {
        Parent message = FXMLLoader.load(getClass().getResource("../borrow/BorrowJc.fxml"));
        getWindow().setScene(new Scene(message, 1280, 800));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

