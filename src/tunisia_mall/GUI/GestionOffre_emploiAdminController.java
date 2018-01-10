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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.Offre_emploiService;
import tunisia_mall.Services.UserService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.Offre_emploi;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author bn
 */
public class GestionOffre_emploiAdminController implements Initializable {

    Connection connection;

    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField post_entry;
    @FXML
    private TextField specialite_entry;
    @FXML
    private TextField salaire_entry;
    @FXML
    private TextField nbrempl_entry;
    @FXML
    private DatePicker date_expiration_entry;
    @FXML
    private Label lbTitulo1;
    @FXML
    private TextField afficher_entry;
    @FXML
    private ToggleGroup menu;
    @FXML
    private TableView<Offre_emploi> table_offre;
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
    private Button refreshbtn;
    @FXML
    private TextField id_offre_entry;
    @FXML
    private Label poste_error;
    @FXML
    private Button modifbtn;
    @FXML
    private Button btnvalider;

    @FXML
    private CheckBox minebtn;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Offre_emploi> list_offre = FXCollections.observableArrayList();

    Offre_emploi o = new Offre_emploi();
    Offre_emploiService oe = new Offre_emploiService();
    @FXML
    private Label message;
    @FXML
    private Label ajoutavecsucce;

    @FXML
    private Label remplirchamp;
    @FXML
    private Label numerror;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //anchorajoutsucce.setVisible(false);
        list_offre = FXCollections.observableArrayList(oe.getAll2());

