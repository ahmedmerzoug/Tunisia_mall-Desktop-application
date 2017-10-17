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
        String req = "insert into user (nom,prenom,date_naissance,login,password,mail,role,numero_telephone,adresse,salaire,date_embauche,date_expiration,path,id_boutique) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

            preparedStatement.setString(4, t.getLogin());
            preparedStatement.setString(5, t.getPassword());
            preparedStatement.setString(6, t.getMail());
            preparedStatement.setString(7, t.getRole());
            preparedStatement.setInt(8, t.getNumero_telephone());
            preparedStatement.setString(9, t.getAdresse());
            preparedStatement.setFloat(10, t.getSalaire());
            try {
                preparedStatement.setDate(11, t.convert(t.getDate_embauche()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                preparedStatement.setDate(12, t.convert(t.getDate_expiration()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            preparedStatement.setString(13, t.getPath());
            preparedStatement.setInt(14, t.getBoutique().getId_boutique());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(Integer r) {
        User user = null;
        String req = "select * from user where id_user =? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        User.convert(resultSet.getDate(4)), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11), resultSet.getFloat(12), User.convert(resultSet.getDate(13)),
                        User.convert(resultSet.getDate(14)), resultSet.getString(15),
                        new BoutiqueService().findById(resultSet.getInt(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


