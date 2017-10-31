/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Interface.IReclamationService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.ReclamationService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Reclamation;
import tunisia_mall.models.User;
import tunisia_mall.util.ControlesaisieJ;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLGestionReclamationCCController implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private ToggleButton envoyerR;
    @FXML
    private ComboBox<String> id_combo;
    @FXML
    private ComboBox<String> type_combo;
    @FXML
    private TextArea description_txt;
     ObservableList<String> comboListtype = FXCollections.observableArrayList("facture", "opposition chequier","Autre");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type_combo.setItems(comboListtype);
        IBoutiqueService iBoutiqueService=new BoutiqueService();
        id_combo.setItems(iBoutiqueService.displayallnom());
        
    }    

    @FXML
    private void envoyerR(ActionEvent event) {
        ControlesaisieJ cj = new ControlesaisieJ();
        if( !(type_combo.getValue().equals("")) && !(description_txt.getText().equals("")) && !( id_combo.getValue().equals("")))
        {
         
        
    
      
        IReclamationService irs=new ReclamationService();
        IUserService ius=new UserService();
        IBoutiqueService ibs=new BoutiqueService();
       // LoginController.LoggedUser.setId_user(LoginController.LoggedUser.getId_user());
        //ius.findPropByIdB(ibs.findBoutiqueByNom(id_combo.getValue()).getId_boutique()).setId_user(ius.findPropByIdB(ibs.findBoutiqueByNom(id_combo.getValue()).getId_boutique()).getId_user());
        Reclamation r=new Reclamation(type_combo.getValue(),description_txt.getText(),LoginController.LoggedUser,
        ius.findPropByIdB(ibs.findBoutiqueByNom(id_combo.getValue()).getId_boutique()));
            System.out.println(r);
        irs.add(r);
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Reclamation ");
            alert.setHeaderText("Réclamation envoyé avec succé");
            Optional<ButtonType> result=alert.showAndWait();
        }else{
             Alert alertE =new Alert(Alert.AlertType.CONFIRMATION);
            alertE.setTitle("Erreur des champs vides ");
            alertE.setHeaderText("Tous les champs sont obligatoires ");
            Optional<ButtonType> result=alertE.showAndWait();
            
        }

    }
    
}
