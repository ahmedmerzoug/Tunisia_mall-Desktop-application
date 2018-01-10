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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

public class adminAppController implements Initializable {

    private static adminAppController instance;

    @FXML
    private AnchorPane boxConteudo;
    @FXML
    private VBox boxEmprestimo;
    @FXML
    private VBox boxVisitas;

    @FXML
    private VBox boxUtilitarios;
    @FXML
    private VBox boxNotas;
    @FXML
    private VBox boxCatalogacao;
    @FXML
    private Label lbMensagem;

//    **************************************Gestion Employers**********************
    @FXML
    private ToggleButton bouton_gestion_employers;

//    ********************************************Gestion Boutiques********************************
    @FXML
    private ToggleButton bouton_gestion_boutique;

////////////////////////////////////////////////////////////////////////////////    
//    ********************************************Gestion clients********************************
    @FXML
    private ToggleButton bouton_gestion_client;

//////////////////////////////////////////////////////////////////////////////
//    ********************************************Gestion responsablesBoutique********************************
    @FXML
    private ToggleButton bouton_gestion_responsable_boutique;

    @FXML
    private VBox boxLocalizacao;

    @FXML
    private ToggleButton btAjoutResponsable;

    @FXML
    private ToggleButton btAfficherResponsableBoutique;

////////////////////////////////////////////////////////////////////////    
    //    ********************************************Gestion reclamation********************************
    @FXML
    private ToggleButton bouton_reclamation;
    @FXML
    private VBox boxReclamation;

    //////////////////////////////////////////////////////////////////////////
    //    ********************************************Gestion offre emploi********************************
    @FXML
    private ToggleButton bouton_Offre_emploi;
    @FXML
    private VBox boxOffreEmploi;
    private ToggleButton btAjoutOffreEmploi;
    private ToggleButton btSupprimerOffreEmploi;
    private ToggleButton btModifierOffreEmploi;
    private ToggleButton btAfficherOffreEmploi;

//////////////////////////////////////////////    
    //    ********************************************Gestion publicite********************************
    @FXML
    private VBox boxPublicite;

    @FXML
    private ToggleButton bouton_Publicite;

////////////////////////////////////////////////////////////////////////////////    
    //    ********************************************Gestion reclamation********************************
    @FXML
    private VBox boxEvenement;

    @FXML
    private ToggleButton bouton_Evenement;
    @FXML
    private ToggleGroup grupoLocaliacao;
    @FXML
    private ToggleGroup grupoMenus;

