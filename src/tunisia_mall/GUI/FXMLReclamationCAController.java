/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.Optional;
import java.util.Properties;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import tunisia_mall.Interface.IReclamationService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.ReclamationService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Reclamation;
import tunisia_mall.models.User;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import tunisia_mall.util.ControlesaisieJ;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLReclamationCAController implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleButton rechercheR;
    @FXML
    private ToggleButton repondreR;
    @FXML
    private ToggleButton supprimerR;
    @FXML
    private ToggleGroup menu;
    @FXML
    private TableView<Reclamation> tblreclamation;
    @FXML
    private TableColumn<Reclamation, String> tblclmtype;
    @FXML
    private TableColumn<Reclamation, String> tblclmdescription;
    @FXML
    private TableColumn tblclmnom;
    @FXML
    private TableColumn tblclmprenom;
    @FXML
    private TableColumn tblclmmail;
    @FXML
    private TableColumn tblclmnumtel;
    ObservableList<String> comboListtype = FXCollections.observableArrayList("facture", "opposition chequier", "Autre");
    @FXML
    private ComboBox<String> id_combo;
    @FXML
    private ComboBox<String> type_combo;
    @FXML
    private TextField mail_txt;
    @FXML
    private TextArea description_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherR();
        type_combo.setItems(comboListtype);
        IReclamationService irs = new ReclamationService();
        id_combo.setItems(irs.displayallclientName());

        tblreclamation.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showRecDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(FXMLGestionEmployeCAController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

        txtPesquisar.textProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterReclamationList((String) oldValue, (String) newValue);

            }

        });
    }

    @FXML
    private void rechercherR(ActionEvent event) {
    }

    @FXML
    private void repondreR(ActionEvent event) {
        ControlesaisieJ cj = new ControlesaisieJ();
        if (!(mail_txt.getText().equals("")) && !(description_txt.getText().equals(""))) {
            if (cj.mailformat(mail_txt.getText())) {
                String mail = mail_txt.getText();
                String description = description_txt.getText();
                reponseR(mail, description);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Reponse de reclamation ");
                alert.setHeaderText("Reponse envoyé avec succé");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                alertE.setTitle("Erreur d'ajout ");
                alertE.setHeaderText("Format du mail invalide");
                Optional<ButtonType> result = alertE.showAndWait();
            }
        } else {
            Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
            alertE.setTitle("Erreur des champs vides ");
            alertE.setHeaderText("Tous les champs sont obligatoires ");
            Optional<ButtonType> result = alertE.showAndWait();

        }
    }

    @FXML
    private void supprimerR(ActionEvent event) {
        if (!tblreclamation.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression de reclamation ");
            alert.setHeaderText("Etes-vous sur que vous voulez supprimer la reclamation"
                    + tblreclamation.getSelectionModel().getSelectedItem().getId_reclamation() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IReclamationService irs = new ReclamationService();
                irs.remove(tblreclamation.getSelectionModel().getSelectedItem().getId_reclamation());
                afficherR();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner une reclamation  ");

            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    void afficherR() {
        IReclamationService irs = new ReclamationService();

        tblreclamation.setItems(irs.displayallR());

        tblclmtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblclmdescription.setCellValueFactory(new PropertyValueFactory<>("text"));

        tblclmnom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                return new SimpleStringProperty(param.getValue().getUserreclamant().getNom());
            }
        });

        tblclmprenom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                return new SimpleStringProperty(param.getValue().getUserreclamant().getPrenom());
            }
        });
        tblclmmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                return new SimpleStringProperty(param.getValue().getUserreclamant().getMail());
            }
        });
        tblclmnumtel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getUserreclamant().getNumero_telephone()));
            }
        });
    }
    String idRnew;
    String idRrnew;

    void showRecDetails(Reclamation r) throws ParseException {

//        id.setText(String.valueOf(p.getId_pub()));
        idRnew = String.valueOf(r.getUserreclamant().getId_user());
        idRrnew = String.valueOf(r.getUserreclame().getId_user());

        IUserService ius = new UserService();
        User u = new User();
        System.out.println(ius.findById(r.getUserreclamant().getId_user()));

        id_combo.setValue(r.getUserreclamant().getNom() + " " + r.getUserreclamant().getPrenom());
        type_combo.setValue(r.getType());
        description_txt.setText("Après étude, il semble en effet que certains manquements ont eu lieu dans "
                + "la gestion mise en place par notre service. Croyez bien que nous sommes désolés de cet état "
                + "de fait et que votre dossier fait dès à présent l’objet d’une étude particulière.\n"
                + "Aussi, vous serez contacté(e) très prochainement par un membre de notre équipe / Monsieur Ahmed Merzoug.\n"
                + "Espérant que tout rentrera dans l’ordre rapidement.\n"
                + "Cordialement");
        mail_txt.setText(ius.findById(r.getUserreclamant().getId_user()).getMail());

    }

    void reponseR(String m, String d) {
        try {
            String host = "smtp.gmail.com";
            String user = "tunisiamalla@gmail.com";
            String pass = "pidev2018";
            String to = m;
            String from = "tunisiamalla@gmail.com";
            String subject = "This is response for your claim";
            String messageText = d;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            //msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    void filterReclamationList(String oldValue, String newValue) {
        IReclamationService irs = new ReclamationService();
        ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
        if (txtPesquisar.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblreclamation.setItems(irs.displayallR());

        } else {

            newValue = newValue.toUpperCase();

            for (Reclamation reclamation : tblreclamation.getItems()) {

                String filtertype = reclamation.getType();

                String filterprenom = reclamation.getUserreclamant().getPrenom();
                String filternom = reclamation.getUserreclamant().getPrenom();

                if (filtertype.toUpperCase().contains(newValue) || filterprenom.toUpperCase().contains(newValue) || filternom.toUpperCase().contains(newValue)) {
                    filteredList.add(reclamation);

                }

            }
            tblreclamation.setItems(filteredList);

        }
    }
}
