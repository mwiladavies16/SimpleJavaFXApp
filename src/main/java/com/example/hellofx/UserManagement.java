package com.example.hellofx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserManagement {

    private final ObservableList<User> users =
            UserData.users;

    public void show(Stage stage, String role) {

        // Only Administrator can access this screen
        if (!role.equals("Administrator")) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR
                    );

            alert.setTitle(
                    "Access Denied"
            );

            alert.setHeaderText(
                    "Administrator Access Required"
            );

            alert.setContentText(
                    "Only Administrators can manage users."
            );

            alert.showAndWait();

            return;
        }

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
                new Label("USER MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        Label loggedIn =
                new Label(
                        "Logged in as: " + role
                );

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

        Button dashboardButton =
                new Button(
                        "← Back to Dashboard"
                );

        Button logoutButton =
                new Button("Logout");

        dashboardButton.setStyle(
                "-fx-background-color: #3498DB;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        logoutButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

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
        // FORM
        // =========================

        Label formTitle =
                new Label("Create User");

        formTitle.setStyle(
                "-fx-font-size: 21px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        Label usernameLabel =
                new Label("Username:");

        TextField usernameField =
                new TextField();

        usernameField.setPromptText(
                "Enter username"
        );

        Label passwordLabel =
                new Label("Password:");

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter password"
        );

        Label roleLabel =
                new Label("Role:");

        ComboBox<String> roleBox =
                new ComboBox<>();

        roleBox.getItems().addAll(
                "Administrator",
                "Receptionist"
        );

        roleBox.setPromptText(
                "Select role"
        );

        Button addButton =
                new Button("Create User");

        Button clearButton =
                new Button("Clear");

        addButton.setStyle(
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

        Label messageLabel =
                new Label();

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(12);

        form.add(
                usernameLabel,
                0,
                0
        );

        form.add(
                usernameField,
                1,
                0
        );

        form.add(
                passwordLabel,
                0,
                1
        );

        form.add(
                passwordField,
                1,
                1
        );

        form.add(
                roleLabel,
                0,
                2
        );

        form.add(
                roleBox,
                1,
                2
        );

        HBox formButtons =
                new HBox(
                        10,
                        addButton,
                        clearButton
                );

        form.add(
                formButtons,
                1,
                3
        );

        form.add(
                messageLabel,
                1,
                4
        );

        VBox formCard =
                new VBox(
                        15,
                        formTitle,
                        form
                );

        formCard.setPadding(
                new Insets(20)
        );

        formCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #D5DDE5;"
        );

        // =========================
        // USER TABLE
        // =========================

        TableView<User> table =
                new TableView<>();

        table.setItems(users);

        TableColumn<User, String> usernameColumn =
                new TableColumn<>("Username");

        usernameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getUsername()
                        )
        );

        TableColumn<User, String> roleColumn =
                new TableColumn<>("Role");

        roleColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getRole()
                        )
        );

        table.getColumns().addAll(
                usernameColumn,
                roleColumn
        );

        // =========================
        // DELETE
        // =========================

        Button deleteButton =
                new Button(
                        "Delete Selected User"
                );

        deleteButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        // =========================
        // CHANGE PASSWORD
        // =========================

        Button changePasswordButton =
                new Button(
                        "Change Password"
                );

        changePasswordButton.setStyle(
                "-fx-background-color: #F39C12;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        HBox tableButtons =
                new HBox(
                        10,
                        deleteButton,
                        changePasswordButton
                );

        // =========================
        // CREATE USER
        // =========================

        Runnable createUser = () -> {

            String username =
                    usernameField.getText()
                            .trim();

            String password =
                    passwordField.getText();

            String selectedRole =
                    roleBox.getValue();

            if (username.isEmpty()
                    || password.isEmpty()
                    || selectedRole == null) {

                messageLabel.setText(
                        "Please complete all fields."
                );

                messageLabel.setStyle(
                        "-fx-text-fill: #E74C3C;" +
                                "-fx-font-weight: bold;"
                );

                return;
            }

            for (User user : users) {

                if (user.getUsername()
                        .equalsIgnoreCase(username)) {

                    messageLabel.setText(
                            "Username already exists."
                    );

                    messageLabel.setStyle(
                            "-fx-text-fill: #E74C3C;" +
                                    "-fx-font-weight: bold;"
                    );

                    usernameField.requestFocus();

                    return;
                }
            }

            User newUser =
                    new User(
                            username,
                            password,
                            selectedRole
                    );

            users.add(newUser);

            messageLabel.setText(
                    "User created successfully."
            );

            messageLabel.setStyle(
                    "-fx-text-fill: #27AE60;" +
                            "-fx-font-weight: bold;"
            );

            usernameField.clear();
            passwordField.clear();
            roleBox.setValue(null);

            usernameField.requestFocus();
        };

        addButton.setOnAction(
                event -> createUser.run()
        );

        // =========================
        // ENTER NAVIGATION
        // =========================

        usernameField.setOnAction(
                event ->
                        passwordField.requestFocus()
        );

        passwordField.setOnAction(
                event ->
                        roleBox.requestFocus()
        );

        roleBox.setOnAction(
                event ->
                        createUser.run()
        );

        // =========================
        // CLEAR
        // =========================

        clearButton.setOnAction(event -> {

            usernameField.clear();
            passwordField.clear();
            roleBox.setValue(null);
            messageLabel.setText("");

            usernameField.requestFocus();
        });

        // =========================
        // DELETE USER
        // =========================

        deleteButton.setOnAction(event -> {

            User selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Please select a user first."
                );

                return;
            }

            // Prevent deleting the default admin
            if (selected.getUsername()
                    .equals("admin")) {

                showMessage(
                        "The default Administrator account cannot be deleted."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Delete User"
            );

            confirmation.setHeaderText(
                    "Delete selected user?"
            );

            confirmation.setContentText(
                    "Username: "
                            + selected.getUsername()
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (response ==
                                ButtonType.OK) {

                            users.remove(
                                    selected
                            );
                        }
                    });
        });

        // =========================
        // CHANGE PASSWORD
        // =========================

        changePasswordButton.setOnAction(
                event -> {

                    User selected =
                            table.getSelectionModel()
                                    .getSelectedItem();

                    if (selected == null) {

                        showMessage(
                                "Please select a user first."
                        );

                        return;
                    }

                    Dialog<String> dialog =
                            new Dialog<>();

                    dialog.setTitle(
                            "Change Password"
                    );

                    dialog.setHeaderText(
                            "Change password for "
                                    + selected.getUsername()
                    );

                    ButtonType saveType =
                            new ButtonType(
                                    "Save",
                                    ButtonBar.ButtonData.OK_DONE
                            );

                    dialog.getDialogPane()
                            .getButtonTypes()
                            .addAll(
                                    saveType,
                                    ButtonType.CANCEL
                            );

                    PasswordField newPassword =
                            new PasswordField();

                    newPassword.setPromptText(
                            "New password"
                    );

                    VBox box =
                            new VBox(
                                    10,
                                    new Label(
                                            "New Password:"
                                    ),
                                    newPassword
                            );

                    box.setPadding(
                            new Insets(15)
                    );

                    dialog.getDialogPane()
                            .setContent(box);

                    dialog.setResultConverter(
                            button -> {

                                if (button ==
                                        saveType) {

                                    return newPassword
                                            .getText();
                                }

                                return null;
                            }
                    );

                    dialog.showAndWait()
                            .ifPresent(newPass -> {

                                if (!newPass.isEmpty()) {

                                    selected.setPassword(
                                            newPass
                                    );

                                    showMessage(
                                            "Password changed successfully."
                                    );
                                }
                            });
                }
        );

        // =========================
        // DASHBOARD
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
        // ROOT
        // =========================

        VBox content =
                new VBox(
                        15,
                        formCard,
                        table,
                        tableButtons
                );

        content.setPadding(
                new Insets(20)
        );

        content.setStyle(
                "-fx-background-color: #F4F7FA;"
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        VBox root =
                new VBox(
                        header,
                        content
                );

        root.setStyle(
                "-fx-background-color: #F4F7FA;"
        );

        Scene scene =
                new Scene(
                        root,
                        1000,
                        750
                );

        stage.setTitle(
                "Lodge Billing System - User Management"
        );

        stage.setScene(scene);

        stage.show();

        usernameField.requestFocus();
    }

    private void showMessage(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Lodge Billing System"
        );

        alert.setHeaderText(null);

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}