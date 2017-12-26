/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import tunisia_mall.Interface.ICarteFideliteService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.models.CarteFidelite;

import tunisia_mall.Services.CarteFideliteService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class GestionCFideliteController implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleGroup menu;
    @FXML
    private AnchorPane tbIDcarte;
    @FXML
    private TableColumn tbidclient;
    @FXML
    private TableColumn<CarteFidelite, Integer> tbnbrpoint;
    @FXML
    private TableColumn<CarteFidelite, Date> tbdatecreation;
    @FXML
    private ComboBox <User> cbIdclient;
    @FXML
    private TableView<CarteFidelite> TableCarteF;
    private TableColumn<CarteFidelite, Integer> tbId_carte;
    @FXML
    private TableColumn tbnomclient;
    @FXML
    private TableColumn tbprenomclient;
    @FXML
    private DatePicker txtdatecarte;
String idnew ;
    @FXML
    private TextField nbpoints;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IUserService iu = new UserService ();
       cbIdclient.setItems(iu.displayall());
       
        TableCarteF.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showPubDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(GestionCFideliteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        
        
        afficherall();
    }
    
    
    
    @FXML
    private void rechercherB(ActionEvent event) {
        ICarteFideliteService aaa = new CarteFideliteService();
        
        TableCarteF.setItems(aaa.findCartebyID(Integer.parseInt(txtPesquisar.getText())));
        tbId_carte.setCellValueFactory(new PropertyValueFactory<>("id_carte_fidelite"));
        tbnbrpoint.setCellValueFactory(new PropertyValueFactory<>("nb_point"));
        tbdatecreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        tbidclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarteFidelite, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CarteFidelite, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getUser().getId_user()));
            }

        });

    }
    
 

    @FXML
    private void ajouter(ActionEvent event) {
      
          ICarteFideliteService aaa = new CarteFideliteService();
          
        CarteFidelite b = new CarteFidelite(Integer.parseInt((nbpoints.getText())),txtdatecarte.getEditor().getText(),cbIdclient.getValue());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("done with success");
       Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
        aaa.add(b);
       //// NomB.clear();cbType.setValue("Veuillez choirsir le type");
       //// cbPosition.setValue("Veuillez choirsir la position");
        
        }
        afficherall();
      
        
    }
    

    @FXML
    private void modifier(ActionEvent event) throws ParseException {
        
                String datefin = txtdatecarte.getEditor().getText();
        SimpleDateFormat inFmt1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt1 = new SimpleDateFormat("dd/MM/yyyy");
        String datef = outFmt1.format(inFmt1.parse(datefin));
        
        User us = cbIdclient.getValue();
        
        int nbr = Integer.parseInt(nbpoints.getText());
        
        CarteFidelite cc = new CarteFidelite();
        cc.setDate_creation(datefin);
        cc.setNb_point(nbr);
        cc.setUser(us);
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("UPDAE de carte de fidelité");
                alert.setHeaderText("etes-vous sur que vous voulez modifier cette carte :  "
                        + TableCarteF.getSelectionModel().getSelectedItem().getId_carte_fidelite());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
        
        ICarteFideliteService dao = new CarteFideliteService(); 
        cc.setId_carte_fidelite(Integer.valueOf(idnew));
        dao.update(cc);
        
       afficherall();
                }
    }

    @FXML
    private void supprimerCarte(ActionEvent event) {
        try {

            if (!TableCarteF.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("suppression de carte de fidelité");
                alert.setHeaderText("etes-vous sur que vous voulez supprimer cette carte :  "
                        + TableCarteF.getSelectionModel().getSelectedItem().getId_carte_fidelite());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    ICarteFideliteService abc = new CarteFideliteService();
                    abc.remove(TableCarteF.getSelectionModel().getSelectedItem().getId_carte_fidelite());
                    afficherall();
                }

            }
        } catch (Exception ex) {
            System.out.println("erreur lors du chargement des forums " + ex.getMessage());

        }

    }

    void afficherall() {
        ICarteFideliteService aaa = new CarteFideliteService();
        ///   User aea = new User();

        TableCarteF.setItems(aaa.displayall());
        tbidclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarteFidelite, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CarteFidelite, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getUser().getId_user()));
            }

        });

        tbnomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarteFidelite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CarteFidelite, String> param) {
                return new SimpleStringProperty(param.getValue().getUser().getNom());
            }
        });

        tbprenomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarteFidelite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CarteFidelite, String> param) {
                return new SimpleStringProperty(param.getValue().getUser().getPrenom());
            }
        });

        tbnbrpoint.setCellValueFactory(new PropertyValueFactory<>("nb_point"));
        tbdatecreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

    }

    
    
      void showPubDetails(CarteFidelite p) throws ParseException {


        idnew = String.valueOf(p.getId_carte_fidelite());

     /*   SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt = new SimpleDateFormat("MM/dd/yyyy");
        String dated = inFmt.format(outFmt.parse(p.getDate_creation()));


        try {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dated, formatter);
            txtdatecarte.setValue(date);
            System.out.println(date);

        } catch (DateTimeParseException exc) {
            throw exc;      // Rethrow the exception.
        }
*/
      
        

        nbpoints.setText(String.valueOf(p.getNb_point()));
       
       cbIdclient.setValue(p.getUser());
    }
}
