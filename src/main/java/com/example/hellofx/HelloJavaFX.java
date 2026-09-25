package com.example.hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        // Window title with student number
        stage.setTitle("Simple JavaFX App -202505170");

        // Welcome message
        Label label = new Label("Welcome, Keegan Sakala!");

        // Start button
        Button startButton = new Button("Start");

        startButton.setOnAction(event -> {
            label.setText("JavaFX Started!");
        });

        // Reset button
        Button resetButton = new Button("Reset");

        resetButton.setOnAction(event -> {
            label.setText("Welcome, Keegan Sakala!");
        });

        // Layout
        VBox root = new VBox(10);

        root.getChildren().addAll(
                label,
                startButton,
                resetButton
        );

        // Scene
        Scene scene = new Scene(root, 400, 200);

        // Set scene
        stage.setScene(scene);

        // Show window
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}