/**
 * 视窗操作类，用于在其他控制类中对视窗进行操作
 * 主要是用于对各个界面的切换
 * 同时，在本类中还存在book、reader、manager三个静态变量
 * 用于在进行页面跳转时标识身份等
 */
package com.zysns.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Window extends Application {

    static Book w_book = null;
    static Reader w_reader = null;
    static Manager w_manager = null;
    //窗口的背景，各个界面共享
    static Stage window = null;

    //getter、setter
    public static Book getW_book() {
        return w_book;
    }

    public static void setW_book(Book w_book) {
        Window.w_book = w_book;
    }

    public static Reader getW_reader() {
        return w_reader;
    }

    public static void setW_reader(Reader w_reader) {
        Window.w_reader = w_reader;
    }

    public static Manager getW_manager() {
        return w_manager;
    }

    public static void setW_manager(Manager w_manager) {
        Window.w_manager = w_manager;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        Window.window = window;
    }

    //继承自Application，要求必须实现
    @Override
    public void start(Stage primaryStage) throws Exception { }
}
