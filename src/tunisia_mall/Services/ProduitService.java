/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisia_mall.Interface.IProduitService;
import tunisia_mall.Technique.DataSource;
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
              String req = "insert into produit (nom,type,prix,quantite,prix_achat_gros,path,description) values (?,?,?,?,?,?,?)";
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
           //// preparedStatement.setInt(8, t.getBoutique().getId_boutique());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produit> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
