/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Services;

import java.util.List;
import tunisia_mall.Interface.IJardinEnfantService;
import tunisia_mall.models.JardinEnfant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisia_mall.models.User;
import tunisia_mall.Technique.DataSource;
/**
 *
 * @author Amine
 */
public class JardinEnfantService implements IJardinEnfantService{
    Connection connection;
    public JardinEnfantService() {
         this.connection =DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(JardinEnfant t) {
        String req = "insert into jardin_enfant (nb_place_total,nb_place_libre,date_reservation,id_user) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
           preparedStatement.setInt(1, t.getNb_place_total());
           preparedStatement.setInt(2, t.getNb_place_libre());
         
           try {
                preparedStatement.setDate(3, t.convert(t.getDate_reservation()));
            } catch (ParseException ex) {
                
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStatement.setInt(4, t.getUser().getId_user());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(JardinEnfant t) {
        String req = "update jardin_enfant set nb_place_total=?,nb_place_libre=?,date_reservation=?,id_user=? where id_jardin = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
          preparedStatement.setInt(1, t.getNb_place_total());
          preparedStatement.setInt(2, t.getNb_place_libre());
            
            try {
                preparedStatement.setDate(3, t.convert(t.getDate_reservation()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStatement.setInt(4, t.getUser().getId_user());
            preparedStatement.setInt(5, t.getId_jardinenfant());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer r) {
        String req = "delete from jardin_enfant where id_jardin =?";
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
    public JardinEnfant findById(Integer r) {
        JardinEnfant jardinEnfant= null;
        String req = "select * from jardin_enfant where id_jardin=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               jardinEnfant = new JardinEnfant(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), new UserService().findById(resultSet.getInt(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jardinEnfant;
    }

    @Override
    public List<JardinEnfant> getAll() {
         List<JardinEnfant> jardinEnfants = new ArrayList<>();
        
        
        String req = "select * from jardin_enfant";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JardinEnfant jardinEnfant   = new JardinEnfant(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), new UserService().findById(resultSet.getInt(5)));
                jardinEnfants.add(jardinEnfant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jardinEnfants;
    }
    
}
