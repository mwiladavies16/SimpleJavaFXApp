package com.example.hellofx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ViewBills {

    private final ObservableList<Bill> bills =
            FXCollections.observableArrayList();

    private final ObservableList<Bill> filteredBills =
            FXCollections.observableArrayList();

    public void show(
            Stage stage,
            String role,
            ObservableList<Bill> savedBills
    ) {

        // Use the bills already saved by LodgeBilling
        bills.clear();
        bills.addAll(savedBills);

        filteredBills.clear();
        filteredBills.addAll(bills);

        // =========================
        // LOGO
        // =========================

        Image logoImage = new Image(
                getClass().getResourceAsStream("/logo.png")
        );

        ImageView logo = new ImageView(logoImage);

        logo.setFitWidth(60);
        logo.setFitHeight(60);
        logo.setPreserveRatio(true);

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("VIEW BILLS");

        title.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        Label user =
                new Label("Logged in as: " + role);

        user.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        VBox titleBox =
                new VBox(3, title, user);

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =========================
        // DASHBOARD BUTTON
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
                new Label("Search Bills:");

        searchLabel.setStyle(
                "-fx-font-weight: bold;"
        );

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search by bill number or guest name"
        );

        searchField.setPrefWidth(350);

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

        TableView<Bill> table =
                new TableView<>();

        table.setItems(filteredBills);

        TableColumn<Bill, String> billColumn =
                new TableColumn<>("Bill Number");

        billColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getBillNumber()
                )
        );

        TableColumn<Bill, String> guestColumn =
                new TableColumn<>("Guest Name");

        guestColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getGuestName()
                )
        );

        TableColumn<Bill, String> roomColumn =
                new TableColumn<>("Room Type");

        roomColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getRoomType()
                )
        );

        TableColumn<Bill, String> nightsColumn =
                new TableColumn<>("Nights");

        nightsColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        String.valueOf(
                                data.getValue().getNights()
                        )
                )
        );

        TableColumn<Bill, String> totalColumn =
                new TableColumn<>("Total");

        totalColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        String.format(
                                "K %.2f",
                                data.getValue().getTotal()
                        )
                )
        );

        TableColumn<Bill, String> paidColumn =
                new TableColumn<>("Paid");

        paidColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        String.format(
                                "K %.2f",
                                data.getValue().getPaid()
                        )
                )
        );

        TableColumn<Bill, String> balanceColumn =
                new TableColumn<>("Balance");

        balanceColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        String.format(
                                "K %.2f",
                                data.getValue().getBalance()
                        )
                )
        );

        table.getColumns().addAll(
                billColumn,
                guestColumn,
                roomColumn,
                nightsColumn,
                totalColumn,
                paidColumn,
                balanceColumn
        );

        // =========================
        // DELETE BUTTON
        // =========================

        Button deleteButton =
                new Button("Delete Selected Bill");

        deleteButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        // Only Administrator can delete
        if (role.equals("Receptionist")) {

            deleteButton.setDisable(true);

            deleteButton.setText(
                    "Delete (Administrator Only)"
            );
        }

        // =========================
        // SEARCH METHOD
        // =========================

        Runnable search = () -> {

            String text =
                    searchField.getText()
                            .trim()
                            .toLowerCase();

            filteredBills.clear();

            if (text.isEmpty()) {

                filteredBills.addAll(bills);

                return;
            }

            for (Bill bill : bills) {

                boolean billMatches =
                        bill.getBillNumber()
                                .toLowerCase()
                                .contains(text);

                boolean guestMatches =
                        bill.getGuestName()
                                .toLowerCase()
                                .contains(text);

                if (billMatches || guestMatches) {

                    filteredBills.add(bill);
                }
            }
        };

        // =========================
        // SEARCH BUTTON
        // =========================

        searchButton.setOnAction(event ->
                search.run()
        );

        // =========================
        // ENTER SEARCH
        // =========================

        searchField.setOnAction(event ->
                search.run()
        );

        // =========================
        // CLEAR SEARCH
        // =========================

        clearButton.setOnAction(event -> {

            searchField.clear();

            filteredBills.clear();

            filteredBills.addAll(bills);

            searchField.requestFocus();
        });

        // =========================
        // DELETE
        // =========================

        deleteButton.setOnAction(event -> {

            Bill selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Please select a bill to delete."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Delete Bill"
            );

            confirmation.setHeaderText(
                    "Delete selected bill?"
            );

            confirmation.setContentText(
                    "Bill Number: "
                            + selected.getBillNumber()
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (response ==
                                ButtonType.OK) {

                            bills.remove(selected);

                            filteredBills.remove(
                                    selected
                            );

                            savedBills.remove(
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

        VBox root =
                new VBox(
                        header,
                        content
                );

        root.setStyle(
                "-fx-background-color: #F4F7FA;"
        );

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1150,
                        750
                );

        stage.setTitle(
                "Lodge Billing System - View Bills"
        );

        stage.setScene(scene);

        stage.show();

        searchField.requestFocus();
    }

    // =========================
    // MESSAGE
    // =========================

    private void showMessage(String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Lodge Billing System"
        );

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}