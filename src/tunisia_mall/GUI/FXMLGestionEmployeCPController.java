/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.User;
import tunisia_mall.GUI.LoginController;
import tunisia_mall.util.BCrypt;
import tunisia_mall.util.ControlesaisieJ;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLGestionEmployeCPController implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleButton rechercheE;
    @FXML
    private ToggleButton ajoutE;
    @FXML
    private ToggleButton modifierE;
    @FXML
    private ToggleButton supprimerE;
    @FXML
    private ToggleGroup menu;
    @FXML
    private TableView<User> tblemploye;
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
    private TableColumn<User, Float> tblclmsalaire;
    @FXML
    private TableColumn<User, String> tblclmdateem;
    @FXML
    private TableColumn<User, String> tblclmdateexp;
    @FXML
    private TableColumn tblclmidboutique;
    @FXML
    private TextField prenom_txt;
    @FXML
    private TextField login_txt;
    @FXML
    private TextField numerotel_txt;
    @FXML
    private TextField mail_txt;
    @FXML
    private TextField adresse_txt;
    @FXML
    private TextField salaire_txt;
    @FXML
    private TextField nom_txt;
    @FXML
    private TextField path_txt;
    private ComboBox<Boutique> idbout_combo;
    @FXML
    private ComboBox<String> sexe_combo;
    private ComboBox<String> role_combo;
    @FXML
    private PasswordField password_txt;
    @FXML
    private DatePicker dnaissance_txt;
    @FXML
    private DatePicker dateem_txt;
    @FXML
    private DatePicker dateexp_txt;
    ObservableList<String> comboListsexe = FXCollections.observableArrayList("Homme", "Femme");

    ObservableList<Boutique> boutiques = FXCollections.observableArrayList();
    @FXML
    private ImageView image;
    @FXML
    private ToggleButton ajoutimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sexe_combo.setValue("Homme");
        sexe_combo.setItems(comboListsexe);

        try {
            afficher(LoginController.LoggedUser);
        } catch (IOException ex) {
            Logger.getLogger(FXMLGestionEmployeCPController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblemploye.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showEmployeDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(FXMLGestionEmployeCAController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

        txtPesquisar.textProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterEmployeList((String) oldValue, (String) newValue);

            }

        });

    }
//////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void rechercherE(ActionEvent event) {

    }
