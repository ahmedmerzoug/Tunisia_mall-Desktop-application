/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class shopownerController implements Initializable {

    @FXML
    private ToggleButton bouton_gestion_employers_shopowner;
    @FXML
    private ToggleGroup grupoMenus;
    @FXML
    private VBox boxCatalogacao;
    @FXML
    private ToggleButton bouton_gestion_boutique_shopowner;
    @FXML
    private VBox boxVisitas;
    @FXML
    private VBox boxEmprestimo;
    @FXML
    private ToggleButton bouton_CFidelité_shopowner;
    @FXML
    private VBox boxLocalizacao;
    @FXML
    private VBox boxUtilitarios;
    @FXML
    private ToggleButton bouton_reclamation_shopowner;
    @FXML
    private ToggleGroup grupoMenus1;
    @FXML
    private VBox boxReclamation;
    @FXML
    private ToggleButton bouton_Offre_emploi_shopowner;
    @FXML
    private ToggleGroup grupoMenus2;
    @FXML
    private VBox boxOffreEmploi;
    @FXML
    private VBox boxPublicite;
    @FXML
    private ToggleButton bouton_Publicite_shopowner;
    @FXML
    private ToggleGroup grupoMenus11;
    @FXML
    private VBox boxEvenement;
    @FXML
    private ToggleButton bouton_Evenement_shopowner;
    @FXML
    private ToggleGroup grupoMenus111;
    @FXML
    private ToggleGroup grupoMenus1111;
    @FXML
    private Button btnClose;
    @FXML
    private AnchorPane boxConteudo;
    @FXML
    private Label lbMensagem;
    @FXML
    private VBox boxNotas;
    @FXML
    private Label hello_text;
    @FXML
    private ToggleButton nsul_demandebtn;
    @FXML
    private ToggleGroup grupoMenus21;
    @FXML
    private ToggleButton bouton_Parametres_shopowner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nom = LoginController.LoggedUser.getNom();
        String prenom = LoginController.LoggedUser.getPrenom();
        hello_text.setText("hello " + nom + " " + prenom);
        IUserService aa = new UserService();
         TrayNotification tray = new TrayNotification();
         User b =  aa.findbypath(LoginController.LoggedUser.getId_user());
          Image whatsAppImg = new Image("http://localhost/TestUser/web/images/Userimage/" + b.getPath());
        
         tray.setTray("welcome", nom +" "+prenom, whatsAppImg, Paint.valueOf("#2A9A84"),AnimationType.SLIDE);
        tray.showAndDismiss(Duration.seconds(10));
        // TODO
    }

    @FXML
    private void menu_gestion_employers_shopowner(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLGestionEmployeCP.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);

        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu_gestion_boutiques_shopowner(ActionEvent event) {
          try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Produitshop123.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);

        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu_CFidelité_shopowner(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionCFideliteFXML.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu_reclamation_shopowner(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLGestionReclamationCP.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);

        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu_offre_emploi_shopowner(ActionEvent event) {

        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionOffre_emploiShop.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void menu_Publicite_shopowner(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Pub_shopowner.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu_Evenement_shopowner(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Event_shopowner.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void menu_Forum_shopowner(ActionEvent event) {

        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CloseWindow(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void siteMuseu(ActionEvent event) {
    }

    @FXML
    private void menu_demande_emploi(ActionEvent event) {

        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ConsulterDemande_emploiShop.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void menu_Parametres_shopowner(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLCompteSO.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);

        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
