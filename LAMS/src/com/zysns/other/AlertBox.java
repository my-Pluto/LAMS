/**
 * 警告类，用于输出各种警告信息，需要传入一个title和一个message
 */

package com.zysns.other;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    public static void showalertbox(String titile, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titile);
        window.setWidth(350);
        window.setHeight(250);

        Label label = new Label(message);

        Button button = new Button("确定");
        //点击确认关闭窗口
        button.setOnAction(e -> window.close());
        //回车关闭窗口
        button.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER)
                window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        //设置窗口居中
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }
}
