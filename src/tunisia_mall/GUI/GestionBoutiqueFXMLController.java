package tunisia_mall.GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.models.Boutique;

public class GestionBoutiqueFXMLController implements Initializable {

    @FXML
    private TableView<Boutique> TableBoutique;

    @FXML
    private TableColumn<Boutique, String> Nom_Column;

    @FXML
    private TableColumn<Boutique, String> Type_Column;

    @FXML
    private TableColumn<Boutique, String> Position_Column;
    @FXML
    private TextField NomB;
    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleGroup menu;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private ComboBox<String> cbPosition;

    
 String idnew;



    void afficher() {
        IBoutiqueService ibs = new BoutiqueService();
        TableBoutique.setItems(ibs.displayall());

        Nom_Column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("type"));
        Position_Column.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    @FXML
    void ajouter(ActionEvent event) throws IOException, InterruptedException {
        IBoutiqueService ibs = new BoutiqueService(); 
        Boutique b = new Boutique(NomB.getText() ,cbType.getValue(), cbPosition.getValue());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("done with success");
       Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
        ibs.add(b);
        NomB.clear();cbType.setValue("Veuillez choirsir le type");
        cbPosition.setValue("Veuillez choirsir la position");
        
        }
        afficher();
    }
    
    @FXML
    void modifier(ActionEvent event) {
    
       
       
      
      String nomBoutique = NomB.getText();
       /////String positionBoutique = positionB.getText();

     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ////  Boutique e = new Boutique(nom, des, datee, path, u);
           
            Boutique n = new Boutique(nomBoutique, cbType.getValue(),cbPosition.getValue());
             n.setId_boutique(Integer.parseInt(idnew));
            IBoutiqueService ibs = new BoutiqueService();
          ibs.update(n);
//   ies.add(e);
            afficher();
       
    }
    }
           

    @FXML
    void supprimer(ActionEvent event) {
        if (!TableBoutique.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression du boutique");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer la boutique:  "
                    + TableBoutique.getSelectionModel().getSelectedItem().getNom());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IBoutiqueService ibs = new BoutiqueService();
                ibs.remove(TableBoutique.getSelectionModel().getSelectedItem().getId_boutique());
                afficher();
            }

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
          cbType.getItems().addAll("accessoires de mode","art et culture","beauté et bien être","restauration","services et technologies","supermarché","sport" );
 cbPosition.getItems().addAll("Niveau 0","Niveau 1","Niveau 2","Niveau 3");
        IBoutiqueService dao = new BoutiqueService();
     ///   txtchoixuser.setItems(ibs.displayall());
        
        TableBoutique.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    showPubDetails(newValue);
                });
        afficher();
      
    }
    
             
            

    @FXML
    private void rechercherB(ActionEvent event) {
          IBoutiqueService dao = new BoutiqueService();
                String a =(txtPesquisar.getText()) ;

      TableBoutique.setItems(dao.findBoutiqueByName(a));
        Nom_Column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("type"));
        Position_Column.setCellValueFactory(new PropertyValueFactory<>("position"));
      
    }
    
    
     void showPubDetails(Boutique e){

//        id.setText(String.valueOf(p.getId_pub()));
       /////// idnew = Integer.toString(e.getId_evenement());
      /////  SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
      /////  SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");
     /////   String dated = outFmt.format(inFmt.parse(e.getDate()));
//        txtdate_debut.setPromptText(dated);

   idnew = Integer.toString(e.getId_boutique());
        
        NomB.setText(e.getNom());
        
        cbType.setValue(e.getType());
        cbPosition.setValue(e.getPosition());
        
        
    }

   

   


    
}



