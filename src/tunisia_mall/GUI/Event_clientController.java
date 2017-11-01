/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import tunisia_mall.Interface.IEvenementService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.EvenementService;
import tunisia_mall.Services.PubliciteService;
import tunisia_mall.Services.UserService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Evenement;
import tunisia_mall.models.Publicite;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class Event_clientController implements Initializable {

    @FXML
    private TableView<Evenement> TableEvent;

    @FXML
    private TableColumn<Evenement, String> nomeventcolumn;

    @FXML
    private TableColumn<Evenement, String> descriptioncolumn;

    @FXML
    private TableColumn<Evenement, JFXDatePicker> date_column;

    @FXML
    private TableColumn<Evenement, String> path_column;
    @FXML
    private TableColumn nom_column;
    @FXML
    private TableColumn prenom_column;
    @FXML
    private TextField recherche;
    @FXML
    private ToggleGroup menu1;

    Connection connection;

    private ObservableList<Evenement> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficher();
        EvenementService ips = new EvenementService();
        TextFields.bindAutoCompletion(recherche, ips.liste_nom_event());
        
        
         recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterEmployeList((String) oldValue, (String) newValue);
            }
        });
    }

    @FXML
    private void search(ActionEvent event) {
        data = FXCollections.observableArrayList();
        connection = DataSource.getInsatance().getConnection();
        Publicite p = null;
        String req = "select * from evenement where nom = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, recherche.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evenement p1 = new Evenement();
                p1.setNom(resultSet.getString("nom"));
                p1.setDescription(resultSet.getString("description"));
                p1.setDate(Publicite.convert(resultSet.getDate("date")));
                p1.setPath(resultSet.getString("path"));
                p1.setUser(new UserService().findById(resultSet.getInt("id_user")));
                data.add(p1);
            }
            TableEvent.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        afficher();
    }

    void afficher() {
        IEvenementService ips = new EvenementService();
        TableEvent.setItems(ips.displayall());

        nomeventcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        path_column.setCellValueFactory(new PropertyValueFactory<>("path"));

//nom de user
        nom_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getUser().getNom());
            }
        });
//prenom user
        prenom_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getUser().getPrenom());
            }
        });

    }
    
    void filterEmployeList(String oldValue, String newValue) {
        IEvenementService ius = new EvenementService();
        ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            TableEvent.setItems(ius.displayall());

        } else {

            newValue = newValue.toUpperCase();

            for (Evenement user : TableEvent.getItems()) {

                String filterName = user.getNom();


                if (filterName.toUpperCase().contains(newValue)) {
                    filteredList.add(user);
                }

            }
            TableEvent.setItems(filteredList);

        }
    }

}
