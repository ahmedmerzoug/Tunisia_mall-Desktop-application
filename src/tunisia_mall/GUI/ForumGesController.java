/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import static tunisia_mall.GUI.LoginController.LoggedUser;
import tunisia_mall.Interface.ICarteFideliteService;
import tunisia_mall.Interface.IForumService;
import tunisia_mall.Services.CarteFideliteService;
import tunisia_mall.Services.ForumService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.CarteFidelite;
import tunisia_mall.models.Commentaire;
import tunisia_mall.models.Forum;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ForumGesController implements Initializable {

    @FXML
    private TableColumn<Forum, String> tb_categorie;
    @FXML
    private TableColumn<Forum, String> tb_description;
    @FXML
    private TableColumn tb_nom_user;
    @FXML
    private TableView<Forum> tbforum;
    @FXML
    private TextField txt_cat;
    @FXML
    private TextArea txt_desc;
    @FXML
    private TableColumn tb_ahmedplease;
    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private ToggleGroup menu;
public static Forum comm ;    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ////// String id= (LoginController.LoggedUser.getLogin()) ;
        ///////   label_user.setText(id);

        afficher();
    }

    void afficher() {
        IForumService aaa = new ForumService();
        //// User aea = new User();

        tbforum.setItems(aaa.displayall());
        tb_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        //////   tb_ahmedplease.setCellValueFactory(new PropertyValueFactory<>("date_topic"));
        //////  tb_nom_user.setCellValueFactory(new PropertyValueFactory<>("login_user"));

        tb_ahmedplease.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Forum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Forum, String> param) {
                return new SimpleStringProperty((param.getValue().getDatetopic()));

            }

        });

        tb_nom_user.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Forum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Forum, String> param) {
                return new SimpleStringProperty((param.getValue().getUser().getLogin()));
            }

        });
    }

    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }

    @FXML
    private void ajouter(ActionEvent event) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        User uu = new User();
/////uu = LoginController.LoggedUser ;
        IForumService aaa = new ForumService();

        System.out.println(dateFormat.format(cal.getTime()));
        Forum b = new Forum(txt_cat.getText(), txt_desc.getText(), dateFormat.format(cal.getTime()), LoginController.LoggedUser);
        System.out.println("today date is " + dateFormat.format(cal.getTime()));

        aaa.add(b);
    }

    @FXML
    private void rechercherB(ActionEvent event) {

        IForumService dao = new ForumService();

        tbforum.setItems(dao.findbycategorie((txtPesquisar.getText())));

        tb_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        //////   tb_ahmedplease.setCellValueFactory(new PropertyValueFactory<>("date_topic"));
        //////  tb_nom_user.setCellValueFactory(new PropertyValueFactory<>("login_user"));

        tb_ahmedplease.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Forum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Forum, String> param) {
                return new SimpleStringProperty((param.getValue().getDatetopic()));

            }

        });

        tb_nom_user.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Forum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Forum, String> param) {
                return new SimpleStringProperty((param.getValue().getUser().getLogin()));
            }

        });

    }

    @FXML
    private void modifier(ActionEvent event) {

    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void select(MouseEvent event) throws IOException {
        Connection connection;

        connection = DataSource.getInsatance().getConnection();

        IForumService DAO = new ForumService();

      //////  if (event.getClickCount() == 2) //Checking double click
     //////   {
                        int S = tbforum.getSelectionModel().getSelectedItem().getId_topic();

            comm= new Forum();
            comm.setId_topic(S);
            
          
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("commentaire.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            /*    Etatrec.setValue(S);
            Etatrec.setItems(comboList);
            r1 = TableReclamation.getSelectionModel().getSelectedItem();
             */
  
    }


