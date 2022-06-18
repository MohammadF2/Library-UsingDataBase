package com.example.databaseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class AdminWindow {

    ObservableList<LoneRequest> lonsRequest = FXCollections.observableArrayList();
    ObservableList<Book> books = FXCollections.observableArrayList();
    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<Request> barrowReq = FXCollections.observableArrayList();


    DataBaseConnection db = new DataBaseConnection();

    User user;

    public void PrintAdminStage (User user) {
        this.user = user;
        Stage AdminStage = new Stage();

        AnchorPane RootPane = new AnchorPane();
        RootPane.setPadding( new Insets( 20, 20, 20, 20 ) );

        HBox ButtonPane_1 = new HBox( 10 );
        ButtonPane_1.setPrefSize( 1353, 46 );
        ButtonPane_1.setLayoutX( 22 );
        ButtonPane_1.setLayoutY( 14 );
        ButtonPane_1.setPrefSize( 1344, 56 );
        ButtonPane_1.setSpacing( 5 );

        HBox ButtonPane_2 = new HBox( 10 );
        ButtonPane_2.setPrefSize( 1344, 56 );
        ButtonPane_2.setLayoutX( 11 );
        ButtonPane_2.setLayoutY( 86 );
        ButtonPane_2.setPrefSize( 1344, 56 );
        ButtonPane_2.setSpacing( 5 );

        Button AddBook = new Button( "Add Book" );
        AddBook.setPrefSize( 320, 46 );

        Button DeleteBook = new Button( "Delete Book" );
        DeleteBook.setPrefSize( 320, 46 );

        Button EditBook = new Button( "Edit Book" );
        EditBook.setPrefSize( 320, 46 );

        Button SearchBook = new Button( "Information" );
        SearchBook.setPrefSize( 320, 46 );

        ButtonPane_1.getChildren().addAll( AddBook, DeleteBook, EditBook, SearchBook );

        Button DisplayBook = new Button( "Display Book" );
        DisplayBook.setPrefSize( 300, 46 );

        Button DisplayUser = new Button( "Display User" );
        DisplayUser.setPrefSize( 300, 46 );

        Button LoanRequests = new Button( "Loan Requests" );
        LoanRequests.setPrefSize( 300, 46 );

        Button BorrowRequests = new Button( "Borrow Requests " );
        BorrowRequests.setPrefSize( 300, 46 );

        Button Logout = new Button( "Logout" );
        Logout.setPrefSize( 300, 46 );

        ButtonPane_2.getChildren().addAll( DisplayBook, DisplayUser, LoanRequests, BorrowRequests, Logout );

        RootPane.getChildren().addAll( ButtonPane_2, ButtonPane_1 );

        AnchorPane SecondPane = new AnchorPane();
        SecondPane.setLayoutX( 19 );
        SecondPane.setLayoutY( 154 );

        Button AddBookScene = new Button( "Add Book" );

        AddBook.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("buttonCurr");
            EditBook.setId("");
            DeleteBook.setId("");
            SearchBook.setId("");
            DisplayBook.setId("");
            LoanRequests.setId("");
            DisplayUser.setId("");
            Label ISBN = new Label( "ISBN" );
            Label Auther = new Label( "Auther" );
            Label Publisher = new Label( "Publisher" );
            Label Year = new Label( "Year" );
            Label Title = new Label( "Title" );
            Label Price = new Label( "Price" );
            Label NumberOfCopies = new Label( "Number Of Copies" );

            //ISBN.setLayoutX( 387 );
            //ISBN.setLayoutY( 92 );
            //ISBN.setTextFill( Color.WHITE );

            Auther.setLayoutX( 381 );
            Auther.setLayoutY( 138 );
            Auther.setTextFill( Color.WHITE );

            Publisher.setLayoutX( 373 );
            Publisher.setLayoutY( 184 );
            Publisher.setTextFill( Color.WHITE );

            Year.setLayoutX( 389 );
            Year.setLayoutY( 230 );
            Year.setTextFill( Color.WHITE );

            Title.setLayoutX( 389 );
            Title.setLayoutY( 281 );
            Title.setTextFill( Color.WHITE );

            Price.setLayoutX( 387 );
            Price.setLayoutY( 324 );
            Price.setTextFill( Color.WHITE );

            NumberOfCopies.setLayoutX( 341 );
            NumberOfCopies.setLayoutY( 374 );
            NumberOfCopies.setTextFill( Color.WHITE );

            //TextField ISBNText = new TextField();
            TextField AutherText = new TextField();
            TextField PublisherText = new TextField();
            TextField YearText = new TextField();
            TextField TitleText = new TextField();
            TextField PriceText = new TextField();
            TextField NumberOfCopiesText = new TextField();


            AddBookScene.setOnAction(e1 -> {
                if (AutherText.getText().isEmpty() || PublisherText.getText().isEmpty() || YearText.getText().isEmpty() || TitleText.getText().isEmpty() || PriceText.getText().isEmpty() || NumberOfCopiesText.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please fill all the fields");
                    alert.showAndWait();
                } else {
                    try{
                        String sql = "Insert into book(Auther_Name, Publisher_Name, Year, Title, price, NumberOfCopies, addDate ) values('"+AutherText.getText()+"','"+PublisherText.getText()+"','"+YearText.getText()+"','"+TitleText.getText()+"','"+PriceText.getText()+"','"+NumberOfCopiesText.getText()+ "', '" +java.time.LocalDate.now() +"')";

                        Connection con = db.getConnection().connectDB();
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Book added successfully");
                        alert.showAndWait();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            });

            //ISBNText.setPromptText( "ex: 30A" );
            //ISBNText.setPromptText( "ex: 30A" );
            //ISBNText.setLayoutX( 531 );
            //ISBNText.setLayoutY( 89 );
            //ISBNText.setPrefSize( 179, 27 );

            AutherText.setLayoutX( 531 );
            AutherText.setLayoutY( 135 );
            AutherText.setPrefSize( 179, 27 );
            AutherText.setPromptText( "ex: Rami" );

            PublisherText.setLayoutX( 531 );
            PublisherText.setLayoutY( 181 );
            PublisherText.setPrefSize( 179, 27 );
            PublisherText.setPromptText( "ex: Dar Al-Rida Publishing" );

            YearText.setLayoutX( 531 );
            YearText.setLayoutY( 227 );
            YearText.setPrefSize( 179, 27 );
            YearText.setPromptText( "ex: 2019" );

            TitleText.setLayoutX( 531 );
            TitleText.setLayoutY( 278 );
            TitleText.setPrefSize( 179, 27 );
            TitleText.setPromptText( "ex: Harry Potter" );

            PriceText.setLayoutX( 531 );
            PriceText.setLayoutY( 321 );
            PriceText.setPrefSize( 179, 27 );
            PriceText.setPromptText( "ex: 30$" );

            NumberOfCopiesText.setPromptText( "ex: 10" );
            NumberOfCopiesText.setLayoutX( 531 );
            NumberOfCopiesText.setLayoutY( 371 );
            NumberOfCopiesText.setPrefSize( 179, 27 );
            NumberOfCopiesText.setPromptText( "ex: 10" );


            AddBookScene.setPrefSize( 200, 46 );
            AddBookScene.setLayoutX( 545 );
            AddBookScene.setLayoutY( 479 );
            SecondPane.getChildren().addAll(Auther, Publisher, Year, Title,  NumberOfCopies, Price,
                    AddBookScene,AutherText, PublisherText, YearText, TitleText,  PriceText ,NumberOfCopiesText );
        });

        Button DeleteBookScene = new Button( "Delete Book" );

        Button showBook = new Button( "Show Book" );

        DeleteBook.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("");
            EditBook.setId("");
            DeleteBook.setId("buttonCurr");
            SearchBook.setId("");
            DisplayBook.setId("");
            LoanRequests.setId("");
            DisplayUser.setId("");
            Label ISBN = new Label( "ISBN" );
            Label Auther = new Label( "Auther" );
            Label Publisher = new Label( "Publisher" );
            Label Year = new Label( "Year" );
            Label Title = new Label( "Title" );
            Label Price = new Label( "Price" );
            Label NumberOfCopies = new Label( "Number Of Copies" );
            ISBN.setLayoutX( 387 );
            ISBN.setLayoutY( 92 );
            ISBN.setTextFill( Color.WHITE );

            Auther.setLayoutX( 381 );
            Auther.setLayoutY( 138 );
            Auther.setTextFill( Color.WHITE );

            Publisher.setLayoutX( 373 );
            Publisher.setLayoutY( 184 );
            Publisher.setTextFill( Color.WHITE );

            Year.setLayoutX( 389 );
            Year.setLayoutY( 230 );
            Year.setTextFill( Color.WHITE );

            Title.setLayoutX( 389 );
            Title.setLayoutY( 281 );
            Title.setTextFill( Color.WHITE );

            Price.setLayoutX( 387 );
            Price.setLayoutY( 324 );
            Price.setTextFill( Color.WHITE );

            NumberOfCopies.setLayoutX( 341 );
            NumberOfCopies.setLayoutY( 374 );
            NumberOfCopies.setTextFill( Color.WHITE );

            TextField ISBNText = new TextField();
            TextField AutherText = new TextField();
            TextField PublisherText = new TextField();
            TextField YearText = new TextField();
            TextField TitleText = new TextField();
            TextField PriceText = new TextField();
            TextField NumberOfCopiesText = new TextField();





            ISBNText.setPromptText( "ex: 30A" );
            ISBNText.setLayoutX( 531 );
            ISBNText.setLayoutY( 89 );
            ISBNText.setPrefSize( 179, 27 );

            AutherText.setEditable( false );
            AutherText.setLayoutX( 531 );
            AutherText.setLayoutY( 135 );
            AutherText.setPrefSize( 179, 27 );

            PublisherText.setEditable( false );
            PublisherText.setLayoutX( 531 );
            PublisherText.setLayoutY( 181 );
            PublisherText.setPrefSize( 179, 27 );

            YearText.setLayoutX( 531 );
            YearText.setLayoutY( 227 );
            YearText.setPrefSize( 179, 27 );
            YearText.setEditable( false );

            TitleText.setLayoutX( 531 );
            TitleText.setLayoutY( 278 );
            TitleText.setPrefSize( 179, 27 );
            TitleText.setEditable( false );

            PriceText.setLayoutX( 531 );
            PriceText.setLayoutY( 321 );
            PriceText.setPrefSize( 179, 27 );
            PriceText.setEditable( false );

            NumberOfCopiesText.setLayoutX( 531 );
            NumberOfCopiesText.setLayoutY( 371 );
            NumberOfCopiesText.setPrefSize( 179, 27 );
            NumberOfCopiesText.setEditable( false );

            DeleteBookScene.setPrefSize( 150, 60 );
            DeleteBookScene.setLayoutX( 450 );
            DeleteBookScene.setLayoutY( 479 );
            DeleteBookScene.setDisable( true );

            showBook.setOnAction(e1 -> {
                try {
                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT * FROM book WHERE ISBN = " + ISBNText.getText() + ";";
                    System.out.println(sql);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery( sql );
                    if (rs.next()){
                        AutherText.setText( rs.getString( 2 ) );
                        PublisherText.setText( rs.getString( 3 ) );
                        YearText.setText( rs.getString( 4 ) );
                        TitleText.setText( rs.getString( 5 ) );
                        PriceText.setText( rs.getString( 6 ) );
                        NumberOfCopiesText.setText( rs.getString( 7 ) );
                        DeleteBookScene.setDisable( false );

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Book Found");
                        alert.setHeaderText("Book Found");
                        alert.setContentText("Book Found");
                        alert.showAndWait();
                    }
                    else {
                        AutherText.setText( "" );
                        PublisherText.setText( "" );
                        YearText.setText( "" );
                        TitleText.setText( "" );
                        PriceText.setText( "" );
                        NumberOfCopiesText.setText( "" );
                        DeleteBookScene.setDisable( true );
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Book Not Found");
                        alert.setHeaderText("Book Not Found");
                        alert.setContentText("Book Not Found");
                        alert.showAndWait();
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            });

            ISBNText.setOnMouseClicked(e1 -> {
                DeleteBookScene.setDisable(true);
            });

            DeleteBookScene.setOnAction(e1 -> {
                try {
                    Connection con = db.getConnection().connectDB();
                    String sql = "delete FROM book WHERE ISBN = " + ISBNText.getText() + ";";
                    System.out.println(sql);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate( sql );
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book Deleted");
                    alert.setHeaderText("Book Deleted");
                    alert.setContentText("Book Deleted");
                    alert.showAndWait();

                    books.remove(new Book(ISBNText.getText(), null, 0, null, 0, 0, 0));

                    AutherText.setText( "" );
                    PublisherText.setText( "" );
                    YearText.setText( "" );
                    TitleText.setText( "" );
                    PriceText.setText( "" );
                    NumberOfCopiesText.setText( "" );
                    DeleteBookScene.setDisable( true );

                }catch (Exception ex){
                    System.out.println(ex);
                }
            });



            showBook.setPrefSize( 150, 60 );
            showBook.setLayoutX( 610 );
            showBook.setLayoutY( 479 );


            SecondPane.getChildren().addAll(ISBN, NumberOfCopiesText, PriceText,  TitleText, YearText, PublisherText, AutherText, ISBNText, NumberOfCopies, Price
                    , Title, Year, Publisher, Auther, DeleteBookScene, showBook );

        } );

        Button EditBookScene = new Button( "Edit Book" );
        EditBook.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("");
            EditBook.setId("buttonCurr");
            DeleteBook.setId("");
            SearchBook.setId("");
            DisplayBook.setId("");
            LoanRequests.setId("");
            DisplayUser.setId("");
            Label ISBN = new Label( "ISBN" );
            Label Auther = new Label( "Auther" );
            Label Publisher = new Label( "Publisher" );
            Label Year = new Label( "Year" );
            Label Title = new Label( "Title" );
            Label Price = new Label( "Price" );
            Label NumberOfCopies = new Label( "Number Of Copies" );
            ISBN.setLayoutX( 387 );
            ISBN.setLayoutY( 92 );
            ISBN.setTextFill( Color.WHITE );

            Auther.setLayoutX( 381 );
            Auther.setLayoutY( 138 );
            Auther.setTextFill( Color.WHITE );

            Publisher.setLayoutX( 373 );
            Publisher.setLayoutY( 184 );
            Publisher.setTextFill( Color.WHITE );

            Year.setLayoutX( 389 );
            Year.setLayoutY( 230 );
            Year.setTextFill( Color.WHITE );

            Title.setLayoutX( 389 );
            Title.setLayoutY( 281 );
            Title.setTextFill( Color.WHITE );

            Price.setLayoutX( 387 );
            Price.setLayoutY( 324 );
            Price.setTextFill( Color.WHITE );

            NumberOfCopies.setLayoutX( 341 );
            NumberOfCopies.setLayoutY( 374 );
            NumberOfCopies.setTextFill( Color.WHITE );

            TextField ISBNText = new TextField();
            TextField AutherText = new TextField();
            AutherText.setEditable( false );
            TextField PublisherText = new TextField();
            PublisherText.setEditable( false );
            TextField YearText = new TextField();
            YearText.setEditable( false );
            TextField TitleText = new TextField();
            TitleText.setEditable( false );
            TextField PriceText = new TextField();
            PriceText.setEditable( false );
            TextField NumberOfCopiesText = new TextField();
            NumberOfCopiesText.setEditable( false );

            ISBNText.setPromptText( "ex: 30A" );
            ISBNText.setPromptText( "ex: 30A" );
            ISBNText.setLayoutX( 531 );
            ISBNText.setLayoutY( 89 );
            ISBNText.setPrefSize( 179, 27 );

            AutherText.setLayoutX( 531 );
            AutherText.setLayoutY( 135 );
            AutherText.setPrefSize( 179, 27 );
            AutherText.setPromptText( "ex: Rami" );

            PublisherText.setLayoutX( 531 );
            PublisherText.setLayoutY( 181 );
            PublisherText.setPrefSize( 179, 27 );
            PublisherText.setPromptText( "ex: Dar Al-Rida Publishing" );

            YearText.setLayoutX( 531 );
            YearText.setLayoutY( 227 );
            YearText.setPrefSize( 179, 27 );
            YearText.setPromptText( "ex: 2019" );

            TitleText.setLayoutX( 531 );
            TitleText.setLayoutY( 278 );
            TitleText.setPrefSize( 179, 27 );
            TitleText.setPromptText( "ex: Harry Potter" );

            PriceText.setLayoutX( 531 );
            PriceText.setLayoutY( 321 );
            PriceText.setPrefSize( 179, 27 );
            PriceText.setPromptText( "ex: 30$" );

            NumberOfCopiesText.setPromptText( "ex: 10" );
            NumberOfCopiesText.setLayoutX( 531 );
            NumberOfCopiesText.setLayoutY( 371 );
            NumberOfCopiesText.setPrefSize( 179, 27 );
            NumberOfCopiesText.setPromptText( "ex: 10" );





            EditBookScene.setOnAction(e1 -> {
                try {
                    Integer.parseInt(PriceText.getText());
                    try {
                        Integer.parseInt(NumberOfCopiesText.getText());
                        try {
                            Connection con = db.getConnection().connectDB();
                            String sql = "update book set Auther_Name = '" + AutherText.getText() + "', Publisher_Name = '" + PublisherText.getText() + "', Year = " + YearText.getText() + ", Title = '" + TitleText.getText() + "', price = " + PriceText.getText() + ", NumberOfCopies = " + NumberOfCopiesText.getText() + " where ISBN = " + ISBNText.getText() + ";";
                            System.out.println(sql);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate( sql );
                            con.close();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Book Edited");
                            alert.setHeaderText("Book Edited");
                            alert.setContentText("Book Edited Successfully");
                            alert.showAndWait();
                            AutherText.setText( "" );
                            PublisherText.setText( "" );
                            YearText.setText( "" );
                            TitleText.setText( "" );
                            PriceText.setText( "" );
                            NumberOfCopiesText.setText( "" );
                            EditBookScene.setDisable( true );
                            ISBNText.setText( "" );
                        }catch (Exception ex){
                            System.out.println(ex);
                        }
                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Number Of Copies must be a number");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e2) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid Price");
                    alert.setHeaderText("Invalid Price");
                    alert.setContentText("Invalid Price");
                    alert.showAndWait();
                }
            });

            Button showBook1 = new Button( "Show Book" );


            showBook1.setOnAction(e1 -> {
                try {
                    if(ISBNText.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Please Enter ISBN");
                        alert.showAndWait();
                    }else {
                        Connection con = db.getConnection().connectDB();
                        String sql = "select * from book where ISBN = " + ISBNText.getText() + ";";
                        System.out.println(sql);
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery( sql );
                        if(rs.next()){
                            AutherText.setText( rs.getString( 2 ) );
                            PublisherText.setText( rs.getString( 3 ) );
                            YearText.setText( rs.getString( 4 ) );
                            TitleText.setText( rs.getString( 5 ) );
                            PriceText.setText( rs.getString( 6 ) );
                            NumberOfCopiesText.setText( rs.getString( 7) );
                            ISBNText.setText( rs.getString( 1 ) );

                            AutherText.setEditable( true );
                            PublisherText.setEditable( true );
                            YearText.setEditable( true );
                            TitleText.setEditable( true );
                            PriceText.setEditable( true );
                            NumberOfCopiesText.setEditable( true );
                            ISBNText.setEditable( true );

                            EditBookScene.setDisable(false);

                        }else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Book Not Found");
                            alert.setHeaderText("Book Not Found");
                            alert.setContentText("Book Not Found");
                            alert.showAndWait();
                        }
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            });

            ISBNText.setOnMouseClicked(e1 -> {
                EditBookScene.setDisable(true);
            });

            showBook1.setPrefSize( 150, 60 );
            showBook1.setLayoutX( 610 );
            showBook1.setLayoutY( 479 );

            EditBookScene.setPrefSize( 150, 60 );
            EditBookScene.setLayoutX( 450 );
            EditBookScene.setLayoutY( 479 );
            EditBookScene.setDisable( true );

            SecondPane.getChildren().addAll( NumberOfCopiesText, PriceText, EditBookScene, TitleText, YearText, PublisherText, AutherText, ISBNText, NumberOfCopies, Price
                    , Title, Year, Publisher, Auther, ISBN, showBook1);
        } );
        Button SearchBookScene = new Button( "Search " );
        SearchBook.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("");
            EditBook.setId("");
            DeleteBook.setId("");
            SearchBook.setId("buttonCurr");
            DisplayBook.setId("");
            LoanRequests.setId("");
            DisplayUser.setId("");
            Label ISBN = new Label( "ISBN" );
            Label Auther = new Label( "Auther" );
            Label Publisher = new Label( "Publisher" );
            Label Year = new Label( "Year" );
            Label Title = new Label( "Title" );
            Label Price = new Label( "Price" );
            Label NumberOfCopies = new Label( "Number Of Copies" );

            ISBN.setLayoutX( 87 );
            ISBN.setLayoutY( 92 );
            ISBN.setTextFill( Color.WHITE );

            Auther.setLayoutX( 81 );
            Auther.setLayoutY( 144 );
            Auther.setTextFill( Color.WHITE );

            Publisher.setLayoutX( 73 );
            Publisher.setLayoutY( 199 );
            Publisher.setTextFill( Color.WHITE );

            Year.setLayoutX( 89 );
            Year.setLayoutY( 254 );
            Year.setTextFill( Color.WHITE );

            Title.setLayoutX( 89 );
            Title.setLayoutY( 309 );
            Title.setTextFill( Color.WHITE );

            Price.setLayoutX( 87 );
            Price.setLayoutY( 364 );
            Price.setTextFill( Color.WHITE );

            NumberOfCopies.setLayoutX( 41 );
            NumberOfCopies.setLayoutY( 419 );
            NumberOfCopies.setTextFill( Color.WHITE );

            TextField ISBNText = new TextField();
            TextField AutherText = new TextField();
            TextField PublisherText = new TextField();
            TextField YearText = new TextField();
            TextField TitleText = new TextField();
            TextField PriceText = new TextField();
            TextField NumberOfCopiesText = new TextField();

             NumberAxis xAxis = new NumberAxis();
             NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Number of days");
            yAxis.setLabel("Number of books");
            LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
            XYChart.Series series = new XYChart.Series();
            series.setName("Books");


            series.getData().add(new XYChart.Data(1, 23));
            series.getData().add(new XYChart.Data(2, 14));
            series.getData().add(new XYChart.Data(3, 15));
            series.getData().add(new XYChart.Data(4, 24));
            series.getData().add(new XYChart.Data(5, 34));
            series.getData().add(new XYChart.Data(6, 36));
            series.getData().add(new XYChart.Data(7, 22));
            series.getData().add(new XYChart.Data(8, 45));
            series.getData().add(new XYChart.Data(9, 43));
            series.getData().add(new XYChart.Data(10, 17));
            series.getData().add(new XYChart.Data(11, 29));
            series.getData().add(new XYChart.Data(12, 25));
            lineChart.getData().add(series);

            lineChart.setLayoutX( 800 );
            lineChart.setLayoutY( 89 );
            lineChart.setPrefSize( 500, 500 );

            ISBNText.setPromptText( "ex: 30A" );
            ISBNText.setLayoutX( 231 );
            ISBNText.setLayoutY( 89 );
            ISBNText.setPrefSize( 179, 27 );

            AutherText.setEditable( false );
            AutherText.setLayoutX( 231 );
            AutherText.setLayoutY( 144 );
            AutherText.setPrefSize( 179, 27 );

            PublisherText.setEditable( false );
            PublisherText.setLayoutX( 231 );
            PublisherText.setLayoutY( 199 );
            PublisherText.setPrefSize( 179, 27 );

            YearText.setLayoutX( 231 );
            YearText.setLayoutY( 254 );
            YearText.setPrefSize( 179, 27 );
            YearText.setEditable( false );

            TitleText.setLayoutX( 231 );
            TitleText.setLayoutY( 309 );
            TitleText.setPrefSize( 179, 27 );
            TitleText.setEditable( false );

            PriceText.setLayoutX( 231 );
            PriceText.setLayoutY( 364 );
            PriceText.setPrefSize( 179, 27 );
            PriceText.setEditable( false );

            TextField UserID = new TextField();
            UserID.setLayoutX( 531 );
            UserID.setLayoutY( 89 );
            UserID.setPrefSize( 179, 27 );
            UserID.setPromptText("ex: 1");

            Label UserIDL = new Label( "User ID." );
            UserIDL.setLayoutX( 430 );
            UserIDL.setLayoutY( 89 );
            UserIDL.setPrefSize( 75, 27 );
            UserIDL.setTextFill( Color.WHITE );

            TextField FName = new TextField();
            FName.setLayoutX( 531 );
            FName.setLayoutY( 144 );
            FName.setPrefSize( 179, 27 );
            FName.setPromptText( "Mohammad" );
            FName.setEditable( false );

            Label FNameL = new Label( "FName." );
            FNameL.setLayoutX( 430 );
            FNameL.setLayoutY( 144 );
            FNameL.setPrefSize( 75, 27 );
            FNameL.setTextFill( Color.WHITE );

            TextField LName = new TextField();
            LName.setLayoutX( 531 );
            LName.setLayoutY( 199 );
            LName.setPrefSize( 179, 27 );
            LName.setPromptText( "ex: Faraj" );
            LName.setEditable( false );

            Label LNameL = new Label( "LName." );
            LNameL.setLayoutX( 430 );
            LNameL.setLayoutY( 199 );
            LNameL.setPrefSize( 75, 27 );
            LNameL.setTextFill( Color.WHITE );

            TextField DateOFB = new TextField();
            DateOFB.setLayoutX( 531 );
            DateOFB.setLayoutY( 254 );
            DateOFB.setPrefSize( 179, 27 );
            DateOFB.setPromptText( "ex: 30/12/2019" );
            DateOFB.setEditable( false );

            Label DOPL = new Label( "DOB." );
            DOPL.setLayoutX( 430 );
            DOPL.setLayoutY( 254 );
            DOPL.setPrefSize( 75, 27 );
            DOPL.setTextFill( Color.WHITE );

            TextField gender = new TextField();
            gender.setLayoutX( 531 );
            gender.setLayoutY( 309 );
            gender.setPrefSize( 179, 27 );
            gender.setPromptText( "Male" );
            gender.setEditable(false);

            Label genderL = new Label( "Gender." );
            genderL.setLayoutX( 430 );
            genderL.setLayoutY( 309 );
            genderL.setPrefSize( 75, 27 );
            genderL.setTextFill( Color.WHITE );

            TextField Email = new TextField(  );
            Email.setLayoutX( 531 );
            Email.setLayoutY( 364 );
            Email.setPrefSize( 179, 27 );
            Email.setPromptText( "mohammad@bzu.com" );
            Email.setEditable( false );

            TextField phone = new TextField(  );
            phone.setLayoutX( 531 );
            phone.setLayoutY( 419 );
            phone.setPrefSize( 179, 27 );
            phone.setPromptText( "0123456789" );
            phone.setEditable( false );

            TextField Address = new TextField(  );
            Address.setLayoutX( 531 );
            Address.setLayoutY( 474 );
            Address.setPrefSize( 179, 27 );
            Address.setPromptText( "ex: Somewhere in the world" );
            Address.setEditable( false );

            Label EmailL = new Label( "Email." );
            EmailL.setLayoutX( 430 );
            EmailL.setLayoutY( 364 );
            EmailL.setPrefSize( 75, 27 );
            EmailL.setTextFill( Color.WHITE );

            Label phoneL = new Label( "Phone." );
            phoneL.setLayoutX( 430 );
            phoneL.setLayoutY( 419 );
            phoneL.setPrefSize( 75, 27 );
            phoneL.setTextFill( Color.WHITE );

            Label AddressL = new Label( "Address." );
            AddressL.setLayoutX( 430 );
            AddressL.setLayoutY( 474 );
            AddressL.setPrefSize( 75, 27 );
            AddressL.setTextFill( Color.WHITE );

            NumberOfCopiesText.setLayoutX( 231 );
            NumberOfCopiesText.setLayoutY( 419 );
            NumberOfCopiesText.setPrefSize( 179, 27 );
            NumberOfCopiesText.setEditable( false );

            TextField addDate = new TextField();
            addDate.setLayoutX( 231 );
            addDate.setLayoutY( 474 );
            addDate.setPrefSize( 179, 27 );
            addDate.setEditable( false );

            Label addDateLabel = new Label( "Add date" );
            addDateLabel.setLayoutX( 41 );
            addDateLabel.setLayoutY( 474 );
            addDateLabel.setPrefSize( 75, 27 );
            addDateLabel.setTextFill( Color.WHITE );

            Button search = new Button( "Search" );
            search.setLayoutX( 531 );
            search.setLayoutY( 529 );
            search.setPrefSize( 179, 27 );



            TextField numberOfBarrows = new TextField();
            numberOfBarrows.setLayoutX( 831 );
            numberOfBarrows.setLayoutY( 0 );
            numberOfBarrows.setPrefSize( 179, 27 );
            numberOfBarrows.setEditable( false );

            numberOfBarrows.setText("");

            Label numberOfBarrowsLabel = new Label( "barrows #." );
            numberOfBarrowsLabel.setLayoutX( 730 );
            numberOfBarrowsLabel.setLayoutY( 0 );
            numberOfBarrowsLabel.setPrefSize( 75, 27 );
            numberOfBarrowsLabel.setTextFill( Color.WHITE );

            TextField numberOfloans = new TextField();
            numberOfloans.setLayoutX( 1131 );
            numberOfloans.setLayoutY( 0 );
            numberOfloans.setPrefSize( 179, 27 );
            numberOfloans.setEditable( false );
            numberOfloans.setText(getNumberOfLoners());

            Label numberOfloansLabel = new Label( "loans #." );
            numberOfloansLabel.setLayoutX( 1030 );
            numberOfloansLabel.setLayoutY( 0 );
            numberOfloansLabel.setPrefSize( 75, 27 );
            numberOfloansLabel.setTextFill( Color.WHITE );

            TextField numberOfUsers = new TextField();
            numberOfUsers.setLayoutX( 531 ); // 231
            numberOfUsers.setLayoutY( 0 );
            numberOfUsers.setPrefSize( 179, 27 );
            numberOfUsers.setEditable( false );
            numberOfUsers.setText(getNumberOfUsers());

            Label numberOfUsersL = new Label( "users #." );
            numberOfUsersL.setLayoutX( 430 );
            numberOfUsersL.setLayoutY( 0 );
            numberOfUsersL.setPrefSize( 150, 27 );
            numberOfUsersL.setTextFill( Color.WHITE );

            TextField numberOfBooks = new TextField();
            numberOfBooks.setLayoutX( 231 ); //
            numberOfBooks.setLayoutY( 0 );
            numberOfBooks.setPrefSize( 179, 27 );
            numberOfBooks.setEditable( false );
            numberOfBooks.setText(getNumberOfBooks());

            Label numberOfBooksL = new Label( "books #." );
            numberOfBooksL.setLayoutX( 87 );
            numberOfBooksL.setLayoutY( 0 );
            numberOfBooksL.setPrefSize( 150, 27 );
            numberOfBooksL.setTextFill( Color.WHITE );



            search.setOnAction(e1 -> {
                if (UserID.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a User ID.");
                    alert.showAndWait();
                } else {
                    try {
                        Connection con = db.getConnection().connectDB();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM user, contact_info WHERE ID = '" + UserID.getText() + "'AND user.ID = contact_info.UserID;");
                        //System.out.println("SELECT * FROM user, contact_info WHERE ID = '" + UserID.getText() + "'AND user.ID = contact_info.UserID;");
                        if (rs.next()) {
                            FName.setText(rs.getString(2));
                            LName.setText(rs.getString(3));
                            DateOFB.setText(rs.getString(4));
                            gender.setText(rs.getString(5));
                            Email.setText(rs.getString(8));
                            phone.setText(rs.getString(9));
                            Address.setText(rs.getString(10));
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error");
                            alert.setContentText("User ID not found.");
                            alert.showAndWait();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            });
            SearchBookScene.setOnAction(e1 -> {
                try {
                    Connection con = db.getConnection().connectDB();
                    if(!ISBN.getText().equals("")) {
                        String sql = "SELECT * FROM book WHERE ISBN = " + ISBNText.getText() +";";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if(rs.next()){
                            AutherText.setText(rs.getString(2));
                            PublisherText.setText(rs.getString(3));
                            YearText.setText(rs.getString(4));
                            TitleText.setText(rs.getString(5));
                            PriceText.setText(rs.getString(6));
                            NumberOfCopiesText.setText(rs.getString(7));
                            addDate.setText(rs.getString(8));

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Book found");
                            alert.setHeaderText("Book found");
                            alert.setContentText("Book found");
                            alert.showAndWait();
                        }
                        else{
                            AutherText.setText("");
                            PublisherText.setText("");
                            YearText.setText("");
                            TitleText.setText("");
                            PriceText.setText("");
                            NumberOfCopiesText.setText("");
                            addDate.setText("");
                        }
                    } else {
                        AutherText.setText("");
                        PublisherText.setText("");
                        YearText.setText("");
                        TitleText.setText("");
                        PriceText.setText("");
                        NumberOfCopiesText.setText("");
                        addDate.setText("");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Book not found");
                        alert.setHeaderText("Error");
                        alert.setContentText("Please fill the ISBN");
                        alert.showAndWait();
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            });

            SearchBookScene.setPrefSize( 179, 46 );
            SearchBookScene.setLayoutX( 231 );
            SearchBookScene.setLayoutY( 529 );
            SecondPane.getChildren().addAll( NumberOfCopiesText, PriceText, SearchBookScene, TitleText, YearText, PublisherText, AutherText, ISBNText, NumberOfCopies, Price
                    , Title, Year, Publisher, Auther, ISBN ,lineChart , addDate, addDateLabel, LName, LNameL, DOPL, UserID, UserIDL, FName, FNameL,
                    gender, genderL, DateOFB, Email, EmailL, phone, phoneL, Address, AddressL, search, numberOfUsersL, numberOfBarrows, numberOfUsers , numberOfBooksL ,
                    numberOfBooks, numberOfBarrowsLabel, numberOfloansLabel, numberOfloans);


        } );

        Button ShowBookScene = new Button( "Show" );

        DisplayBook.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("");
            EditBook.setId("");
            DeleteBook.setId("");
            SearchBook.setId("");
            DisplayBook.setId("buttonCurr");
            LoanRequests.setId("");
            DisplayUser.setId("");

            TableView<Book> BookTable = new TableView<>();
            BookTable.setPadding( new Insets( 10, 10, 10, 10 ) );
            BookTable.setLayoutX( 3 );
            BookTable.setLayoutY( 14 );
            BookTable.setPrefSize( 1344, 450 );

            try {
                books.clear();
                readDataBaseBooks();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            TableColumn ISBN = new TableColumn<Book, Integer>( "ISBN" );
            ISBN.setPrefWidth( 100 );
            ISBN.setResizable( false );
            ISBN.setCellValueFactory( new PropertyValueFactory<Book, Integer>( "ISBN" ) );

            TableColumn<Book, String> Title = new TableColumn<>( "Title" );
            Title.setPrefWidth( 275 );
            Title.setResizable( false );
            Title.setCellValueFactory( new PropertyValueFactory<Book, String>( "title" ) );

            TableColumn<Book, String> Auther = new TableColumn<>( "Auther" );
            Auther.setPrefWidth( 275 );
            Auther.setResizable( false );
            Auther.setCellValueFactory( new PropertyValueFactory<Book, String>( "author" ) );

            TableColumn<Book, String> Publisher = new TableColumn<>( "Publisher" );
            Publisher.setPrefWidth( 275 );
            Publisher.setResizable( false );
            Publisher.setCellValueFactory( new PropertyValueFactory<Book, String>( "publisher" ) );

            TableColumn<Book, Double> price = new TableColumn<>( "price" );
            price.setPrefWidth( 100 );
            price.setResizable( false );
            price.setCellValueFactory( new PropertyValueFactory<Book, Double>( "price" ) );

            TableColumn<Book, Integer> quantity = new TableColumn<>( "quantity" );
            quantity.setPrefWidth( 100 );
            quantity.setResizable( false );
            quantity.setCellValueFactory( new PropertyValueFactory<Book, Integer>( "quantity" ) );

            TableColumn<Book, Integer> year = new TableColumn<>( "Year" );
            year.setPrefWidth( 100 );
            year.setResizable( false );
            year.setCellValueFactory( new PropertyValueFactory<Book, Integer>( "year" ) );

            TableColumn<Book, Button> deleteB = new TableColumn<>( "Delete" );
            deleteB.setPrefWidth( 100 );
            deleteB.setResizable( false );
            deleteB.setCellValueFactory( new PropertyValueFactory<Book, Button>( "deleteButton" ) );

            BookTable.getColumns().addAll( ISBN, Title, Auther, Publisher , price , quantity , year ,deleteB );
            BookTable.setItems(books);
            ShowBookScene.setLayoutX( 639 );
            ShowBookScene.setLayoutY( 496 );
            ShowBookScene.setPrefSize( 200, 46 );

            SecondPane.getChildren().addAll( BookTable, ShowBookScene );
        } );

        Button ShowUserScene = new Button( "Show" );

        DisplayUser.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("");
            EditBook.setId("");
            DeleteBook.setId("");
            SearchBook.setId("");
            DisplayBook.setId("");
            LoanRequests.setId("");
            DisplayUser.setId("buttonCurr");

            TableView<User> BookTable = new TableView<>();
            BookTable.setPadding( new Insets( 10, 10, 10, 10 ) );
            BookTable.setLayoutX( 3 );
            BookTable.setLayoutY( 14 );
            BookTable.setPrefSize( 1350, 450 );

            TableColumn UsserID = new TableColumn<User, Integer>( "User ID" );
            UsserID.setPrefWidth( 75 );
            UsserID.setResizable( false );
            UsserID.setCellValueFactory( new PropertyValueFactory<User, Integer>( "ID" ) );

            TableColumn<User, String> FName = new TableColumn<>( "FName" );
            FName.setPrefWidth( 150 );
            FName.setResizable( false );
            FName.setCellValueFactory( new PropertyValueFactory<User, String>( "Fname" ) );

            TableColumn<User, String> LName = new TableColumn<>( "LName" );
            LName.setPrefWidth( 150 );
            LName.setResizable( false );
            LName.setCellValueFactory( new PropertyValueFactory<User, String>( "Lname" ) );

            TableColumn<User, String> DataOfBirth = new TableColumn<>( "DataOfBirth" );
            DataOfBirth.setPrefWidth( 150 );
            DataOfBirth.setResizable( false );
            DataOfBirth.setCellValueFactory( new PropertyValueFactory<User, String>( "dateOfBirth" ) );

            TableColumn<User, String> Gender = new TableColumn<>( "Gender" );
            Gender.setPrefWidth( 100 );
            Gender.setResizable( false );
            Gender.setCellValueFactory( new PropertyValueFactory<User, String>( "gender" ) );

            TableColumn<User, String> Email = new TableColumn<>( "Email" );
            Email.setPrefWidth( 275 );
            Email.setResizable( false );
            Email.setCellValueFactory( new PropertyValueFactory<User, String>( "email" ) );


            TableColumn<User, String> Phone = new TableColumn<>( "Phone" );
            Phone.setPrefWidth( 150 );
            Phone.setResizable( false );
            Phone.setCellValueFactory( new PropertyValueFactory<User, String>( "phone" ) );

            TableColumn<User, Button> showInve = new TableColumn<>( "Show inv" );
            showInve.setPrefWidth( 140 );
            showInve.setResizable( false );
            showInve.setCellValueFactory( new PropertyValueFactory<User, Button>( "showInv" ) );

            TableColumn<User, Button> BAN = new TableColumn<>( "BAN" );
            BAN.setPrefWidth( 140 );
            BAN.setResizable( false );
            BAN.setCellValueFactory( new PropertyValueFactory<User, Button>( "ban" ) );



            readUsers();
            BookTable.setItems(users);
            BookTable.getColumns().addAll( UsserID, FName, LName, DataOfBirth,Email ,Phone ,  Gender, showInve , BAN );



            ShowUserScene.setLayoutX( 639 );
            ShowUserScene.setLayoutY( 496 );
            ShowUserScene.setPrefSize( 200, 46 );

            SecondPane.getChildren().addAll( BookTable, ShowUserScene );
        } );

        LoanRequests.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("");
            AddBook.setId("");
            EditBook.setId("");
            DeleteBook.setId("");
            SearchBook.setId("");
            DisplayBook.setId("");
            LoanRequests.setId("buttonCurr");
            DisplayUser.setId("");
            readLoneReqs();

            TableView<LoneRequest> BookTable = new TableView<>();
            BookTable.setPadding( new Insets( 10, 10, 10, 10 ) );
            BookTable.setLayoutX( 3 );
            BookTable.setLayoutY( 14 );
            BookTable.setPrefSize( 1344, 450 );

            TableColumn reqID = new TableColumn<LoneRequest, Integer>( "Request ID" );
            reqID.setPrefWidth( 75 );
            reqID.setResizable( false );
            reqID.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "loneRequestId" ) );

            TableColumn UserID = new TableColumn<LoneRequest, Integer>( "User ID" );
            UserID.setPrefWidth( 75 );
            UserID.setResizable( false );
            UserID.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "userId" ) );

            TableColumn<LoneRequest, String> Title = new TableColumn<>( "Title" );
            Title.setPrefWidth( 225 );
            Title.setResizable( false );
            Title.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "title" ) );

            TableColumn<LoneRequest, String> PName = new TableColumn<>( "Publisher name" );
            PName.setPrefWidth( 175 );
            PName.setResizable( false );
            PName.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "publisherName" ) );

            TableColumn<LoneRequest, String> AName = new TableColumn<>( "Author name" );
            AName.setPrefWidth( 175 );
            AName.setResizable( false );
            AName.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "AuthorName" ) );

            TableColumn<LoneRequest, Integer> year = new TableColumn<>( "Year" );
            year.setPrefWidth( 75 );
            year.setResizable( false );
            year.setCellValueFactory( new PropertyValueFactory<LoneRequest, Integer>( "year" ) );

            TableColumn<LoneRequest, Double> price = new TableColumn<>( "price" );
            price.setPrefWidth( 75 );
            price.setResizable( false );
            price.setCellValueFactory( new PropertyValueFactory<LoneRequest, Double>( "price" ) );

            TableColumn<LoneRequest, Integer> quantity = new TableColumn<>( "quantity" );
            quantity.setPrefWidth( 75 );
            quantity.setResizable( false );
            quantity.setCellValueFactory( new PropertyValueFactory<LoneRequest, Integer>( "quantity" ) );

            TableColumn<LoneRequest, String> reqDate = new TableColumn<>( "Request date" );
            reqDate.setPrefWidth( 125 );
            reqDate.setResizable( false );
            reqDate.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "requestDate" ) );

            TableColumn<LoneRequest, String> AppDate = new TableColumn<>( "Approve date" );
            AppDate.setPrefWidth( 125 );
            AppDate.setResizable( false );
            AppDate.setCellValueFactory( new PropertyValueFactory<LoneRequest, String>( "approveDate" ) );

            TableColumn<LoneRequest, Button> button = new TableColumn<>( "Approve" );
            button.setPrefWidth( 130 );
            button.setResizable( false );
            button.setCellValueFactory( new PropertyValueFactory<LoneRequest, Button>( "acceptButton" ) );

            BookTable.getColumns().addAll(reqID, UserID , Title, PName, AName , year, price, quantity, reqDate, AppDate ,button );
            BookTable.setItems( lonsRequest );
            SecondPane.getChildren().addAll( BookTable );

        } );

        BorrowRequests.setOnAction( e -> {
            SecondPane.getChildren().clear();
            BorrowRequests.setId("buttonCurr");
            AddBook.setId("");
            EditBook.setId("");
            DeleteBook.setId("");
            SearchBook.setId("");
            DisplayBook.setId("");
            LoanRequests.setId("");
            DisplayUser.setId("");
            TableView<Request> BookTable = new TableView<>();
            BookTable.setPadding( new Insets( 10, 10, 10, 10 ) );
            BookTable.setLayoutX( 1 );
            BookTable.setLayoutY( 14 );
            BookTable.setPrefSize( 1344, 450 );

            TableColumn RequestID = new TableColumn<Request, Integer>( "Request ID" );
            RequestID.setPrefWidth( 100 );
            RequestID.setResizable( false );
            RequestID.setCellValueFactory( new PropertyValueFactory<Request, String>( "RequestId" ) );

            TableColumn UsserID = new TableColumn<Request, Integer>( "User ID" );
            UsserID.setPrefWidth( 75 );
            UsserID.setResizable( false );
            UsserID.setCellValueFactory( new PropertyValueFactory<Request, String>( "UserId" ) );

            TableColumn ISBN = new TableColumn<Request, Integer>( "ISBN" );
            ISBN.setPrefWidth( 75 );
            ISBN.setResizable( false );
            ISBN.setCellValueFactory( new PropertyValueFactory<Request, String>( "BookISBN" ) );

            TableColumn<Request, String> Title = new TableColumn<>( "Title" );
            Title.setPrefWidth( 200 );
            Title.setResizable( false );
            Title.setCellValueFactory( new PropertyValueFactory<Request, String>( "title" ) );


            TableColumn<Request, String> FName = new TableColumn<>( "FName" );
            FName.setPrefWidth( 150 );
            FName.setResizable( false );
            FName.setCellValueFactory( new PropertyValueFactory<Request, String>( "fName" ) );

            TableColumn<Request, String> LName = new TableColumn<>( "LName" );
            LName.setPrefWidth( 150 );
            LName.setResizable( false );
            LName.setCellValueFactory( new PropertyValueFactory<Request, String>( "lName" ) );

            TableColumn<Request, String> RequestData = new TableColumn<>( "Request Data" );
            RequestData.setPrefWidth( 160 );
            RequestData.setResizable( false );
            RequestData.setCellValueFactory( new PropertyValueFactory<Request, String>( "requestDate" ) );

            TableColumn<Request, String> ApprovalDate = new TableColumn<>( "Approval Date" );
            ApprovalDate.setPrefWidth( 160 );
            ApprovalDate.setResizable( false );
            ApprovalDate.setCellValueFactory( new PropertyValueFactory<Request, String>( "ApproveDate" ) );

            TableColumn<Request, Button> Accept = new TableColumn<>( "Accept" );
            Accept.setPrefWidth( 125 );
            Accept.setResizable( false );
            Accept.setCellValueFactory( new PropertyValueFactory<Request, Button>( "Approve" ) );

            TableColumn<Request, Button> Reject = new TableColumn<>( "Reject" );
            Reject.setPrefWidth( 125 );
            Reject.setResizable( false );
            Reject.setCellValueFactory( new PropertyValueFactory<Request, Button>( "Reject" ) );

            readBarReq();
            BookTable.setItems(barrowReq);
            BookTable.getColumns().addAll(RequestID, UsserID, ISBN, FName, LName, Title ,RequestData , ApprovalDate,  Accept , Reject);
            SecondPane.getChildren().addAll( BookTable );
        } );

        Logout.setOnAction( e -> {
            AdminStage.close();
            MainWindow s = new MainWindow();
            s.PrintSinInStage();
        } );


        RootPane.getChildren().add( SecondPane );

        // insert BackGround and Icon for Root Pane
        Background background = new Background(
                new BackgroundImage( new Image( "file:BackGround.jpg" ),
                        null, null, null, null ) );


        Scene scene = new Scene( RootPane, 1366, 768 );
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );

        RootPane.setBackground( background );
        AdminStage.getIcons().add( new Image( "file:logo.png" ) );
        AdminStage.setScene( scene );
        AdminStage.setTitle( "HEllO" );
        AdminStage.setMaximized( false );
        AdminStage.show();
    }

    private void readLoneReqs(){
        lonsRequest.clear();
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM `lonereq`";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );

            while(rs.next()){
                lonsRequest.add(new LoneRequest(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getString(9),
                        rs.getString(10)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void readBarReq(){
        barrowReq.clear();
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM `request`";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );

            while(rs.next()){
                barrowReq.add(new Request(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void readUsers(){
        users.clear();
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM user, login_info, contact_info WHERE user.ID = login_info.UserID AND user.ID = contact_info.UserID";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while(rs.next()){
                System.out.println(rs.getInt(1));
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(8), rs.getString(12),rs.getString(13), rs.getString(5), rs.getString(6)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String getNumberOfBar(){
        String number = "";
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT DISTINCT count(*), UserID FROM inventory order by count(*) desc";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while(rs.next()){
                number = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return number;
    }

    private String getNumberOfLoners(){
        String number = "";
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT count(*) FROM lonereq where approveDate IS NOT NULL;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while(rs.next()){
                number = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return number;
    }

    private String getNumberOfBooks(){
        String number = "";
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT count(*) FROM book;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while(rs.next()){
                number = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return number;
    }

    private String getNumberOfUsers(){
        String number = "";
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT count(*) FROM user;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while(rs.next()){
                number = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return number;
    }

    private void readDataBaseBooks() throws SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM book";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while (rs.next()) {
                books.add(new Book(rs.getString(5), rs.getString(2), rs.getInt(1),
                        rs.getString(3), rs.getInt(4), rs.getDouble(6), rs.getInt(7), true, user.getID()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
