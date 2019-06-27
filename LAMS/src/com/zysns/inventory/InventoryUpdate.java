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

import static com.zysns.inventory.InventoryJdbc.*;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;

public class InventoryUpdate extends Window implements Initializable {

    @FXML
    private Button account_button;

    @FXML
    private Button select_boob_button;

    @FXML
    private TextField book_isbn;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button update_book_button;

    @FXML
    private TextField book_name;

    @FXML
    private DatePicker book_date;

    @FXML
    private ComboBox<String> book_family;

    @FXML
    private TextField book_no;

    @FXML
    private Button select_button;

    @FXML
    private TextField book_publish_button;

    @FXML
    private TextField bookID;

    @FXML
    private MenuItem About;

    @FXML
    private MenuItem exit;

    @FXML
    private Button new_book_button;

    @FXML
    private TextField book_quantity;

    @FXML
    private Button borrow_button;

    @FXML
    private TextField book_autor;

    @FXML
    private Label user;

    @FXML
    private TextField bookno;

    @FXML
    private Button delete_button;

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
    void new_book() throws IOException {
        Parent new_book = FXMLLoader.load(getClass().getResource("InventoryNew.fxml"));
        getWindow().setScene(new Scene(new_book, 1280, 800));
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

    @FXML
    void update_book() throws SQLException {
        Book book = new Book();
        String no = bookno.getText();
        String bookname = book_name.getText();
        String author = book_autor.getText();
        String book_publish = book_publish_button.getText();
        String ISBN = book_isbn.getText();
        String bno = book_no.getText();
        Integer quaitiy = Integer.parseInt(book_quantity.getText());
        LocalDate publishdate = book_date.getValue();
        String family = book_family.getValue();
        if ((no == null || no.equals("")) || (bookname == null || bookname.equals("")) || (author == null || author.equals("")) ||
                (book_publish == null || book_publish.equals("")) || (ISBN == null || ISBN.equals("")) ||
                (bno == null || bno.equals("")) || (family == null || family.equals("")) || (book_quantity.getText() == null || book_quantity.getText().equals("")) ||
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
        book.setBname(bookname);
        book.setBquantity(quaitiy);
        book.setBpress(book_publish);
        book.setBisbn(ISBN);
        updatebook(book);
    }

    @FXML
    void select_book() throws SQLException {
        String no = bookID.getText();
        Book book = selectbook(no);
        if (book == null)
            return;
        bookno.setText(book.getBno());
        book_isbn.setText(book.getBisbn());
        book_name.setText(book.getBname());
        book_date.setValue(book.getBdate());
        book_publish_button.setText(book.getBpress());
        book_quantity.setText(Integer.toString(book.getBquantity()));
        book_autor.setText(book.getBauthor());
        book_no.setText(book.getBbookno());
        book_family.setValue(book.getBfamily());
    }

    @FXML
    void delete() throws SQLException {
        delete_book(bookno.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
        book_family.getItems().addAll("A马列主义、毛泽东思想、邓小平理论", "B哲学、宗教", "C社会科学总论", "D政治、法律", "E军事", "F经济", "G文化、科学、教育、体育",
                "H语言、文字", "I文学", "J艺术", "K历史、地理", "N自然科学总论", "O数理科学和化学", "P天文学、地球科学", "Q生物科学",
                "R医药、卫生", "S农业科学", "T工业技术", "U交通运输", "V航空、航天", "X环境科学、安全科学", "Z综合性图书");
    }
}

