/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import static tunisia_mall.GUI.NewaccountController.getImageUrl;
import tunisia_mall.Interface.IEvenementService;
import tunisia_mall.Interface.IProduitService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.EvenementService;
import tunisia_mall.Services.ProduitService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Produit;


/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class Produitshop123Controller implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleGroup menu;
    
  String imgName;
    @FXML
    private Text txtnom;
    @FXML
    private Text txtType;
    @FXML
    private Text txtPrix;
    @FXML
    private Text txtquantite;
    @FXML
    private Text txtprix_achat_gros;
    @FXML
    private Text txtDescription;
    @FXML
    private TextField tnom;
    @FXML
    private TextField ttype;
    @FXML
    private TextField tprix;
    @FXML
    private TextField tquan;
    @FXML
    private TextField tpeixgros;
    @FXML
    private TextArea tdescrip;
    @FXML
    private TableView<Produit> tbtable;
    @FXML
    private TableColumn<Produit, String> tbNom;
    @FXML
    private TableColumn<Produit, String> tbtype;
    @FXML
    private TableColumn<Produit, Float> tbprix;
    @FXML
    private TableColumn<Produit, Integer> tbquantite;
    @FXML
    private TableColumn<Produit, Float> tbprixachatengros;
    @FXML
    private TableColumn<Produit, String> tbdesc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherall();
    }    

    @FXML
    private void rechercherB(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
        int ab = LoginController.LoggedUser.getBoutique().getId_boutique();
       int v = LoginController.LoggedUser.getId_user();
        String bb = "ih";
             if (!tnom.getText().equals("") && !ttype.getText().equals("")
                && !tprix.getText().equals("")&& !tquan.getText().equals("")
                     && !tpeixgros.getText().equals("")&& !tdescrip.getText().equals("")&& !imgName.equals("")) {
                 IProduitService ies = new ProduitService();
                 
                 IUserService aa = new UserService();
                         
            Produit  e = new Produit(bb,ttype.getText(),
                   Integer.parseInt( tprix.getText()),
                    Integer.parseInt( tquan.getText()), Integer.parseInt( tpeixgros.getText()),
                     imgName,tdescrip.getText(),ab);
            
            
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("done with success");
       Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            ies.add(e);
            afficherall();
             tnom.clear(); ttype.clear(); tprix.clear(); tquan.clear(); tpeixgros.clear();
              tdescrip.clear() ;
              System.out.println("print the idboutique  "+LoginController.LoggedUser.getBoutique().getId_boutique());
              System.out.println("aaaa" +LoginController.LoggedUser.getPath() );
        }
          } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("erreur champs vides");
            alert.setHeaderText("il ya des champs vides");
            Optional<ButtonType> result = alert.showAndWait();
        }

//             SendMail.sendmail(TableEvent.getSelectionModel().getSelectedItem().getUser().getMail(),
//                    "ajout evenement", "votre evenement a été crée, nom evenement: " + e.toString());
        } 
    

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
           try {

            if (!tbtable.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("suppression de produit");
                alert.setHeaderText("etes-vous sur que vous voulez supprimer ce produit :  "
                        + tbtable.getSelectionModel().getSelectedItem().getNom());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    IProduitService abc = new ProduitService();
                    abc.remove(tbtable.getSelectionModel().getSelectedItem().getId_produit());
                    afficherall();
                }

            }
        } catch (Exception ex) {
            System.out.println("erreur lors du chargement des forums " + ex.getMessage());

        }
    }

    @FXML
    private void on_insert_action(ActionEvent event) throws IOException {
        
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
            File dest = new File("C:\\wamp64\\www\\TestUser\\web\\images\\ahmed\\" + imgName);
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
       
       public void afficherall()
       {
           
            IProduitService ips = new ProduitService();
        tbtable.setItems(ips.displayall());

        tbNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tbquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                tbprixachatengros.setCellValueFactory(new PropertyValueFactory<>("prix_achat_gros"));

                        tbdesc.setCellValueFactory(new PropertyValueFactory<>("description"));

       }
    
}
