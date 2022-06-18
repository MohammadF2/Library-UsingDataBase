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
import javafx.scene.layout.StackPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class adminShowInv {

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

    private int userID;

    Scene scene;



    ObservableList<Inventory> inventories = FXCollections.observableArrayList();

    private TableView<Inventory> inv = new TableView<>();

    public adminShowInv(int userID) throws SQLException, ClassNotFoundException {

        this.userID = userID;

        inv.setPadding( new Insets( 10, 10, 10, 10 ) );
        inv.setId("BookTable");

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


        inventories.clear();
        readDataBaseBorrow();

        inv.getColumns().addAll( ISBN, Title, Auther, Publisher, AquiredDate, ReturnDate, isReturned );
        inv.setLayoutX( 0 );
        inv.setLayoutY( 1 );
        inv.setItems( inventories );
        inv.setLayoutX(2);
        inv.setLayoutY(2);
        inv.setPrefSize( 1280, 571 );

        sp.setPrefSize(1300,700);


        scene = new Scene(sp, 1300, 750);
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        okB.setPrefSize(110,25);
        okB.setLayoutX(568);
        okB.setLayoutY(664);

        root.getChildren().addAll(inv, okB);
        sp.getChildren().addAll(backgroundIV,root);
    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ObservableList<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(ObservableList<Inventory> inventories) {
        this.inventories = inventories;
    }

    public TableView<Inventory> getInv() {
        return inv;
    }

    public void setInv(TableView<Inventory> inv) {
        this.inv = inv;
    }

    public adminShowInv(String Header, boolean isCode) {

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

    private DataBaseConnection db = new DataBaseConnection();

    private void readDataBaseBorrow() throws SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM inventory where UserID = " + userID +" ;";
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


