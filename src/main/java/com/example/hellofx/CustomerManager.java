package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManager extends Application {

    private ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label title = new Label("Customer Manager");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label provinceLabel = new Label("Province:");
        ComboBox<String> provinceComboBox = new ComboBox<>();

        provinceComboBox.getItems().addAll(
                "Central",
                "Copperbelt",
                "Eastern",
                "Luapula",
                "Lusaka",
                "Muchinga",
                "Northern",
                "North-Western",
                "Southern",
                "Western"
        );

        Button addButton = new Button("Add Customer");
        addButton.setDefaultButton(true);
        Button deleteButton = new Button("Delete Selected");

        TableView<Customer> customerTable = new TableView<>();

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Customer, String> provinceColumn =
                new TableColumn<>("Province");

        provinceColumn.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );

        customerTable.getColumns().addAll(
                nameColumn,
                provinceColumn
        );

        customerTable.setItems(customers);

        // ADD CUSTOMER
        addButton.setOnAction(event -> {

            String name = nameField.getText().trim();
            String province = provinceComboBox.getValue();

            if (name.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Name is required");
                alert.setContentText(
                        "Please enter the customer's name."
                );
                alert.showAndWait();

                return;
            }

            if (province == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Province is required");
                alert.setContentText(
                        "Please select a province."
                );
                alert.showAndWait();

                return;
            }

            Customer customer =
                    new Customer(name, province);

            customers.add(customer);

            nameField.clear();
            provinceComboBox.setValue(null);
        });

        // DELETE CUSTOMER
        deleteButton.setOnAction(event -> {

            Customer selectedCustomer =
                    customerTable.getSelectionModel()
                            .getSelectedItem();

            if (selectedCustomer == null) {

                Alert alert =
                        new Alert(Alert.AlertType.WARNING);

                alert.setTitle("No Customer Selected");
                alert.setHeaderText(
                        "No customer selected"
                );
                alert.setContentText(
                        "Please select a customer from the table."
                );

                alert.showAndWait();

                return;
            }

            Alert confirmation =
                    new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Confirm Deletion");
            confirmation.setHeaderText(
                    "Delete selected customer?"
            );

            confirmation.setContentText(
                    "Are you sure you want to delete "
                            + selectedCustomer.getName()
                            + "?"
            );

            if (confirmation.showAndWait()
                    .orElse(ButtonType.CANCEL)
                    == ButtonType.OK) {

                customers.remove(selectedCustomer);
            }
        });

        VBox layout = new VBox(10);

        layout.getChildren().addAll(
                title,
                nameLabel,
                nameField,
                provinceLabel,
                provinceComboBox,
                addButton,
                deleteButton,
                customerTable
        );

        Scene scene = new Scene(layout, 500, 400);

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}