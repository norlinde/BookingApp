package controllers.fxml;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;


public class ViewController {

    @FXML
    Label specialistLabel;
    @FXML
    Label yourNameLabel;
    @FXML
    Label yourNumberLabel;
    @FXML
    Label dateLabel;
    @FXML
    Label timeLabel;

    public void displayName(String name) {
        yourNameLabel.setText("Hey " + name + ", nice one!");
    }

    public void displayNumber(String number) {
        yourNumberLabel.setText("Number : " + number);
    }

    public void displaySpecialist(String specialist) {
        specialistLabel.setText(specialist);
    }

    public void displayDate(LocalDate date) {
        dateLabel.setText(date.toString());
    }

    public void displayTime(String time) {
        timeLabel.setText(time);
    }


    SceneController sceneController = new SceneController();

    public void menu(ActionEvent event) throws IOException {
        sceneController.switchToMenu(event);
    }

}