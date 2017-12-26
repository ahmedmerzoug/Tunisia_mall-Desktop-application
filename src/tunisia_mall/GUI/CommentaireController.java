/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.net.URL;
import java.sql.Connection;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;
import tunisia_mall.Interface.ICommentaireService;
import tunisia_mall.Interface.IForumService;
import tunisia_mall.Services.CommentaireService;
import tunisia_mall.Services.ForumService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Commentaire;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label lbTitulo1;
    @FXML
    private TableColumn tb_date_commentaire;
    @FXML
    private TableColumn tb_login_user;
    @FXML
    private TableColumn<Commentaire, String> tb_contenu;
    @FXML
    private TableView<Commentaire> tb_commentaire;
    @FXML
    private TextArea txt_Ajout;
    private ObservableList<Commentaire> data ;

    
      Connection connection;

    public CommentaireController() {
        connection = DataSource.getInsatance().getConnection();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ///  data = FXCollections.observableArrayList();
         ObservableList<Commentaire> listecommentaire=FXCollections.observableArrayList();
        
        String req = "select * from commentaire where id_forum1=?";
            PreparedStatement preparedStatement;
            try {
                preparedStatement = connection.prepareStatement(req);
                preparedStatement.setInt(1, ForumGesController.comm.getId_topic());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Commentaire p1= new Commentaire();
                    p1.setId_commentaire(resultSet.getInt("id_commentaire"));
                                        p1.setContenu(resultSet.getString("contenu"));

                     p1.setDate_commentaire(Commentaire.convert(resultSet.getDate("date_commentaire")));
                     
                     p1.setUser(LoginController.LoggedUser);
                     listecommentaire.add(p1);
                     

                    
                }
                tb_commentaire.setItems(listecommentaire);
            } catch (SQLException ex) {
                Logger.getLogger(ForumGesController.class.getName()).log(Level.SEVERE, null, ex);
            }
          ////////  System.out.println(S);
           
      ///// afficher ();    
    }    

      void afficher ()
    {
         ICommentaireService aaa = new CommentaireService();
         
        tb_commentaire.setItems(aaa.displayallbyidforum(ForumGesController.comm.getId_topic()));
        tb_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        
     
         tb_date_commentaire.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commentaire, String>, ObservableValue<String>>() {
             @Override
             public ObservableValue<String> call(TableColumn.CellDataFeatures<Commentaire, String> param) {
            return new SimpleStringProperty((param.getValue().getDate_commentaire()));

             }
           
            
        });
      
      
      
            tb_login_user.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commentaire, String>, ObservableValue<String>>() {
             @Override
             public ObservableValue<String> call(TableColumn.CellDataFeatures<Commentaire, String> param) {
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
         ICommentaireService aaa = new CommentaireService();
     
        System.out.println(dateFormat.format(cal.getTime()));
      Commentaire b = new Commentaire(txt_Ajout.getText(),dateFormat.format(cal.getTime()),LoginController.LoggedUser,ForumGesController.comm);
       ///////// System.out.println("today date is "+dateFormat.format(cal.getTime()));
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("commentaire added with sucess");
       Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){      
       aaa.add(b);
    }txt_Ajout.clear();
    afficher();
    }

    private void modifier(ActionEvent event) {
       if(( tb_commentaire.getSelectionModel().getSelectedItem().getId_commentaire())==1)
       {
            System.out.println("this line numberis ");
        }
    }
    

    
}
