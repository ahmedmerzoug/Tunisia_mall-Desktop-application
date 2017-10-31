/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisia_mall.GUI.ClientOffre_emploiController;

import tunisia_mall.GUI.LoginController;

import tunisia_mall.Interface.IDemande_emploiService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Demande_emploi;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public class Demande_emploiService implements IDemande_emploiService {

    Connection connection;

    public Demande_emploiService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Demande_emploi d) {
        String req = "insert into demande_emploi (id_user_fk,id_offre_fk,nom_emp,prenom_emp,date_naissance,adresse,sexe,email,num_tel,qualification,experience) values (?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);

            // preparedStatement.setInt(1,d.getId());
            preparedStatement.setInt(1, LoginController.LoggedUser.getId_user());
           preparedStatement.setInt(2, ClientOffre_emploiController.OffreS.getId_offre());

            preparedStatement.setString(3, d.getNom_emp());
            preparedStatement.setString(4, d.getPrenom_emp());

            preparedStatement.setDate(5, d.getDate_naissance());

            preparedStatement.setString(6, d.getAdresse());
            preparedStatement.setString(7, d.getSexe());
            preparedStatement.setString(8, d.getEmail());
            preparedStatement.setString(9, d.getNum_tel());
            preparedStatement.setString(10, d.getQualification());
            preparedStatement.setInt(11, d.getExperience());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Demande_emploiService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Demande_emploi d) {
        String req = "update demande_emploi set nom_emp=?,prenom_emp=?,date_naissance=?,adresse=?,sexe=?,email=?,num_tel=?,qualification=?,experience=? where id_demande = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            //preparedStatement.setInt(1,FXMLController.LoggedUser.getId_user());
           // preparedStatement.setInt(2, Offre_clientController.OffreS.getId_offre());
            preparedStatement.setString(1, d.getNom_emp());
            preparedStatement.setString(2, d.getPrenom_emp());
            preparedStatement.setDate(3, d.getDate_naissance());
            preparedStatement.setString(4, d.getAdresse());
            preparedStatement.setString(5, d.getSexe());
            preparedStatement.setString(6, d.getEmail());
            preparedStatement.setString(7, d.getNum_tel());
            preparedStatement.setString(8, d.getQualification());
            preparedStatement.setInt(9, d.getExperience());
            preparedStatement.setInt(10, d.getId_demande());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void remove(Integer id) {
        String req = "delete from demande_emploi where id_demande =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Demande_emploi findById(Integer id) {
        Demande_emploi demande_emploi = null;
        String req = "select * from demande_emploi where id_demande =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                demande_emploi = new Demande_emploi(resultSet.getInt("id_demande"), new UserService().findById(resultSet.getInt(2)), new Offre_emploiService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demande_emploi;

    }

    @Override
    public List<Demande_emploi> getAll() {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from demande_emploi";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getInt("id_demande"), new UserService().findById(resultSet.getInt(2)), new Offre_emploiService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
                demandes.add(demande_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }

    public List<Demande_emploi> getAll2() {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from demande_emploi";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
                demandes.add(demande_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }
    
    
     public List<Demande_emploi> getAll3(int id) {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from demande_emploi where id_user_fk =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
                demandes.add(demande_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }
    

     public Demande_emploi Rechercher(String mail) {
       Demande_emploi demande_emploi = null;
         PreparedStatement preparedStatement;
            String req = "select * from demande_emploi where email=?";
           
         try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1,mail);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

   demande_emploi = new Demande_emploi(resultSet.getInt("id_demande"), new UserService().findById(resultSet.getInt(2)), new Offre_emploiService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Demande_emploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return demande_emploi;
    }
    
   
    @Override
    public List<Demande_emploi> getByUser(User user) {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from Demande_emploi where id_user_fk=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, user.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getInt("id_demande"), new UserService().findById(resultSet.getInt(2)), new Offre_emploiService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
                demandes.add(demande_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }
    
    
   
    public List<Demande_emploi> getByExp(int i) {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from Demande_emploi where experience=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
                demandes.add(demande_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }
    

    public ObservableList<Integer> displayall() {
         ObservableList<Integer> listeexp=FXCollections.observableArrayList();
             String req = "select experience from demande_emploi";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Demande_emploi d = new Demande_emploi(resultSet.getInt("experience"));
                listeexp.add(d.getExperience());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listeexp;

    }
    
    
    public int nbr_dem (int id_user, int id_offre)
    {
    String req = "select * from demande_emploi where id_user_fk =? and id_offre_fk=?";
        PreparedStatement preparedStatement;
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
             preparedStatement.setInt(2, id_offre);
            ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        
              i=resultSet.getRow();
              
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    return i;
    }
    
    
    public List<String> getbyboutiq(int i) {
        List<String> boutique = new ArrayList<>();
        String req = "select * from Demande_emploi where id_boutique=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
      //          boutique.add(demande_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return boutique;
    }
    
    
    
    
}
