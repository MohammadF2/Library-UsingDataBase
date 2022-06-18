package com.example.databaseproject;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

public class CantSignInSc {

    private Image logo = new Image("logo.png");
    private Image background = new Image("background.png");
    private ImageView backgroundIV = new ImageView(background);
    private ImageView topLeftLogo = new ImageView(logo);
    private StackPane sp = new StackPane();
    private TextField userNameTF = new TextField();
    private Label accountNameL = new Label("Account name:");
    private Label emailL = new Label("Email:");
    private Label passwordL = new Label("Password:");
    private Label RPasswordL = new Label("Repeat password:");
    private Label haveAccL = new Label("Already have an account:");
    private TextField emailTF = new TextField();
    private PasswordField passwordTF = new PasswordField();
    private PasswordField repeatPasswordTF = new PasswordField();
    private Button signUpB= new Button("Send code");
    private Button cancelB= new Button("Cancel");
    private Button loginB = new Button("Login");

    public Button getChangeB() {
        return changeB;
    }

    public void setChangeB(Button changeB) {
        this.changeB = changeB;
    }

    private Line line = new Line();
    private Label createAccount = new Label("Having trouble logging in?");
    private AnchorPane root = new AnchorPane();
    private Button changeB = new Button("Change");
    public StackPane getSp() {
        return sp;
    }
    public CantSignInSc() {
        userNameTF.setLayoutX(131);
        userNameTF.setLayoutY(98);
        userNameTF.setPrefSize(309,25);
        userNameTF.setDisable(true);

        accountNameL.setLayoutX(5);
        accountNameL.setLayoutY(101);
        accountNameL.setPrefSize(120,17);
        accountNameL.setAlignment(Pos.CENTER_RIGHT);

        emailL.setLayoutX(5);
        emailL.setLayoutY(145);
        emailL.setPrefSize(120,21);
        emailL.setAlignment(Pos.CENTER_RIGHT);

        passwordL.setLayoutX(5);
        passwordL.setLayoutY(188);
        passwordL.setPrefSize(120,26);
        passwordL.setAlignment(Pos.CENTER_RIGHT);

        RPasswordL.setLayoutX(5);
        RPasswordL.setLayoutY(237);
        RPasswordL.setPrefSize(123,18);
        RPasswordL.setAlignment(Pos.CENTER_RIGHT);

        haveAccL.setLayoutX(5);
        haveAccL.setLayoutY(401);
        haveAccL.setPrefSize(309,16);
        haveAccL.setId("alreadyHave");

        emailTF.setLayoutX(131);
        emailTF.setLayoutY(143);
        emailTF.setPrefSize(309,25);

        passwordTF.setLayoutX(131);
        passwordTF.setLayoutY(188);
        passwordTF.setPrefSize(309,25);
        passwordTF.setDisable(true);

        repeatPasswordTF.setLayoutX(131);
        repeatPasswordTF.setLayoutY(233);
        repeatPasswordTF.setPrefSize(309,25);
        repeatPasswordTF.setDisable(true);

        signUpB.setLayoutX(131);
        signUpB.setLayoutY(288);
        signUpB.setPrefSize(149,25);

        changeB.setLayoutX(291);
        changeB.setLayoutY(288);
        changeB.setPrefSize(149,25);
        changeB.setDisable(true);

        loginB.setLayoutX(187);
        loginB.setLayoutY(396);
        loginB.setPrefSize(118,25);

        line.setStartX(-213);
        line.setEndX(159);
        line.setLayoutX(273);
        line.setLayoutY(363);

        createAccount.setLayoutY(31);
        createAccount.setLayoutX(131);
        createAccount.setPrefSize(300,30);
        createAccount.setId("createTop");

        topLeftLogo.setLayoutX(14);
        topLeftLogo.setLayoutY(14);
        topLeftLogo.setFitHeight(64);
        topLeftLogo.setFitWidth(64);

        cancelB.setLayoutX(315);
        cancelB.setLayoutY(396);
        cancelB.setPrefSize(118,25);

        root.getChildren().addAll(userNameTF,emailTF,passwordTF,repeatPasswordTF,signUpB,cancelB,loginB,line,topLeftLogo,createAccount,accountNameL,emailL,passwordL,RPasswordL,haveAccL,changeB);
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(backgroundIV,root);
    }

    public Image getLogo() {
        return logo;
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
