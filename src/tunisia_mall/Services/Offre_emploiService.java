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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisia_mall.GUI.adminAppController;
import tunisia_mall.GUI.GestionOffre_emploiAdminController;
import tunisia_mall.GUI.LoginController;
import tunisia_mall.models.Offre_emploi;
import tunisia_mall.Interface.IOffre_emploiService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public class Offre_emploiService implements IOffre_emploiService {

    Connection connection;

    PreparedStatement preparedStatement;
    
           
           
    public Offre_emploiService() {

        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Offre_emploi o) {

        try {
            String req = "insert into offre_emploi (id_user_fk,id_boutique_fk,poste,specialite,salaire,nbr_demande,date_expiration) values (?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(req);

            //preparedStatement.setInt(1,o.getId());
            preparedStatement.setInt(1, LoginController.LoggedUser.getId_user());
            preparedStatement.setInt(2, o.getBoutique().getId_boutique());
            preparedStatement.setString(3, o.getPoste());
            preparedStatement.setString(4, o.getSpecialite());
            preparedStatement.setFloat(5, o.getSalaire());
            preparedStatement.setInt(6, o.getNbr_demandé());

            preparedStatement.setDate(7, o.getDate_expiration());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Offre_emploiService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Offre_emploi o) {
        String req = "update offre_emploi set  poste=?,specialite=?,salaire=?,nbr_demande=?,date_expiration=? where id_offre = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

//            preparedStatement.setInt(1, o.getUser().getId_user());
//            preparedStatement.setInt(2, o.getBoutique().getId_boutique());
            preparedStatement.setString(1, o.getPoste());
            preparedStatement.setString(2, o.getSpecialite());
            preparedStatement.setFloat(3, o.getSalaire());
            preparedStatement.setInt(4, o.getNbr_demandé());
            preparedStatement.setDate(5, o.getDate_expiration());
            preparedStatement.setInt(6, o.getId_offre());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from offre_emploi where id_offre =?";
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
    public Offre_emploi findById(Integer id) {
        Offre_emploi offre_emploi = null;
        String req = "select * from Offre_emploi where id_offre =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offre_emploi = new Offre_emploi(resultSet.getInt("id_offre"), new UserService().findById(resultSet.getInt(2)), new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offre_emploi;
    }
    
   
             

    @Override
    public List<Offre_emploi> getAll() {
        List<Offre_emploi> offres = new ArrayList<>();
        String req = "select * from offre_emploi";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Offre_emploi offre_emploi = new Offre_emploi(resultSet.getInt("id_offre"), new UserService().findById(resultSet.getInt(2)), new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
                offres.add(offre_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }

      public List<Offre_emploi> getAll2() {
        List<Offre_emploi> offres = new ArrayList<>();
        String req = "select * from offre_emploi";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Offre_emploi offre_emploi = new Offre_emploi(resultSet.getInt(1),new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
                offres.add(offre_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }
      
      
      
      public List<Offre_emploi> getAll3(int id) {
          
        List<Offre_emploi> offres = new ArrayList<>();
        String req = "select * from offre_emploi where id_user_fk=?";
        //LoginController.LoggedUser.setId_user(id);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Offre_emploi offre_emploi = new Offre_emploi(resultSet.getInt(1), new UserService().findById(resultSet.getInt(2)),new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
                offres.add(offre_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }
    
    
    
    public List<Offre_emploi> getByUser(User user) {
        List<Offre_emploi> offres = new ArrayList<>();
        String req = "select * from Offre_emploi where id_user_fk=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, user.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Offre_emploi offre_emploi = new Offre_emploi(resultSet.getInt("id_offre"), new UserService().findById(resultSet.getInt(2)), new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
                offres.add(offre_emploi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }

    public List<Offre_emploi> Rechercher(String poste) {
        List<Offre_emploi> offres = new ArrayList<>();
        try {
            String req = "select * from offre_emploi where poste=?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, poste);
            //int idu;
            // idu = utilisateurService.loggedUser.getId();
            // ste.setInt(1, idu);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Offre_emploi offre_emploi = new Offre_emploi(resultSet.getInt("id_offre"), new UserService().findById(resultSet.getInt(2)), new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
                offres.add(offre_emploi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Offre_emploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offres;
    }
    
    public List<Offre_emploi> Rechercher2(String Specialite) {
        List<Offre_emploi> offres = new ArrayList<>();
        try {
            String req = "select * from offre_emploi where specialite=?";

            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, Specialite);
            //int idu;
            // idu = utilisateurService.loggedUser.getId();
            // ste.setInt(1, idu);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Offre_emploi offre_emploi = new Offre_emploi(resultSet.getInt("id_offre"), new UserService().findById(resultSet.getInt(2)), new BoutiqueService().findById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getDate(8));
                offres.add(offre_emploi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Offre_emploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offres;
    }
    
    
    
    
    
    
    

}
