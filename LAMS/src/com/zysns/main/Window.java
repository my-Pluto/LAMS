package com.zysns.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Window extends Application {
    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        Window.window = window;
    }

    static Stage window = null;

    @Override
    public void start(Stage primaryStage) throws Exception { }
}
