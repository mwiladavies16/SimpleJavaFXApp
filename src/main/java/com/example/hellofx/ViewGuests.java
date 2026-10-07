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

public class ViewGuests {

    private final ObservableList<Guest> guests =
            AppData.guests;

    private final ObservableList<Guest> filteredGuests =
            javafx.collections.FXCollections.observableArrayList();

    public void show(Stage stage, String role) {

        filteredGuests.clear();
        filteredGuests.addAll(guests);

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
        // TITLE
        // =========================

        Label title =
                new Label("REGISTERED GUESTS");

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
        // SEARCH
        // =========================

        Label searchLabel =
                new Label("Search:");

        searchLabel.setStyle(
                "-fx-font-weight: bold;"
        );

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search by ID, first name, last name or phone"
        );

        searchField.setPrefWidth(380);

        Button searchButton =
                new Button("Search");

        Button clearButton =
                new Button("Clear");

        searchButton.setStyle(
                "-fx-background-color: #3498DB;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        clearButton.setStyle(
                "-fx-background-color: #95A5A6;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        HBox searchBox =
                new HBox(
                        10,
                        searchLabel,
                        searchField,
                        searchButton,
                        clearButton
                );

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================
        // TABLE
        // =========================

        TableView<Guest> table =
                new TableView<>();

        table.setItems(
                filteredGuests
        );

        TableColumn<Guest, String> idColumn =
                new TableColumn<>("Guest ID");

        idColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getGuestId()
                        )
        );

        TableColumn<Guest, String> firstNameColumn =
                new TableColumn<>("First Name");

        firstNameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getFirstName()
                        )
        );

        TableColumn<Guest, String> lastNameColumn =
                new TableColumn<>("Last Name");

        lastNameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getLastName()
                        )
        );

        TableColumn<Guest, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getPhone()
                        )
        );

        TableColumn<Guest, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getEmail()
                        )
        );

        TableColumn<Guest, String> genderColumn =
                new TableColumn<>("Gender");

        genderColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getGender()
                        )
        );

        TableColumn<Guest, String> roomColumn =
                new TableColumn<>("Room");

        roomColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getRoomType()
                        )
        );

        TableColumn<Guest, String> nightsColumn =
                new TableColumn<>("Nights");

        nightsColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .getNights()
                                )
                        )
        );

        table.getColumns().addAll(
                idColumn,
                firstNameColumn,
                lastNameColumn,
                phoneColumn,
                emailColumn,
                genderColumn,
                roomColumn,
                nightsColumn
        );

        // =========================
        // DELETE BUTTON
        // =========================

        Button deleteButton =
                new Button("Delete Selected Guest");

        deleteButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        if (role.equals("Receptionist")) {

            deleteButton.setDisable(true);

            deleteButton.setText(
                    "Delete (Administrator Only)"
            );
        }

        // =========================
        // SEARCH
        // =========================

        Runnable searchGuests = () -> {

            String search =
                    searchField.getText()
                            .trim()
                            .toLowerCase();

            filteredGuests.clear();

            if (search.isEmpty()) {

                filteredGuests.addAll(
                        guests
                );

                return;
            }

            for (Guest guest : guests) {

                boolean idMatches =
                        guest.getGuestId()
                                .toLowerCase()
                                .contains(search);

                boolean firstNameMatches =
                        guest.getFirstName()
                                .toLowerCase()
                                .contains(search);

                boolean lastNameMatches =
                        guest.getLastName()
                                .toLowerCase()
                                .contains(search);

                boolean phoneMatches =
                        guest.getPhone()
                                .toLowerCase()
                                .contains(search);

                if (idMatches
                        || firstNameMatches
                        || lastNameMatches
                        || phoneMatches) {

                    filteredGuests.add(
                            guest
                    );
                }
            }
        };

        // =========================
        // SEARCH BUTTON
        // =========================

        searchButton.setOnAction(
                event -> searchGuests.run()
        );

        // =========================
        // ENTER SEARCH
        // =========================

        searchField.setOnAction(
                event -> searchGuests.run()
        );

        // =========================
        // CLEAR
        // =========================

        clearButton.setOnAction(event -> {

            searchField.clear();

            filteredGuests.clear();

            filteredGuests.addAll(
                    guests
            );

            searchField.requestFocus();
        });

        // =========================
        // DELETE
        // =========================

        deleteButton.setOnAction(event -> {

            Guest selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Please select a guest first."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Delete Guest"
            );

            confirmation.setHeaderText(
                    "Delete selected guest?"
            );

            confirmation.setContentText(
                    "Guest: "
                            + selected.getFullName()
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (response ==
                                ButtonType.OK) {

                            AppData.guests.remove(
                                    selected
                            );

                            filteredGuests.remove(
                                    selected
                            );
                        }
                    });
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
        // CONTENT
        // =========================

        VBox content =
                new VBox(
                        15,
                        searchBox,
                        table,
                        deleteButton
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

        // =========================
        // ROOT
        // =========================

        VBox root =
                new VBox(
                        header,
                        content
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
                        1200,
                        750
                );

        stage.setTitle(
                "Lodge Billing System - Registered Guests"
        );

        stage.setScene(scene);

        stage.show();

        searchField.requestFocus();
    }

    // =========================
    // MESSAGE
    // =========================

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