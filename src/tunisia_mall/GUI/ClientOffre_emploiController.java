/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.Offre_emploiService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.Offre_emploi;

/**
 * FXML Controller class
 *
 * @author bn
 */
public class ClientOffre_emploiController implements Initializable {

     Connection connection;
    public static Offre_emploi OffreS;
    
    
    
     public void StartConnection() {
        connection = DataSource.getInsatance().getConnection();
    }
    
   @FXML
    private DatePicker date_picker; 
   @FXML
    private TableView<Offre_emploi> table_offre_client;
    
    @FXML
    private TableColumn<Offre_emploi, String> col_boutiq;
    @FXML
    private TableColumn<Offre_emploi, String> col_post;
    @FXML
    private TableColumn<Offre_emploi, String> col_specialite;
    @FXML
    private TableColumn<Offre_emploi, Float> col_salaire;
    @FXML
    private TableColumn<Offre_emploi, Integer> col_nbr;
    @FXML
    private TableColumn<Offre_emploi, Date> col_date;
    @FXML
    private ComboBox<?> combotype;
    @FXML
    private TextField recherche_entry;
    @FXML
    private Button affichbtn;
    @FXML
    private TextField id_offre_entry;
    @FXML
    private Button poster_demandebtn;
    @FXML
    private Button refreshbtn;
    @FXML
    private Label specialite_error;

    /**
     * Initializes the controller class.
     */
    
    
    private ObservableList<Offre_emploi> list_offre = FXCollections.observableArrayList();
    // private ObservableList options = FXCollections.observableArrayList();
    Offre_emploi o = new Offre_emploi();
    Offre_emploiService oe = new Offre_emploiService();
    public static Boutique BoutiqS;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      list_offre = FXCollections.observableArrayList(oe.getAll2());

        col_boutiq.setCellValueFactory(new Callback <TableColumn.CellDataFeatures<Offre_emploi, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre_emploi, String> param) {
                return new SimpleStringProperty(param.getValue().getBoutique().getNom());
            }

        });

        col_post.setCellValueFactory(new PropertyValueFactory<>("poste"));
        col_post.cellFactoryProperty();

       
        col_specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        col_specialite.cellFactoryProperty();

        col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        col_salaire.cellFactoryProperty();

        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr_demandé"));
        col_nbr.cellFactoryProperty();

        col_date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        col_date.cellFactoryProperty();
        table_offre_client.setItems(list_offre);        
        
    }    

    /* public boolean id_offre (int id_offre) {
      
        String req = "select * from offre_emploi where id_offre= ? ";
       PreparedStatement preparedStatement;
       connection = DataSource.getInsatance().getConnection();
        try { 
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,id_offre);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OffreS = new Offre_emploi();
             OffreS.setId_offre(resultSet.getInt("id_offre"));
           return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    } */
    
    
    @FXML
    private void clicked_table(MouseEvent event) {
    
           if (table_offre_client.getSelectionModel().getSelectedItem() != null) {
            Offre_emploi o = table_offre_client.getSelectionModel().getSelectedItem();
            
            Boutique b = new Boutique();
            Offre_emploiService oe= new Offre_emploiService();
            
            OffreS = new Offre_emploi();
            OffreS.setId_offre(o.getId_offre());
           OffreS.setBoutique(o.getBoutique());
            BoutiqS= new Boutique();
            BoutiqS.setId_boutique(OffreS.getBoutique().getId_boutique());
            
            
                LocalDate ld = o.getDate_expiration().toLocalDate();
                System.out.println(OffreS);
               System.out.println(BoutiqS);
                 date_picker.setValue(ld);
           
        
    }
        
    
    }
 
    

    @FXML
    private void comboaction(ActionEvent event) {
    }

    private String x;
    @FXML
    private void afficher(ActionEvent event) {

        table_offre_client.setVisible(true);
        x = recherche_entry.getText();
        List<Offre_emploi> offres = oe.Rechercher2(x);

        table_offre_client.setItems(FXCollections.observableArrayList(offres));

        col_boutiq.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre_emploi, String>, ObservableValue<String>>() 
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre_emploi, String> param) {
                return new SimpleStringProperty(param.getValue().getBoutique().getNom());
            }

        });

       
        col_post.setCellValueFactory(new PropertyValueFactory<>("poste"));
        col_post.cellFactoryProperty();

        col_specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        col_specialite.cellFactoryProperty();

        col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        col_salaire.cellFactoryProperty();

        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr_demandé"));
        col_nbr.cellFactoryProperty();

        col_date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        col_date.cellFactoryProperty();

    
    }

    
    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/ MMMM/ yyyy");
        String text = df.format(d);
        return text;
    }
    
    
    @FXML
    private void poster_demande(ActionEvent event) throws IOException {
    
       ZoneId z = ZoneId.of( "Africa/Tunis" );
    LocalDate today = LocalDate.now(z);
   
         LocalDate db = date_picker.getValue();
     
         if (db.isAfter(today))
         {
             System.out.println("c bon");
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ClientGestion_demande.fxml"));
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
         }}

    @FXML
    private void refresh(ActionEvent event) {
        
        ObservableList<Offre_emploi> list = FXCollections.observableArrayList(oe.getAll2());
//            tableApprenant.setItems(list);
        table_offre_client.setItems(list);
        table_offre_client.getColumns().get(0).setVisible(false);
        table_offre_client.getColumns().get(0).setVisible(true);
        
    }

    
}
