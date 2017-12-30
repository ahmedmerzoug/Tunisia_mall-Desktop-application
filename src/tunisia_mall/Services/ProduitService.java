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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisia_mall.Interface.IProduitService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.CarteFidelite;
import tunisia_mall.models.Produit;

/**
 *
 * @author Amine
 */
public class ProduitService implements IProduitService{
     Connection connection;
     
      public ProduitService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Produit t) {
              String req = "insert into produit (nom,type,prix,quantite,prix_achat_gros,path,description,id_boutique) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getType());
             preparedStatement.setFloat(3, t.getPrix());

            preparedStatement.setInt(4, t.getQuantite());
            preparedStatement.setFloat(5, t.getPrix_achat_gros());
            
            preparedStatement.setString(6, t.getPath());
            preparedStatement.setString(7, t.getDescription());
            preparedStatement.setInt(8, t.getBoutique());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer r) {
   String req = "delete from produit where id_produit =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Produit findById(Integer r) {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    @Override
    public List<Produit> getAll() {
               List<Produit> produits = new ArrayList<>();
        String req = "select * from produit";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produit produit = new Produit(resultSet.getString(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getInt(4)
                        ,resultSet.getFloat(5),resultSet.getString(6));
                produits.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;
    }

    @Override
    public ObservableList<Produit> displayall() {
          ObservableList<Produit> listeproduits=FXCollections.observableArrayList();
        String req= "select * from produit";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=connection.prepareStatement(req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                  Produit produit = new Produit(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getInt(5)
                        ,resultSet.getFloat(6),resultSet.getInt(7),resultSet.getString(8),
                 resultSet.getString(9) );
                
                listeproduits.add(produit);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeproduits;
    }
    
}
