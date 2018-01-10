/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.User;
import tunisia_mall.util.ControlesaisieJ;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLCompteClientController implements Initializable {

    @FXML
    private TextField login_txt;
    @FXML
    private TextField numerotel_txt;
    @FXML
    private TextField mail_txt;
    @FXML
    private TextField adresse_txt;
    @FXML
    private TextField path_txt;
    @FXML
    private ComboBox<String> sexe_combo;
    @FXML
    private PasswordField password_txt;
    @FXML
    private DatePicker dnaissance_txt;
    @FXML
    private Label lbTitulo1;
    @FXML
    private ToggleButton modifierC;
    @FXML
    private ToggleButton desactiver;
    @FXML
    private ToggleGroup menu;
    ObservableList<String> comboListsexe = FXCollections.observableArrayList("Homme", "Femme");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
 
        
        
        sexe_combo.setItems(comboListsexe);
        afficher();
    }

    @FXML
    private void modifierC(ActionEvent event) throws ParseException {
        ControlesaisieJ cj = new ControlesaisieJ();
        if (!(dnaissance_txt.getEditor().getText().equals("")) && !(sexe_combo.getValue().equals("")) && !(login_txt.getText().equals(""))
                && !(password_txt.getText().equals("")) && !(mail_txt.getText().equals("")) && !(numerotel_txt.getText().equals(""))) {

            if (cj.testlogin(login_txt.getText())) {

                if (cj.testpassword(password_txt.getText())) {
                    if (cj.GSM(numerotel_txt.getText())) {
                        if (cj.mailformat(mail_txt.getText())) {
                            String datenaissance = dnaissance_txt.getEditor().getText();
                            SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");

                            String dateN = outFmt.format(inFmt.parse(datenaissance));

                            LocalDate db = dnaissance_txt.getValue();

                            /*String dateembauche = LoginController.LoggedUser.getDate_embauche();
                            SimpleDateFormat inFmt1 = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat outFmt1 = new SimpleDateFormat("dd/MM/yyyy");
                            String dateEMB = outFmt1.format(inFmt1.parse(dateembauche));

                            String dateexpiration = LoginController.LoggedUser.getDate_expiration();
                            SimpleDateFormat inFmt2 = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat outFmt2 = new SimpleDateFormat("dd/MM/yyyy");

                            String dateexp = outFmt2.format(inFmt2.parse(dateexpiration));*/

                            LoginController.LoggedUser.setDate_naissance(dateN);
                            /*LoginController.LoggedUser.setDate_embauche(dateEMB);
                            LoginController.LoggedUser.setDate_expiration(dateexp);*/

                            LoginController.LoggedUser.setSexe(sexe_combo.getValue());
                            LoginController.LoggedUser.setLogin(login_txt.getText());
                            LoginController.LoggedUser.setPassword(password_txt.getText());
                            LoginController.LoggedUser.setMail(mail_txt.getText());

                            LoginController.LoggedUser.setNumero_telephone(Integer.parseInt(numerotel_txt.getText()));
                            LoginController.LoggedUser.setAdresse(adresse_txt.getText());
                            LoginController.LoggedUser.setSalaire(0);
                            LoginController.LoggedUser.setPath(path_txt.getText());

                            IUserService ius = new UserService();
                            UserService us=new UserService();
                            us.update2(LoginController.LoggedUser);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modifier Données ");
                            alert.setHeaderText("Modification des données est effectué avec succé");
                            Optional<ButtonType> result = alert.showAndWait();

                        } else {
                            Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                            alertE.setTitle("Erreur d'ajout ");
                            alertE.setHeaderText("Format du mail invalide");
                            Optional<ButtonType> result = alertE.showAndWait();
                        }
                    } else {
                        Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                        alertE.setTitle("Erreur d'ajout ");
                        alertE.setHeaderText("Numero telephone doit contenir 8 chiffres");
                        Optional<ButtonType> result = alertE.showAndWait();
                    }
                } else {
                    Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                    alertE.setTitle("Erreur d'ajout ");
                    alertE.setHeaderText("Longueur de Mot de passe inférieur à 8");
                    Optional<ButtonType> result = alertE.showAndWait();
                }

            } else {
                Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                alertE.setTitle("Erreur d'ajout ");
                alertE.setHeaderText("Format du login invalide ");
                Optional<ButtonType> result = alertE.showAndWait();
            }
        } else {
            Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
            alertE.setTitle("Erreur de modification ");
            alertE.setHeaderText("Tous les champs sont obligatoires ");
            Optional<ButtonType> result = alertE.showAndWait();

        }
    }

    @FXML
    private void desactivercompte(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Desactivation compte ");
        alert.setHeaderText("Etes-vous sur que vous voulez desactiver votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            IUserService ius = new UserService();
            ius.desactivercompte(LoginController.LoggedUser.getId_user());
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Desactivation compte ");
            alert.setHeaderText("Desactivation effectué ");
            Optional<ButtonType> result1 = alert1.showAndWait();
        }
    }

    void afficher() {
        String datenaissance = LoginController.LoggedUser.getDate_naissance();
        SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");

        String dateN;
        try {

            dateN = outFmt.format(inFmt.parse(datenaissance));
            dnaissance_txt.getEditor().setText(dateN);
        } catch (ParseException ex) {
            Logger.getLogger(FXMLCompteAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sexe_combo.setValue(LoginController.LoggedUser.getSexe());
        login_txt.setText(LoginController.LoggedUser.getLogin());
        password_txt.setText(LoginController.LoggedUser.getPassword());
        mail_txt.setText(LoginController.LoggedUser.getMail());
        numerotel_txt.setText(Integer.toString(LoginController.LoggedUser.getNumero_telephone()));
        adresse_txt.setText(LoginController.LoggedUser.getAdresse());
        path_txt.setText(LoginController.LoggedUser.getPath());
    }
}
