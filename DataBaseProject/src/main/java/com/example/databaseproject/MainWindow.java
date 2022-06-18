package com.example.databaseproject;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class MainWindow {

    static String email;

    public MainWindow () {
    }

    User user = new User();
    public void PrintSinInStage () {
        Stage SingINStage = new Stage();

        AnchorPane RootPane = new AnchorPane();

        ImageView logo = new ImageView( new Image( "file:logo.png" ) );
        logo.setFitHeight( 79 );
        logo.setFitWidth( 78 );
        logo.setLayoutX( 14 );
        logo.setLayoutY( 14 );
        RootPane.getChildren().add( logo );

        Label Email = new Label( "Email :" );
        Email.setTextFill(javafx.scene.paint.Color.WHITE);
        Label Password = new Label( "Password :" );
        Password.setTextFill(javafx.scene.paint.Color.WHITE);
        Email.setPrefSize( 70, 17 );
        Email.setLayoutX( 165 );
        Email.setLayoutY( 98 );
        Password.setPrefSize( 70, 17 );
        Password.setLayoutX( 165 );
        Password.setLayoutY( 148 );
        RootPane.getChildren().addAll( Email, Password );

        TextField EmailText = new TextField();
        EmailText.setPrefSize( 200, 27 );
        EmailText.setLayoutX( 277 );
        EmailText.setLayoutY( 93 );
        EmailText.setPromptText( "Your Email Address" );
        RootPane.getChildren().add( EmailText );

        PasswordField passwordText = new PasswordField();
        passwordText.setPrefSize( 200, 27 );
        passwordText.setLayoutX( 277 );
        passwordText.setLayoutY( 143 );
        passwordText.setPromptText( "Password" );
        RootPane.getChildren().add( passwordText );
        DataBaseConnection db = new DataBaseConnection();
        Button SingIn = new Button( "Sign In" );
        SingIn.setPrefSize( 100, 46 );
        SingIn.setLayoutX( 338 );
        SingIn.setLayoutY( 188 );
        RootPane.getChildren().add( SingIn );
        SingIn.setOnAction(e -> {
            if(EmailText.getText().equals("") || passwordText.getText().equals("")){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Sign in error");
                a.setContentText("All text fields are required");
                a.setHeaderText("VLIB");
                a.setGraphic(new ImageView(new Image( "file:logo.png")));
                a.show();
            } else {
                try{
                    email = EmailText.getText();
                    getTheUser();
                    Connection con = db.getConnection().connectDB();
                    String command  = "SELECT Password, UserID FROM Login_Info where Email = '" + EmailText.getText() + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(command);
                    if(rs.next()){
                        String password = rs.getString(1);
                        int userID = rs.getInt(2);
                        if(rs.getString(1).equals(passwordText.getText())){
                            String command1  = "SELECT IS_Mod FROM user where ID = " + userID + ";";
                            Statement stmt1 = con.createStatement();
                            ResultSet rs1 = stmt1.executeQuery(command1);
                            if(rs1.next()){
                                String isMod = rs1.getString(1);
                                if (!isMod.equals("T")){
                                    UserWindow userWindow = new UserWindow();
                                    userWindow.PrintUserStage(user);
                                    SingINStage.close();
                                } else {
                                    AdminWindow adminWindow = new AdminWindow();
                                    adminWindow.PrintAdminStage(user);
                                    SingINStage.close();
                                }
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Sign in error");
                            a.setContentText("Wrong password");
                            a.setHeaderText("VLIB");
                            a.setGraphic(new ImageView(new Image( "file:logo.png")));
                            a.show();
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Sign in error");
                        a.setContentText("Wrong user name");
                        a.setHeaderText("VLIB");
                        a.setGraphic(new ImageView(new Image( "file:logo.png")));
                        a.show();
                    }
                } catch (Exception error){

                }
            }
        });




        Line HRLine = new Line();
        HRLine.setLayoutX( 374 );
        HRLine.setLayoutY( 249 );
        HRLine.setStartX( -354.5 );
        HRLine.setEndX( 206.5 );
        HRLine.setStartY( 0 );
        HRLine.setEndY( 0 );
        RootPane.getChildren().add( HRLine );

        HBox ButtonPane = new HBox( 10 );
        ButtonPane.setLayoutX( 166 );
        ButtonPane.setLayoutY( 277 );


        Button SignUp = new Button( "Sign Up" );
        SignUp.setPrefSize( 100, 46 );
        SignUp.setLayoutX( 477 );
        SignUp.setLayoutY( 273 );
        RootPane.getChildren().add( SignUp );



        Button ForgetPassword = new Button( "Forget password" );
        ForgetPassword.setPrefSize( 175, 46 );
        ForgetPassword.setLayoutX( 165 );
        ForgetPassword.setLayoutY( 273 );
        RootPane.getChildren().add( ForgetPassword );


        // insert BackGround and Icon for Root Pane
        Background background = new Background(
                new BackgroundImage( new Image( "file:BackGround.jpg" ),
                        null, null, null, null ) );


        Scene scene = new Scene( RootPane, 601, 338 );
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );

        RootPane.setBackground( background );
        SingINStage.getIcons().add( new Image( "file:logo.png" ) );
        SingINStage.setScene( scene );
        SingINStage.setTitle( "SignIN" );
        SingINStage.show();


        SignUp.setOnAction( e -> {
            SignUpSc signUpSc = new SignUpSc();
            SingINStage.setScene(signUpSc.getScene());
            SingINStage.show();

            signUpSc.getLoginB().setOnAction(e1 -> {
                SingINStage.setScene(scene);
            });

            signUpSc.getCancelB().setOnAction(e1 -> {
                SingINStage.close();
            });

            signUpSc.getSignUpB().setOnAction(e1 -> {
                if(signUpSc.getUserNameTF().getText().equals("") || signUpSc.getEmailTF().getText().equals("") || signUpSc.getPasswordTF().getText().equals("")
                        || signUpSc.getRepeatPasswordTF().getText().equals("") || signUpSc.getDatePicker().getValue() == null || signUpSc.getPhone().getText().equals("")
                        || signUpSc.getGender().getValue() == null) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Sign up error");
                    a.setContentText("All text fields are required");
                    a.setHeaderText("VLIB");
                    a.setGraphic(new ImageView(new Image( "file:logo.png")));
                    a.show();
                } else if (!signUpSc.getPasswordTF().getText().equals(signUpSc.getRepeatPasswordTF().getText())){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Sign up error");
                    a.setContentText("Passwords do not match");
                    a.setHeaderText("VLIB");
                    a.setGraphic(new ImageView(new Image( "file:logo.png")));
                    a.show();
                } else {
                    try {
                        Connection con = db.getConnection().connectDB();
                        String command = "SELECT Email FROM login_info where Email = '" + signUpSc.getEmailTF().getText() + "';";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(command);
                        if (rs.next()) {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Sign up error");
                            a.setContentText("Email already exists");
                            a.setHeaderText("VLIB");
                            a.setGraphic(new ImageView(new Image("file:logo.png")));
                            a.show();
                        } else {
                            System.out.println("Email not exists");
                            String[] name = signUpSc.getUserNameTF().getText().split(" ");
                            String command1 = "INSERT INTO user (FName, LName, Date_Of_Bairth, Gender, IS_Mod) values ('" +  name[0] + "', '" + name[1] + "', '" + signUpSc.getDatePicker().getValue()
                                    +"', '"+ signUpSc.getGender().getValue() + "', " + " 'F');";
                            Statement stmt1 = con.createStatement();
                            stmt1.executeUpdate(command1);
                            command1 = "select max(ID) from user;";

                            stmt1 = con.createStatement();
                            rs = stmt1.executeQuery(command1);

                            int userID = 0;

                            if (rs.next()) {
                                userID = rs.getInt(1);
                                System.out.println(userID);
                            }
                            command1 = "INSERT INTO login_info  values ("+ userID +", '" + signUpSc.getEmailTF().getText() + "', '" + signUpSc.getPasswordTF().getText() + "' );";
                            stmt1 = con.createStatement();
                            stmt1.executeUpdate(command1);

                            command1 = "INSERT INTO contact_info values ("+ userID +", '" + signUpSc.getEmailTF().getText() + "', '"+ signUpSc.getPhone().getText() +"', null );";

                            stmt1 = con.createStatement();
                            stmt1.executeUpdate(command1);

                            stmt1.close();
                            con.close();

                            MainWindow s = new MainWindow();
                            s.PrintSinInStage();
                            SingINStage.close();
                        }
                    } catch (Exception er) {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Sign up error");
                        a.setContentText("Something went wrong");
                        a.setHeaderText("VLIB");
                        a.setGraphic(new ImageView(new Image( "file:logo.png")));
                        a.show();
                        System.out.println(er);
                    }
                }
            });
        });

    }

    public void getTheUser() throws SQLException, ClassNotFoundException {
        String sql = "Select UserID from Login_Info where Email = '" + email + "'";
        System.out.println(sql);
        DataBaseConnection db = new DataBaseConnection();
        Connection con = db.getConnection().connectDB();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            int userIDCome = 0;
            if(rs.next()) {
                userIDCome = rs.getInt( 1 );
                sql = "Select * from User where ID = '" + userIDCome + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery( sql );
                if(rs.next()) {
                    user.setID(rs.getInt( 1 ) );
                    System.out.println(user.getID());
                    user.setFname(rs.getString( 2 ) );
                    user.setLname(rs.getString( 3 ) );
                    user.setDateOfBirth(rs.getString( 4 ) );
                    user.setGender(rs.getString( 5 ) );
                    user.setIsMod(rs.getString( 6 ) );
                    user.setEmail(email);
                    sql = "Select * from Contact_Info where UserID = '" + userIDCome + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery( sql );
                    if(rs.next()) {
                        user.setPhone(rs.getString( 3 ) );
                        user.setAddress(rs.getString( 4 ) );
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println( e );
        }
    }

}

