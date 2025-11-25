package com.example.gpa_calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Result {

    @FXML private TableView<StudentRecord> table;
    @FXML private TableColumn<StudentRecord, String> colName;
    @FXML private TableColumn<StudentRecord, String> colRoll;
    @FXML private TableColumn<StudentRecord, Double> colGPA;
    @FXML private TableColumn<StudentRecord, Void> colActions;

    private ObservableList<StudentRecord> data;

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoll.setCellValueFactory(new PropertyValueFactory<>("roll"));
        colGPA.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        addButtonToTable();

        System.out.println("Fetching students...");
        data = FXCollections.observableArrayList(DatabaseManager.fetchAllStudents());
        System.out.println("Fetched: " + data.size() + " students");
        table.setItems(data);
    }

    private void addButtonToTable() {
        colActions.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    StudentRecord student = getTableView().getItems().get(getIndex());
                    DatabaseManager.deleteStudent(student.getId());
                    reloadData();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox pane = new HBox(deleteButton);
                    pane.setSpacing(10);
                    setGraphic(pane);
                }
            }
        });
    }

    private void reloadData() {
        data = FXCollections.observableArrayList(DatabaseManager.fetchAllStudents());
        table.setItems(data);
    }

    @FXML
    private void restart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gpa_calculator.fxml"));
            Scene scene = new Scene(loader.load(), 700, 700);
            Stage stage = (Stage) table.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){ e.printStackTrace(); }
    }

    @FXML
    private void exitApp() { System.exit(0); }
}
