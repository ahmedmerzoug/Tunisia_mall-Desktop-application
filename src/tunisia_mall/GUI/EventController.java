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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import static tunisia_mall.GUI.NewaccountController.getImageUrl;
import tunisia_mall.Interface.IEvenementService;
import tunisia_mall.Interface.IPubliciteService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.EvenementService;
import tunisia_mall.Services.PubliciteService;
import tunisia_mall.Services.UserService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Evenement;
import tunisia_mall.models.Publicite;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class EventController implements Initializable {

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
    private JFXDatePicker txtdate;

    @FXML
    private JFXTextField txtpath;

    @FXML
    private JFXComboBox<User> txtchoixuser;

    @FXML
    private JFXTextField txt_eventname;

    @FXML
    private JFXTextField txt_description;

    String idnew;
    @FXML
    private TableColumn nom_column;
    @FXML
    private TableColumn prenom_column;
    @FXML
    private TextField rechercher;
    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleGroup menu1;

    @FXML
    private JFXButton btinsertimage;
    
    @FXML
    private ImageView image;

    String imgName;

    Connection connection;

    private ObservableList<Evenement> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IUserService ibs = new UserService();
        txtchoixuser.setItems(ibs.displayall());

        TableEvent.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showPubDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(PubController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

        afficher();
        EvenementService ips = new EvenementService();
        TextFields.bindAutoCompletion(rechercher, ips.liste_nom_event());

        rechercher.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterEmployeList((String) oldValue, (String) newValue);
            }
        });
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (!txt_eventname.getText().equals("") && !txt_description.getText().equals("")
                && !txtdate.getEditor().getText().equals("") && !imgName.equals("")) {
            IEvenementService ies = new EvenementService();
            Evenement e = new Evenement(
                    txt_eventname.getText(),
                    txt_description.getText(),
                    txtdate.getEditor().getText(),
                    imgName,
                    txtchoixuser.getValue()
            );
            ies.add(e);
            afficher();

//             SendMail.sendmail(TableEvent.getSelectionModel().getSelectedItem().getUser().getMail(),
//                    "ajout evenement", "votre evenement a été crée, nom evenement: " + e.toString());
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("erreur champs vides");
            alert.setHeaderText("il ya des champs vides");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        String nom = txt_eventname.getText();
        String des = txt_description.getText();
        String datee = txtdate.getEditor().getText();
//        String path = txtpath.getText();
//        imgName = btinsertimage.getText();
//        btinsertimage.setText(path);
        User u = txtchoixuser.getValue();
        System.out.println("in event " + imgName);
        System.out.println(btinsertimage.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            if (imgName == null) {
                imgName = btinsertimage.getText();
            }

            Evenement e = new Evenement(nom, des, datee, imgName, u);
            e.setId_evenement(Integer.parseInt(idnew));
            IEvenementService ies = new EvenementService();
            ies.update(e);
//            SendMail.sendmail(TableEvent.getSelectionModel().getSelectedItem().getUser().getMail(),
//                    "Modification des informations d'evenement", "nous vous informons qu il y a des modifications d evenement " + e.toString());

//        ies.add(e);
            afficher();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (!TableEvent.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d evenement");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer evenement:  "
                    + TableEvent.getSelectionModel().getSelectedItem().getNom() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IEvenementService ips = new EvenementService();
                System.out.println(TableEvent.getSelectionModel().getSelectedItem().getUser().getMail());
                ips.remove(TableEvent.getSelectionModel().getSelectedItem().getId_evenement());
//                SendMail.sendmail(TableEvent.getSelectionModel().getSelectedItem().getUser().getMail(),
//                        "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                afficher();
            }
        }
    }

    void afficher() {
        IEvenementService ips = new EvenementService();
        TableEvent.setItems(ips.displayall());

//        System.out.println(ips.displayall());
//        idpub.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        nomeventcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        path_column.setCellValueFactory(new PropertyValueFactory<>("path"));
//        path_column.setCellValueFactory(new PropertyValueFactory<>("path"));
//        nomboutique_column.setCellValueFactory(new PropertyValueFactory<>("id_boutique"));

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

    void showPubDetails(Evenement e) throws ParseException {

//        id.setText(String.valueOf(p.getId_pub()));
        System.out.println("id of event is " + e.getId_evenement());
        idnew = Integer.toString(e.getId_evenement());
        SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");
        String dated = outFmt.format(inFmt.parse(e.getDate()));
//        txtdate_debut.setPromptText(dated);

        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dated, formatter);
            txtdate.setValue(date);

        } catch (DateTimeParseException exc) {
//            throw exc;      // Rethrow the exception.
        }
        
//        Image image1=new Image("C:\\wamp64\\www\\TestUser\\web\\images\\amine\\"+e.getPath());
//        image.setImage(image1);

        txt_eventname.setText(e.getNom());
        txt_description.setText(e.getDescription());
        btinsertimage.setText(e.getPath());
        btinsertimage.setText(e.getPath());
        txtchoixuser.setValue(e.getUser());
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
            preparedStatement.setString(1, rechercher.getText());
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

    //    ***************************************************************************
    void filterEmployeList(String oldValue, String newValue) {
        IEvenementService ius = new EvenementService();
        ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
        if (rechercher.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
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

    @FXML
    private void insert_image(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            getImageUrl = selectedfile.getAbsolutePath();
            System.out.println("s " + selectedfile);
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            System.out.println(getImageUrl);
            int fileNameIndex = getImageUrl.lastIndexOf("\\") + 1;

            imgName = getImageUrl.substring(fileNameIndex);
            File dest = new File("C:\\wamp64\\www\\TestUser\\web\\images\\amine\\" + imgName);
            System.out.println("hello" + imgName);
            copyFileUsingStream(selectedfile, dest);
        } else {
            System.out.println("file does not exist");
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
