/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Services;
import tunisia_mall.models.CarteFidelite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisia_mall.Technique.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import tunisia_mall.models.User;
import tunisia_mall.Interface.ICarteFideliteService;




/**
 *
 * @author Amine
 */
public class CarteFideliteService implements ICarteFideliteService {
    Connection connection;

    public CarteFideliteService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(CarteFidelite c) {
         String req = "insert into carte_fidelite (nb_point,date_creation,id_user) values (?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,c.getNb_point());
            
            preparedStatement.setDate(2, c.convert(c.getDate_creation()));
            
           
            preparedStatement.setInt(3, c.getUser().getId_user());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(CarteFideliteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(CarteFidelite c) {
        String req = "update carte_fidelite set nb_point=?,date_creation=?,id_user=? where id_carte_fidelite = ?";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,c.getNb_point());
            
            preparedStatement.setDate(2, c.convert(c.getDate_creation()));
            
           
            preparedStatement.setInt(3, c.getUser().getId_user());
            preparedStatement.setInt(4, c.getId_carte_fidelite());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(CarteFideliteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Integer r) {
           String req = "delete from carte_fidelite where id_carte_fidelite =?";
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
    public CarteFidelite findById(Integer id_carte) {
         CarteFidelite carteFidelite = null;
        String req = "select * from carte_fidelite where id_carte_fidelite =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_carte);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            carteFidelite = new CarteFidelite(resultSet.getInt(1),resultSet.getInt(2),CarteFidelite.convert(resultSet.getDate(3)), new UserService().findById(resultSet.getInt(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carteFidelite;
    }

    @Override
    public List<tunisia_mall.models.CarteFidelite> getAll() {
              List<CarteFidelite> carteFidelites = new ArrayList<>();
        String req = "select * from carte_fidelite";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CarteFidelite carteFidelite = new CarteFidelite(resultSet.getInt(1),resultSet.getInt(2),CarteFidelite.convert(resultSet.getDate(3)), new UserService().findById(resultSet.getInt(4)));
                carteFidelites.add(carteFidelite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carteFidelites;

    }
    
}
