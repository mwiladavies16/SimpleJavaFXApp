package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageUsers {

    // =========================
    // USER MODEL
    // =========================
    public static class User {

        private String username;
        private String role;

        public User(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    // =========================
    // USER LIST
    // =========================
    private final ObservableList<User> users =
            FXCollections.observableArrayList(
                    new User("admin", "Administrator"),
                    new User("manager", "Manager"),
                    new User("reception", "Receptionist")
            );

    // =========================
    // START MANAGE USERS
    // =========================
    public void start(Stage stage, String role) {

        Label title = new Label("MANAGE USERS");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        // =========================
        // USERNAME FIELD
        // =========================
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        // =========================
        // ROLE COMBO BOX
        // =========================
        ComboBox<String> roleBox = new ComboBox<>();

        roleBox.getItems().addAll(
                "Administrator",
                "Manager",
                "Receptionist"
        );

        roleBox.setPromptText("Select role");

        // =========================
        // BUTTONS
        // =========================
        Button addButton = new Button("Add User");
        Button deleteButton = new Button("Delete User");
        Button clearButton = new Button("Clear");
        Button backButton = new Button("Back to Dashboard");

        // =========================
        // TABLE
        // =========================
        TableView<User> table = new TableView<>();

        TableColumn<User, String> usernameColumn =
                new TableColumn<>("Username");

        usernameColumn.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );

        TableColumn<User, String> roleColumn =
                new TableColumn<>("Role");

        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );

        table.getColumns().addAll(
                usernameColumn,
                roleColumn
        );

        table.setItems(users);

        usernameColumn.setPrefWidth(250);
        roleColumn.setPrefWidth(250);

        // =========================
        // ADD USER
        // =========================
        addButton.setOnAction(event -> {

            String username = usernameField.getText().trim();
            String selectedRole = roleBox.getValue();

            if (username.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Username",
                        "Please enter a username."
                );

                return;
            }

            if (selectedRole == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Role",
                        "Please select a role."
                );

                return;
            }

            // Check duplicate username
            for (User user : users) {

                if (user.getUsername()
                        .equalsIgnoreCase(username)) {

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Duplicate User",
                            "This username already exists."
                    );

                    return;
                }
            }

            // Add user
            users.add(
                    new User(username, selectedRole)
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "User Added",
                    "User '" + username + "' has been added successfully."
            );

            usernameField.clear();
            roleBox.setValue(null);
        });

        // =========================
        // DELETE USER
        // =========================
        deleteButton.setOnAction(event -> {

            User selectedUser =
                    table.getSelectionModel().getSelectedItem();

            if (selectedUser == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "No User Selected",
                        "Please select a user to delete."
                );

                return;
            }

            Alert confirmation = new Alert(
                    Alert.AlertType.CONFIRMATION
            );

            confirmation.setTitle("Delete User");
            confirmation.setHeaderText("Delete Selected User?");
            confirmation.setContentText(
                    "Are you sure you want to delete user: "
                            + selectedUser.getUsername() + "?"
            );

            confirmation.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {

                    users.remove(selectedUser);

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "User Deleted",
                            "User deleted successfully."
                    );
                }
            });
        });

        // =========================
        // CLEAR FIELDS
        // =========================
        clearButton.setOnAction(event -> {

            usernameField.clear();
            roleBox.setValue(null);

        });

        // =========================
        // BACK TO DASHBOARD
        // =========================
        backButton.setOnAction(event -> {

            Dashboard dashboard = new Dashboard();

            dashboard.show(stage, role);
        });

        // =========================
        // FORM
        // =========================
        VBox form = new VBox(
                10,
                new Label("Username:"),
                usernameField,
                new Label("Role:"),
                roleBox
        );

        form.setPadding(new Insets(20));

        // =========================
        // BUTTON AREA
        // =========================
        HBox buttons = new HBox(
                10,
                addButton,
                deleteButton,
                clearButton
        );

        buttons.setAlignment(Pos.CENTER);

        // =========================
        // BOTTOM AREA
        // =========================
        HBox bottom = new HBox(
                backButton
        );

        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(15));

        // =========================
        // MAIN LAYOUT
        // =========================
        VBox top = new VBox(
                15,
                title,
                form,
                buttons
        );

        top.setPadding(new Insets(20));
        top.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

        root.setTop(top);
        root.setCenter(table);
        root.setBottom(bottom);

        // =========================
        // SCENE
        // =========================
        Scene scene = new Scene(
                root,
                650,
                600
        );

        stage.setTitle("Lodge Billing System - Manage Users");
        stage.setScene(scene);
        stage.show();
    }

    // =========================
    // ALERT METHOD
    // =========================
    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}