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
import tunisia_mall.Interface.IEvenementService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Evenement;

/**
 *
 * @author Amine
 */
public class EvenementService implements IEvenementService {

    Connection connection;

    public EvenementService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Evenement t) {
        String req = "insert into evenement (nom,description,date,path,id_user) values (?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getDescription());
            preparedStatement.setDate(3, t.convert(t.getDate()));
            preparedStatement.setString(4, t.getPath());
            preparedStatement.setInt(5, t.getUser().getId_user());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Evenement t) {
        String req = "update evenement set nom=?,description=?,date=?,path=?,id_user=? where id_evenement = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getDescription());
            preparedStatement.setDate(3, t.convert(t.getDate()));
            preparedStatement.setString(4, t.getPath());
            preparedStatement.setInt(5, t.getUser().getId_user());
            preparedStatement.setInt(6, t.getId_evenement());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(Integer r) {
        String req = "delete from evenement where id_evenement =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Evenement findById(Integer r) {
        Evenement e = null;
        String req = "select * from evenement where id_evenement =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                e = new Evenement(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), Evenement.convert(resultSet.getDate(4)), resultSet.getString(5), new UserService().findById(resultSet.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    @Override
    public List<Evenement> getAll() {
        List<Evenement> evenements = new ArrayList<>();
        String req = "select * from evenement ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evenement evenement = new Evenement(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), Evenement.convert(resultSet.getDate(4)),
                        resultSet.getString(5), new UserService().findById(resultSet.getInt(6)));
                evenements.add(evenement);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }

    @Override
    public ObservableList<Evenement> displayall() {
        ObservableList<Evenement> listeevent = FXCollections.observableArrayList();
        String req = "select * from evenement";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evenement b = new Evenement(
                        resultSet.getInt("id_evenement"),
                        resultSet.getString("nom"),
                        resultSet.getString("description"),
                        Evenement.convert(resultSet.getDate("date")),
                        resultSet.getString("path"),
                        new UserService().findById(resultSet.getInt("id_user")));
                listeevent.add(b);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeevent;
    }

    @Override
    public List<String> liste_nom_event() {
        List<String> listenomevent = FXCollections.observableArrayList();
        String req = "select nom from evenement";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listenomevent.add(resultSet.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listenomevent;
    }
    
}
