/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tunisia_mall.Services.Demande_emploiService;
import tunisia_mall.Services.Email_tesnim;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.Demande_emploi;

/**
 * FXML Controller class
 *
 * @author bn
 */
public class ConsulterDemande_emploiAdminController implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private TableView<Demande_emploi> demande_table;
    @FXML
    private TableColumn<Demande_emploi, String> nom_emp;
    @FXML
    private TableColumn<Demande_emploi, String> prenom_emp;
    @FXML
    private TableColumn<Demande_emploi, Date> date_naissance;
    @FXML
    private TableColumn<Demande_emploi, String> adresse;
    @FXML
    private TableColumn<Demande_emploi, String> sexe;
    @FXML
    private TableColumn<Demande_emploi, String> email;
    @FXML
    private TableColumn<Demande_emploi, String> num_tel;
    @FXML
    private TableColumn<Demande_emploi, String> qualification;
    @FXML
    private TableColumn<Demande_emploi, Integer> experience;
    @FXML
    private ComboBox<Integer> combobox;
    @FXML
    private Button statbtn;
    @FXML
    private Button mail_btn;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Demande_emploi> list_demande = FXCollections.observableArrayList();
    Demande_emploi d = new Demande_emploi();
    Demande_emploiService de = new Demande_emploiService();
    @FXML
    private CheckBox mine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Demande_emploiService ds = new Demande_emploiService();
        // IUserService iu = new UserService ();
        combobox.setItems(ds.displayall());

        list_demande = FXCollections.observableArrayList(de.getAll2());

        nom_emp.setCellValueFactory(new PropertyValueFactory<>("nom_emp"));
        nom_emp.cellFactoryProperty();

        date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        date_naissance.cellFactoryProperty();

        prenom_emp.setCellValueFactory(new PropertyValueFactory<>("prenom_emp"));
        prenom_emp.cellFactoryProperty();

        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        adresse.cellFactoryProperty();

        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        sexe.cellFactoryProperty();

        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.cellFactoryProperty();

        num_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        num_tel.cellFactoryProperty();

        qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        qualification.cellFactoryProperty();

        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        experience.cellFactoryProperty();

        demande_table.setItems(list_demande);
    }

    public static String adress;

    @FXML
    private void choix_demande(MouseEvent event) {

        if (demande_table.getSelectionModel().getSelectedItem() != null) {
            Demande_emploi d = demande_table.getSelectionModel().getSelectedItem();

            adress = d.getEmail();

        }

    }

    @FXML
    private void envoyermail(ActionEvent event) {

        Email_tesnim emailS = new Email_tesnim();
        String[] to = {adress};
        /*from = "Tunisia Mall administration";
             subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
            String messageText = "Bonjour, félicitation nous avons pris votre demande d'emploi en considération et vous êtes accepté pour ce poste\n Responsable ";
         */
        String adresse = LoginController.LoggedUser.getMail();
        System.out.println(adresse);
        String subject = "Tunisia Mall";
        String messageText = "Bonjour, félicitation nous avons pris votre demande d'emploi en considération et vous êtes accepté pour ce poste\n Responsable ";

        if (emailS.sendMail(adresse,"21202014", messageText, subject, to)) {
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.initOwner(adresse.getScene().getWindow());
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Email Envoyer Avec Succées ");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

            }
        });

    }

    private int i;

    @FXML
    private void affcombo(ActionEvent event) {

        i = combobox.getValue();

        List<Demande_emploi> demandes = de.getByExp(i);

        demande_table.setItems(FXCollections.observableArrayList(demandes));

    }

    @FXML
    private void stat(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminDemande_emploi_stat.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    private void mes_boutiques(ActionEvent event) {

        ObservableList<Demande_emploi> list = FXCollections.observableArrayList(de.getAll3(LoginController.LoggedUser.getId_user()));

        demande_table.setItems(list);
        demande_table.getColumns().get(0).setVisible(false);
        demande_table.getColumns().get(0).setVisible(true);

    }

}
