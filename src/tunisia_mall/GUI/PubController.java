/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import static tunisia_mall.GUI.NewaccountController.getImageUrl;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Interface.IPubliciteService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.PubliciteService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.Publicite;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class PubController implements Initializable {

    @FXML
    private TableView<Publicite> TablePub;

    private TableColumn<Publicite, Integer> idpub;

    @FXML
    private TableColumn<Publicite, JFXDatePicker> date_debut_column;

    @FXML
    private TableColumn<Publicite, JFXDatePicker> date_fin_column;

    @FXML
    private TableColumn<Publicite, Float> prix_column;

    @FXML
    private TableColumn<Publicite, String> page_column;

    private TableColumn<Publicite, String> path_column;

    @FXML
    private ToggleGroup menu;

    @FXML
    private JFXDatePicker txtdate_debut;

    @FXML
    private JFXDatePicker txtdate_fin;

    @FXML
    private JFXTextField txtprix;

    @FXML
    private JFXTextField txtpage;

    private JFXTextField txtpath;

    @FXML
    private JFXComboBox<Boutique> txtchoixboutique;

    @FXML
    private TableColumn nomboutique_column;

    Connection connection;
    @FXML
    private TextField txtrecherchepage;

    private ObservableList<Publicite> data;
    @FXML
    private ToggleGroup menu1;
    @FXML
    private JFXButton btinsertimage;

    void afficher() {
        IPubliciteService ips = new PubliciteService();
        TablePub.setItems(ips.displayall());
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
    String idnew;

    void showPubDetails(Publicite p) throws ParseException {

//        id.setText(String.valueOf(p.getId_pub()));
        idnew = String.valueOf(p.getId_pub());

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
        txtchoixboutique.setValue(p.getBoutique());
    }

//    @FXML
//    void ajouter(ActionEvent event) {
//        IPubliciteService ips = new PubliciteService();
//        Publicite p = new Publicite(txtdate_debut.getEditor().getText(), txtdate_fin.getEditor().getText(),
//                Float.parseFloat(txtprix.getText()), txtpage.getText(), getImageUrl, txtchoixboutique.getValue());
//
//        ips.add(p);
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Ajout publicité");
//        alert.setHeaderText("ajouté avec succès");
//        Optional<ButtonType> result = alert.showAndWait();
//        afficher();
//    }

    @FXML
    void modifier(ActionEvent event) throws ParseException {
        String datedebut = txtdate_debut.getEditor().getText();
        SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt = new SimpleDateFormat("yyyy/MM/dd");
        String dated = outFmt.format(inFmt.parse(datedebut));

        LocalDate db = txtdate_debut.getValue();

        String datefin = txtdate_fin.getEditor().getText();
        SimpleDateFormat inFmt1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt1 = new SimpleDateFormat("yyyy/MM/dd");
        String datef = outFmt1.format(inFmt1.parse(datefin));

        float prix = Float.parseFloat(txtprix.getText());
        String page = txtpage.getText();
        System.out.println(btinsertimage.getText());
        Boutique b = txtchoixboutique.getValue();

        Publicite p = new Publicite();
        p.setDate_debut(datedebut);
        p.setDate_fin(datefin);
        p.setPrix(prix);
        p.setPage(page);
        p.setPath(btinsertimage.getText());
        p.setBoutique(b);

        IPubliciteService ips = new PubliciteService();
//        System.out.println(id.getText());
        p.setId_pub(Integer.valueOf(idnew));
        ips.update(p);
//        System.out.println(p);
//        afficher();

    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        data = FXCollections.observableArrayList();
        connection = DataSource.getInsatance().getConnection();
        Publicite p = null;
        String req = "select * from publicite where page = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, txtrecherchepage.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publicite p1 = new Publicite();
                p1.setDate_debut(Publicite.convert(resultSet.getDate(2)));
                p1.setDate_fin(Publicite.convert(resultSet.getDate(3)));
                p1.setPrix(resultSet.getFloat(4));
                p1.setPage(resultSet.getString(5));
                p1.setPath(resultSet.getString(6));
                p1.setBoutique(new BoutiqueService().findById(resultSet.getInt(7)));
                data.add(p1);
            }
            TablePub.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void supprimer(ActionEvent event) {
        if (!TablePub.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression du boutique");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer la boutique:  "
                    + TablePub.getSelectionModel().getSelectedItem().getPage() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IPubliciteService ips = new PubliciteService();
                ips.remove(TablePub.getSelectionModel().getSelectedItem().getId_pub());
                afficher();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IBoutiqueService ibs = new BoutiqueService();
        txtchoixboutique.setItems(ibs.displayall());

        TablePub.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showPubDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(PubController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });


        afficher();
        IPubliciteService ips = new PubliciteService();
        TextFields.bindAutoCompletion(txtrecherchepage, ips.liste_nom_pub());

        txtrecherchepage.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterEmployeList((String) oldValue, (String) newValue);
    }
        });

        
        
        
    }
    

    @FXML
    private void insert_image(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        System.out.println(selectedfile);
        if (selectedfile != null) {
            getImageUrl = selectedfile.getAbsolutePath();
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            System.out.println(getImageUrl);
        } else {
            System.out.println("file does not exist");
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        afficher();
    }
    
    
    
//    **********************************************Caution test*********************
    
     @FXML
    void ajouter(ActionEvent event) {
        IPubliciteService ips = new PubliciteService();
        Publicite p = new Publicite(txtdate_debut.getEditor().getText(), txtdate_fin.getEditor().getText(),
                Float.parseFloat(txtprix.getText()), txtpage.getText(), getImageUrl, txtchoixboutique.getValue());

        ips.add(p);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout publicité");
        alert.setHeaderText("ajouté avec succès");
        Optional<ButtonType> result = alert.showAndWait();
        afficher();
    }

    
    
    
//    ***************************************************************************
    
    void filterEmployeList(String oldValue, String newValue) {
        IPubliciteService ius = new PubliciteService();
        ObservableList<Publicite> filteredList = FXCollections.observableArrayList();
        if (txtrecherchepage.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            TablePub.setItems(ius.displayall());

        } else {

            newValue = newValue.toUpperCase();

            for (Publicite user : TablePub.getItems()) {

                String filterName = user.getPage();


                if (filterName.toUpperCase().contains(newValue)) {
                    filteredList.add(user);
}

            }
            TablePub.setItems(filteredList);

        }
    }

}
