package com.zysns.login;

import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;

public class Register extends Window implements Initializable {

    @FXML
    private MenuItem exit_button;

    @FXML
    private MenuItem about_button;

    @FXML
    private TextField age_text;

    @FXML
    private ChoiceBox<?> reader_grade;

    @FXML
    private DatePicker birthday_date;

    @FXML
    private TextField name_text;

    @FXML
    private Button register_button;

    @FXML
    private TextField password_text;

    @FXML
    private TextField card_id;

    @FXML
    private Button back_button;

    @FXML
    void register() {

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
    void back() throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
        getWindow().setScene(new Scene(login, 1280, 800));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
