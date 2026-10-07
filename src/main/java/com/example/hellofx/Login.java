package com.example.hellofx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login {

    public void start(Stage stage) {

        // =========================
        // LOGO
        // =========================

        Image logoImage =
                new Image(
                        getClass().getResourceAsStream(
                                "/logo.png"
                        )
                );

        ImageView logo =
                new ImageView(logoImage);

        logo.setFitWidth(100);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label(
                        "LODGE BILLING SYSTEM"
                );

        title.setStyle(
                "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        Label subtitle =
                new Label(
                        "Login to your account"
                );

        subtitle.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-text-fill: #666666;"
        );

        // =========================
        // USERNAME
        // =========================

        Label usernameLabel =
                new Label("Username:");

        usernameLabel.setStyle(
                "-fx-font-weight: bold;"
        );

        TextField usernameField =
                new TextField();

        usernameField.setPromptText(
                "Enter username"
        );

        usernameField.setPrefHeight(40);

        // =========================
        // PASSWORD
        // =========================

        Label passwordLabel =
                new Label("Password:");

        passwordLabel.setStyle(
                "-fx-font-weight: bold;"
        );

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter password"
        );

        passwordField.setPrefHeight(40);

        // =========================
        // MESSAGE
        // =========================

        Label messageLabel =
                new Label();

        messageLabel.setWrapText(true);

        messageLabel.setAlignment(
                Pos.CENTER
        );

        // =========================
        // LOGIN BUTTON
        // =========================

        Button loginButton =
                new Button("LOGIN");

        loginButton.setPrefWidth(180);
        loginButton.setPrefHeight(42);

        loginButton.setStyle(
                "-fx-background-color: #1F4E78;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;"
        );

        // =========================
        // EXIT BUTTON
        // =========================

        Button exitButton =
                new Button("Exit");

        exitButton.setPrefWidth(180);
        exitButton.setPrefHeight(38);

        exitButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;"
        );

        // =========================
        // LOGIN METHOD
        // =========================

        Runnable login = () -> {

            String username =
                    usernameField.getText()
                            .trim();

            String password =
                    passwordField.getText();

            // Check empty fields
            if (username.isEmpty()
                    || password.isEmpty()) {

                messageLabel.setText(
                        "Please enter your username and password."
                );

                messageLabel.setStyle(
                        "-fx-text-fill: #E74C3C;" +
                                "-fx-font-weight: bold;"
                );

                return;
            }

            // =========================
            // CHECK USERS
            // =========================

            for (User user :
                    UserData.users) {

                if (user.getUsername()
                        .equalsIgnoreCase(username)
                        &&
                        user.getPassword()
                                .equals(password)) {

                    String role =
                            user.getRole();

                    // Clear fields
                    usernameField.clear();
                    passwordField.clear();

                    // =========================
                    // OPEN DASHBOARD
                    // =========================

                    Dashboard dashboard =
                            new Dashboard();

                    dashboard.show(
                            stage,
                            role
                    );

                    return;
                }
            }

            // =========================
            // INVALID LOGIN
            // =========================

            messageLabel.setText(
                    "Invalid username or password."
            );

            messageLabel.setStyle(
                    "-fx-text-fill: #E74C3C;" +
                            "-fx-font-weight: bold;"
            );

            passwordField.clear();
            passwordField.requestFocus();
        };

        // =========================
        // LOGIN BUTTON
        // =========================

        loginButton.setOnAction(
                event -> login.run()
        );

        // =========================
        // ENTER USERNAME
        // =========================

        usernameField.setOnAction(
                event ->
                        passwordField.requestFocus()
        );

        // =========================
        // ENTER PASSWORD
        // =========================

        passwordField.setOnAction(
                event ->
                        login.run()
        );

        // =========================
        // EXIT
        // =========================

        exitButton.setOnAction(event -> {

            Alert alert =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            alert.setTitle(
                    "Exit Application"
            );

            alert.setHeaderText(
                    "Are you sure you want to exit?"
            );

            alert.showAndWait()
                    .ifPresent(response -> {

                        if (response ==
                                ButtonType.OK) {

                            stage.close();
                        }
                    });
        });

        // =========================
        // FORM
        // =========================

        VBox form =
                new VBox(
                        10,
                        usernameLabel,
                        usernameField,
                        passwordLabel,
                        passwordField,
                        messageLabel,
                        loginButton,
                        exitButton
                );

        form.setAlignment(
                Pos.CENTER
        );

        form.setMaxWidth(350);

        // =========================
        // LOGIN CARD
        // =========================

        VBox card =
                new VBox(
                        15,
                        logo,
                        title,
                        subtitle,
                        form
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPadding(
                new Insets(35)
        );

        card.setMaxWidth(450);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(" +
                        "gaussian, rgba(0,0,0,0.25), 15, 0, 0, 5);"
        );

        // =========================
        // BACKGROUND
        // =========================

        Image backgroundImage =
                new Image(
                        getClass().getResourceAsStream(
                                "/Reception.png"
                        )
                );

        BackgroundImage bg =
                new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                100,
                                100,
                                true,
                                true,
                                true,
                                true
                        )
                );

        // =========================
        // ROOT
        // =========================

        StackPane root =
                new StackPane();

        root.setBackground(
                new Background(bg)
        );

        root.getChildren().add(card);

        StackPane.setAlignment(
                card,
                Pos.CENTER
        );

        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1000,
                        700
                );

        stage.setTitle(
                "Lodge Billing System - Login"
        );

        stage.setScene(scene);

        stage.show();

        // Put cursor in username
        usernameField.requestFocus();
    }
}