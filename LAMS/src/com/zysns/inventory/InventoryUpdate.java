package com.zysns.inventory;

import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;

public class InventoryUpdate extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private Button select_boob_button;

    @FXML
    private TextField book_isbn;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button update_book_button;

    @FXML
    private TextField book_name;

    @FXML
    private DatePicker book_date;

    @FXML
    private ComboBox<?> book_family;

    @FXML
    private TextField book_no;

    @FXML
    private Button select_button;

    @FXML
    private TextField book_publish_button;

    @FXML
    private TextField bookID;

    @FXML
    private MenuItem About;

    @FXML
    private MenuItem exit;

    @FXML
    private Button new_book_button;

    @FXML
    private TextField book_quantity;

    @FXML
    private Button borrow_button;

    @FXML
    private TextField book_autor;

    @FXML
    private Label user;


    @FXML
    void borrow() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
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
    void new_book() throws IOException {
        Parent new_book = FXMLLoader.load(getClass().getResource("InventoryNew.fxml"));
        getWindow().setScene(new Scene(new_book, 1280, 800));
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
    void update_book() {

    }

    @FXML
    void select_book() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

