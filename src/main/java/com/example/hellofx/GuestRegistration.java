package com.example.hellofx;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GuestRegistration {

    // Shared guest list
    private final ObservableList<Guest> guests =
            AppData.guests;

    public void show(Stage stage, String role) {

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

        logo.setFitWidth(60);
        logo.setFitHeight(60);
        logo.setPreserveRatio(true);

        // =========================
        // HEADER
        // =========================

        Label title =
                new Label("GUEST REGISTRATION");

        title.setStyle(
                "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        Label loggedIn =
                new Label("Logged in as: " + role);

        loggedIn.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        VBox titleBox =
                new VBox(
                        3,
                        title,
                        loggedIn
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =========================
        // DASHBOARD
        // =========================

        Button dashboardButton =
                new Button("← Back to Dashboard");

        dashboardButton.setStyle(
                "-fx-background-color: #3498DB;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        // =========================
        // LOGOUT
        // =========================

        Button logoutButton =
                new Button("Logout");

        logoutButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        // =========================
        // HEADER
        // =========================

        HBox header =
                new HBox(
                        15,
                        logo,
                        titleBox,
                        spacer,
                        dashboardButton,
                        logoutButton
                );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(15)
        );

        header.setStyle(
                "-fx-background-color: #1F4E78;"
        );

        // =========================
        // FORM TITLE
        // =========================

        Label formTitle =
                new Label("Guest Details");

        formTitle.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        // =========================
        // GUEST ID
        // =========================

        Label idLabel =
                new Label("Guest ID:");

        TextField idField =
                new TextField();

        idField.setPromptText(
                "Enter guest ID"
        );

        // =========================
        // FIRST NAME
        // =========================

        Label firstNameLabel =
                new Label("First Name:");

        TextField firstNameField =
                new TextField();

        firstNameField.setPromptText(
                "Enter first name"
        );

        // =========================
        // LAST NAME
        // =========================

        Label lastNameLabel =
                new Label("Last Name:");

        TextField lastNameField =
                new TextField();

        lastNameField.setPromptText(
                "Enter last name"
        );

        // =========================
        // PHONE
        // =========================

        Label phoneLabel =
                new Label("Phone Number:");

        TextField phoneField =
                new TextField();

        phoneField.setPromptText(
                "Enter phone number"
        );

        // =========================
        // EMAIL
        // =========================

        Label emailLabel =
                new Label("Email:");

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter email address"
        );

        // =========================
        // GENDER
        // =========================

        Label genderLabel =
                new Label("Gender:");

        ComboBox<String> genderBox =
                new ComboBox<>();

        genderBox.getItems().addAll(
                "Male",
                "Female"
        );

        genderBox.setPromptText(
                "Select gender"
        );

        // =========================
        // ROOM TYPE
        // =========================

        Label roomLabel =
                new Label("Room Type:");

        ComboBox<String> roomBox =
                new ComboBox<>();

        roomBox.getItems().addAll(
                "Standard",
                "Deluxe",
                "Family"
        );

        roomBox.setPromptText(
                "Select room type"
        );

        // =========================
        // NIGHTS
        // =========================

        Label nightsLabel =
                new Label("Number of Nights:");

        TextField nightsField =
                new TextField();

        nightsField.setPromptText(
                "Enter number of nights"
        );

        // =========================
        // ADDRESS
        // =========================

        Label addressLabel =
                new Label("Address:");

        TextArea addressField =
                new TextArea();

        addressField.setPromptText(
                "Enter guest address"
        );

        addressField.setPrefRowCount(3);

        // =========================
        // MESSAGE
        // =========================

        Label messageLabel =
                new Label();

        messageLabel.setWrapText(true);

        // =========================
        // BUTTONS
        // =========================

        Button registerButton =
                new Button("Register Guest");

        Button clearButton =
                new Button("Clear");

        registerButton.setStyle(
                "-fx-background-color: #27AE60;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 20;"
        );

        clearButton.setStyle(
                "-fx-background-color: #95A5A6;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 20;"
        );

        // =========================
        // FORM GRID
        // =========================

        GridPane grid =
                new GridPane();

        grid.setHgap(15);
        grid.setVgap(12);

        grid.add(idLabel, 0, 0);
        grid.add(idField, 1, 0);

        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);

        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameField, 1, 2);

        grid.add(phoneLabel, 0, 3);
        grid.add(phoneField, 1, 3);

        grid.add(emailLabel, 0, 4);
        grid.add(emailField, 1, 4);

        grid.add(genderLabel, 0, 5);
        grid.add(genderBox, 1, 5);

        grid.add(roomLabel, 0, 6);
        grid.add(roomBox, 1, 6);

        grid.add(nightsLabel, 0, 7);
        grid.add(nightsField, 1, 7);

        grid.add(addressLabel, 0, 8);
        grid.add(addressField, 1, 8);

        HBox buttons =
                new HBox(
                        10,
                        registerButton,
                        clearButton
                );

        grid.add(buttons, 1, 9);
        grid.add(messageLabel, 1, 10);

        // =========================
        // REGISTER METHOD
        // =========================

        Runnable registerGuest = () -> {

            String id =
                    idField.getText().trim();

            String firstName =
                    firstNameField.getText().trim();

            String lastName =
                    lastNameField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            String email =
                    emailField.getText().trim();

            String gender =
                    genderBox.getValue();

            String room =
                    roomBox.getValue();

            String nightsText =
                    nightsField.getText().trim();

            String address =
                    addressField.getText().trim();

            // =========================
            // VALIDATION
            // =========================

            if (id.isEmpty()
                    || firstName.isEmpty()
                    || lastName.isEmpty()
                    || phone.isEmpty()
                    || email.isEmpty()
                    || gender == null
                    || room == null
                    || nightsText.isEmpty()
                    || address.isEmpty()) {

                messageLabel.setText(
                        "Please complete all guest details."
                );

                messageLabel.setStyle(
                        "-fx-text-fill: #E74C3C;" +
                                "-fx-font-weight: bold;"
                );

                return;
            }

            int nights;

            try {

                nights =
                        Integer.parseInt(
                                nightsText
                        );

                if (nights <= 0) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {

                messageLabel.setText(
                        "Number of nights must be a valid number."
                );

                messageLabel.setStyle(
                        "-fx-text-fill: #E74C3C;" +
                                "-fx-font-weight: bold;"
                );

                nightsField.requestFocus();

                return;
            }

            // =========================
            // CHECK DUPLICATE ID
            // =========================

            for (Guest existing : guests) {

                if (existing.getGuestId()
                        .equalsIgnoreCase(id)) {

                    messageLabel.setText(
                            "A guest with this ID already exists."
                    );

                    messageLabel.setStyle(
                            "-fx-text-fill: #E74C3C;" +
                                    "-fx-font-weight: bold;"
                    );

                    idField.requestFocus();

                    return;
                }
            }

            // =========================
            // CREATE GUEST
            // =========================

            Guest guest =
                    new Guest(
                            id,
                            firstName,
                            lastName,
                            phone,
                            email,
                            gender,
                            room,
                            nights,
                            address
                    );

            // SAVE TO SHARED LIST
            AppData.guests.add(guest);

            messageLabel.setText(
                    "Guest registered successfully!"
            );

            messageLabel.setStyle(
                    "-fx-text-fill: #27AE60;" +
                            "-fx-font-weight: bold;"
            );

            // =========================
            // CLEAR FORM
            // =========================

            idField.clear();
            firstNameField.clear();
            lastNameField.clear();
            phoneField.clear();
            emailField.clear();
            genderBox.setValue(null);
            roomBox.setValue(null);
            nightsField.clear();
            addressField.clear();

            idField.requestFocus();
        };

        // =========================
        // REGISTER BUTTON
        // =========================

        registerButton.setOnAction(
                event -> registerGuest.run()
        );

        // =========================
        // ENTER NAVIGATION
        // =========================

        idField.setOnAction(
                event -> firstNameField.requestFocus()
        );

        firstNameField.setOnAction(
                event -> lastNameField.requestFocus()
        );

        lastNameField.setOnAction(
                event -> phoneField.requestFocus()
        );

        phoneField.setOnAction(
                event -> emailField.requestFocus()
        );

        emailField.setOnAction(
                event -> genderBox.requestFocus()
        );

        genderBox.setOnAction(
                event -> roomBox.requestFocus()
        );

        roomBox.setOnAction(
                event -> nightsField.requestFocus()
        );

        nightsField.setOnAction(
                event -> addressField.requestFocus()
        );

        /*
         * Address uses Enter for a new line.
         *
         * Press Ctrl + Enter to register.
         */
        addressField.setOnKeyPressed(event -> {

            if (event.getCode()
                    .toString()
                    .equals("ENTER")
                    && event.isControlDown()) {

                registerGuest.run();
            }
        });

        // =========================
        // CLEAR
        // =========================

        clearButton.setOnAction(event -> {

            idField.clear();
            firstNameField.clear();
            lastNameField.clear();
            phoneField.clear();
            emailField.clear();
            genderBox.setValue(null);
            roomBox.setValue(null);
            nightsField.clear();
            addressField.clear();

            messageLabel.setText("");

            idField.requestFocus();
        });

        // =========================
        // BACK TO DASHBOARD
        // =========================

        dashboardButton.setOnAction(event -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(
                    stage,
                    role
            );
        });

        // =========================
        // LOGOUT
        // =========================

        logoutButton.setOnAction(event -> {

            Login login =
                    new Login();

            login.start(stage);
        });

        // =========================
        // FORM CARD
        // =========================

        VBox formCard =
                new VBox(
                        15,
                        formTitle,
                        grid
                );

        formCard.setPadding(
                new Insets(25)
        );

        formCard.setMaxWidth(700);

        formCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: #D5DDE5;" +
                        "-fx-effect: dropshadow(" +
                        "gaussian, rgba(0,0,0,0.15), 10, 0, 0, 3);"
        );

        // =========================
        // ROOT
        // =========================

        VBox root =
                new VBox(
                        header,
                        formCard
                );

        root.setAlignment(
                Pos.TOP_CENTER
        );

        root.setStyle(
                "-fx-background-color: #F4F7FA;"
        );

        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1000,
                        800
                );

        stage.setTitle(
                "Lodge Billing System - Guest Registration"
        );

        stage.setScene(scene);

        stage.show();

        // Start cursor here
        idField.requestFocus();
    }
}