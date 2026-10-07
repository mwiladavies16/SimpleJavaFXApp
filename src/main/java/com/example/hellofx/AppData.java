package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppData {

    // Shared bills for the whole application
    public static final ObservableList<Bill> bills =
            FXCollections.observableArrayList();

    // Shared guests for the whole application
    public static final ObservableList<Guest> guests =
            FXCollections.observableArrayList();

    private AppData() {
        // Prevent creating AppData objects
    }
}