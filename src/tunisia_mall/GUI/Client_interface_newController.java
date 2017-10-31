/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static tunisia_mall.GUI.NewaccountController.getImageUrl;
import tunisia_mall.Interface.IPubliciteService;
import tunisia_mall.Services.PubliciteService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class Client_interface_newController implements Initializable {

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnAlerts;
    @FXML
    private AnchorPane holderPane;

    public AnchorPane getHolderPane() {
        return holderPane;
    }

    public void setHolderPane(AnchorPane holderPane) {
        this.holderPane = holderPane;
    }
    
    
    @FXML
    private JFXButton btnControls1;
    @FXML
    private JFXButton btnControls11;
    @FXML
    private ImageView publicite_image;
    @FXML
    private Label hello_text;
    @FXML
    private Button btnClose;
    @FXML
    private JFXButton btpanier;
    @FXML
    private JFXButton btProfile;
    @FXML
    private JFXButton btforum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IPubliciteService ips = new PubliciteService();
        String img = ips.getPubImage();
        System.out.println(img);
        File file = new File(img);
        Image ima = new Image(file.toURI().toString());
        publicite_image.setImage(ima);
        
        String nom = LoginController.LoggedUser.getNom();
        String prenom=LoginController.LoggedUser.getPrenom();
        hello_text.setText("hello "+nom+" "+prenom);
    }

    @FXML
    private void acceuil(ActionEvent event) {
//        client_interface_new
            holderPane.getChildren().clear();
    }


    @FXML
    private void evenement(ActionEvent event) {
//        client consulte la meme espace que le shopowner
        try {
            holderPane.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Event_shopowner.fxml"));
            holderPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void offreemploi(ActionEvent event) throws IOException {
        
           try {
            holderPane.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ClientOffre_emploi.fxml"));
            holderPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               
            }
        
    

    @FXML
    private void profile(ActionEvent event) {
        try {
            holderPane.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLCompteClient.fxml"));
            holderPane.getChildren().add(newLoadedPane);
            
            
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reclamation(ActionEvent event) {
        try {
            holderPane.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLGestionReclamationCC.fxml"));
            holderPane.getChildren().add(newLoadedPane);
            
            
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void panier(ActionEvent event) {
    }

    @FXML
    private void parking(ActionEvent event) {
    }

    @FXML
    private void jardinEnfant(ActionEvent event) {
    }

    @FXML
    private void CloseWindow(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void forum(ActionEvent event) {
        
                try {
            holderPane.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ForumGes.fxml"));
            holderPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
