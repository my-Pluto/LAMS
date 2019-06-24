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

public class InventoryNew extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private TextField bbookno;

    @FXML
    private DatePicker bdate;

    @FXML
    private TextField bpublish;

    @FXML
    private Button exit_login_button;

    @FXML
    private TextField baoutor;

    @FXML
    private Button update_book_button;

    @FXML
    private Button select_button;

    @FXML
    private TextField bisbn;

    @FXML
    private MenuItem about_button;

    @FXML
    private TextField bname;

    @FXML
    private TextField bquantity;

    @FXML
    private Button borrow_button;

    @FXML
    private Button new_button;

    @FXML
    private ComboBox<?> bfamily;

    @FXML
    void new_book_jdbc() {

    }

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
    void update_book() throws IOException {
        Parent update_book = FXMLLoader.load(getClass().getResource("../inventory/InventoryUpdate.fxml"));
        getWindow().setScene(new Scene(update_book, 1280, 800));
    }

    @FXML
    void exit() {
        System.exit(0);
    }

    @FXML
    void about() {
        showabout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
