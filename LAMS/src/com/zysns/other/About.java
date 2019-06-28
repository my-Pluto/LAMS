/**
 * ahout界面
 * 用于输出程序信息
 */

package com.zysns.other;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class About {
    public static void showabout() {
        Stage stage = new Stage();
        stage.setTitle("About");

        stage.setWidth(600);
        stage.setHeight(400);
        Label lamsname = new Label();
        lamsname.setText("图书馆自动化管理系统");

        Label label1 = new Label("组长：张玉飞");
        Label name = new Label("小组成员：张玉飞  张凯承  高昊民");
        Label label2 = new Label("青岛理工大学 信息与控制工程学院 计算173");
        Label version = new Label("Version 1.0");

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(lamsname, label1, label2, name, version);
        //设置文字居中
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        //设置窗口大小不可变
        stage.setResizable(false);
        //该窗口不关闭，其他窗口无法使用
        stage.showAndWait();
    }
}
