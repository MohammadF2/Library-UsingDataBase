package com.example.databaseproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Book implements Comparable<Book> {

    private String title;
    private String author;
    private int ISBN;
    private String publisher;
    private int year;
    private double price;
    private int quantity;

    private Button deleteButton;

    private Button Borrow;

    private int userId;


    public Book(String title, String author, int ISBN, String publisher, int year, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
    }

    public Book(String title, String author, int ISBN, String publisher, int year, double price, int quantity, boolean isAdmin, int userId) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;

        deleteButton = new Button("Delete");
        Borrow = new Button("Borrow");
        Borrow.setPrefSize(280, 40);
        deleteButton.setPrefSize(280, 40);

        if(isAdmin) {
            deleteButton.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
            deleteButton.setId("buttonInv");
        }

        if(!isAdmin) {
            Borrow.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
            Borrow.setId("buttonInv");
        }

        if(quantity == 0) {
            Borrow.setDisable(true);
            Borrow.setText("Out of stock");
        }

        DataBaseConnection db = new DataBaseConnection();
        try {
            Connection con = db.getConnection().connectDB();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select BookISBN from inventory where BookISBN = " + ISBN + " and isReturned = 'No' and UserID =" + userId + " ;");
            if (rs.next()) {
                Borrow.setText("Requested");
                Borrow.setDisable(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        deleteButton.setOnAction(e -> {
            System.out.println("Delete button clicked");
            try {
                CodeEnter code = new CodeEnter("Are you sure you want to delete this book?", false);
                Stage enterCodeStage = new Stage();
                Scene sc = new Scene(code.getSp(), 480, 172);
                sc.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
                enterCodeStage.setScene(sc);
                enterCodeStage.setTitle("Delete Book");
                enterCodeStage.show();
                code.getOkB().setOnAction(e1 -> {
                    try {
                        Connection con = db.getCon().connectDB();
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("DELETE FROM inventory WHERE BookISBN = " + ISBN);
                        stmt.executeUpdate("DELETE FROM request WHERE BookISBN = " + ISBN);
                        stmt.executeUpdate("DELETE FROM book WHERE ISBN = " + ISBN);
                        deleteButton.setDisable(true);
                        enterCodeStage.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                });

                code.getCancelB().setOnAction(e1 -> {
                    enterCodeStage.close();
                });
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Borrow.setOnAction(e -> {
            System.out.println("Borrow button clicked");
            try {
                Connection con = db.getCon().connectDB();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO request(UserID, BookISBN, Req_Date, Req_Approvel_Date) VALUES (" + userId + ", " + ISBN + ", '" + java.time.LocalDate.now() +"', " + null + ");");
                Borrow.setDisable(true);
                Borrow.setText("Requested");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public Button getBorrow() {
        return Borrow;
    }

    public void setBorrow(Button borrow) {
        Borrow = borrow;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Button getBarowButton() {
        return Borrow;
    }

    public void setBarowButton(Button barowButton) {
        this.Borrow = barowButton;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nPublisher: " + publisher + "\nYear: " + year + "\nPrice: " + price + "\nQuantity: " + quantity;
    }

    @Override
    public int compareTo(Book o) {
        return Integer.compare(this.ISBN, o.ISBN);
    }
}
