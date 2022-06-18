package com.example.databaseproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;

public class LoneRequest {

    private int loneRequestId;
    private int userId;
    private int bookISBN;
    private String publisherName;
    private String AuthorName;
    private int year;
    private String title;
    private double price;
    private int quantity;
    private String requestDate;

    private String approveDate;

    Button acceptButton;


    public LoneRequest(int loneRequestId, int userId, String publisherName, String authorName, int year, String title, double price, int quantity, String requestDate, String approveDate) {
        this.loneRequestId = loneRequestId;
        this.userId = userId;
        this.publisherName = publisherName;
        this.AuthorName = authorName;
        this.year = year;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.requestDate = requestDate;
        this.approveDate = approveDate;
        acceptButton = new Button("Accept");
        System.out.println("loneRequestId: " + loneRequestId);

        acceptButton.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        acceptButton.setId("buttonInv");

        if(approveDate != null){
            acceptButton.setDisable(true);
            acceptButton.setText("Accepted");
        }

        acceptButton.setOnAction(e -> {
           System.out.println("Accept button clicked");
            DataBaseConnection db = new DataBaseConnection();
            try {
                CodeEnter code = new CodeEnter("Are you sure you want to accept this request?", false);
                Stage enterCodeStage = new Stage();
                Scene sc = new Scene(code.getSp(), 480, 172);
                sc.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
                enterCodeStage.setScene(sc);
                enterCodeStage.setTitle("Accept Request");
                enterCodeStage.show();

                code.getCancelB().setOnAction(e1 -> {
                    enterCodeStage.close();
                });

                code.getOkB().setOnAction(e1 -> {
                    try {
                        String sql1 = "Update lonereq set approveDate = '" + java.time.LocalDate.now() + "' where loneReq_ID = " + loneRequestId + " ;";
                        System.out.println(sql1);
                        String sql2 = "Insert into book(Auther_Name, Publisher_Name, Year, Title, price, NumberOfCopies, addDate) values(" + " '" + authorName + "', '" + publisherName + "', " + year + ", '" + title + "', " + price + ", " + quantity + ", '" +java.time.LocalDate.now() +"');";
                        System.out.println(sql2);
                        Connection con = db.getConnection().connectDB();
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql1);
                        stmt.executeUpdate(sql2);
                        acceptButton.setDisable(true);
                        enterCodeStage.close();
                    }catch (Exception e2){
                        System.out.println(e2);
                    }
                });

            } catch (Exception error){
                System.out.println("Error: " + error);
            }


        });

    }

    public int getLoneRequestId() {
        return loneRequestId;
    }

    public void setLoneRequestId(int loneRequestId) {
        this.loneRequestId = loneRequestId;
    }

    public int getUserId() {
        return userId;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public void setAcceptButton(Button acceptButton) {
        this.acceptButton = acceptButton;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(int bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
