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
import tunisia_mall.Interface.IDemande_emploiService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Demande_emploi;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public class Demande_emploiService implements IDemande_emploiService{
    
     Connection connection;

    public Demande_emploiService () {
        connection = DataSource.getInsatance().getConnection();
    }

   
    @Override
    public void add(Demande_emploi d){
     String req = "insert into demande_emploi (id_user_fk,id_offre_fk,nom_emp,prenom_emp,date_naissance,adresse,sexe,email,num_tel,qualification,experience) values (?,?,?,?,?,?,?,?,?,?,?)";
       
        PreparedStatement preparedStatement;
       
        try {
            preparedStatement = connection.prepareStatement(req);
            
           // preparedStatement.setInt(1,d.getId());
            preparedStatement.setInt(1,d.getUser().getId_user());
            preparedStatement.setInt(2,d.getOffre_emploi().getId_offre());
            preparedStatement.setString(3,d.getNom_emp());
            preparedStatement.setString(4,d.getPrenom_emp());
            
            try {  
               preparedStatement.setDate(5, d.convert(d.getDate_naissance()) );
           } catch (ParseException ex) {
               Logger.getLogger(Demande_emploiService.class.getName()).log(Level.SEVERE, null, ex);
           } 
            
            
            preparedStatement.setString(6,d.getAdresse());
            preparedStatement.setString(7,d.getSexe());
            preparedStatement.setString(8,d.getEmail());
            preparedStatement.setString(9,d.getNum_tel());
            preparedStatement.setString(10,d.getQualification());
            preparedStatement.setInt(11,d.getExperience());
           
            preparedStatement.executeUpdate();
          
       }catch (SQLException ex) {
         ex.printStackTrace();
        }

     }

    @Override
    public void update(Demande_emploi d){
   String req = "update demande_emploi set id_user_fk=?,id_offre_fk=?,nom_emp=?,prenom_emp=?,date_naissance=?,adresse=?,sexe=?,email=?,num_tel=?,qualification=?,experience=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
            
            preparedStatement.setInt(1,d.getUser().getId_user());
            preparedStatement.setInt(2,d.getOffre_emploi().getId_offre());
            preparedStatement.setString(3,d.getNom_emp());
            preparedStatement.setString(4,d.getPrenom_emp());
            preparedStatement.setString(5,d.getDate_naissance());
            preparedStatement.setString(6,d.getAdresse());
            preparedStatement.setString(7,d.getSexe());
            preparedStatement.setString(8,d.getEmail());
            preparedStatement.setString(9,d.getNum_tel());
            preparedStatement.setString(10,d.getQualification());
            preparedStatement.setInt(11,d.getExperience());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
     ex.printStackTrace();
        }
   
   
   
   }

    @Override
    public void remove(Integer id){
    String req = "delete from demande_emploi where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
     ex.printStackTrace();
        }
   
   }

    @Override
    public Demande_emploi findById(Integer id) {
        Demande_emploi demande_emploi = null;
        String req = "select * from demande_emploi where id_demande =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                demande_emploi = new Demande_emploi(resultSet.getInt("id_demande"),new UserService().findById(resultSet.getInt(2)),new Offre_emploiService().findById(resultSet.getInt(3)),resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12));
            }
        } catch (SQLException ex) {
 ex.printStackTrace();
        }
        return demande_emploi;

 }

    @Override
    public List<Demande_emploi> getAll() {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from demande_emploi";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            
while (resultSet.next()) {
Demande_emploi demande_emploi = new Demande_emploi(resultSet.getInt("id"),new UserService().findById(resultSet.getInt(2)),new Offre_emploiService().findById(resultSet.getInt(3)),resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12));
demandes.add(demande_emploi);
                         }
        } catch (SQLException ex) {
       ex.printStackTrace();
        }
        return demandes;
    }
 
@Override
    public List<Demande_emploi> getByUser (User user) {
        List<Demande_emploi> demandes = new ArrayList<>();
        String req = "select * from Demande_emploi where id_user_fk=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, user.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Demande_emploi demande_emploi = new Demande_emploi(resultSet.getInt("id_demande"),new UserService().findById(resultSet.getInt(2)),new Offre_emploiService().findById(resultSet.getInt(3)),resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12));
                demandes.add(demande_emploi);
            }
        } catch (SQLException ex) {
   ex.printStackTrace();
        }
        return demandes;
    }

   
     
}

