/**
 * 警告类，用于输出各种警告信息，需要传入一个title和一个message
 */

package com.zysns.other;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    public static void showalertbox(String titile, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titile);
        window.setWidth(300);
        window.setHeight(200);

        Label label = new Label(message);

        Button button = new Button("确定");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
