package controllers.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBooking(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("booking.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToBookingDisplay(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("displayBookings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLookup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("lookup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}