        col_boutiq.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre_emploi, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre_emploi, String> param) {
                return new SimpleStringProperty(param.getValue().getBoutique().getNom());
            }

        });

        col_post.setCellValueFactory(new PropertyValueFactory<>("poste"));
        col_post.cellFactoryProperty();

        // col_id_offre.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
        //  col_id_offre.cellFactoryProperty();
        col_specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        col_specialite.cellFactoryProperty();

        col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        col_salaire.cellFactoryProperty();

        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr_demande"));
        col_nbr.cellFactoryProperty();

        col_date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        col_date.cellFactoryProperty();
        table_offre.setItems(FXCollections.observableArrayList(list_offre));

    }

    private String x;

    @FXML
    private void rechercher(ActionEvent event) {
        //anchorajoutsucce.setVisible(false);
        table_offre.setVisible(true);
        x = afficher_entry.getText();
        List<Offre_emploi> offres = oe.Rechercher(x);

        table_offre.setItems(FXCollections.observableArrayList(offres));

        col_boutiq.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre_emploi, String>, ObservableValue<String>>() {
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

        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr_demande"));
        col_nbr.cellFactoryProperty();

        col_date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        col_date.cellFactoryProperty();

        modifbtn.setVisible(false);
        btnvalider.setVisible(false);
        anchor.setVisible(false);

    }

    /*  public Boutique findByNom(String nom) {

        String req = "select * from boutique where nom = ?";
        PreparedStatement preparedStatement;
        connection = DataSource.getInsatance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bout = new Boutique();
                Bout.setId_boutique(resultSet.getInt("id_boutique"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Bout;
    }*/
    @FXML
    private void anchorajout(ActionEvent event) {
        modifbtn.setVisible(false);
        anchor.setVisible(true);
        btnvalider.setVisible(true);
        post_entry.setText("");
        specialite_entry.setText("");
        salaire_entry.setText("");
        nbrempl_entry.setText("");
       // date_expiration_entry.setٍText(null);
      // anchorajoutsucce.setVisible(false);
      //ajoutavecsucce.setVisible(false);
    }

    @FXML
    private void anchormodif(ActionEvent event) {

        btnvalider.setVisible(false);
        anchor.setVisible(true);
        // anchorajoutsucce.setVisible(false);

        modifbtn.setVisible(true);

    }

    @FXML
    private void supprimer(ActionEvent event) {

        if (table_offre.getSelectionModel().getSelectedItem() != null) {
            Offre_emploi o = table_offre.getSelectionModel().getSelectedItem();
            Offre_emploiService oe = new Offre_emploiService();
            //int i = Integer.valueOf(id_offre_entry.getText());

            oe.remove(o.getId_offre());

        }
    }

    @FXML
    private void affich_in_entry(MouseEvent event) {

        anchor.setVisible(false);
        modifbtn.setVisible(false);
     btnvalider.setVisible(false);
       ajoutavecsucce.setVisible(false);
        // anchorajoutsucce.setVisible(false);

        if (table_offre.getSelectionModel().getSelectedItem() != null) {
            Offre_emploi o = table_offre.getSelectionModel().getSelectedItem();

            //id_offre_entry.setText(String.valueOf(o.getId_offre()));
            post_entry.setText(o.getPoste());
            specialite_entry.setText(o.getSpecialite());
            salaire_entry.setText((String.valueOf(o.getSalaire())));

            nbrempl_entry.setText((String.valueOf(o.getNbr_demandé())));

            Date d = o.getDate_expiration();
            LocalDate L = d.toLocalDate();
            date_expiration_entry.setValue(L);

        }
                modifbtn.setVisible(false);
                btnvalider.setVisible(false);
    }

    @FXML
    private void refresh(ActionEvent event) {

        anchor.setVisible(false);
            ajoutavecsucce.setVisible(false);
        ObservableList<Offre_emploi> list = FXCollections.observableArrayList(oe.getAll2());

        table_offre.setItems(list);
        table_offre.getColumns().get(0).setVisible(false);
        table_offre.getColumns().get(0).setVisible(true);
        modifbtn.setVisible(false);
        btnvalider.setVisible(false);
        
        // anchorajoutsucce.setVisible(false);
    }

    @FXML
    private void modifier(ActionEvent event) {
        // anchorajoutsucce.setVisible(false);
        if (table_offre.getSelectionModel().getSelectedItem() != null) {
            Offre_emploi o = table_offre.getSelectionModel().getSelectedItem();
            Offre_emploiService oe = new Offre_emploiService();

            o = oe.findById(o.getId_offre());

            //   o.setBoutique(Bout);
            o.setPoste(post_entry.getText());
            o.setSpecialite(specialite_entry.getText());
            o.setNbr_demandé(Integer.valueOf(nbrempl_entry.getText()));
            LocalDate db = date_expiration_entry.getValue();
            o.setDate_expiration(java.sql.Date.valueOf(db));
            o.setSalaire(Float.valueOf(salaire_entry.getText()));
            //o.setUser(LoginController.LoggedUser);

            oe.update(o);
            table_offre.getColumns().get(0).setVisible(true);

        }

        anchor.setVisible(false);
        modifbtn.setVisible(false);

    }

    @FXML
    private void valider(ActionEvent event) throws IOException, SQLException {

        // anchorajoutsucce.setVisible(false);
        remplirchamp.setText("");
        //  ajoutavecsucce.setVisible(false);
        message.setText("");
        Offre_emploi o = new Offre_emploi();
        Offre_emploiService of = new Offre_emploiService();
        boolean valide = true;
        boolean validedate = true;
        User u = new User();
        UserService us = new UserService();
        u = us.findById(LoginController.LoggedUser.getId_user());

        if (post_entry.getText().equals("")) {
            valide = false;
            remplirchamp.setText("Il faut remplir tous les champs");
        }
        if (nbrempl_entry.getText().equals("")) {
            valide = false;
            remplirchamp.setText("Il faut remplir tous les champs");
        }
        if (specialite_entry.getText().equals("")) {
            valide = false;
            remplirchamp.setText("Il faut remplir tous les champs");
        }
        if (date_expiration_entry.getValue() == null) {
            valide = false;
            remplirchamp.setText("Il faut remplir tous les champs");
        }
        if (salaire_entry.getText().equals("")) {
            valide = false;
            remplirchamp.setText("Il faut remplir tous les champs");
        }

        ZoneId z = ZoneId.of("Africa/Tunis");
        LocalDate today = LocalDate.now(z);
        if (date_expiration_entry.getValue() != null) {
            LocalDate db = date_expiration_entry.getValue();

            if (!db.isAfter(today)) {
                validedate = false;
                message.setText("Date passée");
            }
        }

        if ((validedate) && (valide)) {
            LocalDate db = date_expiration_entry.getValue();
            o.setBoutique(u.getBoutique());
            o.setPoste(post_entry.getText());
            o.setSpecialite(specialite_entry.getText());
            o.setNbr_demandé(Integer.valueOf(nbrempl_entry.getText()));
            o.setDate_expiration(java.sql.Date.valueOf(db));
            o.setSalaire(Float.valueOf(salaire_entry.getText()));

            of.add(o);
            ajoutavecsucce.setText("Ajout effectué avec succès");
            anchor.setVisible(false);
            //  anchorajoutsucce.setVisible(true);
        }
        
        btnvalider.setVisible(false);
      
        
    }

    @FXML
    private void mes_boutiques(ActionEvent event) {

        ObservableList<Offre_emploi> list = FXCollections.observableArrayList(oe.getAll3(LoginController.LoggedUser.getId_user()));

        table_offre.setItems(list);
        table_offre.getColumns().get(0).setVisible(false);
        table_offre.getColumns().get(0).setVisible(true);

    }

    @FXML
    private void tout(MouseEvent event) {

        ObservableList<Offre_emploi> list = FXCollections.observableArrayList(oe.getAll2());

        table_offre.setItems(list);
        table_offre.getColumns().get(0).setVisible(false);
        table_offre.getColumns().get(0).setVisible(true);

    }

    @FXML
    private void salairenum(KeyEvent event) {

        if (salaire_entry.getText().trim().length() > 0) {
            try {
                int i = Integer.parseInt(salaire_entry.getText());
            } catch (NumberFormatException e) {
                numerror.setVisible(true);
                numerror.setText("veuillez entrez des valeurs numeriques");
                //  Signup.setDisable(true);
            }
        } else {
            numerror.setVisible(false);
            // Signup.setDisable(false);
        }

    }

    @FXML
    private void nbrnum(KeyEvent event) {

        if (nbrempl_entry.getText().trim().length() > 0) {
            try {
                int i = Integer.parseInt(salaire_entry.getText());
            } catch (NumberFormatException e) {
                numerror.setVisible(true);
                numerror.setText("veuillez entrez des valeurs numeriques");
                //  Signup.setDisable(true);
            }
        } else {
            numerror.setVisible(false);
            // Signup.setDisable(false);
        }

    }

}
