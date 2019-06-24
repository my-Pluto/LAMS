package com.zysns.select;

import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;

public class SelectBook extends Window implements Initializable {

    @FXML
    private ComboBox<?> combobox_select;

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private TreeView<?> treeview_book;

    @FXML
    private TextField select_text;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private TableView<?> table_book;

    @FXML
    private Button reader_message_button;

    @FXML
    private MenuItem about_button;

    @FXML
    private Button high_button;

    @FXML
    private Button borrow_button;

    @FXML
    private Button select_book_button;

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
    void high() throws IOException {
        Parent high = FXMLLoader.load(getClass().getResource("../select/SelectR.fxml"));
        getWindow().setScene(new Scene(high, 1280, 800));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

