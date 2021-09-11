package controllers.fxml;

import com.sun.istack.internal.NotNull;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.DBHandler;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    @FXML
    private ListView<String> myListView;
    @FXML
    private Label specialistLabel;
    @FXML
    private ChoiceBox<String> selectTimeMenu;

    private String[] timeslots = {"10AM", "12AM", "2PM", "4PM"};

    private String[] specialists = {"Masseuse", "Hairdresser", "Beauty Therapist"};

    public String currentSpec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myListView.getItems().addAll(specialists);

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentSpec = myListView.getSelectionModel().getSelectedItem();
                specialistLabel.setText("You chose: " + currentSpec);
            }
        });

        selectTimeMenu.getItems().addAll(timeslots);
    }


    @FXML
    private TextField enterNameField;
    @FXML
    private TextField enterNumberField;
    @FXML
    private DatePicker dateView;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void book(ActionEvent event) throws IOException, InterruptedException, SQLException {
        DBHandler dbHandler = new DBHandler();

        Connection connection = dbHandler.getConnection();

        String query = "INSERT INTO user(name, phoneNumber, specialist,date,time) VALUES (?,?,?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, enterNameField.getText());
        preparedStatement.setInt(2, Integer.parseInt(enterNumberField.getText()));
        preparedStatement.setString(3, currentSpec);
        preparedStatement.setString(4, String.valueOf(dateView.getValue()));
        preparedStatement.setString(5, selectTimeMenu.getValue());

        preparedStatement.execute();
        preparedStatement.close();
        String name = enterNameField.getText();
        String number = enterNumberField.getText();
        LocalDate date = dateView.getValue();
        String specialist = currentSpec;
        String time = selectTimeMenu.getValue();


        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentSpec = myListView.getSelectionModel().getSelectedItem();
                specialistLabel.setText(currentSpec);
            }
        });


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"));
        root = loader.load();

        ViewController viewController = loader.getController();
        viewController.displayName(name);
        viewController.displayNumber(number);
        try {
            viewController.displayDate(date);
        } catch (NullPointerException ex) {
            System.out.println("");
        }
        viewController.displaySpecialist(specialist);
        viewController.displayTime(time);

        validation(event);

    }

    @FXML
    Label errorLabel;

    public void validation(ActionEvent event) throws InterruptedException {

        String name = enterNameField.getText();
        String number = enterNumberField.getText();
        LocalDate date = dateView.getValue();

        if ((!name.equals("") && !number.equals("") && number.length() == 6) && (!date.equals(""))) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            errorLabel.setText("All fields must be filled and number must be 6");
            errorLabel.setStyle("-fx-text-fill:red");
        }
    }
}