//////////////////////////////////////////////////////////////////////////////////////////////////
public String hashmdp(String password)
    {
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt()); 
    return hashed;
    }
    @FXML
    private void ajouterE(ActionEvent event) throws ParseException {
        ControlesaisieJ cj = new ControlesaisieJ();
        IBoutiqueService ibs = new BoutiqueService();
        IUserService ius = new UserService();
        if (!(nom_txt.getText().equals("")) && !(prenom_txt.getText().equals("")) && !(dnaissance_txt.getEditor().getText().equals(""))
                && !(sexe_combo.getValue().equals("")) && !(login_txt.getText().equals("")) && !(password_txt.getText().equals(""))
                && !(mail_txt.getText().equals("")) && !(numerotel_txt.getText().equals(""))
                && !(salaire_txt.getText().equals("")) && !(dateem_txt.getEditor().getText().equals(""))
                && !(dateexp_txt.getEditor().getText().equals(""))) {
            if (cj.testnomprenom(nom_txt.getText())) {
                if (cj.testnomprenom(prenom_txt.getText())) {
                    if (cj.testlogin(login_txt.getText())) {
                        if (cj.loginvalide(login_txt.getText())) {

                            if (cj.testpassword(password_txt.getText())) {
                                if (cj.GSM(numerotel_txt.getText())) {
                                    if (cj.mailformat(mail_txt.getText())) {
                                        if ((cj.testdateEMB_dateEXP(dateem_txt.getEditor().getText(), dateexp_txt.getEditor().getText())) == -1) {
                                            //****************************************************************
                                            User u = new User(nom_txt.getText(), prenom_txt.getText(), dnaissance_txt.getEditor().getText(), sexe_combo.getValue(), login_txt.getText(),
                                                    hashmdp(password_txt.getText()), mail_txt.getText(), "Employe", Integer.parseInt(numerotel_txt.getText()), adresse_txt.getText(),
                                                    Integer.parseInt(salaire_txt.getText()), dateem_txt.getEditor().getText(), dateexp_txt.getEditor().getText(), path_txt.getText(), ibs.findById(LoginController.LoggedUser.getBoutique().getId_boutique()));
                                            ius.add(u);

                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Succés d'ajout ");
                                            alert.setHeaderText("Employé ajouté avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            try {
                                                afficher(LoginController.LoggedUser);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLGestionEmployeCPController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            //***********************************************************************
                                        } else {
                                            Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                                            alertE.setTitle("Erreur d'ajout ");
                                            alertE.setHeaderText("Date d'embauche doit etre inferieur a la date expiration");
                                            Optional<ButtonType> result = alertE.showAndWait();
                                        }
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
                            alertE.setHeaderText("Login deja utilisé ");
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
                    alertE.setTitle("Erreur d'ajout ");
                    alertE.setHeaderText("Format du prenom invalide ");
                    Optional<ButtonType> result = alertE.showAndWait();
                }
            } else {
                Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                alertE.setTitle("Erreur d'ajout ");
                alertE.setHeaderText("Format du nom invalide ");
                Optional<ButtonType> result = alertE.showAndWait();
            }

        } else {
            Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
            alertE.setTitle("Erreur d'ajout ");
            alertE.setHeaderText("Les champs * sont obligatoires ");
            Optional<ButtonType> result = alertE.showAndWait();
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void modifierE(ActionEvent event) throws ParseException {
        if (!tblemploye.getSelectionModel().isEmpty()) {
            String datenaissance = dnaissance_txt.getEditor().getText();
            SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outFmt = new SimpleDateFormat("dd MMMM yyyy");

            String dateN = outFmt.format(inFmt.parse(datenaissance));

            LocalDate db = dnaissance_txt.getValue();

            String dateembauche = dateem_txt.getEditor().getText();
            SimpleDateFormat inFmt1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outFmt1 = new SimpleDateFormat("dd MMMM yyyy");
            String dateEMB = outFmt1.format(inFmt1.parse(dateembauche));

            String dateexpiration = dateexp_txt.getEditor().getText();
            SimpleDateFormat inFmt2 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outFmt2 = new SimpleDateFormat("dd MMMM yyyy");

            String dateexp = outFmt2.format(inFmt2.parse(dateexpiration));

            User u = new User();
            IBoutiqueService ibs = new BoutiqueService();
            u.setDate_naissance(datenaissance);
            u.setDate_embauche(dateembauche);
            u.setDate_expiration(dateexpiration);
            u.setNom(nom_txt.getText());
            u.setPrenom(prenom_txt.getText());
            u.setSexe(sexe_combo.getValue());
            u.setLogin(login_txt.getText());
            u.setPassword(password_txt.getText());
            u.setMail(mail_txt.getText());
            u.setRole("employe");
            u.setNumero_telephone(Integer.parseInt(numerotel_txt.getText()));
            u.setAdresse(adresse_txt.getText());
            u.setSalaire(Float.parseFloat(salaire_txt.getText()));
            u.setPath(path_txt.getText());
            u.setBoutique(ibs.findById(LoginController.LoggedUser.getBoutique().getId_boutique()));

            IUserService ius = new UserService();
            u.setId_user(Integer.valueOf(idEnew));
            //***************** controle de saisie ***********************************
            ControlesaisieJ cj = new ControlesaisieJ();
            if (!(nom_txt.getText().equals("")) && !(prenom_txt.getText().equals("")) && !(dnaissance_txt.getEditor().getText().equals(""))
                    && !(sexe_combo.getValue().equals("")) && !(login_txt.getText().equals("")) && !(password_txt.getText().equals(""))
                    && !(mail_txt.getText().equals("")) && !(numerotel_txt.getText().equals(""))
                    && !(salaire_txt.getText().equals("")) && !(dateem_txt.getEditor().getText().equals(""))
                    && !(dateexp_txt.getEditor().getText().equals(""))) {
                if (cj.testnomprenom(nom_txt.getText())) {
                    if (cj.testnomprenom(prenom_txt.getText())) {
                        if (cj.testlogin(login_txt.getText())) {

                            if (cj.testpassword(password_txt.getText())) {
                                if (cj.GSM(numerotel_txt.getText())) {
                                    if (cj.mailformat(mail_txt.getText())) {
                                        if ((cj.testdateEMB_dateEXP(dateem_txt.getEditor().getText(), dateexp_txt.getEditor().getText())) == -1) {
                                            //****************************************************************
                                            ius.update(u);

                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Modifier Employe ");
                                            alert.setHeaderText("Modificatio de l'employe"
                                                    + tblemploye.getSelectionModel().getSelectedItem().getId_user() + " est effectué avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            try {
                                                afficher(LoginController.LoggedUser);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLGestionEmployeCPController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            //***********************************************************************
                                        } else {
                                            Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                                            alertE.setTitle("Erreur d'ajout ");
                                            alertE.setHeaderText("Date d'embauche doit etre inferieur a la date expiration");
                                            Optional<ButtonType> result = alertE.showAndWait();
                                        }
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
                        alertE.setTitle("Erreur d'ajout ");
                        alertE.setHeaderText("Format du prenom invalide ");
                        Optional<ButtonType> result = alertE.showAndWait();
                    }
                } else {
                    Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                    alertE.setTitle("Erreur d'ajout ");
                    alertE.setHeaderText("Format du nom invalide ");
                    Optional<ButtonType> result = alertE.showAndWait();
                }

            } else {
                Alert alertE = new Alert(Alert.AlertType.CONFIRMATION);
                alertE.setTitle("Erreur d'ajout ");
                alertE.setHeaderText("Les champs * sont obligatoires ");
                Optional<ButtonType> result = alertE.showAndWait();
            }
            //************************************************************************

        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Erreur de selection");
            alert1.setHeaderText("Vous etes obligé de selectioner un employe  ");

            Optional<ButtonType> result = alert1.showAndWait();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void supprimerE(ActionEvent event) {
        if (!tblemploye.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'employe ");
            alert.setHeaderText("Etes-vous sur que vous voulez supprimer l'employe"
                    + tblemploye.getSelectionModel().getSelectedItem().getId_user() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IUserService ius = new UserService();
                ius.remove(tblemploye.getSelectionModel().getSelectedItem().getId_user());
                try {
                    afficher(LoginController.LoggedUser);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLGestionEmployeCPController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un employe  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////    

    void afficher(User u) throws IOException {
        /* User u=new User();
        FXMLLoader loader1=new FXMLLoader (getClass().getResource("Login.fxml"));
        Parent root = loader1.load();
        
        LoginController employecontroller =loader1.getController();
         employecontroller.setUserShop(u) ;*/
        IUserService ius = new UserService();
        tblemploye.setItems(ius.displayallempl(u.getBoutique().getId_boutique()));

        tblclmnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblclmprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblclmdatenais.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tblclmsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        tblclmmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tblclmnum.setCellValueFactory(new PropertyValueFactory<>("numero_telephone"));
        tblclmadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tblclmsalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        tblclmdateem.setCellValueFactory(new PropertyValueFactory<>("date_embauche"));
        tblclmdateexp.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        ////// tblclmidboutique.setCellValueFactory(new PropertyValueFactory<>("id_boutique"));
        tblclmidboutique.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getBoutique().getNom());
            }
        });

    }
    String idEnew;

    void showEmployeDetails(User e) throws ParseException {

//        id.setText(String.valueOf(p.getId_pub()));
        idEnew = String.valueOf(e.getId_user());

        SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");

        String dateN = outFmt.format(inFmt.parse(e.getDate_naissance()));

//        txtdate_debut.setPromptText(dated);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateN, formatter);
            dnaissance_txt.setValue(date);
            System.out.println(date);

        } catch (DateTimeParseException exc) {
            throw exc;      // throw the exception.
        }

        SimpleDateFormat inFmt1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt1 = new SimpleDateFormat("dd/MM/yyyy");

        String dateem = outFmt1.format(inFmt1.parse(e.getDate_embauche()));
        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date1 = LocalDate.parse(dateem, formatter);
            dateem_txt.setValue(date1);
            System.out.println(date1);

        } catch (DateTimeParseException exc) {
            throw exc;      // Rethrow the exception.
        }
        SimpleDateFormat inFmt2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt2 = new SimpleDateFormat("dd/MM/yyyy");

        String dateexp = outFmt2.format(inFmt2.parse(e.getDate_expiration()));
        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date2 = LocalDate.parse(dateexp, formatter);
            dateexp_txt.setValue(date2);
            System.out.println(date2);

        } catch (DateTimeParseException exc) {
            throw exc;      // Rethrow the exception.
        }

        nom_txt.setText(e.getNom());
        prenom_txt.setText(e.getPrenom());
        sexe_combo.setValue(e.getSexe());
        login_txt.setText(e.getLogin());
        password_txt.setText(e.getPassword());
        mail_txt.setText(e.getMail());

        numerotel_txt.setText(String.valueOf(e.getNumero_telephone()));
        adresse_txt.setText(e.getAdresse());
        salaire_txt.setText(String.valueOf(e.getSalaire()));
        path_txt.setText(e.getPath());

    }

    void filterEmployeList(String oldValue, String newValue) {
        IUserService ius = new UserService();
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        if (txtPesquisar.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblemploye.setItems(ius.displayallempl(LoginController.LoggedUser.getBoutique().getId_boutique()));

        } else {

            newValue = newValue.toUpperCase();

            for (User user : tblemploye.getItems()) {

                String filterName = user.getNom();

                String filterprenom = user.getPrenom();
                String filterboutique = user.getBoutique().getNom();

                if (filterName.toUpperCase().contains(newValue) || filterprenom.toUpperCase().contains(newValue) || filterboutique.toUpperCase().contains(newValue)) {
                    filteredList.add(user);

                }

            }
            tblemploye.setItems(filteredList);

        }
    }

    @FXML
    private void afficherimage(MouseEvent event) {
        if (event.getClickCount() == 1) //Checking double click
        {
            int S = tblemploye.getSelectionModel().getSelectedItem().getId_user();
            User u = new User();
            IUserService irs = new UserService();
            u = irs.findById(S);

            String imageFile = (irs.findById(tblemploye.getSelectionModel().getSelectedItem().getId_user()).getPath());
            System.out.println(imageFile);

            Image image1 = new Image(imageFile);

            image.setImage(image1);

        }
    }

    @FXML
    private void ajouterimg(ActionEvent event) throws MalformedURLException {
        String imageFile;
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            //    getImageUrl = selectedFile.getAbsolutePath();
            // System.out.println(getImageUrl);
            // Image value = new Image(getImageUrl);
            //  img.setImage(value);
            imageFile = selectedFile.toURI().toURL().toString();
            System.out.println(imageFile);

            Image image1 = new Image(imageFile);

            image.setImage(image1);
            //////a changer static

            //////////
            path_txt.setText(imageFile);

            /////
        } else {
            System.out.println("file doesn't exist");
        }
    }

}
