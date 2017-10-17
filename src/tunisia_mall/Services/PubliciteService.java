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
import tunisia_mall.Interface.IPubliciteService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.Publicite;

/**
 *
 * @author Amine
 */
public class PubliciteService implements IPubliciteService {

    Connection connection;

    public PubliciteService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Publicite t) {
        String req = "insert into publicite (date_debut,date_fin,prix,page,path,id_boutique) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, t.convert(t.getDate_debut()));
            preparedStatement.setDate(2, t.convert(t.getDate_fin()));
            preparedStatement.setFloat(3, t.getPrix());
            preparedStatement.setString(4, t.getPage());
            preparedStatement.setString(5, t.getPath());
            preparedStatement.setInt(6, t.getBoutique().getId_boutique());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Publicite t) {
        String req = "update publicite set date_debut=?,date_fin=?,prix=?,page=?,path=?,id_boutique=? where id_pub = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, t.convert(t.getDate_debut()));
            preparedStatement.setDate(2, t.convert(t.getDate_fin()));
            preparedStatement.setFloat(3, t.getPrix());
            preparedStatement.setString(4, t.getPage());
            preparedStatement.setString(5, t.getPath());
            preparedStatement.setInt(6, t.getBoutique().getId_boutique());
            preparedStatement.setInt(7, t.getId_pub());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(Integer r) {
        String req = "delete from publicite where id_pub =?";
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
    public Publicite findById(Integer r) {
        Publicite p = null;
        String req = "select * from publicite where id_pub =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                p = new Publicite(resultSet.getInt(1), Publicite.convert(resultSet.getDate(2)), Publicite.convert(resultSet.getDate(3)), resultSet.getFloat(4), resultSet.getString(5), resultSet.getString(6), new BoutiqueService().findById(resultSet.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<Publicite> getAll() {
        List<Publicite> publicites = new ArrayList<>();
        String req = "select * from publicite ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publicite p = new Publicite(resultSet.getInt(1), Publicite.convert(resultSet.getDate(2)), Publicite.convert(resultSet.getDate(3)), resultSet.getFloat(4), resultSet.getString(5), resultSet.getString(6), new BoutiqueService().findById(resultSet.getInt(7)));
                publicites.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicites;
    }

}
