package com.zysns.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.zysns.main.Window;
import static com.zysns.other.About.showabout;

public class Login extends Window implements Initializable {

    @FXML
    private MenuItem about_menu;

    @FXML
    private Button loginbuttion;

    @FXML
    private ComboBox<String> select_user_or_admin;

    @FXML
    private PasswordField password_text;

    @FXML
    private MenuItem exit_menu;

    @FXML
    private TextField user_text;

    @FXML
    private Button registerbutton;

    @FXML
    void register() throws IOException {
        Parent regiser = FXMLLoader.load(getClass().getResource("../login/Register.fxml"));
        getWindow().setScene(new Scene(regiser, 1280, 800));
    }

    @FXML
    void login() throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../login/LoginView.fxml"));
        getWindow().setScene(new Scene(view, 1280, 800));
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
        select_user_or_admin.getItems().addAll("管理员", "普通用户");
    }
}

