package com.example.databaseproject;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class
HelloApplication extends Application {
    @Override
    public void start ( Stage stage ) throws IOException {
        MainWindow ss = new MainWindow();
        ss.PrintSinInStage();
    }

    public static void main ( String[] args ) {
        launch();
    }
}