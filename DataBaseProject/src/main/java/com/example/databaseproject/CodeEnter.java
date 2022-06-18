package com.example.databaseproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class CodeEnter {

    private StackPane sp = new StackPane();
    private AnchorPane root = new AnchorPane();
    private Label topL = new Label("Enter the book ISBN, dont know it? cancel and search for it");
    private Button okB = new Button("OK");
    private Button cancelB = new Button("Cancel");
    private Label codeL = new Label("ISBN");
    private TextField codeTF = new TextField();
    private Image logo = new Image("file:logo.png");
    private ImageView logoIV = new ImageView(logo);
    private Image background = new Image("file:BackGround.jpg");
    private ImageView backgroundIV = new ImageView(background);

    public CodeEnter() {

        logoIV.setLayoutX(7);
        logoIV.setLayoutY(7);
        logoIV.setFitHeight(64);
        logoIV.setFitWidth(64);

        topL.setPrefSize(247,17);
        topL.setLayoutX(135);
        topL.setLayoutY(31);

        codeL.setPrefSize(50,17);
        codeL.setLayoutX(80);
        codeL.setLayoutY(78);

        codeTF.setPrefSize(247,25);
        codeTF.setLayoutX(134);
        codeTF.setLayoutY(74);

        okB.setPrefSize(110,25);
        okB.setLayoutX(135);
        okB.setLayoutY(119);

        cancelB.setPrefSize(110,25);
        cancelB.setLayoutX(271);
        cancelB.setLayoutY(119);

        root.getChildren().addAll(logoIV,topL,codeL,codeTF,okB,cancelB);
        sp.getChildren().addAll(backgroundIV,root);
    }



    public CodeEnter(String Header, boolean isCode) {

        logoIV.setLayoutX(7);
        logoIV.setLayoutY(7);
        logoIV.setFitHeight(64);
        logoIV.setFitWidth(64);

        topL.setPrefSize(247,17);
        topL.setLayoutX(135);
        topL.setLayoutY(31);
        topL.setText(Header);

        codeL.setPrefSize(50,17);
        codeL.setLayoutX(80);
        codeL.setLayoutY(78);

        codeTF.setPrefSize(247,25);
        codeTF.setLayoutX(134);
        codeTF.setLayoutY(74);

        okB.setPrefSize(110,25);
        okB.setLayoutX(135);
        okB.setLayoutY(119);

        if(!isCode){
            codeTF.setVisible(false);
            codeL.setVisible(false);
        }

        cancelB.setPrefSize(110,25);
        cancelB.setLayoutX(271);
        cancelB.setLayoutY(119);

        root.getChildren().addAll(logoIV,topL,codeL,codeTF,okB,cancelB);
        sp.getChildren().addAll(backgroundIV,root);
    }

    public StackPane getSp() {
        return sp;
    }

    public void setSp(StackPane sp) {
        this.sp = sp;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public Label getTopL() {
        return topL;
    }

    public void setTopL(Label topL) {
        this.topL = topL;
    }

    public Button getOkB() {
        return okB;
    }

    public void setOkB(Button okB) {
        this.okB = okB;
    }

    public Button getCancelB() {
        return cancelB;
    }

    public void setCancelB(Button cancelB) {
        this.cancelB = cancelB;
    }

    public Label getCodeL() {
        return codeL;
    }

    public void setCodeL(Label codeL) {
        this.codeL = codeL;
    }

    public TextField getCodeTF() {
        return codeTF;
    }

    public void setCodeTF(TextField codeTF) {
        this.codeTF = codeTF;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public ImageView getLogoIV() {
        return logoIV;
    }

    public void setLogoIV(ImageView logoIV) {
        this.logoIV = logoIV;
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
}
