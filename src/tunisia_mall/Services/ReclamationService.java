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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisia_mall.GUI.LoginController;
import tunisia_mall.Interface.IReclamationService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Reclamation;

/**
 *
 * @author Amine
 */
public class ReclamationService implements IReclamationService {

    Connection connection;

    public ReclamationService() {
        this.connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Reclamation t) {
        String req = "insert into reclamation (type,text,id_reclamant,id_P_reclame) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, t.getType());

            preparedStatement.setString(2, t.getText());
            preparedStatement.setInt(3, t.getUserreclamant().getId_user());
            preparedStatement.setInt(4, t.getUserreclame().getId_user());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Reclamation t) {
        String req = "update reclamation set type=?,text=?,id_reclamant=?,id_P_reclame=? where id_reclamation = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            //preparedStatement.setInt(1, t.getId_reclamation());
            preparedStatement.setString(1, t.getType());

            preparedStatement.setString(2, t.getText());
            preparedStatement.setInt(3, t.getUserreclamant().getId_user());
            preparedStatement.setInt(4, t.getUserreclame().getId_user());
            preparedStatement.setInt(5, t.getId_reclamation());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer r) {
        String req = "delete from reclamation where id_reclamation =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Reclamation findById(Integer r) {
        Reclamation reclamation = null;
        String req = "select * from reclamation where id_reclamation =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reclamation = new Reclamation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)), new UserService().findById(resultSet.getInt(5)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamation;
    }

    @Override
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "select * from reclamation";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)), new UserService().findById(resultSet.getInt(5)));

                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;
    }

    @Override
    public ObservableList<Reclamation> displayallR() {
        ObservableList<Reclamation> listeuser = FXCollections.observableArrayList();
        String req = "select * from reclamation";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reclamation r = new Reclamation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)), new UserService().findById(resultSet.getInt(5)));

                listeuser.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;

    }

    @Override
    public ObservableList<Reclamation> displayallR(int i) {
        ObservableList<Reclamation> listeuser = FXCollections.observableArrayList();
        String req = "select * from reclamation where id_P_reclame=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reclamation r = new Reclamation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)), new UserService().findById(resultSet.getInt(5)));

                listeuser.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public ObservableList<String> displayallclientName() {
        IUserService ius = new UserService();
        ObservableList<String> listeuser = FXCollections.observableArrayList();
        String req = "select * from reclamation ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reclamation r = new Reclamation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)), new UserService().findById(resultSet.getInt(5)));

                listeuser.add(r.getUserreclamant().getNom() + " " + r.getUserreclamant().getPrenom());

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public ObservableList<String> displayallclientNameCP() {
        IUserService ius = new UserService();
        ObservableList<String> listeuser = FXCollections.observableArrayList();
        String req = "select * from reclamation where id_P_reclame=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, LoginController.LoggedUser.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Reclamation r = new Reclamation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)), new UserService().findById(resultSet.getInt(5)));

                listeuser.add(r.getUserreclamant().getNom() + " " + r.getUserreclamant().getPrenom());

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

}
