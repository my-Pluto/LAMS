package com.zysns.main;

import com.zysns.other.About;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.zysns.other.*;
public class Controller {

    @FXML
    private Button check;

    @FXML
    void check() {
        About.showabout();
    }

}
