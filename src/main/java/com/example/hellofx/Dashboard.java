package com.example.hellofx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {

    public void show(Stage stage, String role) {

        // =========================================
        // LOGO
        // =========================================

        Image logoImage = new Image(
                getClass().getResourceAsStream("/logo.png")
        );

        ImageView logo = new ImageView(logoImage);

        logo.setFitWidth(60);
        logo.setFitHeight(60);
        logo.setPreserveRatio(true);

        // =========================================
        // TITLE
        // =========================================

        javafx.scene.control.Label title =
                new javafx.scene.control.Label(
                        "LODGE BILLING SYSTEM"
                );

        title.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        javafx.scene.control.Label subtitle =
                new javafx.scene.control.Label(
                        "Management Dashboard"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #D6EAF8;"
        );

        javafx.scene.control.Label loggedIn =
                new javafx.scene.control.Label(
                        "Logged in as: " + role
                );

        loggedIn.setStyle(
                "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        VBox titleBox = new VBox(
                2,
                title,
                subtitle,
                loggedIn
        );

        // =========================================
        // LOGOUT BUTTON
        // =========================================

        Button logoutButton =
                new Button("Logout");

        logoutButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 12px;" +
                        "-fx-padding: 7 14 7 14;" +
                        "-fx-cursor: hand;"
        );

        Region headerSpacer = new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        // =========================================
        // HEADER
        // =========================================

        HBox header = new HBox(
                12,
                logo,
                titleBox,
                headerSpacer,
                logoutButton
        );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(10, 15, 10, 15)
        );

        header.setStyle(
                "-fx-background-color: #1F4E78;"
        );

        // =========================================
        // WELCOME TEXT
        // =========================================

        javafx.scene.control.Label welcome =
                new javafx.scene.control.Label(
                        "Welcome to the Lodge Management System"
                );

        welcome.setStyle(
                "-fx-font-size: 19px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F4E78;"
        );

        javafx.scene.control.Label description =
                new javafx.scene.control.Label(
                        "Manage guests, billing and system users."
                );

        description.setStyle(
                "-fx-font-size: 12px;" +
                        "-fx-text-fill: #555555;"
        );

        VBox welcomeBox = new VBox(
                3,
                welcome,
                description
        );

        welcomeBox.setAlignment(
                Pos.CENTER
        );

        // =========================================
        // BUTTON STYLE
        // =========================================

        String buttonStyle =
                "-fx-background-color: #1F4E78;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 7;" +
                        "-fx-padding: 10 18 10 18;" +
                        "-fx-cursor: hand;";

        // =========================================
        // GUEST REGISTRATION
        // =========================================

        Button guestButton =
                new Button("Guest Registration");

        guestButton.setStyle(buttonStyle);
        guestButton.setPrefWidth(220);
        guestButton.setPrefHeight(42);

        // =========================================
        // VIEW GUESTS
        // =========================================

        Button viewGuestsButton =
                new Button("View Guests");

        viewGuestsButton.setStyle(buttonStyle);
        viewGuestsButton.setPrefWidth(220);
        viewGuestsButton.setPrefHeight(42);

        // =========================================
        // CREATE BILL
        // =========================================

        Button billingButton =
                new Button("Create Bill");

        billingButton.setStyle(buttonStyle);
        billingButton.setPrefWidth(220);
        billingButton.setPrefHeight(42);

        // =========================================
        // VIEW BILLS
        // =========================================

        Button viewBillsButton =
                new Button("View Bills");

        viewBillsButton.setStyle(buttonStyle);
        viewBillsButton.setPrefWidth(220);
        viewBillsButton.setPrefHeight(42);

        // =========================================
        // MANAGE USERS
        // =========================================

        Button usersButton =
                new Button("Manage Users");

        usersButton.setStyle(buttonStyle);
        usersButton.setPrefWidth(220);
        usersButton.setPrefHeight(42);

        // =========================================
        // ROLE PERMISSION
        // =========================================

        if (!role.equalsIgnoreCase("Administrator")) {

            usersButton.setDisable(true);

            usersButton.setTooltip(
                    new Tooltip(
                            "Only Administrators can manage users."
                    )
            );
        }

        // =========================================
        // NAVIGATION
        // =========================================

        VBox navigation = new VBox(
                7,
                guestButton,
                viewGuestsButton,
                billingButton,
                viewBillsButton,
                usersButton
        );

        navigation.setAlignment(
                Pos.CENTER
        );

        // =========================================
        // BACKGROUND IMAGE
        // =========================================

        Image backgroundImage = null;

        try {

            backgroundImage = new Image(
                    getClass()
                            .getResourceAsStream(
                                    "/Reception.png"
                            )
            );

        } catch (Exception e) {

            System.out.println(
                    "Reception.png could not be loaded."
            );
        }

        // =========================================
        // CONTENT AREA
        // =========================================

        StackPane contentArea =
                new StackPane();

        // =========================================
        // BACKGROUND
        // =========================================

        if (backgroundImage != null) {

            ImageView backgroundView =
                    new ImageView(
                            backgroundImage
                    );

            backgroundView.setPreserveRatio(
                    false
            );

            backgroundView.fitWidthProperty()
                    .bind(
                            contentArea.widthProperty()
                    );

            backgroundView.fitHeightProperty()
                    .bind(
                            contentArea.heightProperty()
                    );

            contentArea.getChildren().add(
                    backgroundView
            );
        }

        // =========================================
        // DARK OVERLAY
        // =========================================

        Region overlay =
                new Region();

        overlay.setStyle(
                "-fx-background-color: rgba(0,0,0,0.30);"
        );

        contentArea.getChildren().add(
                overlay
        );

        // =========================================
        // DASHBOARD CARD
        // =========================================

        VBox dashboardCard = new VBox(
                14,
                welcomeBox,
                navigation
        );

        dashboardCard.setAlignment(
                Pos.CENTER
        );

        dashboardCard.setPadding(
                new Insets(15)
        );

        dashboardCard.setMaxWidth(
                400
        );

        dashboardCard.setMaxHeight(
                520
        );

        dashboardCard.setStyle(
                "-fx-background-color: rgba(255,255,255,0.94);" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: white;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;"
        );

        // =========================================
        // PUT CARD AT TOP CENTER
        // =========================================

        StackPane.setAlignment(
                dashboardCard,
                Pos.TOP_CENTER
        );

        dashboardCard.setTranslateY(15);

        contentArea.getChildren().add(
                dashboardCard
        );

        // =========================================
        // GUEST REGISTRATION ACTION
        // =========================================

        guestButton.setOnAction(event -> {

            GuestRegistration guestRegistration =
                    new GuestRegistration();

            guestRegistration.show(
                    stage,
                    role
            );
        });

        // =========================================
        // VIEW GUESTS ACTION
        // =========================================

        viewGuestsButton.setOnAction(event -> {

            ViewGuests viewGuests =
                    new ViewGuests();

            viewGuests.show(
                    stage,
                    role
            );
        });

        // =========================================
        // CREATE BILL ACTION
        // =========================================

        billingButton.setOnAction(event -> {

            LodgeBilling billing =
                    new LodgeBilling();

            billing.start(
                    stage,
                    role
            );
        });

        // =========================================
        // VIEW BILLS ACTION
        // =========================================

        viewBillsButton.setOnAction(event -> {

            ViewBills viewBills =
                    new ViewBills();

            viewBills.show(
                    stage,
                    role,
                    AppData.bills
            );
        });

        // =========================================
        // MANAGE USERS ACTION
        // =========================================

        usersButton.setOnAction(event -> {

            if (role.equalsIgnoreCase("Administrator")) {

                UserManagement userManagement =
                        new UserManagement();

                userManagement.show(
                        stage,
                        role
                );

            } else {

                showMessage(
                        "Only Administrators can manage users."
                );
            }
        });

        // =========================================
        // LOGOUT ACTION
        // =========================================

        logoutButton.setOnAction(event -> {

            Login login =
                    new Login();

            login.start(stage);
        });

        // =========================================
        // ROOT
        // =========================================

        BorderPane root =
                new BorderPane();

        root.setTop(header);

        root.setCenter(
                contentArea
        );

        // =========================================
        // SCENE
        // =========================================

        Scene scene =
                new Scene(
                        root,
                        950,
                        650
                );

        stage.setTitle(
                "Lodge Billing System - Dashboard"
        );

        stage.setScene(scene);

        stage.setMinWidth(850);
        stage.setMinHeight(600);
        stage.setMaximized(false);

        stage.show();
    }

    // =========================================
    // MESSAGE
    // =========================================

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