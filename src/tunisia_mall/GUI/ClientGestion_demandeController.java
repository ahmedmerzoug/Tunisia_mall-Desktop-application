/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.NativeDate;
import tunisia_mall.Services.Demande_emploiService;
import tunisia_mall.Services.Offre_emploiService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Demande_emploi;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author bn
 */
public class ClientGestion_demandeController implements Initializable {

    @FXML
    private Button retourbtn;
    @FXML
    private TextField nom_entry;
    @FXML
    private TextField prenom_entry;
    @FXML
    private DatePicker daten_entry;
    @FXML
    private TextField adress_entry;
    @FXML
    private TextField sexe_entry;
    @FXML
    private TextField email_entry;
    @FXML
    private TextField num_tel_entry;
    @FXML
    private TextField qualif_entry;
    @FXML
    private TextField exper_entry;
    @FXML
    private Button ajouterbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button rechercherbtn;
    @FXML
    private TextField rech_entry;
    @FXML
    private TextField id_offre_dem;
    @FXML
    private TextField id_dem_entry;
    @FXML
    private Label message;
    @FXML
    private RadioButton affich_coordbtn;

    /**
     * Initializes the controller class.
     */
    
    
    Demande_emploi d = new Demande_emploi();
    Demande_emploiService de = new Demande_emploiService();
    Offre_emploiService oe = new Offre_emploiService();
    
    @FXML
    private Label nbr_dem;
    @FXML
    private Label emailerror;
    @FXML
    private Label qualiferror;
    
   

       
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        int i;
       i= de.nbr_dem(LoginController.LoggedUser.getId_user(),ClientOffre_emploiController.OffreS.getId_offre());
       nbr_dem.setText(String.valueOf(i));
        // id_offre_dem.setText(String.valueOf(ClientOffre_emploiController.OffreS.getId_offre())); 

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ClientOffre_emploi.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    

    @FXML
    private void ajouter_dem(ActionEvent event)  {

        Demande_emploi d = new Demande_emploi();
        Demande_emploiService des = new Demande_emploiService();

        d.setNom_emp(nom_entry.getText());

        d.setPrenom_emp(prenom_entry.getText());
        d.setExperience(Integer.valueOf(exper_entry.getText()));
        LocalDate db = daten_entry.getValue();
        d.setDate_naissance(java.sql.Date.valueOf(db));

        d.setEmail(email_entry.getText());
        d.setNum_tel(num_tel_entry.getText());
        d.setQualification(qualif_entry.getText());
        d.setAdresse(adress_entry.getText());
        d.setSexe(sexe_entry.getText());

        d.setUser(LoginController.LoggedUser);
        d.setOffre_emploi(ClientOffre_emploiController.OffreS);

        des.add(d);

       // d.setId_demande(Integer.valueOf(id_dem_entry.getText()));

    }

    @FXML
    private void modif_dem(ActionEvent event) {

        //(demande_table.getSelectionModel().getSelectedItem() != null) {
        //   Demande_emploi d = demande_table.getSelectionModel().getSelectedtem();
        Demande_emploiService des = new Demande_emploiService();

        d = des.findById(Integer.valueOf(id_dem_entry.getText()));

        d.setNom_emp(nom_entry.getText());
        d.setPrenom_emp(prenom_entry.getText());
        d.setExperience(Integer.valueOf(exper_entry.getText()));
        LocalDate db = daten_entry.getValue();
        d.setDate_naissance(java.sql.Date.valueOf(db));
        d.setEmail(email_entry.getText());
        d.setNum_tel(num_tel_entry.getText());
        d.setQualification(qualif_entry.getText());
        d.setAdresse(adress_entry.getText());
        d.setSexe(sexe_entry.getText());

        d.setUser(LoginController.LoggedUser);
        d.setOffre_emploi(ClientOffre_emploiController.OffreS);

        des.update(d);

    }

    @FXML
    private void supp_dem(ActionEvent event) {

        Demande_emploiService de = new Demande_emploiService();
        //int i = Integer.valueOf(id_dem_entry.getText());

        de.remove(d.getId_demande());

    }

    private String x;

    @FXML
    private void afficher(ActionEvent event) {

        //demande_table.setVisible(true);
        Demande_emploi d = new Demande_emploi();
        x = rech_entry.getText();
        d = de.Rechercher(x);

        {
            if (de.Rechercher(x) != null) // id_offre_entry.setText(String.valueOf(o.getId_offre()));
            {
                nom_entry.setText(d.getNom_emp());
                prenom_entry.setText(d.getPrenom_emp());
                exper_entry.setText((String.valueOf(d.getExperience())));
                email_entry.setText(d.getEmail());
                num_tel_entry.setText(d.getNum_tel());
                qualif_entry.setText(d.getQualification());
                adress_entry.setText(d.getAdresse());
                sexe_entry.setText(d.getSexe());
                id_dem_entry.setText(String.valueOf(d.getId_demande()));
                
                Date da = d.getDate_naissance();
                LocalDate L = da.toLocalDate();
                daten_entry.setValue(L);
            }
        }
        if (de.Rechercher(x) == null) {
            message.setText("E-mail n'existe pas, vous n'avez pas de demande dans cette offre");
        }
    }

    
    public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
        return sqlDate;
    }
    
    @FXML
    private void affich_coord(ActionEvent event)  {
         UserService us = new UserService();
        User u = new User();
        u = us.findById(LoginController.LoggedUser.getId_user());

        nom_entry.setText(u.getNom());
        prenom_entry.setText(u.getPrenom());
        java.sql.Date l;
        try {
            l = convert(u.getDate_naissance());
            LocalDate L = l.toLocalDate();
        daten_entry.setValue(L);
        
        } catch (ParseException ex) {
            Logger.getLogger(ClientGestion_demandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        adress_entry.setText(u.getAdresse());
        num_tel_entry.setText(String.valueOf(u.getNumero_telephone()));
        email_entry.setText(u.getMail());
        sexe_entry.setText(u.getSexe());
        
    }

    @FXML
    private void email(KeyEvent event) {
        
         String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"+"[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email_entry.getText());




        
        if (controler.matches()){            
            emailerror.setVisible(false);
            
           // Signup.setDisable(false);                            
        }
        else{
        emailerror.setVisible(true);
        emailerror.setText("veuillez entrez une adresse email valide");
       // Signup.setDisable(true);
        }
        
    }

    @FXML
    private void num(KeyEvent event) {
        
            if(qualif_entry.getText().trim().length()>0)
        {
         try {
            int i = Integer.parseInt(qualif_entry.getText());
             }
         catch (NumberFormatException e) {
            qualiferror.setVisible(true);
            qualiferror.setText("veuillez entrez des valeurs numeriques");
            //Signup.setDisable(true);
            }
        }
        
        else{
            qualiferror.setVisible(false);
           // Signup.setDisable(false);
            }
 
        
    }

 

}
