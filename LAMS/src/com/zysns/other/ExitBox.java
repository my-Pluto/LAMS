/**
 * 主要用于判断是否真的退出
 * 以避免误触
 * 有时也可以用于对是否的选择，返回一个boolean
 */
package com.zysns.other;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitBox {

    public static boolean isAnswer() {
        return answer;
    }

    public static void setAnswer(boolean answer) {
        ExitBox.answer = answer;
    }

    //静态变量，用于确认是否真的退出
    static boolean answer = false;

    //确认函数
    public static boolean showexitbox(String titile, String message) {
        //设置窗口参数
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titile);
        window.setWidth(300);
        window.setHeight(200);

        //显示内容
        Label label = new Label(message);

        //设置是否两个按钮，如果是则设置为true，否则设置为false，同时两种结果都关闭当前对话框
        Button yes_button = new Button("确定");
        yes_button.setOnAction(e -> {
            setAnswer(true);
            window.close();
        });
        yes_button.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER){
                setAnswer(true);
                window.close();
            }
        });
        Button no_button = new Button("取消");
        no_button.setOnAction(e -> {
            setAnswer(false);
            window.close();
        });

        //窗口布局
        VBox layout = new VBox(10);
        HBox layout_button = new HBox(20);
        layout_button.getChildren().addAll(yes_button, no_button);
        layout.getChildren().addAll(label, layout_button);
        layout.setAlignment(Pos.CENTER);
        layout_button.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }


}
