package com.example.databaseproject;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Request {

    private int UserId;
    private int BookISBN;
    private String requestDate;
    private String ApproveDate;

    private String title;
    private String fName;
    private String lName;

    private Button Approve;

    private Button Reject;
    private int RequestId;

    public Request() {}

    public Request(int UserId, int BookISBN, String requestDate, String ApproveDate, int RequestId) {
        this.UserId = UserId;
        this.BookISBN = BookISBN;
        this.requestDate = requestDate;
        this.ApproveDate = ApproveDate;
        this.RequestId = RequestId;

        Approve = new Button("Approve");
        Approve.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
        Approve.setId("buttonInv");

        Reject = new Button("Reject");
        Reject.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
        Reject.setId("buttonInv");

        DataBaseConnection db = new DataBaseConnection();
        try {
            Connection con = db.getConnection().connectDB();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select FName, LName from user where ID = " + UserId + " ;");
            while(rs.next()) {
                fName = rs.getString(1);
                lName = rs.getString(2);
            }
            rs = stmt.executeQuery("Select Title from book where ISBN = " + BookISBN + " ;");
            while(rs.next()) {
                title = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if(ApproveDate != null) {
            Approve.setDisable(true);
            Approve.setText("Approved");
            Reject.setDisable(true);
        }

        if(ApproveDate == null) {
            Approve.setDisable(false);
            Approve.setText("Approve");
        }
        Approve.setOnAction(e -> {
            try {
                Connection con = db.getConnection().connectDB();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Update request set Req_Approvel_Date = '" + java.time.LocalDate.now() + "' where Request_ID = " + RequestId + " ;");
                Approve.setDisable(true);
                Approve.setText("Approved");
                Reject.setText("Approved");
                Reject.setDisable(true);
                stmt.executeUpdate("insert into inventory(UserID, BookISBN, AquiredDate, ReturnDate, isReturned) values (" + UserId + ", " + BookISBN + ", '" + java.time.LocalDate.now() + "', '" + java.time.LocalDate.now().plusDays(15) + "', 'No');");
                stmt.executeUpdate("Update book set NumberOfCopies = NumberOfCopies - 1 where ISBN = " + BookISBN + " ;");

                System.out.println("Request Approved");
                System.out.println("insert into inventory(UserID, BookISBN, AquiredDate, ReturnDate, isReturned) values (" + UserId + ", " + BookISBN + ", '" + java.time.LocalDate.now() + "', '" + java.time.LocalDate.now().plusDays(15) + "', 'No');");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Request Approved");
                alert.setHeaderText("Request Approved");
                alert.setContentText("Request Approved");
                alert.showAndWait();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Reject.setOnAction(e -> {
            try {
                Connection con = db.getConnection().connectDB();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete from request where Request_ID = " + RequestId + " ;");
                Approve.setDisable(true);
                Approve.setText("Rejected");
                Reject.setText("Rejected");
                Reject.setDisable(true);
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        });


    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getBookISBN() {
        return BookISBN;
    }

    public void setBookISBN(int bookISBN) {
        BookISBN = bookISBN;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApproveDate() {
        return ApproveDate;
    }

    public void setApproveDate(String approveDate) {
        ApproveDate = approveDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Button getApprove() {
        return Approve;
    }

    public void setApprove(Button approve) {
        Approve = approve;
    }

    public Button getReject() {
        return Reject;
    }

    public void setReject(Button reject) {
        Reject = reject;
    }

    public int getRequestId() {
        return RequestId;
    }

    public void setRequestId(int requestId) {
        RequestId = requestId;
    }

    @Override
    public String toString() {
        return "Request [UserId=" + UserId + ", BookISBN=" + BookISBN + ", requestDate=" + requestDate + ", ApproveDate=" + ApproveDate + "]";
    }
}
