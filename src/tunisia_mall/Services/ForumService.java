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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisia_mall.GUI.LoginController;
import tunisia_mall.Interface.IForumService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.CarteFidelite;

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
         String req = "insert into topic1 (categorie,description,date_topic,login_user) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getCategorie());
            preparedStatement.setString(2, t.getDescription());
            preparedStatement.setDate(3, t.convert(t.getDatetopic()));
            preparedStatement.setString(4, t.getUser().getLogin());

            preparedStatement.executeUpdate();
         
    }   catch (SQLException ex) {
            Logger.getLogger(ForumService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ForumService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    //////////// i must delete this 2 methode after 
        public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        return sqlDate;
    }

    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }
    */

  
                                                           
    @Override
    public ObservableList<Forum> displayall() {
 ObservableList<Forum> listeforum=FXCollections.observableArrayList();
        String req= "select * from topic1";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=connection.prepareStatement(req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
           Forum forum = new Forum(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Forum.convert(resultSet.getDate(4)),new UserService().findbyLogin(resultSet.getString(5)) );
              listeforum.add(forum);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeforum;   
    }
    
 
    @Override
    public void update(Forum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Forum findById(Integer r) {
         Forum forum = null;
        String req = "select * from topic1 where id_topic1 =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            forum = new Forum(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Forum.convert(resultSet.getDate(4)),new UserService().findbyLogin(resultSet.getString(5)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return forum;
    }

    @Override
    public List<Forum> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Forum> findbycategorie(String cat) {
          ObservableList<Forum> ListeForum = FXCollections.observableArrayList();
            Forum forum = null;
        String req = "select * from topic1 where categorie =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, cat);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
           forum = new Forum(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Forum.convert(resultSet.getDate(4)),new UserService().findbyLogin(resultSet.getString(5)));
           ListeForum.add(forum);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeForum;
    }
    
     @Override
    public int recherchecommentaire() {
       int d=0;
        String req = "SELECT *  FROM commentaire where id_forum1 = ?";
        PreparedStatement preparedStatement;
        try {
          preparedStatement = connection.prepareStatement(req); 
            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                 System.out.println(req);
             d= resultSet.getInt(1);

            }
/////           d= resultSet.getDate(1);
        } catch (SQLException ex) {
            Logger.getLogger(WinnerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
        
         
    }
}
