package com.example.hellofx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LodgeBilling {

    private final ObservableList<Bill> bills =
            AppData.bills;

    public void start(Stage stage, String role) {

        // =========================
        // LOGO
        // =========================

        Image logoImage =
                new Image(
                        getClass().getResourceAsStream("/logo.png")
                );

        ImageView logo =
                new ImageView(logoImage);

        logo.setFitWidth(65);
        logo.setFitHeight(65);
        logo.setPreserveRatio(true);

        // =========================
        // HEADER
        // =========================

        Label title =
                new Label("LODGE BILLING SYSTEM");

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
                new VBox(4, title, loggedIn);

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button dashboardButton =
                new Button("← Back to Dashboard");

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
        // FORM TITLE
        // =========================

        Label formTitle =
                new Label("Guest Billing Details");

        formTitle.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        // =========================
        // GUEST
        // =========================

        Label guestLabel =
                new Label("Guest Name:");

        TextField guestField =
                new TextField();

        guestField.setPromptText(
                "Enter guest name"
        );

        // =========================
        // ROOM
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
        // EXTRAS
        // =========================

        Label extrasLabel =
                new Label("Extras (K):");

        TextField extrasField =
                new TextField("0");

        // =========================
        // PAID
        // =========================

        Label paidLabel =
                new Label("Amount Paid (K):");

        TextField paidField =
                new TextField("0");

        // =========================
        // NOTES
        // =========================

        Label notesLabel =
                new Label("Notes:");

        TextArea notesField =
                new TextArea();

        notesField.setPromptText(
                "Additional notes..."
        );

        notesField.setPrefRowCount(3);

        // =========================
        // RESULT
        // =========================

        Label resultLabel =
                new Label();

        resultLabel.setWrapText(true);

        resultLabel.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        // =========================
        // BUTTONS
        // =========================

        Button calculateButton =
                new Button("Calculate");

        Button saveButton =
                new Button("Save Bill");

        Button clearButton =
                new Button("Clear");

        calculateButton.setStyle(
                "-fx-background-color: #3498DB;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        saveButton.setStyle(
                "-fx-background-color: #27AE60;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        clearButton.setStyle(
                "-fx-background-color: #95A5A6;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        // =========================
        // FORM GRID
        // =========================

        GridPane grid =
                new GridPane();

        grid.setHgap(15);
        grid.setVgap(12);

        grid.add(
                guestLabel,
                0,
                0
        );

        grid.add(
                guestField,
                1,
                0
        );

        grid.add(
                roomLabel,
                0,
                1
        );

        grid.add(
                roomBox,
                1,
                1
        );

        grid.add(
                nightsLabel,
                0,
                2
        );

        grid.add(
                nightsField,
                1,
                2
        );

        grid.add(
                extrasLabel,
                0,
                3
        );

        grid.add(
                extrasField,
                1,
                3
        );

        grid.add(
                paidLabel,
                0,
                4
        );

        grid.add(
                paidField,
                1,
                4
        );

        grid.add(
                notesLabel,
                0,
                5
        );

        grid.add(
                notesField,
                1,
                5
        );

        HBox buttons =
                new HBox(
                        10,
                        calculateButton,
                        saveButton,
                        clearButton
                );

        grid.add(
                buttons,
                1,
                6
        );

        grid.add(
                resultLabel,
                1,
                7
        );

        // =========================
        // BILL TABLE
        // =========================

        TableView<Bill> billTable =
                new TableView<>();

        billTable.setItems(
                bills
        );

        TableColumn<Bill, String> billColumn =
                new TableColumn<>("Bill");

        billColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getBillNumber()
                        )
        );

        TableColumn<Bill, String> guestColumn =
                new TableColumn<>("Guest");

        guestColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getGuestName()
                        )
        );

        TableColumn<Bill, String> roomColumn =
                new TableColumn<>("Room");

        roomColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getRoomType()
                        )
        );

        TableColumn<Bill, String> nightsColumn =
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

        TableColumn<Bill, String> totalColumn =
                new TableColumn<>("Total");

        totalColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "K %.2f",
                                        data.getValue()
                                                .getTotal()
                                )
                        )
        );

        TableColumn<Bill, String> paidColumn =
                new TableColumn<>("Paid");

        paidColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "K %.2f",
                                        data.getValue()
                                                .getPaid()
                                )
                        )
        );

        TableColumn<Bill, String> balanceColumn =
                new TableColumn<>("Balance");

        balanceColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "K %.2f",
                                        data.getValue()
                                                .getBalance()
                                )
                        )
        );

        TableColumn<Bill, String> notesColumn =
                new TableColumn<>("Notes");

        notesColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getNotes()
                        )
        );

        billTable.getColumns().addAll(
                billColumn,
                guestColumn,
                roomColumn,
                nightsColumn,
                totalColumn,
                paidColumn,
                balanceColumn,
                notesColumn
        );

        // =========================
        // CALCULATE BUTTON
        // =========================

        calculateButton.setOnAction(event -> {

            calculateBill(
                    roomBox,
                    nightsField,
                    extrasField,
                    paidField,
                    resultLabel
            );
        });

        // =========================
        // SAVE BUTTON
        // =========================

        saveButton.setOnAction(event -> {

            saveBill(
                    guestField,
                    roomBox,
                    nightsField,
                    extrasField,
                    paidField,
                    notesField,
                    resultLabel
            );
        });

        // =========================
        // CLEAR BUTTON
        // =========================

        clearButton.setOnAction(event -> {

            clearForm(
                    guestField,
                    roomBox,
                    nightsField,
                    extrasField,
                    paidField,
                    notesField,
                    resultLabel
            );

            guestField.requestFocus();
        });

        // =========================
        // ENTER NAVIGATION
        // =========================

        guestField.setOnAction(event -> {

            roomBox.requestFocus();
            roomBox.show();
        });

        roomBox.setOnAction(event -> {

            nightsField.requestFocus();
        });

        nightsField.setOnAction(event -> {

            extrasField.requestFocus();
            extrasField.selectAll();
        });

        extrasField.setOnAction(event -> {

            paidField.requestFocus();
            paidField.selectAll();
        });

        paidField.setOnAction(event -> {

            notesField.requestFocus();
        });

        // Ctrl + Enter saves
        notesField.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER
                    && event.isControlDown()) {

                saveBill(
                        guestField,
                        roomBox,
                        nightsField,
                        extrasField,
                        paidField,
                        notesField,
                        resultLabel
                );
            }
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
                new Insets(20)
        );

        formCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #D5DDE5;"
        );

        // =========================
        // TABLE CARD
        // =========================

        Label tableTitle =
                new Label("Saved Bills");

        tableTitle.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        VBox tableCard =
                new VBox(
                        10,
                        tableTitle,
                        billTable
                );

        tableCard.setPadding(
                new Insets(20)
        );

        tableCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #D5DDE5;"
        );

        VBox.setVgrow(
                billTable,
                Priority.ALWAYS
        );

        VBox.setVgrow(
                tableCard,
                Priority.ALWAYS
        );

        // =========================
        // ROOT
        // =========================

        VBox root =
                new VBox(
                        15,
                        header,
                        formCard,
                        tableCard
                );

        root.setPadding(
                new Insets(
                        0,
                        20,
                        20,
                        20
                )
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
                        1150,
                        800
                );

        stage.setTitle(
                "Lodge Billing System - Billing"
        );

        stage.setScene(scene);

        stage.show();

        guestField.requestFocus();
    }

    // =====================================================
    // CALCULATE BILL
    // =====================================================

    private void calculateBill(
            ComboBox<String> roomBox,
            TextField nightsField,
            TextField extrasField,
            TextField paidField,
            Label resultLabel
    ) {

        try {

            if (roomBox.getValue() == null) {

                resultLabel.setText(
                        "Please select a room type."
                );

                return;
            }

            int nights =
                    Integer.parseInt(
                            nightsField.getText()
                                    .trim()
                    );

            double extras =
                    Double.parseDouble(
                            extrasField.getText()
                                    .trim()
                    );

            double paid =
                    Double.parseDouble(
                            paidField.getText()
                                    .trim()
                    );

            if (nights <= 0) {

                resultLabel.setText(
                        "Number of nights must be greater than zero."
                );

                return;
            }

            if (extras < 0 || paid < 0) {

                resultLabel.setText(
                        "Extras and payment cannot be negative."
                );

                return;
            }

            double roomPrice =
                    getRoomPrice(
                            roomBox.getValue()
                    );

            double roomCharge =
                    roomPrice * nights;

            double discount = 0;

            if (nights >= 5) {

                discount =
                        roomCharge * 0.05;
            }

            double total =
                    roomCharge
                            - discount
                            + extras;

            double balance =
                    total - paid;

            resultLabel.setText(
                    String.format(
                            "Total: K %.2f | Balance: K %.2f",
                            total,
                            balance
                    )
            );

        } catch (NumberFormatException e) {

            resultLabel.setText(
                    "Please enter valid numbers."
            );
        }
    }

    // =====================================================
    // SAVE BILL
    // =====================================================

    private void saveBill(
            TextField guestField,
            ComboBox<String> roomBox,
            TextField nightsField,
            TextField extrasField,
            TextField paidField,
            TextArea notesField,
            Label resultLabel
    ) {

        try {

            String guest =
                    guestField.getText()
                            .trim();

            String notes =
                    notesField.getText()
                            .trim();

            // Guest
            if (guest.isEmpty()) {

                resultLabel.setText(
                        "Please enter guest name."
                );

                guestField.requestFocus();

                return;
            }

            // Room
            if (roomBox.getValue() == null) {

                resultLabel.setText(
                        "Please select room type."
                );

                roomBox.requestFocus();

                return;
            }

            // Nights
            int nights =
                    Integer.parseInt(
                            nightsField.getText()
                                    .trim()
                    );

            if (nights <= 0) {

                resultLabel.setText(
                        "Number of nights must be greater than zero."
                );

                nightsField.requestFocus();

                return;
            }

            // Extras
            double extras =
                    Double.parseDouble(
                            extrasField.getText()
                                    .trim()
                    );

            // Paid
            double paid =
                    Double.parseDouble(
                            paidField.getText()
                                    .trim()
                    );

            if (extras < 0) {

                resultLabel.setText(
                        "Extras cannot be negative."
                );

                extrasField.requestFocus();

                return;
            }

            if (paid < 0) {

                resultLabel.setText(
                        "Amount paid cannot be negative."
                );

                paidField.requestFocus();

                return;
            }

            // =========================
            // ROOM PRICE
            // =========================

            double roomPrice =
                    getRoomPrice(
                            roomBox.getValue()
                    );

            // =========================
            // ROOM CHARGE
            // =========================

            double roomCharge =
                    roomPrice * nights;

            // =========================
            // DISCOUNT
            // =========================

            double discount = 0;

            if (nights >= 5) {

                discount =
                        roomCharge * 0.05;
            }

            // =========================
            // TOTAL
            // =========================

            double total =
                    roomCharge
                            - discount
                            + extras;

            // =========================
            // BILL NUMBER
            // =========================

            int nextNumber =
                    bills.size() + 1;

            String billNumber =
                    String.format(
                            "B%03d",
                            nextNumber
                    );

            // =========================
            // CREATE BILL
            // =========================

            Bill bill =
                    new Bill(
                            billNumber,
                            guest,
                            roomBox.getValue(),
                            nights,
                            total,
                            paid,
                            notes
                    );

            // =========================
            // SAVE
            // =========================

            AppData.bills.add(
                    bill
            );

            // =========================
            // SUCCESS
            // =========================

            resultLabel.setStyle(
                    "-fx-font-weight: bold;" +
                            "-fx-text-fill: #27AE60;"
            );

            resultLabel.setText(
                    String.format(
                            "Bill %s saved successfully! " +
                                    "Total: K %.2f | " +
                                    "Paid: K %.2f | " +
                                    "Balance: K %.2f",
                            bill.getBillNumber(),
                            bill.getTotal(),
                            bill.getPaid(),
                            bill.getBalance()
                    )
            );

            // =========================
            // CLEAR FORM
            // =========================

            guestField.clear();

            roomBox.setValue(null);

            nightsField.clear();

            extrasField.setText("0");

            paidField.setText("0");

            notesField.clear();

            guestField.requestFocus();

        } catch (NumberFormatException e) {

            resultLabel.setStyle(
                    "-fx-font-weight: bold;" +
                            "-fx-text-fill: #E74C3C;"
            );

            resultLabel.setText(
                    "Please enter valid numbers."
            );
        }
    }

    // =====================================================
    // ROOM PRICE
    // =====================================================

    private double getRoomPrice(
            String roomType
    ) {

        switch (roomType) {

            case "Standard":
                return 350.00;

            case "Deluxe":
                return 550.00;

            case "Family":
                return 750.00;

            default:
                return 0.00;
        }
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm(
            TextField guestField,
            ComboBox<String> roomBox,
            TextField nightsField,
            TextField extrasField,
            TextField paidField,
            TextArea notesField,
            Label resultLabel
    ) {

        guestField.clear();

        roomBox.setValue(null);

        nightsField.clear();

        extrasField.setText("0");

        paidField.setText("0");

        notesField.clear();

        resultLabel.setText("");

        resultLabel.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );
    }
}