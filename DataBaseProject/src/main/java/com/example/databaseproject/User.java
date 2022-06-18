package com.example.databaseproject;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class User {

    private int ID;
    private String Fname;
    private String Lname;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String isMod;
    private Button ban;
    private Button showInv;

    public User(int ID, String fname, String lname, String dateOfBirth, String email, String phone, String address, String gender, String isMod) {
        this.ID = ID;
        this.Fname = fname;
        this.Lname = lname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.isMod = isMod;


        ban = new Button("BAN");
        ban.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        ban.setId("buttonInv");

        showInv = new Button("inventory");
        showInv.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        showInv.setId("buttonInv");

        if(isMod.equals("T")){
            ban.setDisable(true);
            ban.setText("NoPermission");
            showInv.setDisable(true);
        }

        DataBaseConnection db = new DataBaseConnection();

        ban.setOnAction(e -> {
            try {
                Connection con = db.getConnection().connectDB();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("delete from user u where ID = " + ID);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User has been banned");
                alert.setContentText("User has been banned");
                alert.showAndWait();

                ban.setDisable(true);
                showInv.setDisable(true);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });


        showInv.setOnAction(e -> {
            try {
                adminShowInv a = new adminShowInv(ID);
                Connection con = db.getConnection().connectDB();
                Stage stage = new Stage();
                stage.setTitle("Inventory");
                stage.setScene(a.getScene());
                stage.show();
                a.getOkB().setOnAction(e1 -> {
                    stage.close();
                });
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }


    public Button getBan() {
        return ban;
    }

    public void setBan(Button ban) {
        this.ban = ban;
    }

    public Button getShowInv() {
        return showInv;
    }

    public void setShowInv(Button showInv) {
        this.showInv = showInv;
    }

    public User() {
        this.ID = 1;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsMod() {
        return isMod;
    }

    public void setIsMod(String isMod) {
        this.isMod = isMod;
    }


    @Override
    public String toString() {
        return "Name: " + Fname + " " + Lname + "\n" + "Date of Birth: " + dateOfBirth + "\n";
    }
}
