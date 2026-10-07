package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserData {

    public static final ObservableList<User> users =
            FXCollections.observableArrayList();

    static {

        // Default Administrator account
        users.add(
                new User(
                        "admin",
                        "admin123",
                        "Administrator"
                )
        );

        // Default Receptionist account
        users.add(
                new User(
                        "reception",
                        "reception123",
                        "Receptionist"
                )
        );
    }

    private UserData() {
    }
}