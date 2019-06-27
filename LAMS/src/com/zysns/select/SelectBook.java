package com.zysns.select;

import com.zysns.main.Book;
import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.select.SelectJdbc.getbook;
import static com.zysns.select.SelectJdbc.selectbook;

public class SelectBook extends Window implements Initializable {

    @FXML
    private ComboBox<String> combobox_select;

    @FXML
    private Button account_button;

    @FXML
    private MenuItem exit_button;

    @FXML
    private TreeView<?> treeview_book;

    @FXML
    private TextField select_text;

    @FXML
    private Button exit_login_button;

    @FXML
    private Button book_button;

    @FXML
    private TableView<Book> table_book;

    @FXML
    private Button reader_message_button;

    @FXML
    private MenuItem about_button;

    @FXML
    private Button high_button;

    @FXML
    private Button borrow_button;

    @FXML
    private Button select_book_button;

    @FXML
    private Label user;

    @FXML
    private TableColumn<Book, String> book_isbn;

    @FXML
    private TableColumn<Book, String> book_author;

    @FXML
    private TableColumn<Book, String> book_name;

    @FXML
    private TableColumn<Book, Locale> book_date;

    @FXML
    private TableColumn<Book, String> book_bno;

    @FXML
    private TableColumn<Book, String> book_family;

    @FXML
    private TableColumn<Book, String> book_no;

    @FXML
    private TableColumn<Book, String> book_press;

    @FXML
    private TableColumn<Book, Integer> book_quality;

    @FXML
    void book() throws IOException {
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent book = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(book, 1280, 800));
    }

    @FXML
    void borrow_book() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
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
    void reader_message() throws IOException {
        Parent reader_message = FXMLLoader.load(getClass().getResource("../select/SelectPerson.fxml"));
        getWindow().setScene(new Scene(reader_message, 1280, 800));
    }

    @FXML
    void high() throws IOException {
        Parent high = FXMLLoader.load(getClass().getResource("../select/SelectR.fxml"));
        getWindow().setScene(new Scene(high, 1280, 800));
    }

    public static TreeItem makeBranch(String title, TreeItem parent) {
        TreeItem item = new TreeItem(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    public void set_table_view() throws SQLException {
        book_isbn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        book_author.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("bname"));
        book_date.setCellValueFactory(new PropertyValueFactory<>("bdate"));
        book_bno.setCellValueFactory(new PropertyValueFactory<>("bbookno"));
        book_family.setCellValueFactory(new PropertyValueFactory<>("bfamily"));
        book_no.setCellValueFactory(new PropertyValueFactory<>("bno"));
        book_press.setCellValueFactory(new PropertyValueFactory<>("bpress"));
        book_quality.setCellValueFactory(new PropertyValueFactory<>("bquantity"));
        table_book.setItems(getbook());
    }

    @FXML
    void select_book() throws SQLException {
        String key = select_text.getText();
        if (key == null || key.equals("")){
            showalertbox("警告", "您输入的查询信息有误！\n请重新输入！");
            return;
        }
        selectbook(combobox_select.getValue(), key);
        set_table_view();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
        combobox_select.getItems().addAll("图书编号", "图书名称", "图书作者", "出版社", "出版时间", "ISBN", "书架编号", "所属类别");
        TreeItem root = new TreeItem("图书分类");
        root.setExpanded(true);
        makeBranch("A马列主义、毛泽东思想、邓小平理论", root);
        makeBranch("B哲学、宗教", root);
        makeBranch("C社会科学总论", root);
        makeBranch("D政治、法律", root);
        makeBranch("E军事", root);
        makeBranch("F经济", root);
        makeBranch("G文化、科学、教育、体育", root);
        makeBranch("H语言、文字", root);
        makeBranch("I文学", root);
        makeBranch("J艺术", root);
        makeBranch("K历史、地理", root);
        makeBranch("N自然科学总论", root);
        makeBranch("O数理科学和化学", root);
        makeBranch("P天文学、地球科学", root);
        makeBranch("Q生物科学", root);
        makeBranch("R医药、卫生", root);
        makeBranch("S农业科学", root);
        makeBranch("T工业技术", root);
        makeBranch("U交通运输", root);
        makeBranch("V航空、航天", root);
        makeBranch("X环境科学、安全科学", root);
        makeBranch("Z综合性图书", root);
        treeview_book.setRoot(root);
        treeview_book.setShowRoot(true);
        treeview_book.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null){
                try {
                    selectbook("所属类别", newValue.getValue().toString());
                    set_table_view();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

