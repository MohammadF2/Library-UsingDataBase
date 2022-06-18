package com.example.databaseproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class SearchBookUser {


    AnchorPane root = new AnchorPane();
    TextField Title = new TextField();
    TextField Author = new TextField();
    TextField ISBN = new TextField();
    TextField Year = new TextField();
    TextField Publisher = new TextField();

    Label TitleL = new Label("Title");
    Label AuthorL = new Label("Author");
    Label ISBNL = new Label("ISBN");
    Label YearL = new Label("Year");
    Label PublisherL = new Label("Publisher");
    Label topL = new Label("You can enter at least on search subject");

    Button SearchB = new Button("Search");
    Button cancelB = new Button("Cancel");

    Image logo = new Image("file:logo.png");
    ImageView logoIV = new ImageView(logo);
    Image background = new Image("file:BackGround.jpg");
    ImageView backgroundIV = new ImageView(background);

    Scene sc;


    public SearchBookUser(){
        ISBN.setLayoutY(81);
        ISBN.setLayoutX(130);
        ISBN.setPrefSize(151,27);

        Title.setLayoutY(132);
        Title.setLayoutX(130);
        Title.setPrefSize(151,27);

        Year.setLayoutY(184);
        Year.setLayoutX(130);
        Year.setPrefSize(151,27);

        Author.setLayoutY(108);
        Author.setLayoutX(361);
        Author.setPrefSize(151,27);

        Publisher.setLayoutY(159);
        Publisher.setLayoutX(361);
        Publisher.setPrefSize(151,27);

        ISBNL.setLayoutX(81);
        ISBNL.setLayoutY(86);
        ISBNL.setPrefSize(50,17);

        TitleL.setLayoutX(81);
        TitleL.setLayoutY(137);
        TitleL.setPrefSize(50,17);

        YearL.setLayoutX(81);
        YearL.setLayoutY(189);
        YearL.setPrefSize(50,17);

        AuthorL.setLayoutX(305);
        AuthorL.setLayoutY(113);
        AuthorL.setPrefSize(50,17);

        PublisherL.setLayoutX(305);
        PublisherL.setLayoutY(164);
        PublisherL.setPrefSize(50,17);

        SearchB.setLayoutX(162);
        SearchB.setLayoutY(272);
        SearchB.setPrefSize(87,46);

        cancelB.setLayoutX(393);
        cancelB.setLayoutY(272);
        cancelB.setPrefSize(87,46);

        logoIV.setLayoutX(545);
        logoIV.setLayoutY(10);
        logoIV.setFitHeight(86);
        logoIV.setFitWidth(86);

        topL.setLayoutX(215);
        topL.setLayoutY(17);
        topL.setPrefSize(210,17);

        root.getChildren().addAll(backgroundIV,logoIV,topL,ISBNL,ISBN,TitleL,Title,YearL,Year,AuthorL,Author,PublisherL,Publisher,SearchB,cancelB);
        sc = new Scene(root,646,367);
        sc.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );


    }

    public TextField getTitle() {
        return Title;
    }

    public TextField getAuthor() {
        return Author;
    }

    public TextField getISBN() {
        return ISBN;
    }

    public TextField getYear() {
        return Year;
    }

    public TextField getPublisher() {
        return Publisher;
    }

    public Scene getSc() {
        return sc;
    }

    public Button getSearchB() {
        return SearchB;
    }

    public Button getCancelB() {
        return cancelB;
    }

}
