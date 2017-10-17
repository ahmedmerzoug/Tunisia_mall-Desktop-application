/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.User;

/**
 *
 * @author ahmed
 */
public class UserService implements IUserService {

    Connection connection;

    public UserService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(User t) {
        String req = "insert into user (nom,prenom,date_naissance,sexe,login,password,mail,role,numero_telephone,adresse,salaire,date_embauche,date_expiration,path,id_boutique) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            try {
                preparedStatement.setDate(3, t.convert(t.getDate_naissance()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStatement.setString(4, t.getSexe());
            preparedStatement.setString(5, t.getLogin());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, t.getMail());
            preparedStatement.setString(8, t.getRole());
            preparedStatement.setInt(9, t.getNumero_telephone());
            preparedStatement.setString(10, t.getAdresse());
            preparedStatement.setFloat(11, t.getSalaire());
            try {
                preparedStatement.setDate(12, t.convert(t.getDate_embauche()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                preparedStatement.setDate(13, t.convert(t.getDate_expiration()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            preparedStatement.setString(14, t.getPath());
            preparedStatement.setInt(15, t.getBoutique().getId_boutique());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User t) {
         String req = "update user set nom=?,prenom=?,date_naissance=?,sexe=?,login=?,password=?,mail=?,role=?,numero_telephone=?,adresse=?,salaire=?,date_embauche=?,date_expiration=?,path=?,id_boutique=? where id_user = ?";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setDate(3, t.convert(t.getDate_naissance()) );
            preparedStatement.setString(4, t.getSexe());

            preparedStatement.setString(5, t.getLogin());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, t.getMail());
            preparedStatement.setString(8, t.getRole());
            preparedStatement.setInt(9, t.getNumero_telephone());
            preparedStatement.setString(10, t.getAdresse());
            preparedStatement.setFloat(11, t.getSalaire());
            preparedStatement.setDate(12, t.convert(t.getDate_embauche()));
            preparedStatement.setDate(13, t.convert (t.getDate_expiration()));
            preparedStatement.setString(14, t.getPath());
             preparedStatement.setInt(15, t.getBoutique().getId_boutique());
            preparedStatement.setInt(16, t.getId_user());

            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }  catch (ParseException ex) {
               Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
           }
   
    }

    @Override
    public void remove(Integer id_user) {
         String req = "delete from user where id_user =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User findById(Integer id_user) {
 User user = null;
        String req = "select * from user where id_user =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),User.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getString(11),resultSet.getFloat(12),User.convert(resultSet.getDate(13)),User.convert(resultSet.getDate(14)),resultSet.getString(15), new BoutiqueService().findById(resultSet.getInt(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    @Override
   public User findbyIdRole  (int id_user,String role)
   {
            User user = null;
        String req = "select * from user where id_user =? AND role=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),User.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getString(11),resultSet.getFloat(12),User.convert(resultSet.getDate(13)),User.convert(resultSet.getDate(14)),resultSet.getString(15), new BoutiqueService().findById(resultSet.getInt(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
       
   }

    @Override
    public List<User> getAll() {
              List<User> users = new ArrayList<>();
        String req = "select * from user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),User.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getString(11),resultSet.getFloat(12),User.convert(resultSet.getDate(13)),User.convert(resultSet.getDate(14)),resultSet.getString(15), new BoutiqueService().findById(resultSet.getInt(16)));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;

        
    }

  

  

   

  

}
