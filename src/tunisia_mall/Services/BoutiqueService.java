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
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Boutique;

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

}
