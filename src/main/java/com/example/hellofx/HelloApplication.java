package com.example.hellofx;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        Login login = new Login();

        login.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}