    /**
     * Obter instancia do controler
     */
    public static adminAppController getInstance() {
        return instance;
    }
    @FXML
    private ToggleGroup grupoEmprestimo1;
    @FXML
    private ToggleGroup grupoMenus1;
    @FXML
    private ToggleGroup grupoMenus2;
    @FXML
    private ToggleGroup grupoMenus11;
    @FXML
    private ToggleGroup grupoMenus111;
    @FXML
    private Label hello_text;
    @FXML
    private Button btnClose;
    @FXML
    private VBox boxPublicite1;
    @FXML
    private ToggleButton bouton_demande_pub;
    @FXML
    private ToggleGroup grupoMenus112;
    @FXML
    private ToggleButton bouton_demande_emploi1;
    @FXML
    private ToggleGroup grupoMenus21;
    @FXML
    private ToggleButton bouton_Parametres;
    @FXML
    private ToggleGroup grupoMenus1121;
    @FXML
    private ToggleButton winner_admin;
    @FXML
    private ToggleGroup grupoMenus11211;

//    ****************************************Gestion Employers**************************
    @FXML
    void menu_gestion_employers(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLGestionEmployeCA.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void subAjoutEmployer(ActionEvent event) throws IOException {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("test.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void subSupprimerEmployer(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("test1.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void subAfficherEmployer(ActionEvent event) {

    }

    void subModifierEmployer(ActionEvent event) {

    }
//////////////////////////////////////////////////////////////////////////////////

    //    ********************************************Gestion Boutiques********************************
    @FXML
    void menu_gestion_boutiques(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionBoutiqueFXML.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    void subAjoutBoutique(ActionEvent event) {

    }

    void subISupprimerBoutique(ActionEvent event) {

    }

    void subModifierBoutique(ActionEvent event) {

    }

    void subAfficherBoutique(ActionEvent event) {

    }
////////////////////////////////////////////////////////////////////////////////////////////////

//    ********************************************Gestion clients********************************
    @FXML
    void menu_gestion_clients(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLGestionClient.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////    
    //    ********************************************Gestion responsable boutique********************************
    @FXML
    void menu_gestion_responsable_boutique(ActionEvent event) {
        //submenus(bouton_gestion_responsable_boutique, boxLocalizacao, btAfficherResponsableBoutique, btAjoutResponsable);
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLGestionPropCA.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void subAjoutResponsable(ActionEvent event) {

    }

    @FXML
    void subAfficherResponsableBoutique(ActionEvent event) {

    }

//////////////////////////////////////////////////////////////////////////////////////////    
    //    ********************************************Gestion  reclamation********************************
    @FXML
    void menu_reclamation(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLReclamationCA.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////
    //    ********************************************Gestion  offre emploi********************************
    @FXML
    void menu_offre_emploi(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionOffre_emploiAdmin.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void subAfficherOffreEmploi(ActionEvent event) {

    }

    void subAjoutOffreEmploi(ActionEvent event) {

    }

    void subModifierOffreEmploi(ActionEvent event) {

    }

    void subSupprimerOffreEmploi(ActionEvent event) {

    }

//////////////////////////////////////////////////////////////////////////////    
    //    ********************************************Gestion publicite********************************
    @FXML
    void menu_Publicite(ActionEvent event) {
//Publicite
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Pub.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    ********************************************Gestion evenement********************************

    @FXML
    void menu_Evenement(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Event.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    ********************************************Gestion demande pub********************************

    @FXML
    private void menu_demande_pub(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("demande_pub_liste.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void menuEmprestimo(ActionEvent event) {

    }

    void menuIdentificacao(ActionEvent event) {

    }

    void menuLocalizacao(ActionEvent event) {

    }

    void menuMovimentacao(ActionEvent event) {

    }

    void menuPesquisa(ActionEvent event) {

    }

    void menuRelatorios(ActionEvent event) {

    }

//    close doesnt work need revision !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    void CloseWindow(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }

    void menuSeguranca(ActionEvent event) {

    }

    void menuUtilitario(ActionEvent event) {

    }

    @FXML
    void siteMuseu(ActionEvent event) {

    }

    void subDevolucaoEmprestimo(ActionEvent event) {

    }

    void subEmprestimo(ActionEvent event) {

    }

    void subHistoricoEmprestimo(ActionEvent event) {

    }

    void subInstituicao(ActionEvent event) {

    }

    void subItensEmprestimo(ActionEvent event) {

    }

    void subLocal(ActionEvent event) {

    }

    void subLocalizar(ActionEvent event) {

    }

    void subOrganizacao(ActionEvent event) {

    }

    void subSetor(ActionEvent event) {

    }

    void subUsuarios(ActionEvent event) {

    }

    void subVisitantes(ActionEvent event) {

    }

    void initialize() {

    }

    /**
     * Obter componente para exbição das notas
     */
    public VBox boxNotas() {
        return boxNotas;
    }

    /**
     * Obter componente para exibição dos modulos da aplicação
     */
    public AnchorPane getBoxConteudo() {
        return boxConteudo;
    }

    /**
     * Exibir e ocultar submenus
     */
    public void submenus(ToggleButton menu, VBox box, ToggleButton... submenus) {
        if (box.getChildren().isEmpty()) {
            box.getChildren().addAll(submenus);
            SubMenuFade.fade(box);
            estilo(menu, "menu-grupo");
        } else {
            desativarSubmenus(box);
            estilo(menu, "menu-grupo-inativo");
        }
    }

    /**
     * Desativar e esconder todos submenus
     */
    public void desativarSubmenus(VBox... boxes) {
        for (VBox box : boxes) {
            box.getChildren().clear();
        }
    }

    /**
     * Aplicar estilo para mostrar/ocultar submenus
     */
    public void estilo(Node no, String estilo) {
        no.getStyleClass().remove(3);
        no.getStyleClass().add(estilo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     String nom = LoginController.LoggedUser.getNom();
        String prenom = LoginController.LoggedUser.getPrenom();
        hello_text.setText("hello " + nom + " " + prenom);
        IUserService aa = new UserService();
         TrayNotification tray = new TrayNotification();
         User b =  aa.findbypath(LoginController.LoggedUser.getId_user());
          Image whatsAppImg = new Image("http://localhost/TestUser/web/images/Userimage/" + b.getPath());
        
         tray.setTray("welcome", nom +" "+prenom, whatsAppImg, Paint.valueOf("#2A9A84"),AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(10));

    }

    @FXML
    private void menu_demande_emploi(ActionEvent event) {

        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ConsulterDemande_emploiAdmin.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void menu_Parametres(ActionEvent event) {
        try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("FXMLCompteAdmin.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);

        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void on_winner_action(ActionEvent event) {
         try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Winner.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
