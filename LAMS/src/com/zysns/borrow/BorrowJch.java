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

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private MenuItem about;

    @FXML
    private TextField readerID;

    @FXML
    private Button message;

    @FXML
    private Button select_button;

    @FXML
    private Button select_urge_button;

    @FXML
    private Button borrow_book_button;

    @FXML
    private TableView<Borrow_Book> Table_view;

    @FXML
    private Button still_book_button;

    @FXML
    private Label user;

    @FXML
    private TableColumn<Borrow_Book, LocalDate> borrow_date;

    @FXML
    private TableColumn<Borrow_Book, String> reader_ID;

    @FXML
    private TableColumn<Borrow_Book, String> book_no;

    @FXML
    private TableColumn<Borrow_Book, LocalDate> still_date;

    @FXML
    private TableColumn<Borrow_Book, String> book_name;

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
    void select() throws IOException {
        Parent select = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(select, 1280, 800));
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
    void borrow_book() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/BorrowJJ.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
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

    @FXML
    void select_urge() throws Exception {
        borrow_date.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
        reader_ID.setCellValueFactory(new PropertyValueFactory<>("Rno"));
        book_no.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        still_date.setCellValueFactory(new PropertyValueFactory<>("still_date"));
        if (getW_manager() == null) {
            borrowselect(getW_reader().getRno());
        }
        else {
            borrowselect(readerID.getText());
        }
        Table_view.setItems(geturge());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

