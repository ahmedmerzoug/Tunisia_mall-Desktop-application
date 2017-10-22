package tunisia_mall.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class responsableAppController {

    private static adminAppController instance;

    @FXML
    private AnchorPane boxConteudo;
    @FXML
    private VBox boxEmprestimo;
    @FXML
    private VBox boxVisitas;
    @FXML
    private VBox boxLocalizacao;
    @FXML
    private VBox boxUtilitarios;
    @FXML
    private VBox boxNotas;
    @FXML
    private VBox boxCatalogacao;
    @FXML
    private Label lbUser;
    @FXML
    private Label lbMensagem;

//    **************************************Gestion Employers**********************
    @FXML
    private ToggleButton bouton_gestion_employers;

    @FXML
    private ToggleButton btAjoutEmployer;

    @FXML
    private ToggleButton btSupprimerEmployer;

    @FXML
    private ToggleButton btModifierEmployer;

    @FXML
    private ToggleButton btAfficherEmployer;

//    ********************************************Gestion Boutiques********************************
    @FXML
    private ToggleButton bouton_gestion_boutique;

    @FXML
    private ToggleButton btAjoutBoutique;

    @FXML
    private ToggleButton btSupprimerBoutique;

    @FXML
    private ToggleButton btModifierBoutique;

    @FXML
    private ToggleButton btAfficherBoutique;

////////////////////////////////////////////////////////////////////////////////    
//    ********************************************Gestion clients********************************
    @FXML
    private ToggleButton bouton_gestion_client;

    @FXML
    private ToggleButton btAfficherClient;

//////////////////////////////////////////////////////////////////////////////
//    ********************************************Gestion responsablesBoutique********************************
    @FXML
    private ToggleButton bouton_gestion_responsable_boutique;
    
    @FXML
    private ToggleButton btAfficherResponsableBoutique;

////////////////////////////////////////////////////////////////////////    
    @FXML
    private ToggleButton btSetor;
//    @FXML
//    private ToggleButton btVisitas;
    @FXML
    private ToggleButton btLocal;
//    @FXML
//    private ToggleButton btInstituicao;
//    @FXML
//    private ToggleButton btExcursao;
////    @FXML
//    private ToggleButton btCatalogar;
    @FXML
    private ToggleButton btLocalizacao;
    @FXML
    private ToggleButton btLocalizar;
//    @FXML
//    private ToggleButton btColecao;
    @FXML
    private ToggleButton btEmprestimos;
    @FXML
    private ToggleButton btEmprestimo;
    @FXML
    private ToggleButton btHistorico;
    @FXML
    private ToggleButton btItens;
    @FXML
    private ToggleButton btUtilitarios;
    @FXML
    private ToggleButton btOrganizacao;
    @FXML
    private ToggleButton btDesginacao;
    @FXML
    private ToggleButton btUsuarios;
    @FXML
    private ToggleButton btDevolucao;
//    @FXML
//    private ToggleButton btCatalogacao;
    @FXML
    private ToggleButton btEstratigrafia;
//    @FXML
//    private ToggleButton btVisitantes;
    @FXML
    private ToggleButton btMovimentacao;
    @FXML
    private ToggleButton btIdentificacao;
    @FXML
    private ToggleButton btRelatorios;
    @FXML
    private ToggleButton btPesquisa;
    @FXML
    private ToggleButton btSeguranca;
    @FXML
    private ToggleGroup grupoLocaliacao;
    @FXML
    private ToggleGroup grupoUtilidades;
    @FXML
    private ToggleGroup grupoMenus;
    @FXML
    private ToggleGroup grupoEmprestimo;
    @FXML
    private ToggleGroup grupoCatalogacao;
    @FXML
    private ToggleGroup grupoVisitantes;

    /**
     * Obter instancia do controler
     */
    public static adminAppController getInstance() {
        return instance;
    }

//    ****************************************Gestion Employers**************************
    @FXML
    void menu_gestion_employers(ActionEvent event) {
        submenus(bouton_gestion_employers, boxCatalogacao, btAjoutEmployer, btSupprimerEmployer, btModifierEmployer, btAfficherEmployer);
    }

    @FXML
    void subAjoutEmployer(ActionEvent event) {

    }

    @FXML
    void subSupprimerEmployer(ActionEvent event) {

    }

    @FXML
    void subAfficherEmployer(ActionEvent event) {

    }

    @FXML
    void subModifierEmployer(ActionEvent event) {

    }
//////////////////////////////////////////////////////////////////////////////////

    //    ********************************************Gestion Boutiques********************************
    @FXML
    void menu_gestion_boutiques(ActionEvent event) {
        submenus(bouton_gestion_boutique, boxVisitas, btAjoutBoutique, btSupprimerBoutique, btModifierBoutique, btAfficherBoutique);
    }

    @FXML
    void subAjoutBoutique(ActionEvent event) {

    }

    @FXML
    void subISupprimerBoutique(ActionEvent event) {

    }

    @FXML
    void subModifierBoutique(ActionEvent event) {

    }

    @FXML
    void subAfficherBoutique(ActionEvent event) {

    }
////////////////////////////////////////////////////////////////////////////////////////////////

//    ********************************************Gestion clients********************************
    @FXML
    void menu_gestion_clients(ActionEvent event) {
        submenus(bouton_gestion_client, boxEmprestimo, btAfficherClient);
    }

    @FXML
    void subAfficherClient(ActionEvent event) {

    }

//////////////////////////////////////////////////////////////////////////////////////    
    //    ********************************************Gestion responsable boutique********************************

@FXML
    void menu_gestion_responsable_boutique(ActionEvent event) {
        submenus(bouton_gestion_responsable_boutique, boxLocalizacao, btAfficherResponsableBoutique);
    }
    
    @FXML
    void subAfficherResponsableBoutique(ActionEvent event) {

    }

//////////////////////////////////////////////////////////////////////////////////////////    
    @FXML
    void menuEmprestimo(ActionEvent event) {

    }

    @FXML
    void menuIdentificacao(ActionEvent event) {

    }

    @FXML
    void menuLocalizacao(ActionEvent event) {

    }

    @FXML
    void menuMovimentacao(ActionEvent event) {

    }

    @FXML
    void menuPesquisa(ActionEvent event) {

    }

    @FXML
    void menuRelatorios(ActionEvent event) {

    }

//    close doesnt work need revision !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    void CloseWindow(ActionEvent event) {
        System.out.println("test");
        mainfx.window.close();

    }

    @FXML
    void menuSeguranca(ActionEvent event) {

    }

    @FXML
    void menuUtilitario(ActionEvent event) {

    }

    @FXML
    void siteMuseu(ActionEvent event) {

    }

    @FXML
    void subDevolucaoEmprestimo(ActionEvent event) {

    }

    @FXML
    void subEmprestimo(ActionEvent event) {

    }

    @FXML
    void subHistoricoEmprestimo(ActionEvent event) {

    }

    @FXML
    void subInstituicao(ActionEvent event) {

    }

    @FXML
    void subItensEmprestimo(ActionEvent event) {

    }

    @FXML
    void subLocal(ActionEvent event) {

    }

    @FXML
    void subLocalizar(ActionEvent event) {

    }

    @FXML
    void subOrganizacao(ActionEvent event) {

    }

    @FXML
    void subSetor(ActionEvent event) {

    }

    @FXML
    void subUsuarios(ActionEvent event) {

    }

    @FXML
    void subVisitantes(ActionEvent event) {

    }

    @FXML
    void initialize() {
//        instance = this;
//        Grupo.notEmpty(grupoMenus, grupoCatalogacao, grupoEmprestimo, grupoLocaliacao, grupoUtilidades, grupoVisitantes);//não permite grupos de menus com menus deselecionados
//        menuDashboard(null);
        //lbUser.setText("Olá, " + LoginController.usuarioLogado.getNome());
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

}
