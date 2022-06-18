package com.example.databaseproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Objects;

public class SignUpSc {
    private Image logo = new Image("file:logo.png");
    private Image background = new Image("file:BackGround.jpg");
    private ImageView backgroundIV = new ImageView(background);
    private ImageView topLeftLogo = new ImageView(logo);
    private StackPane sp = new StackPane();
    private TextField userNameTF = new TextField();
    private Label accountNameL = new Label("Full name:");
    private Label emailL = new Label("Email:");
    private Label passwordL = new Label("Password:");
    private Label RPasswordL = new Label("Repeat password:");
    private Label haveAccL = new Label("have an account?:");
    private TextField emailTF = new TextField();
    private PasswordField passwordTF = new PasswordField();
    private PasswordField repeatPasswordTF = new PasswordField();
    private Button signUpB= new Button("Sign up");
    private Button cancelB= new Button("Cancel");
    private Button loginB = new Button("Login");
    private Line line = new Line();
    private Label createAccount = new Label("Create new account");
    private AnchorPane root = new AnchorPane();

    DatePicker datePicker = new DatePicker();
    TextField phone = new TextField();
    ComboBox gender = new ComboBox();

    private Button sendEmailB = new Button("Send email");

    public Stage stage;
    private Scene scene;

    public StackPane getSp() {
        return sp;
    }

    public void setSp(StackPane sp) {
        this.sp = sp;
    }

    public SignUpSc() {
        userNameTF.setLayoutX(131);
        userNameTF.setLayoutY(98);
        userNameTF.setPrefSize(250,25);

        accountNameL.setLayoutX(5);
        accountNameL.setLayoutY(101);
        accountNameL.setPrefSize(120,17);
        accountNameL.setAlignment(Pos.CENTER_RIGHT);
        accountNameL.setTextFill(javafx.scene.paint.Color.WHITE);

        datePicker.setLayoutY(98);
        datePicker.setLayoutX(431);
        datePicker.setPrefSize(250,25);
        datePicker.setPromptText("Date of birth");

        phone.setLayoutX(431);
        phone.setLayoutY(143);
        phone.setPrefSize(250,25);
        phone.setPromptText("Phone number");

        gender.setLayoutX(431);
        gender.setLayoutY(188);
        gender.setPrefSize(250,25);
        gender.setPromptText("Gender");

        gender.getItems().add("Male");
        gender.getItems().add("Female");
        gender.getItems().add("Other");

        emailL.setLayoutX(5);
        emailL.setLayoutY(145);
        emailL.setPrefSize(120,21);
        emailL.setAlignment(Pos.CENTER_RIGHT);
        emailL.setTextFill(javafx.scene.paint.Color.WHITE);

        passwordL.setLayoutX(5);
        passwordL.setLayoutY(188);
        passwordL.setPrefSize(120,26);
        passwordL.setAlignment(Pos.CENTER_RIGHT);
        passwordL.setTextFill(javafx.scene.paint.Color.WHITE);

        RPasswordL.setLayoutX(5);
        RPasswordL.setLayoutY(237);
        RPasswordL.setPrefSize(123,18);
        RPasswordL.setAlignment(Pos.CENTER_RIGHT);
        RPasswordL.setTextFill(javafx.scene.paint.Color.WHITE);

        haveAccL.setLayoutX(5);
        haveAccL.setLayoutY(401);
        haveAccL.setPrefSize(150,16);
        haveAccL.setId("alreadyHave");
        haveAccL.setTextFill(javafx.scene.paint.Color.WHITE);

        emailTF.setLayoutX(131);
        emailTF.setLayoutY(143);
        emailTF.setPrefSize(250,25);

        passwordTF.setLayoutX(131);
        passwordTF.setLayoutY(188);
        passwordTF.setPrefSize(250,25);

        repeatPasswordTF.setLayoutX(131);
        repeatPasswordTF.setLayoutY(233);
        repeatPasswordTF.setPrefSize(250,25);

        signUpB.setLayoutX(131);
        signUpB.setLayoutY(288);
        signUpB.setPrefSize(149,25);

        loginB.setLayoutX(159);
        loginB.setLayoutY(396);
        loginB.setPrefSize(135,25);

        cancelB.setLayoutX(304);
        cancelB.setLayoutY(396);
        cancelB.setPrefSize(135,25);

        line.setStartX(-213);
        line.setEndX(159);
        line.setLayoutX(273);
        line.setLayoutY(363);

        createAccount.setLayoutY(31);
        createAccount.setLayoutX(131);
        createAccount.setPrefSize(255,30);
        createAccount.setId("createTop");
        createAccount.setTextFill(javafx.scene.paint.Color.WHITE);

        topLeftLogo.setLayoutX(14);
        topLeftLogo.setLayoutY(14);
        topLeftLogo.setFitHeight(64);
        topLeftLogo.setFitWidth(64);



        root.getChildren().addAll(userNameTF,emailTF,passwordTF,repeatPasswordTF,signUpB,cancelB,loginB,line,topLeftLogo,createAccount,accountNameL,
                emailL,passwordL,RPasswordL,haveAccL, datePicker, phone, gender);
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(backgroundIV,root);

        scene = new Scene(sp,1000,472);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.setResizable(false);
        stage.getIcons().add(logo);
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
    }

