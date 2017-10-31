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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class Pub_shopownerController implements Initializable {

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
    private TableColumn nomboutique_column;
    @FXML
    private TextField txtrecherchepage;
    @FXML
    private ToggleGroup menu1;

    Connection connection;
    

    private ObservableList<Publicite> data;
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
    
    public static String pathOfimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IBoutiqueService ibs = new BoutiqueService();
        txtchoixboutique.setItems(ibs.displayall());
        afficher();
        IPubliciteService ips = new PubliciteService();
        TextFields.bindAutoCompletion(txtrecherchepage, ips.liste_nom_pub());

    }

    void afficher() {
        IPubliciteService ips = new PubliciteService();
        TablePub.setItems(ips.displayall());

//        System.out.println(ips.displayall());
//        idpub.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        date_debut_column.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin_column.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("prix"));
        page_column.setCellValueFactory(new PropertyValueFactory<>("page"));
//        path_column.setCellValueFactory(new PropertyValueFactory<>("path"));

//        TablePub.setItems(ips.displayall());
        nomboutique_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Publicite, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Publicite, String> param) {
                return new SimpleStringProperty(param.getValue().getBoutique().getNom());
            }
        });

    }

    @FXML
    private void search(ActionEvent event) {
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
    private void refresh(ActionEvent event) {
        afficher();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        connection = DataSource.getInsatance().getConnection();
        Publicite t=new Publicite();
        String req = "insert into demande_pub (date_debut,date_fin,prix,page,path,id_boutique) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, t.convert(txtdate_debut.getEditor().getText()));
            preparedStatement.setDate(2, t.convert(txtdate_fin.getEditor().getText()));
            preparedStatement.setFloat(3, Float.parseFloat(txtprix.getText()));
            preparedStatement.setString(4, txtpage.getText());
            
            
            File thefile = new File(getImageUrl);
            FileInputStream input = null;
            try {
                input = new FileInputStream(thefile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
            }

            preparedStatement.setBinaryStream(5, input);
            
            preparedStatement.setInt(6, txtchoixboutique.getValue().getId_boutique());
            
            pathOfimage=getImageUrl;
            System.out.println(pathOfimage);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void insert_image(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {
            getImageUrl = selectedfile.getAbsolutePath();
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
        } else {
            System.out.println("file does not exist");
        }
    }

}
