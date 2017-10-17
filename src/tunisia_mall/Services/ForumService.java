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
import tunisia_mall.Interface.IForumService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Forum;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public class ForumService implements IForumService{

    Connection connection;

    public ForumService() {
        connection = DataSource.getInsatance().getConnection();
    }

    

    @Override
    public void add(Forum t) {
         String req = "insert into forum (description,id_user) values (?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getDescription());
            preparedStatement.setInt(2, t.getUser().getId_user());

            preparedStatement.executeUpdate();
         
    }   catch (SQLException ex) {
            Logger.getLogger(ForumService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Forum t) {
        String req = "update forum set description=?,id_user=? where id_forum = ?";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getDescription());
          
        
             preparedStatement.setInt(2, t.getUser().getId_user());
            preparedStatement.setInt(3, t.getId_forum());

            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }
   
    }

    @Override
    public void remove(Integer r) {
            String req = "delete from forum where id_forum =?";
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
    public Forum findById(Integer id_for) {
        Forum forum = null;
        String req = "select * from forum where id_forum =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_for);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                forum = new Forum(resultSet.getInt(1),resultSet.getString(2),new UserService().findById(resultSet.getInt(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return forum;
    }

    @Override
    public List<Forum> getAll() {
                  List<Forum> forums = new ArrayList<>();
        String req = "select * from forum";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Forum forum = new Forum(resultSet.getInt(1),resultSet.getString(2),new UserService().findById(resultSet.getInt(3)));
                forums.add(forum);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return forums;

    }
    
}
