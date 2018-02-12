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
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public class BoutiqueService implements IBoutiqueService {

    Connection connection;

    public BoutiqueService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Boutique t) {
        String req = "insert into boutique (nom,type,position) values (?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getType());
            preparedStatement.setString(3, t.getPosition());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Boutique t) {
        String req = "update boutique set nom=?,type=?,position=? where id_boutique = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getType());
            preparedStatement.setString(3, t.getPosition());

            preparedStatement.setInt(4, t.getId_boutique());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(Integer r) {
        String req = "delete from boutique where id_boutique =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Boutique findById(Integer r) {
        Boutique b = null;
        String req = "select * from boutique where id_boutique =?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                b = new Boutique(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public List<Boutique> getAll() {
        List<Boutique> boutiques = new ArrayList<>();
        String req = "select * from boutique ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Boutique b = new Boutique(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                boutiques.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boutiques;
    }

    @Override
    public ObservableList<Boutique> displayall() {
        ObservableList<Boutique> listeboutique = FXCollections.observableArrayList();
        String req = "select * from boutique";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Boutique b = new Boutique(resultSet.getInt(1),
                        resultSet.getString(2), 
                        resultSet.getString(3),
                        resultSet.getString(4));
                listeboutique.add(b);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeboutique;
    }

    @Override
    public ObservableList<String> displayallnom() {
        ObservableList<String> listeboutique = FXCollections.observableArrayList();
        String req = "select * from boutique";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Boutique b = new Boutique(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                listeboutique.add(b.getNom());

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeboutique;
    }

    @Override
    public ObservableList<String> displayallBshopowner() {
        ObservableList<String> listeboutique = FXCollections.observableArrayList();
        String req = "select * from boutique";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!(idbutilise(resultSet.getInt("id_boutique")))) {
                    Boutique b = new Boutique(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    listeboutique.add(b.getNom());
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeboutique;
    }

    @Override
    public ObservableList<Boutique> findBoutiqueByName(String search) {
        ObservableList<Boutique> listepromo = FXCollections.observableArrayList();
        String requete = "select * from boutique where nom=?";
        //// "select * from user where username like '"+search+"
        System.out.println(requete);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, search);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {

                Boutique pro = new Boutique();
                pro.setId_boutique(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setType(resultat.getString(3));

                pro.setPosition(resultat.getString(4));

                listepromo.add(pro);
                System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Boutique findBoutiqueByNom(String nom) {
        String requete = "select * from boutique where nom = ?";

        PreparedStatement preparedStatement;
        Boutique pro = new Boutique();
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, nom);
            ResultSet resultat = preparedStatement.executeQuery();

            while (resultat.next()) {

                pro.setId_boutique(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setType(resultat.getString(3));

                pro.setPosition(resultat.getString(4));
                System.out.println(pro);
            }
            return pro;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return pro;

        }
    }

    @Override
    public boolean idbutilise(int id) {
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), User.convert(resultSet.getDate(4)), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getFloat(12), User.convert(resultSet.getDate(13)), User.convert(resultSet.getDate(14)), resultSet.getString(15), new BoutiqueService().findById(resultSet.getInt(16)));
                if (u.getBoutique().getId_boutique() == id) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
