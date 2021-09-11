package controllers.fxml;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    SceneController sceneController = new SceneController();

    public void booking(ActionEvent event) throws IOException {
        sceneController.switchToBooking(event);
    }

    public void bookingDisplay(ActionEvent event) throws IOException {
        sceneController.switchToBookingDisplay(event);
    }
    public void lookup(ActionEvent event) throws IOException {
        sceneController.switchToLookup(event);
    }
}