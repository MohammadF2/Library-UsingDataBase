package com.example.databaseproject;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Inventory {

    private int userID;
    private String  bookISBN;
    private String  bookTitle;
    private String  bookAuthor;
    private String  bookPublisher;
    private String aquiredDate;
    private String returnDate;
    private String status;
    private Button returnB;

    private int inventoryID;


    public Inventory(int userID, String bookISBN, String bookTitle, String bookAuthor, String bookPublisher, String aquiredDate, String returnDate, String status, int inventoryID) {
        this.userID = userID;
        this.bookISBN = bookISBN;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.aquiredDate = aquiredDate;
        this.returnDate = returnDate;
        this.status = status;
        this.inventoryID = inventoryID;
        returnB = new Button("Return");
        returnB.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        returnB.setId("buttonInv");

        if(!status.equals("No")) {
            returnB.setDisable(true);
            returnB.setText("Returned");
        }

        returnB.setPrefSize(116, 40);

        returnB.setOnAction(e -> {
            System.out.println("Return button clicked");
            DataBaseConnection db = new DataBaseConnection();
            try {
                CodeEnter code = new CodeEnter("Are you sure you want to return this book?", false);
                Stage enterCodeStage = new Stage();
                Scene sc = new Scene(code.getSp(), 480, 172);
                sc.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
                enterCodeStage.setScene(sc);
                enterCodeStage.setTitle("Return Book");
                enterCodeStage.show();
                code.getOkB().setOnAction(e1 ->{
                    try {
                        Connection con = db.getCon().connectDB();
                        Statement stmt = con.createStatement();
                        String sql = "Update inventory set ReturnDate = '" + java.time.LocalDate.now() + "', isReturned = 't' where inventoryID = " + inventoryID + " ;";
                        //System.out.println(sql);
                        stmt.executeUpdate(sql);
                        stmt.executeUpdate("Update book set NumberOfCopies = NumberOfCopies + 1 where ISBN = " + bookISBN + " ;");
                        returnB.setText("Returned");
                        returnB.setDisable(true);
                        enterCodeStage.close();
                        con.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Error in returning book");
                        enterCodeStage.close();
                        alert.showAndWait();
                        System.out.println(ex);
                    }

                });
                code.getCancelB().setOnAction(e1 -> {
                    enterCodeStage.close();
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    public Button getReturnB() {
        return returnB;
    }

    public void setReturnB(Button returnB) {
        this.returnB = returnB;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getAquiredDate() {
        return aquiredDate;
    }

    public void setAquiredDate(String aquiredDate) {
        this.aquiredDate = aquiredDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Inventory [userID=" + userID + ", bookISBN=" + bookISBN + ", aquiredDate=" + aquiredDate + ", returnDate=" + returnDate + ", status=" + status + "]";
    }

}
