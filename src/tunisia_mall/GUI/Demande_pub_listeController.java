/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import static tunisia_mall.GUI.NewaccountController.getImageUrl;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Interface.IPubliciteService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.PubliciteService;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.Publicite;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class Demande_pub_listeController implements Initializable {

    @FXML
    private TableView<Publicite> TabledemandePub;

    @FXML
    private TableColumn<Publicite, JFXDatePicker> date_debut_column;
    @FXML
    private TableColumn<Publicite, JFXDatePicker> date_fin_column;
    @FXML
    private TableColumn<Publicite, Float> prix_column;
    @FXML
    private TableColumn<Publicite, String> page_column;
    @FXML
    private TableColumn<Publicite, String> path_column;
    @FXML
    private TableColumn nomboutique_column;
    @FXML
    private TextField txtrecherchepage;
    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleGroup menu1;
    @FXML
    private JFXDatePicker txtdate_debut;
    @FXML
    private JFXDatePicker txtdate_fin;
    @FXML
    private JFXTextField txtprix;
    @FXML
    private JFXTextField txtpage;
    @FXML
    private JFXComboBox<Boutique> txtchoixboutique;
    @FXML
    private JFXButton btinsertimage;

    String idnew;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IBoutiqueService ibs = new BoutiqueService();
        txtchoixboutique.setItems(ibs.displayall());

        TabledemandePub.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showPubDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(PubController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        IPubliciteService ips = new PubliciteService();
        TextFields.bindAutoCompletion(txtrecherchepage, ips.liste_nom_pub());

        afficher();
        // TODO
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
//        System.out.println(Pub_shopownerController.pathOfimage);
        IPubliciteService ips = new PubliciteService();
        Publicite p = new Publicite(txtdate_debut.getEditor().getText(), txtdate_fin.getEditor().getText(),
                Float.parseFloat(txtprix.getText()), txtpage.getText(), btinsertimage.getText(), txtchoixboutique.getValue());
        ips.add(p);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout publicité");
        alert.setHeaderText("ajouté avec succès");
        Optional<ButtonType> result = alert.showAndWait();
//        ips.remove_demande_pub(TabledemandePub.getSelectionModel().getSelectedItem().getId_pub());
        afficher();
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (!TabledemandePub.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression du boutique");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer la boutique:  "
                    + TabledemandePub.getSelectionModel().getSelectedItem().getPage() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IPubliciteService ips = new PubliciteService();
                ips.remove_demande_pub(TabledemandePub.getSelectionModel().getSelectedItem().getId_pub());
                afficher();
            }
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        afficher();
    }

    @FXML
    private void insert_image(ActionEvent event) {
    }

    void afficher() {
        IPubliciteService ips = new PubliciteService();
        TabledemandePub.setItems(ips.displayallDemandePub());
        System.out.println(ips.liste_nom_pub());

        date_debut_column.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin_column.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("prix"));
        page_column.setCellValueFactory(new PropertyValueFactory<>("page"));
//        path_column.setCellValueFactory(new PropertyValueFactory<>("path"));

        nomboutique_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Publicite, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Publicite, String> param) {
                return new SimpleStringProperty(param.getValue().getBoutique().getNom());
            }
        });
    }

    void showPubDetails(Publicite p) throws ParseException {

//        id.setText(String.valueOf(p.getId_pub()));
//        idnew = String.valueOf(p.getId_pub());
        SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");
        String dated = outFmt.format(inFmt.parse(p.getDate_debut()));
//        txtdate_debut.setPromptText(dated);

        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dated, formatter);
            txtdate_debut.setValue(date);
            System.out.println(date);

        } catch (DateTimeParseException exc) {
            throw exc;      // Rethrow the exception.
        }

        SimpleDateFormat inFmt1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt1 = new SimpleDateFormat("dd/MM/yyyy");
        String datef = outFmt1.format(inFmt1.parse(p.getDate_fin()));
//        txtdate_fin.setPromptText(datef);

        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(datef, formatter);
            txtdate_fin.setValue(date);
            System.out.println(date);

        } catch (DateTimeParseException exc) {
            throw exc;      // Rethrow the exception.
        }

        txtprix.setText(String.valueOf(p.getPrix()));
        txtpage.setText(p.getPage());
        btinsertimage.setText(p.getPath());
        System.out.println(btinsertimage.getText());
        txtchoixboutique.setValue(p.getBoutique());
    }

}
