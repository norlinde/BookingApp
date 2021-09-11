package controllers.fxml;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LookupController implements Initializable {
    DBHandler dbHandler = new DBHandler();
    SceneController sceneController = new SceneController();

    @FXML
    TextField viewingSearchField;

    @FXML
    Button lookupBtn;

    @FXML
    TableView<User> TableView;
    @FXML
    TableColumn<User, Integer> ID = new TableColumn<>("ID");
    @FXML
    TableColumn<User, String> NAME = new TableColumn<>("NAME");
    @FXML
    TableColumn<User, Integer> NUMBER = new TableColumn<>("NUMBER");
    @FXML
    TableColumn<User, String> SPECIALIST = new TableColumn<>("SPECIALIST");
    @FXML
    TableColumn<User, String> DATE = new TableColumn<>("DATE");
    @FXML
    TableColumn<User, String> TIME = new TableColumn<>("TIME");

    ObservableList<User> userObservableList = FXCollections.observableArrayList();

    public void menu(ActionEvent event) throws IOException {
        sceneController.switchToMenu(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {
        DBHandler dbHandler = new DBHandler();
        Connection connection = dbHandler.getConnection();
        User user = TableView.getSelectionModel().getSelectedItem();

        String query = "DELETE from User WHERE id =" + user.getId();

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        System.out.println("Deleted successfully");
        sceneController.switchToLookup(actionEvent);

    }

    public void search(ActionEvent actionEvent) throws SQLException {
        Connection connection = dbHandler.getConnection();


        String query = "SELECT id, name, phoneNumber, specialist, date, time FROM user WHERE phoneNumber = "
                + viewingSearchField.getText();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer phoneNumber = resultSet.getInt("phoneNumber");
                String specialist = resultSet.getString("specialist");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                userObservableList.add(new User(id, name, phoneNumber, specialist, date, time));
            }

            ID.setCellValueFactory(new PropertyValueFactory<>("id"));
            NAME.setCellValueFactory(new PropertyValueFactory<>("name"));
            NUMBER.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            SPECIALIST.setCellValueFactory(new PropertyValueFactory<>("specialist"));
            DATE.setCellValueFactory(new PropertyValueFactory<>("date"));
            TIME.setCellValueFactory(new PropertyValueFactory<>("time"));

            TableView.setItems(userObservableList);
            FilteredList<User> filteredData = new FilteredList<>(userObservableList, b -> true);

            viewingSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(user -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }

                    String viewingSearchField = newValue.toLowerCase(Locale.ROOT);

                    if (user.getName().toLowerCase(Locale.ROOT).indexOf(viewingSearchField) > -1) {
                        return true;

                    } else if (user.getSpecialist().toLowerCase(Locale.ROOT).indexOf(viewingSearchField) > -1) {
                        return true;

                    } else if (Integer.toString(user.getPhoneNumber()).indexOf(viewingSearchField) > -1) {
                        return true;

                    } else
                        return false;
                });
            });
            SortedList<User> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableView.comparatorProperty());
            TableView.setItems(sortedData);
            statement.close();
        } catch (SQLException e) {
            Logger.getLogger(DisplayBookingsController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}



