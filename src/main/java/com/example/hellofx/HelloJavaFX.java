package com.example.hellofx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {
    private ObservableList<Student> students = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        // Title
        Label title = new Label("STUDENT REGISTRATION SYSTEM");

        // Student ID
        Label studentIdLabel = new Label("Student ID:");
        TextField studentIdField = new TextField();

        // First Name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        // Gender
        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setPromptText("Select Gender");

        // Programme
        Label programmeLabel = new Label("Programme:");
        ComboBox<String> programmeBox = new ComboBox<>();
        programmeBox.getItems().addAll(
                "Computer Science",
                "Information Technology",
                "Computer Engineering",
                "Cyber Security"
        );
        programmeBox.setPromptText("Select Programme");

        // Year of Study
        Label yearLabel = new Label("Year of Study:");
        ComboBox<String> yearBox = new ComboBox<>();
        yearBox.getItems().addAll(
                "Year 1",
                "Year 2",
                "Year 3",
                "Year 4"
        );
        yearBox.setPromptText("Select Year");

        // Email
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        // Phone
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
// Student Table
        TableView<Student> studentTable = new TableView<>();

// Table Columns
        TableColumn<Student, String> idColumn =
                new TableColumn<>("Student ID");

        TableColumn<Student, String> firstNameColumn =
                new TableColumn<>("First Name");

        TableColumn<Student, String> lastNameColumn =
                new TableColumn<>("Last Name");

        TableColumn<Student, String> programmeColumn =
                new TableColumn<>("Programme");

        TableColumn<Student, String> yearColumn =
                new TableColumn<>("Year");

        idColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getStudentId()
                )
        );

        firstNameColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getFirstName()
                )
        );
        lastNameColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getLastName()
                )
        );

        programmeColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getProgramme()
                )
        );

        yearColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getYearOfStudy()
                )
        );
        studentTable.getColumns().addAll(
                idColumn,
                firstNameColumn,
                lastNameColumn,
                programmeColumn,
                yearColumn
        );
        studentTable.setItems(students);

        // Buttons
        Button registerButton = new Button("Register");
        Button clearButton = new Button("Clear");

        // Layout
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Add components to GridPane
        grid.add(studentIdLabel, 0, 0);
        grid.add(studentIdField, 1, 0);

        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);

        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameField, 1, 2);

        grid.add(genderLabel, 0, 3);
        grid.add(genderBox, 1, 3);

        grid.add(programmeLabel, 0, 4);
        grid.add(programmeBox, 1, 4);

        grid.add(yearLabel, 0, 5);
        grid.add(yearBox, 1, 5);

        grid.add(emailLabel, 0, 6);
        grid.add(emailField, 1, 6);

        grid.add(phoneLabel, 0, 7);
        grid.add(phoneField, 1, 7);

        grid.add(registerButton, 0, 8);
        grid.add(clearButton, 1, 8);

        // Register button
        registerButton.setOnAction(event -> {

            String studentId = studentIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String gender = genderBox.getValue();
            String programme = programmeBox.getValue();
            String yearOfStudy = yearBox.getValue();
            String email = emailField.getText();
            String phone = phoneField.getText();

            Student student = new Student(
                    studentId,
                    firstName,
                    lastName,
                    gender,
                    programme,
                    yearOfStudy,
                    email,
                    phone
            );

            System.out.println("Student Registered Successfully!");
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Programme: " + student.getProgramme());
        });
        // Clear button
        clearButton.setOnAction(event -> {
            studentIdField.clear();
            firstNameField.clear();
            lastNameField.clear();
            genderBox.setValue(null);
            programmeBox.setValue(null);
            yearBox.setValue(null);
            emailField.clear();
            phoneField.clear();
        });

        // Main layout
        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                title,
                grid,
                studentTable
        );

        // Scene
        Scene scene = new Scene(root, 800, 650);

        // Window
        stage.setTitle("Student Registration System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}