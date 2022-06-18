package com.example.databaseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserWindow {

    ObservableList<Book> books = FXCollections.observableArrayList();
    ObservableList<Inventory> inventories = FXCollections.observableArrayList();
    DataBaseConnection db = new DataBaseConnection();

    User user;



    public void PrintUserStage (User user) throws SQLException, ClassNotFoundException {
        this.user = user;
        String email = user.getEmail();
        Stage UserStage = new Stage();
        readDataBaseBooks();
        //Connection con = db.getConnection().connectDB(); // dataBase connection
        System.out.println("User Window" + "ID: " + user.getID());
        AnchorPane RootPane = new AnchorPane();
        RootPane.setPadding( new Insets( 20, 20, 20, 20 ) );
        HBox ButtonPane = new HBox( 5 );
        ButtonPane.setLayoutX( 6 );
        ButtonPane.setLayoutX( 14 );
        ButtonPane.setPrefSize( 1215, 58 );

        // Make Button
        Button ProfileButton = new Button( "Profile" );
        Button BookButton = new Button( "Book" );
        Button InvintoryButton = new Button( "invintory" );
        Button LoneButton = new Button( "Lone a Book" );
        Button LogoutButton = new Button( "Logout" );

        LogoutButton.setOnAction( e -> {
            UserStage.close();
            MainWindow mw = new MainWindow();
            mw.PrintSinInStage();
        } );

        ProfileButton.setPrefSize( 275, 45 );
        BookButton.setPrefSize( 275, 45 );
        InvintoryButton.setPrefSize( 275, 45 );
        LoneButton.setPrefSize( 275, 45 );
        LogoutButton.setPrefSize( 275, 45 );

        AnchorPane SecondPane = new AnchorPane();
        SecondPane.setLayoutX( 0 );
        SecondPane.setLayoutY( 53 );
        //make Button
        Button SaveButton = new Button( "Save" );
        Button cancelButton = new Button( "cancel" );


        ProfileButton.setOnAction( e -> {
            SecondPane.getChildren().clear();
            TextField FNameText = new TextField();
            TextField LNameText = new TextField();
            TextField EmailText = new TextField();
            ComboBox<String> Gender = new ComboBox<>();
            DatePicker DateText = new DatePicker();
            DateText.setPromptText("Date of birth");
            PasswordField passwordField = new PasswordField();
            PasswordField RePasswordField = new PasswordField();
            ImageView profileImage = new ImageView( new Image( "file:profile.png" ) );
            ImageView logoImage = new ImageView( new Image( "file:logo.png" ) );



            SaveButton.setOnAction( ea -> {
                try {
                    if(FNameText.getText().equals("") && LNameText.getText().equals("") && EmailText.getText().equals("") &&
                            passwordField.getText().equals("") && RePasswordField.getText().equals("")) {

                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Error");
                        a.setContentText("You have changed nothing");
                        a.setHeaderText("VLIB");
                        a.setGraphic(new ImageView(new Image( "files:logo.png")));
                        a.show();
                    } else {
                        String quere = "update user set ";
                        if(!FNameText.getText().equals("")) {
                            quere += "FName = '" + FNameText.getText() + "',";
                        }
                        if(!LNameText.getText().equals("")) {
                            quere += "LName = '" + LNameText.getText() + "'";
                        }
                        if(!quere.equals("update user set ")){
                            quere += " where ID = " + user.getID() + "";
                            Connection con = db.getConnection().connectDB();
                            try{
                                Statement stmt = con.createStatement();
                                stmt.executeUpdate(quere);
                                System.out.println("Update Success");
                                System.out.println(quere);
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }

                        }
                        if(!EmailText.getText().equals("")) {

                            String passQ = "update Login_Info set Email = '" + EmailText.getText() + "' where UserID = " + user.getID() + "";
                            Connection con = db.getConnection().connectDB();
                            try{
                                Statement stmt = con.createStatement();
                                stmt.executeUpdate(passQ);
                                System.out.println(passQ);
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }

                            passQ = "update contact_info set Email = '" + EmailText.getText() + "' where UserID = " + user.getID() + "";
                            try{
                                Statement stmt = con.createStatement();
                                stmt.executeUpdate(passQ);
                                System.out.println(passQ);
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                        }
                        if(!passwordField.getText().equals("") && !RePasswordField.getText().equals("")) {
                            if(passwordField.getText().equals(RePasswordField.getText()) && passwordField.getText().length() >= 6) {
                                String passQ = "update Login_Info set Password = '" + passwordField.getText() + "' where UserID = " + user.getID() + "";
                                Connection con = db.getConnection().connectDB();
                                try{
                                    Statement stmt = con.createStatement();
                                    stmt.executeUpdate(passQ);
                                    con.close();
                                }catch (Exception error){
                                    con.close();
                                }
                            }else {
                                Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setTitle("Error");
                                a.setContentText("Password is not correct");
                                a.setHeaderText("VLIB");
                                a.setGraphic(new ImageView(new Image( "file:logo.png")));
                                a.show();
                            }
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });


            cancelButton.setOnAction( ea -> {
            });


            // set Layout
            SaveButton.setLayoutX( 602 );
            SaveButton.setLayoutY( 400 );
            SaveButton.setPrefSize( 121, 52 );

            cancelButton.setLayoutX( 682 );
            cancelButton.setLayoutY( 475 );
            cancelButton.setPrefSize( 121, 52 );

            passwordField.setLayoutX( 520 );
            passwordField.setLayoutY( 268 );
            passwordField.setPromptText( " Password" );
            passwordField.setPrefSize( 277, 40 );

            RePasswordField.setLayoutX( 520 );
            RePasswordField.setLayoutY( 322 );
            RePasswordField.setPrefSize( 277, 40 );
            RePasswordField.setPromptText( " RePassword" );

            Gender.getItems().addAll( "Male", " Female" );
            EmailText.setLayoutX( 520 );
            EmailText.setLayoutY( 213 );
            EmailText.setPrefSize( 277, 40 );
            EmailText.setPromptText( "Email: ahmad123@example.com" );

            LNameText.setLayoutX( 664 );
            LNameText.setLayoutY( 164 );
            LNameText.setPrefSize( 133, 40 );
            LNameText.setPromptText( "Last Name" );

            FNameText.setLayoutX( 520 );
            FNameText.setLayoutY( 164 );
            FNameText.setPrefSize( 133, 40 );
            FNameText.setPromptText( "First Name" );

            profileImage.setLayoutX( 560 );
            profileImage.setLayoutY( 7 );
            profileImage.setFitWidth( 200 );
            profileImage.setFitHeight( 150 );

            logoImage.setFitWidth( 200 );
            logoImage.setFitHeight( 150 );
            logoImage.setLayoutX( 14 );
            logoImage.setLayoutY( 14 );
            SecondPane.getChildren().addAll( FNameText, LNameText, EmailText,  passwordField, RePasswordField, profileImage, logoImage, SaveButton );
            InvintoryButton.setId(null);
            ProfileButton.setId("buttonCurr");
            BookButton.setId(null);
            LoneButton.setId(null);

        } );

        Button BorrowButton = new Button( "Borrow" );

        BorrowButton.setOnAction(ea -> {
            CodeEnter codeEnter = new CodeEnter();
            Stage enterCodeStage = new Stage();
            Scene sc = new Scene(codeEnter.getSp(), 480, 172);
            sc.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
            enterCodeStage.setScene(sc);

            codeEnter.getCancelB().setOnAction(eaw -> {
                enterCodeStage.close();
            });

            codeEnter.getOkB().setOnAction(e -> {
                if (codeEnter.getCodeTF().getText().equals("")){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("VLIB");
                    a.setHeaderText("Error");
                    a.setContentText("Please write the book ISBN");
                    a.setGraphic(new ImageView(new Image( "file:logo.png")));
                    a.show();
                } else {
                    try{
                        String sql = "SELECT ISBN from book where ISBN = '" + codeEnter.getCodeTF().getText().trim() + "';";
                        Connection con = db.getCon().connectDB();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery( sql );
                        if(rs.next()) {
                            try {
                                sql = "INSERT into request(UserID, BookISBN, Req_Date, Req_Approvel_Date) values (" + user.getID() + ", " + codeEnter.getCodeTF().getText().trim() +" ," +
                                        " '" + java.time.LocalDate.now() + "', "+ null + ");";
                                System.out.println(sql);
                                con = db.getCon().connectDB();
                                stmt = con.createStatement();
                                stmt.executeUpdate( sql );
                                con.close();
                                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                                a.setTitle("VLIB");
                                a.setHeaderText("Confirmation");
                                a.setContentText("Your request has been sent");
                                a.setGraphic(new ImageView(new Image( "file:logo.png")));
                                a.show();
                            } catch (Exception e1) {
                                Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setTitle("VLIB");
                                a.setHeaderText("Error");
                                a.setContentText("Your request has not been sent");
                                a.setGraphic(new ImageView(new Image( "file:logo.png")));
                                a.show();
                                e1.printStackTrace();
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("VLIB");
                            a.setHeaderText("Error");
                            a.setContentText("Wrong ISBN!!");
                            a.setGraphic(new ImageView(new Image( "file:logo.png")));
                            a.show();
                        }
                    } catch (Exception error){
                        error.printStackTrace();
                    }

                }
            });

            enterCodeStage.show();
        });

        Button SearchButton = new Button( "Search" );

        SearchButton.setOnAction(e -> {
            SearchBookUser searchBookUser = new SearchBookUser();
            Stage searchStage = new Stage();
            searchStage.setScene(searchBookUser.getSc());
            searchStage.setTitle("Search");
            searchStage.show();
            searchBookUser.getCancelB().setOnAction(eaw -> {
                searchStage.close();
            });

            searchBookUser.getSearchB().setOnAction(ea -> {
                String sqlCommand = "SELECT * from book where ";
                if(!searchBookUser.getISBN().getText().equals("")){
                    sqlCommand += "ISBN = '" + searchBookUser.getISBN().getText().trim() + "'";
                }
                if(!searchBookUser.getTitle().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Title = '" + searchBookUser.getTitle().getText().trim() + "'";
                }

                if(!searchBookUser.getAuthor().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("") || !searchBookUser.getTitle().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Auther_Name = '" + searchBookUser.getAuthor().getText().trim() + "'";
                }

                if(!searchBookUser.getPublisher().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("") || !searchBookUser.getTitle().getText().equals("") || !searchBookUser.getAuthor().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Publisher_Name = '" + searchBookUser.getPublisher().getText().trim() + "'";
                }

                if(!searchBookUser.getYear().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("") || !searchBookUser.getTitle().getText().equals("") ||
                            !searchBookUser.getAuthor().getText().equals("") || !searchBookUser.getPublisher().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Year = '" + searchBookUser.getYear().getText().trim() + "'";
                }

                if(!sqlCommand.equals("SELECT * from book where ")){
                    try {
                        sqlCommand += ";";
                        Connection con = db.getCon().connectDB();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery( sqlCommand );
                        if (rs.next()){
                            books.clear();
                            do {
                                books.add(new Book(rs.getString(5), rs.getString(2), rs.getInt(1),
                                        rs.getString(3), rs.getInt(4), rs.getDouble(6), rs.getInt(7), false, user.getID()));
                            } while (rs.next());
                            con.close();
                            searchStage.close();
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("VLIB");
                            a.setHeaderText("Error");
                            a.setContentText("No books found");
                            a.setGraphic(new ImageView(new Image( "file:logo.png")));
                            a.show();
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("VLIB");
                    a.setHeaderText("Error");
                    a.setContentText("Please enter at least one field");
                    a.setGraphic(new ImageView(new Image( "file:logo.png")));
                    a.show();
                }
            });
        }); // this for book

        Button restSearchB = new Button( "Reset Search" ); // this for book

        Button restSearchB2 = new Button( "Reset Search" ); // this for inventory

        Button searchB = new Button( "Search" ); // this for inventory

        searchB.setOnAction(e -> {
            SearchBookUser searchBookUser = new SearchBookUser();
            Stage searchStage = new Stage();
            searchStage.setScene(searchBookUser.getSc());
            searchStage.setTitle("Search");
            searchStage.show();
            searchBookUser.getCancelB().setOnAction(eaw -> {
                searchStage.close();
            });

            searchBookUser.getSearchB().setOnAction(ea -> {
                String sqlCommand = "SELECT * from inventory where user_ID = " + user.getID() + " and ";
                if(!searchBookUser.getISBN().getText().equals("")){
                    sqlCommand += "ISBN = '" + searchBookUser.getISBN().getText().trim() + "'";
                }
                if(!searchBookUser.getTitle().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Title = '" + searchBookUser.getTitle().getText().trim() + "'";
                }

                if(!searchBookUser.getAuthor().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("") || !searchBookUser.getTitle().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Auther_Name = '" + searchBookUser.getAuthor().getText().trim() + "'";
                }

                if(!searchBookUser.getPublisher().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("") || !searchBookUser.getTitle().getText().equals("") || !searchBookUser.getAuthor().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Publisher_Name = '" + searchBookUser.getPublisher().getText().trim() + "'";
                }

                if(!searchBookUser.getYear().getText().equals("")){
                    if(!searchBookUser.getISBN().getText().equals("") || !searchBookUser.getTitle().getText().equals("") ||
                            !searchBookUser.getAuthor().getText().equals("") || !searchBookUser.getPublisher().getText().equals("")){
                        sqlCommand += " and ";
                    }
                    sqlCommand += "Year = '" + searchBookUser.getYear().getText().trim() + "'";
                }

                if(!sqlCommand.equals("SELECT * from book where ")){
                    try {
                        sqlCommand += ";";
                        Connection con = db.getCon().connectDB();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery( sqlCommand );
                        if (rs.next()){
                            books.clear();
                            do {
                                books.add(new Book(rs.getString(5), rs.getString(2), rs.getInt(1),
                                        rs.getString(3), rs.getInt(4), rs.getDouble(6), rs.getInt(7) , false, user.getID()));
                            } while (rs.next());
                            con.close();
                            searchStage.close();
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("VLIB");
                            a.setHeaderText("Error");
                            a.setContentText("No books found");
                            a.setGraphic(new ImageView(new Image( "file:logo.png")));
                            a.show();
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("VLIB");
                    a.setHeaderText("Error");
                    a.setContentText("Please enter at least one field");
                    a.setGraphic(new ImageView(new Image( "files:logo.png")));
                    a.show();
                }
            });
        });

        restSearchB.setOnAction( e -> {
            books.clear();
            try {
                readDataBaseBooks();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        BookButton.setOnAction( e -> {
            SecondPane.getChildren().clear();
            TableView<Book> BookTable = new TableView<>();
            BookTable.setPadding( new Insets( 10, 10, 10, 10 ) );

            TableColumn<Book, Integer> ISBN = new TableColumn ("ISBN" );
            ISBN.setPrefWidth( 75 );
            ISBN.setResizable( false );
            ISBN.setCellValueFactory( new PropertyValueFactory<Book, Integer>( "ISBN" ) );

            TableColumn<Book, String> Title = new TableColumn<>( "Title" );
            Title.setPrefWidth( 256 );
            Title.setResizable( false );
            Title.setCellValueFactory( new PropertyValueFactory<Book, String>( "title" ) );

            TableColumn<Book, String> Auther = new TableColumn<>( "Auther" );
            Auther.setPrefWidth( 206 );
            Auther.setResizable( false );
            Auther.setCellValueFactory( new PropertyValueFactory<Book, String>( "author" ) );

            TableColumn<Book, String> Publisher = new TableColumn<>( "Publisher" );
            Publisher.setPrefWidth( 206 );
            Publisher.setResizable( false );
            Publisher.setCellValueFactory( new PropertyValueFactory<Book, String>( "publisher" ) );

            TableColumn<Book, String> year = new TableColumn<>( "Year" );
            year.setPrefWidth( 130 );
            year.setResizable( false );
            year.setCellValueFactory( new PropertyValueFactory<Book, String>( "year" ) );

            TableColumn<Book, String> quantity = new TableColumn<>( "quantity" );
            quantity.setPrefWidth( 130 );
            quantity.setResizable( false );
            quantity.setCellValueFactory( new PropertyValueFactory<Book, String>( "quantity" ) );

            TableColumn<Book, Button> barrow = new TableColumn<>( "barrow" );
            barrow.setPrefWidth( 280 );
            barrow.setResizable( false );
            barrow.setCellValueFactory( new PropertyValueFactory<Book, Button>( "Borrow" ) );


            BookTable.getColumns().addAll( ISBN, Title, Auther, Publisher, year, quantity , barrow );
            BookTable.setLayoutX( 0 );
            BookTable.setLayoutY( 1 );
            BookTable.setPrefSize( 1280, 571 );
            BookTable.setItems( books );

            BorrowButton.setLayoutX( 92 );
            BorrowButton.setLayoutY( 591 );
            BorrowButton.setPrefSize( 245, 46 );

            restSearchB.setLayoutX( 557 );
            restSearchB.setLayoutY( 591 );
            restSearchB.setPrefSize( 245, 46 );

            SearchButton.setLayoutX( 952 );
            SearchButton.setLayoutY( 591 );
            SearchButton.setPrefSize( 245, 46 );
            SecondPane.getChildren().addAll( BookTable, BorrowButton, SearchButton, restSearchB );
            InvintoryButton.setId(null);
            ProfileButton.setId(null);
            BookButton.setId("buttonCurr");
            LoneButton.setId(null);
        });

        Button ReturnButton = new Button( "Return" );

        InvintoryButton.setOnAction( e -> {
            inventories.clear();
            try {
                readDataBaseBorrow();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            SecondPane.getChildren().clear();
            TableView<Inventory> BookTable = new TableView<>();
            BookTable.setPadding( new Insets( 10, 10, 10, 10 ) );
            BookTable.setId("BookTable");

            TableColumn ISBN = new TableColumn<Inventory, Integer>( "ISBN" );
            ISBN.setPrefWidth( 75 );
            ISBN.setResizable( false );
            ISBN.setCellValueFactory( new PropertyValueFactory<Inventory, Integer>( "bookISBN" ) );

            TableColumn<Inventory, String> Title = new TableColumn<>( "Title" );
            Title.setPrefWidth( 256 );
            Title.setResizable( false );
            Title.setCellValueFactory( new PropertyValueFactory<Inventory, String>( "bookTitle" ) );

            TableColumn<Inventory, String> Auther = new TableColumn<>( "Auther" );
            Auther.setPrefWidth( 256 );
            Auther.setResizable( false );
            Auther.setCellValueFactory( new PropertyValueFactory<Inventory, String>( "bookAuthor" ) );

            TableColumn<Inventory, String> Publisher = new TableColumn<>( "Publisher" );
            Publisher.setPrefWidth( 256 );
            Publisher.setResizable( false );
            Publisher.setCellValueFactory( new PropertyValueFactory<Inventory, String>( "bookPublisher" ) );

            TableColumn<Inventory, String> AquiredDate = new TableColumn<>( "Aquired Date" );
            AquiredDate.setPrefWidth( 150 );
            AquiredDate.setResizable( false );
            AquiredDate.setCellValueFactory( new PropertyValueFactory<Inventory, String>( "aquiredDate" ) );

            TableColumn<Inventory, String> ReturnDate = new TableColumn<>( "Return Date" );
            ReturnDate.setPrefWidth( 150 );
            ReturnDate.setResizable( false );
            ReturnDate.setCellValueFactory( new PropertyValueFactory<Inventory, String>( "returnDate" ) );

            TableColumn<Inventory, Button> isReturned = new TableColumn<>( "Return" );
            isReturned.setPrefWidth( 116 );
            isReturned.setResizable( false );
            isReturned.setCellValueFactory( new PropertyValueFactory<Inventory, Button>( "returnB" ) );


            BookTable.getColumns().addAll( ISBN, Title, Auther, Publisher, AquiredDate, ReturnDate, isReturned );
            BookTable.setLayoutX( 0 );
            BookTable.setLayoutY( 1 );
            BookTable.setItems( inventories );
            BookTable.setPrefSize( 1280, 571 );

            restSearchB2.setLayoutX( 92 );
            restSearchB2.setLayoutY( 591 );
            restSearchB2.setPrefSize( 245, 46 );

            SearchButton.setLayoutX( 952 );
            SearchButton.setLayoutY( 591 );
            SearchButton.setPrefSize( 245, 46 );
            SecondPane.getChildren().addAll( BookTable, restSearchB2, SearchButton );
            InvintoryButton.setId("buttonCurr");
            ProfileButton.setId(null);
            BookButton.setId(null);
            LoneButton.setId(null);
        } ); //   ButtonPane.getChildren().addAll( ProfileButton, InvintoryButton, BookButton, LoneButton, LogoutButton );

        Button SubmitButton = new Button( "Submit" );

        SubmitButton.setOnAction( e -> {

        });

        LoneButton.setOnAction( e -> {
            SecondPane.getChildren().clear();
            TextField TitleText = new TextField();
            TextField AutherText = new TextField();
            TextField PublisherText = new TextField();
            TextField yearText = new TextField();
            TextField priceText = new TextField();
            //TextField bookISBNText = new TextField();
            TextField numberOfCopiesText = new TextField();
            Button SubmitButton1 = new Button( "Submit" );
            ImageView logoImage = new ImageView( new Image( "file:logo.png" ) );

            logoImage.setFitWidth( 200 );
            logoImage.setFitHeight( 150 );
            logoImage.setLayoutX( 40 );
            logoImage.setLayoutY( 31 );

            SubmitButton1.setLayoutX( 580 );
            SubmitButton1.setLayoutY( 579 );
            SubmitButton1.setPrefSize( 121, 52 );

            //bookISBNText.setLayoutX( 515 );
            //bookISBNText.setLayoutY( 129 );
            //bookISBNText.setPrefSize( 277, 40 );
            //bookISBNText.setPromptText( "ISBN" );

            SubmitButton1.setOnAction( e1 -> {

                if(TitleText.getText().isEmpty() || AutherText.getText().isEmpty() || PublisherText.getText().isEmpty() || yearText.getText().isEmpty() || priceText.getText().isEmpty() || numberOfCopiesText.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please fill all the fields");
                    alert.showAndWait();
                } else{
                    try {
                        String sql = "insert into loneReq(UserID, Publisher_Name, AutherName, Year , Title ,price, NumberOfCopies, requestDate, approveDate) values (" + user.getID() + ", '"  + PublisherText.getText() + "', '" + AutherText.getText() + "', " +
                                yearText.getText() + ", '" + TitleText.getText() + "', " + priceText.getText() + ", " + numberOfCopiesText.getText() +  ", '" + java.time.LocalDate.now() + "', " + null +  ");";
                        Connection con = db.getConnection().connectDB();
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        con.close();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Your request has been sent");
                        alert.showAndWait();

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            });


            TitleText.setLayoutX( 515 );
            TitleText.setLayoutY( 194 );
            TitleText.setPrefSize( 277, 40 );
            TitleText.setPromptText( " Title" );

            PublisherText.setLayoutX( 515 );
            PublisherText.setLayoutY( 257 );
            PublisherText.setPrefSize( 277, 40 );
            PublisherText.setPromptText( " Publisher" );

            AutherText.setLayoutX( 515 );
            AutherText.setLayoutY( 322 );
            AutherText.setPrefSize( 277, 40 );
            AutherText.setPromptText( " Auther" );


            yearText.setLayoutX( 515 );
            yearText.setLayoutY( 387 );
            yearText.setPrefSize( 277, 40 );
            yearText.setPromptText( " Year" );


            priceText.setLayoutX( 515 );
            priceText.setLayoutY( 452 );
            priceText.setPrefSize( 277, 40 );
            priceText.setPromptText( " Price" );


            numberOfCopiesText.setLayoutX( 515 );
            numberOfCopiesText.setLayoutY( 517 );
            numberOfCopiesText.setPrefSize( 277, 40 );
            numberOfCopiesText.setPromptText( " Number of Copies" );




            SecondPane.getChildren().addAll( AutherText, PublisherText, TitleText,yearText, priceText, numberOfCopiesText , SubmitButton1, logoImage );
            InvintoryButton.setId(null);
            ProfileButton.setId(null);
            BookButton.setId(null);
            LoneButton.setId("buttonCurr");
        } );

        ButtonPane.getChildren().addAll( ProfileButton, InvintoryButton, BookButton, LoneButton, LogoutButton );

        RootPane.getChildren().addAll( ButtonPane, SecondPane );

        // insert BackGround and Icon for Root Pane
        Background background = new Background(
                new BackgroundImage( new Image( "file:BackGround.jpg" ),
                        null, null, null, null ) );


        Scene scene = new Scene( RootPane, 1280, 720 );
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );

        RootPane.setBackground( background );
        UserStage.getIcons().add( new Image( "file:logo.png" ) );
        UserStage.setScene( scene );
        UserStage.setTitle( "HEllO" );
        UserStage.setMaximized( true );
        UserStage.show();
    }

    private void readDataBaseBooks() throws SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM book";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while (rs.next()) {
                books.add(new Book(rs.getString(5), rs.getString(2), rs.getInt(1),
                        rs.getString(3), rs.getInt(4), rs.getDouble(6), rs.getInt(7), false, user.getID()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void readDataBaseBorrow() throws SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM inventory where UserID = " + user.getID() +" ;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            String title = "";
            String auther = "";
            String publisher = "";
            String isbn = "";
            if(rs.next()){
                do {
                    isbn = rs.getString(2);
                    String sql1 = "SELECT * FROM book where ISBN = '" + isbn + "' ;";
                    Statement stmt1 = con.createStatement();
                    ResultSet rs1 = stmt1.executeQuery( sql1 );
                    if (rs1.next()){
                        title = rs1.getString(5);
                        auther = rs1.getString(2);
                        publisher = rs1.getString(3);
                    }
                    inventories.add(new Inventory(rs.getInt(1), isbn, title, auther, publisher,
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
                } while (rs.next());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    } // int userID, String bookISBN, String bookTitle, String bookAuthor, String bookPublisher, String aquiredDate, String returnDate, String status

}
