/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLGestionClientController implements Initializable {

    @FXML
    private TableColumn<User, String> tblclmnom;
    @FXML
    private TableColumn<User, String> tblclmprenom;
    @FXML
    private TableColumn<User, String> tblclmdatenais;
    @FXML
    private TableColumn<User, String> tblclmsexe;
    @FXML
    private TableColumn<User, String> tblclmmail;
    @FXML
    private TableColumn<User, Integer> tblclmnum;
    @FXML
    private TableColumn<User, String> tblclmadresse;
    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleButton rechercheC;
    @FXML
    private TableView<User> tblclient;
    @FXML
    private ToggleButton supprimerCl;
    @FXML
    private ToggleGroup menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherclient();
        IUserService ius = new UserService();
        tblclient.setItems(ius.displayallclient());

        txtPesquisar.textProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterClientList((String) oldValue, (String) newValue);

            }

        });

    }

    @FXML
    private void rechercherC(ActionEvent event) {
        IUserService ius = new UserService();
        String a = (txtPesquisar.getText());

        tblclient.setItems(ius.findClientByName(a));
        tblclmnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblclmprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblclmdatenais.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tblclmsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        tblclmmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tblclmnum.setCellValueFactory(new PropertyValueFactory<>("numero_telephone"));
        tblclmadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

    }

    void afficherclient() {
        IUserService ius = new UserService();

        tblclient.setItems(ius.displayallclient());

        tblclmnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblclmprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblclmdatenais.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tblclmsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        tblclmmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tblclmnum.setCellValueFactory(new PropertyValueFactory<>("numero_telephone"));
        tblclmadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

    }

    @FXML
    private void supprimerCl(ActionEvent event) {
        if (!tblclient.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'employe ");
            alert.setHeaderText("Etes-vous sur que vous voulez supprimer le client"
                    + tblclient.getSelectionModel().getSelectedItem().getId_user() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IUserService ius = new UserService();
                ius.remove(tblclient.getSelectionModel().getSelectedItem().getId_user());
                afficherclient();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Erreur de selection");
            alert1.setHeaderText("Vous etes obligé de selectioner un employe  ");

            Optional<ButtonType> result = alert1.showAndWait();
        }
    }

    void filterClientList(String oldValue, String newValue) {
        IUserService ius = new UserService();
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        if (txtPesquisar.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblclient.setItems(ius.displayallclient());

        } else {

            newValue = newValue.toUpperCase();

            for (User user : tblclient.getItems()) {

                String filterName = user.getNom();

                String filterprenom = user.getPrenom();

                if (filterName.toUpperCase().contains(newValue) || filterprenom.toUpperCase().contains(newValue)) {
                    filteredList.add(user);

                }

            }
            tblclient.setItems(filteredList);

        }
    }
}
