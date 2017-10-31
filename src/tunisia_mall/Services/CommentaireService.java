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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisia_mall.Interface.ICommentaireService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Commentaire;

/**
 *
 * @author ahmed
 */
public class CommentaireService implements ICommentaireService{
    
      Connection connection;

    public CommentaireService() {
        connection = DataSource.getInsatance().getConnection();
    }


    @Override
    public ObservableList<Commentaire> displayall() {
         ObservableList<Commentaire> listecommentaire=FXCollections.observableArrayList();
        String req= "select * from commentaire";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=connection.prepareStatement(req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
           Commentaire commentaire = new Commentaire(resultSet.getInt(1),resultSet.getString(2),Commentaire.convert(resultSet.getDate(3)),new UserService().findbyLogin(resultSet.getString(4)),new ForumService().findById(resultSet.getInt(5)));
              listecommentaire.add(commentaire);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listecommentaire;   
    }

    @Override
    public void add(Commentaire t) {
         String req = "insert into commentaire (contenu,date_commentaire,login_user,id_forum1) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getContenu());
           
            preparedStatement.setDate(2, t.convert(t.getDate_commentaire()));
            preparedStatement.setString(3, t.getUser().getLogin());
            preparedStatement.setInt(4, t.getForum().getId_topic());


            preparedStatement.executeUpdate();
         
    }   catch (SQLException ex) {
            Logger.getLogger(ForumService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ForumService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void update(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire findById(Integer r) {
          Commentaire commentaire = null;
        /*
         Commentaire commentaire = null;
        String req = "select * from commentaire where id_forum1 =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            commentaire = new Commentaire(resultSet.getInt(1),resultSet.getString(2),Commentaire.convert(resultSet.getDate(3)), new UserService().findById(resultSet.getInt(4)), new ForumService().findById(resultSet.getInt(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
        return commentaire;
    }

    @Override
    public List<Commentaire> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    }

  
   
    