    public Image getLogo() {
        return logo;
    }


    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public ImageView getBackgroundIV() {
        return backgroundIV;
    }

    public void setBackgroundIV(ImageView backgroundIV) {
        this.backgroundIV = backgroundIV;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public TextField getPhone() {
        return phone;
    }

    public void setPhone(TextField phone) {
        this.phone = phone;
    }

    public ComboBox getGender() {
        return gender;
    }

    public void setGender(ComboBox gender) {
        this.gender = gender;
    }

    public Label getAccountNameL() {
        return accountNameL;
    }

    public void setAccountNameL(Label accountNameL) {
        this.accountNameL = accountNameL;
    }

    public Label getEmailL() {
        return emailL;
    }

    public void setEmailL(Label emailL) {
        this.emailL = emailL;
    }

    public Label getPasswordL() {
        return passwordL;
    }

    public void setPasswordL(Label passwordL) {
        this.passwordL = passwordL;
    }

    public Label getRPasswordL() {
        return RPasswordL;
    }

    public void setRPasswordL(Label RPasswordL) {
        this.RPasswordL = RPasswordL;
    }

    public Label getHaveAccL() {
        return haveAccL;
    }

    public void setHaveAccL(Label haveAccL) {
        this.haveAccL = haveAccL;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public ImageView getTopLeftLogo() {
        return topLeftLogo;
    }

    public void setTopLeftLogo(ImageView topLeftLogo) {
        this.topLeftLogo = topLeftLogo;
    }

    public TextField getUserNameTF() {
        return userNameTF;
    }

    public void setUserNameTF(TextField userNameTF) {
        this.userNameTF = userNameTF;
    }

    public TextField getEmailTF() {
        return emailTF;
    }

    public void setEmailTF(TextField emailTF) {
        this.emailTF = emailTF;
    }

    public PasswordField getPasswordTF() {
        return passwordTF;
    }

    public void setPasswordTF(PasswordField passwordTF) {
        this.passwordTF = passwordTF;
    }

    public Button getSendEmailB() {
        return sendEmailB;
    }

    public void setSendEmailB(Button sendEmailB) {
        this.sendEmailB = sendEmailB;
    }

    public PasswordField getRepeatPasswordTF() {
        return repeatPasswordTF;
    }

    public void setRepeatPasswordTF(PasswordField PasswordField) {
        this.repeatPasswordTF = repeatPasswordTF;
    }

    public Button getSignUpB() {
        return signUpB;
    }

    public void setSignUpB(Button signUpB) {
        this.signUpB = signUpB;
    }

    public Button getCancelB() {
        return cancelB;
    }

    public void setCancelB(Button cancelB) {
        this.cancelB = cancelB;
    }

    public Button getLoginB() {
        return loginB;
    }

    public void setLoginB(Button loginB) {
        this.loginB = loginB;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Label getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Label createAccount) {
        this.createAccount = createAccount;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }
}
