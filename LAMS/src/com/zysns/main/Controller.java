package com.zysns.main;

import com.zysns.other.About;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Controller extends Window{

    //显示图书借还界面
    public void cborrow() throws IOException {
        Parent account = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    public void cselect() throws IOException {
        Parent account = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    void cbook() throws IOException {
        Parent account = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    void caccount() throws IOException {
        Parent account = FXMLLoader.load(getClass().getResource("../account/account.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    void cexit_login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }


}
