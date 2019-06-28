/**
 * 读者账号创建界面控制类
 * 主要用于各个界面之间的跳转
 * 以及各个事件的响应
 */
package com.zysns.account;

import com.zysns.main.Reader;
import com.zysns.main.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static com.zysns.account.AccountJdbc.reader_create;
import static com.zysns.other.About.showabout;
import static com.zysns.other.AlertBox.showalertbox;
import static com.zysns.other.ExitBox.showexitbox;
import static com.zysns.other.SHA.getResult;

public class AccountReader extends Window implements Initializable {

    //退出按钮
    @FXML
    private MenuItem exit_button;

    //姓名输入框
    @FXML
    private TextField name_button;

    //删除账号跳转按钮
    @FXML
    private Button delete_button;

    //管理员账号创建跳转按钮
    @FXML
    private Button message_create_button;

    //退出登录按钮
    @FXML
    private Button exit_login_button;

    //图书管理跳转按钮
    @FXML
    private Button book_button;

    //性别选择
    @FXML
    private ComboBox<String> sex_combobox;

    //图书查询按钮
    @FXML
    private Button select_button;

    //账号创建按钮
    @FXML
    private Button creage_button;

    //读者证号输入框
    @FXML
    private TextField ID_text;

    //关于按钮
    @FXML
    private MenuItem about_button;

    //年龄输入框
    @FXML
    private TextField age_text;

    //生日输入
    @FXML
    private DatePicker birthday_date;

    //图书借还按钮
    @FXML
    private Button borrow_button;

    //用户名标签
    @FXML
    private Label user;

    //读者借阅权限选择
    @FXML
    private ComboBox<String> grade_combobox;

    //密码输入框
    @FXML
    private TextField password_text;

    @FXML
        //显示图书借还界面
    void borrow() throws IOException {
        Parent borrow = FXMLLoader.load(getClass().getResource("../borrow/Borrow.fxml"));
        getWindow().setScene(new Scene(borrow, 1280, 800));
    }

    @FXML
        //显示查询界面
    void select() throws IOException {
        Parent select = FXMLLoader.load(getClass().getResource("../select/Select.fxml"));
        getWindow().setScene(new Scene(select, 1280, 800));
    }

    @FXML
        //显示图书管理界面
    void book() throws IOException {
        if (getW_manager() == null){
            showalertbox("警告", "对不起，您的账号没有权限使用该功能");
            return;
        }
        Parent book = FXMLLoader.load(getClass().getResource("../inventory/Inventory.fxml"));
        getWindow().setScene(new Scene(book, 1280, 800));
    }

    @FXML
        //点击退出后返回登录界面
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
        //点击exit后退出系统
    void exit() {
        boolean answer = showexitbox("提示", "您是否真的要关闭当前系统？");
        if(answer) {
            System.exit(0);
        }
    }

    @FXML
        //显示About信息
    void about() { showabout(); }

    //跳转的管理员账号创建界面
    @FXML
    void message_create() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountG.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    //跳转的账号删除界面
    @FXML
    void account_delete() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../account/AccountDelete.fxml"));
        getWindow().setScene(new Scene(root, 1280, 800));
    }

    //读者账号创建事件响应
    @FXML
    void create_reader() throws Exception {
        Reader reader = new Reader();

        //获取输入的读者证号、年龄、姓名、性别、读者等级、生日、密码等
        String ID = ID_text.getText();
        String age = age_text.getText();
        String name = name_button.getText();
        String sex = sex_combobox.getValue();
        String grade = grade_combobox.getValue();
        LocalDate date = birthday_date.getValue();
        String passward = password_text.getText();

        //检验输入的密码是否小于6位，如小于，要求注册者重新输入
        if (passward.length() < 6) {
            showalertbox("警告", "输入的密码小于6位\n请重新输入");
            return;
        }
        //对读者输入的姓名进行检验，如果存在明显错误，则要求注册者重新输入
        if ((name.length() < 2) || (name.length() > 6)) {
            showalertbox("警告", "您输入的姓名过长或过短\n请检查您的输入或联系管理员");
            return;
        }
        //获取读者的生日，对读者输入的生日进行检验，如果存在明显错误，则要求读者重新输入
        int years = Period.between(date, LocalDate.now()).getYears();
        if ((years <= 0) || years >= 100 || years != Integer.parseInt(age_text.getText())) {
            showalertbox("警告", "您输入的年龄或生日有误\n请检查您的输入或联系管理员");
            return;
        }
        //对输入的各个值检验是否为空
        if ((grade == null || grade.equals("")) || (ID == null || ID.equals("")) || (date == null) ||
                (age == null ||age.equals("")) || (sex == null || sex.equals(""))){
            showalertbox("警告", "您输入的信息不全");
            return;
        }

        //设置各个值
        reader.setRno(ID);
        reader.setRsex(sex);
        reader.setRname(name);
        reader.setRpower(grade);
        reader.setRbrithday(date);
        //获取读者注册时间，即当前时间
        reader.setRcreate(LocalDate.now());
        //对读者设置的密码进行加密，保证在服务器端不会存储用户的明文密码，已保证安全，采用SHA加密的方法
        reader.setRpassword(getResult(passward));
        //在一切检查完毕后，调用对数据库的操作，将读者的注册信息添加到数据库中
        reader_create(reader);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化各个下拉框菜单
        sex_combobox.getItems().addAll("男", "女");
        grade_combobox.getItems().addAll("儿童读者", "成人读者");
        //界面初始化，此处主要用于初始化用户名位置信息
        if (getW_manager() != null){
            user.setText(getW_manager().getMname());
        }
        else {
            user.setText(getW_reader().getRname());
        }
    }
}

