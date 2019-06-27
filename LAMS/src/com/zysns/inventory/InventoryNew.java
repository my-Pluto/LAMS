package com.zysns.inventory;

import com.zysns.main.Book;
import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.zysns.inventory.InventoryJdbc.create_book;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class InventoryNew extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private TextField bbookno;

    @FXML
    private DatePicker bdate;

    @FXML
    private TextField bpublish;

    @FXML
    private Button exit_login_button;

    @FXML
    private TextField baoutor;

    @FXML
    private Button update_book_button;

    @FXML
    private Button select_button;

    @FXML
    private TextField bisbn;

    @FXML
    private MenuItem about_button;

    @FXML
    private TextField bname;

    @FXML
    private TextField bquantity;

    @FXML
    private Button borrow_button;

    @FXML
    private Button new_button;

    @FXML
    private ComboBox<String> bfamily;

    @FXML
    private TextField book_no;

    @FXML
    private Label user;

    @FXML
    void new_book_jdbc() throws SQLException {
        Book book = new Book();
        String no = book_no.getText();
        String book_name = bname.getText();
        String author = baoutor.getText();
        String book_publish = bpublish.getText();
        String ISBN = bisbn.getText();
        String bno = bbookno.getText();
        Integer quaitiy = Integer.parseInt(bquantity.getText());
        LocalDate publishdate = bdate.getValue();
        String family = bfamily.getValue();
        if ((no == null || no.equals("")) || (book_name == null || book_name.equals("")) || (author == null || author.equals("")) ||
                (book_publish == null || book_publish.equals("")) || (ISBN == null || ISBN.equals("")) ||
                (bno == null || bno.equals("")) || (family == null || family.equals("")) || (bquantity.getText() == null || bquantity.getText().equals("")) ||
                publishdate == null){
            showalertbox("警告", "您输入的信息不全");
            return;
        }
        if (publishdate.toEpochDay() - LocalDate.now().toEpochDay() > 0){
            showalertbox("警告", "您输入的出版时间有误！\n请重新检查输入");
            return;
        }
        if (quaitiy == 0){
            showalertbox("警告", "您输入的采购数量有误！\n请重新检查输入");
            return;
        }
        if (no.length() != 8){
            showalertbox("警告", "您输入的图书编号有误！\n请重新检查输入");
            return;
        }
        if (ISBN.length() != 17){
            showalertbox("警告", "您输入的ISBN有误！\n请重新检查输入");
            return;
        }
        book.setBno(no);
        book.setBauthor(author);
        book.setBbookno(bno);
        book.setBdate(publishdate);
        book.setBfamily(family);
        book.setBname(book_name);
        book.setBquantity(quaitiy);
        book.setBpress(book_publish);
        book.setBisbn(ISBN);
        create_book(book);
    }

    @FXML
    void borrow() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
    }

    @FXML
    void select() throws IOException {
        Parent select = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(select, 1280, 800));
    }

    @FXML
    void account() throws IOException {
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent account = FXMLLoader.load(getClass().getResource("../account/account.fxml"));
        getWindow().setScene(new Scene(account, 1280, 800));
    }

    @FXML
    void exit_login() throws IOException {
        boolean answer = showexitbox("提示", "您是否真的要退出当前登录的账号？");
        if (answer){
            //将当前用户信息清除
            setW_manager(null);
            setW_reader(null);
            //跳转到登录界面
            Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
            getWindow().setScene(new Scene(root, 1280, 800));
        }
    }

    @FXML
    void update_book() throws IOException {
        Parent update_book = FXMLLoader.load(getClass().getResource("../inventory/InventoryUpdate.fxml"));
        getWindow().setScene(new Scene(update_book, 1280, 800));
    }

    @FXML
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    @FXML
    void about() {
        showabout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
        bfamily.getItems().addAll("A马列主义、毛泽东思想、邓小平理论", "B哲学、宗教", "C社会科学总论", "D政治、法律", "E军事", "F经济", "G文化、科学、教育、体育",
                "H语言、文字", "I文学", "J艺术", "K历史、地理", "N自然科学总论", "O数理科学和化学", "P天文学、地球科学", "Q生物科学",
                "R医药、卫生", "S农业科学", "T工业技术", "U交通运输", "V航空、航天", "X环境科学、安全科学", "Z综合性图书");
    }
}
