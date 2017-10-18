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
import tunisia_mall.Interface.IFactureService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Facture;
import tunisia_mall.models.Produit;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public class FactureService implements IFactureService{

      Connection connection;

    public FactureService () {
        connection = DataSource.getInsatance().getConnection();
    }
    
    @Override
    public void add(Facture f) {
     String req = "insert into facture(id_user_fk,id_produit_fk,id_boutique_fk,prix,date) values (?,?,?,?,?)";
       
        PreparedStatement preparedStatement;
       
        try {
            preparedStatement = connection.prepareStatement(req);
            
           
            preparedStatement.setInt(1,f.getUser().getId_user());
            preparedStatement.setInt(2,f.getProduit().getId_produit());
            preparedStatement.setInt(3,f.getBoutique().getId_boutique());
            preparedStatement.setFloat(4,f.getPrix());
            
            try {  
               preparedStatement.setDate(5,f.convert(f.getDate()) );
           } catch (ParseException ex) {
               Logger.getLogger(Demande_emploiService.class.getName()).log(Level.SEVERE, null, ex);
           } 
            
            
         
            preparedStatement.executeUpdate();
          
       }catch (SQLException ex) {
         ex.printStackTrace();
        }

     }
    
    
    @Override
    public void update(Facture f){
   String req = "update facture set id_user_fk=?,id_produit=?,id_boutique_fk=?,prix=?,date=? where id_facture = ?" ;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
            
            preparedStatement.setInt(1,f.getUser().getId_user());
            preparedStatement.setInt(2,f.getProduit().getId_produit());
            preparedStatement.setInt(3,f.getBoutique().getId_boutique());
            preparedStatement.setFloat(4,f.getProduit().getPrix());
            preparedStatement.setString(5,f.getDate());
            preparedStatement.setInt(6,f.getId_facture());
           

            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
     ex.printStackTrace();
        }
   }

    @Override
    public void remove(Integer id){
    String req1 = "delete from facture where id_facture =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req1);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
     ex.printStackTrace();
        }
   
   }   

    @Override
    public Facture findById(Integer id) {
        Facture facture = null;
        String req2 = "select * from facture where id_facture =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req2);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                facture = new Facture (resultSet.getInt("id_facture"),new UserService().findById(resultSet.getInt(2)),new ProduitService().findById(resultSet.getInt(3)),new BoutiqueService().findById(resultSet.getInt(4)),resultSet.getFloat(5),Facture.convert(resultSet.getDate(6)));
            }
        } catch (SQLException ex) {
 ex.printStackTrace();
        }
        return facture;

 }   

    @Override
    public List<Facture> getAll() {
        List<Facture> factures = new ArrayList<>();
        String req = "select * from facture";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            
while (resultSet.next()) {
                Facture fac= new Facture (resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),new ProduitService().findById(resultSet.getInt(3)),new BoutiqueService().findById(resultSet.getInt(4)),resultSet.getFloat(5),Facture.convert(resultSet.getDate(6)));
factures.add(fac);
                         }
        } catch (SQLException ex) {
       ex.printStackTrace();
        }
        return factures;
    }
    
    @Override

    public List<Facture> getByUser (User user) {
        List<Facture> factures = new ArrayList<>();
        String req = "select * from facture where id_user_fk=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, user.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Facture fac= new Facture (resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),new ProduitService().findById(resultSet.getInt(3)),new BoutiqueService().findById(resultSet.getInt(4)),resultSet.getFloat(5),Facture.convert(resultSet.getDate(6)));
                factures.add(fac);
            }
        } catch (SQLException ex) {
         ex.printStackTrace();
        }
        return factures;
    }
    
    
    
}
