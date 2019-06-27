package com.zysns.select;

import com.zysns.main.Reader;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.select.SelectJdbc.select_reader_message;

public class SelectPerson extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private Label sextext;

    @FXML
    private Button book_select_button;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private TextField readerID;

    @FXML
    private Label IDtext;

    @FXML
    private Label gradetext;

    @FXML
    private MenuItem about_button;

    @FXML
    private Label datetext;

    @FXML
    private Button high_button;

    @FXML
    private Label dateIDtext;

    @FXML
    private Button select_reader_button;

    @FXML
    private Button borrow_button;

    @FXML
    private Label nametext;

    @FXML
    private Label user;

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
    void book_select() throws IOException {
        Parent book_message = FXMLLoader.load(getClass().getResource("../select/SelectBook.fxml"));
        getWindow().setScene(new Scene(book_message, 1280, 800));
    }

    @FXML
    void high() throws IOException {
        Parent high = FXMLLoader.load(getClass().getResource("../select/SelectR.fxml"));
        getWindow().setScene(new Scene(high, 1280, 800));
    }

    @FXML
    void select_reader() throws SQLException {
        String reader = readerID.getText();
        if (reader == null || reader.equals("")){
            showalertbox("警告", "您输入的读者证号有误！");
            return;
        }

        Reader read = select_reader_message(reader);
        if (read == null){
            return;
        }
        sextext.setText(read.getRsex());
        IDtext.setText(read.getRno());
        gradetext.setText(read.getRpower());
        datetext.setText(read.getRbrithday().toString());
        dateIDtext.setText(read.getRcreate().toString());
        nametext.setText(read.getRname());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
            readerID.setDisable(true);
            readerID.setText(getW_reader().getRno());
        }
    }
}

