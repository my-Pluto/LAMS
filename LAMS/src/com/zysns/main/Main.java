package com.zysns.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.zysns.main.Jdbc.jdbcconnection;

public class Main extends Window{

    @Override    public void start(Stage primaryStage) throws Exception{
        //连接数据库
        jdbcconnection();
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
        primaryStage.setTitle("图书馆自动化管理系统");
        primaryStage.setScene(new Scene(root, 1280, 800));
        //将窗口设置为大小不可改变
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
