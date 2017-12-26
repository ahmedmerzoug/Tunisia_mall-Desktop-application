/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
import sun.nio.ch.IOUtil;
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

//    @Override
//    public void add(Publicite t) {
//        String req = "insert into publicite (date_debut,date_fin,prix,page,path,id_boutique) values (?,?,?,?,?,?)";
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(req);
//            preparedStatement.setDate(1, t.convert(t.getDate_debut()));
//            preparedStatement.setDate(2, t.convert(t.getDate_fin()));
//            preparedStatement.setFloat(3, t.getPrix());
//            preparedStatement.setString(4, t.getPage());
//            preparedStatement.setString(5, t.getPath());
//            preparedStatement.setInt(6, t.getBoutique().getId_boutique());
//            preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
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
                Publicite p = new Publicite(resultSet.getInt(1), Publicite.convert(resultSet.getDate(2)),
                        Publicite.convert(resultSet.getDate(3)), resultSet.getFloat(4),
                        resultSet.getString(5), resultSet.getString(6),
                        new BoutiqueService().findById(resultSet.getInt(7)));
                publicites.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicites;
    }

    @Override
    public ObservableList<Publicite> displayall() {
        ObservableList<Publicite> listepub = FXCollections.observableArrayList();
        String req = "select * from publicite";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publicite b = new Publicite(resultSet.getInt(1), Publicite.convert(resultSet.getDate(2)),
                        Publicite.convert(resultSet.getDate(3)), resultSet.getFloat(4),
                        resultSet.getString(5), Publicite.convertStreamToString(resultSet.getBinaryStream(6)),
                        new BoutiqueService().findById(resultSet.getInt(7)));
                listepub.add(b);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listepub;
    }

//    resultSet.getBinaryStream(6)
//    @Override
//    public String getPubImage() {
//        String req = "SELECT path FROM publicite WHERE length(path)>20 order by rand() limit 1";
//        PreparedStatement preparedStatement;
//
//        try {
//            preparedStatement = connection.prepareStatement(req);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                String s = resultSet.getString("path");
//                return s;
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
//            return "";
//        }
//        return "";
//    }
    @Override
    public List<String> liste_nom_pub() {
        List<String> listenompub = FXCollections.observableArrayList();
        String req = "select page from publicite";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listenompub.add(resultSet.getString("page"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listenompub;
    }

//    @Override
//    public ObservableList<Publicite> displayallDemandePub() {
//        ObservableList<Publicite> listepub = FXCollections.observableArrayList();
//        String req = "select * from demande_pub";
//        PreparedStatement preparedStatement;
//
//        try {
//            preparedStatement = connection.prepareStatement(req);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Publicite b = new Publicite(resultSet.getInt(1), Publicite.convert(resultSet.getDate(2)),
//                        Publicite.convert(resultSet.getDate(3)), resultSet.getFloat(4),
//                        resultSet.getString(5), resultSet.getString(6),
//                        new BoutiqueService().findById(resultSet.getInt(7)));
//
//                listepub.add(b);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listepub;
//    }
    @Override
    public void remove_demande_pub(Integer r) {
        String req = "delete from demande_pub where id_pub =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    ****************************************caution test***********************
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
//
//            File thefile = new File(t.getPath());
//            FileInputStream input = null;
//            try {
//                input = new FileInputStream(thefile);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
//            }

//            preparedStatement.setBinaryStream(5, input);
            preparedStatement.setString(5, t.getPath());

            preparedStatement.setInt(6, t.getBoutique().getId_boutique());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    ****************************************caution*******************************
    @Override
    public String getPubImage() {
        String req = "SELECT path FROM publicite  order by rand() limit 1";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            File thefile = new File("src/tunisia_mall/GUI/img/test.png");
//            File x=new File("");
            FileOutputStream output = new FileOutputStream(thefile);
            if (resultSet.next()) {
                InputStream input = resultSet.getBinaryStream("path");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                return "src/tunisia_mall/GUI/img/test.png";
            }

        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

//*********************************caution test***********************
    @Override
    public ObservableList<Publicite> displayallDemandePub() {
        
        
        ObservableList<Publicite> listepub = FXCollections.observableArrayList();
        String req = "select * from demande_pub";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publicite b = new Publicite(resultSet.getInt(1), Publicite.convert(resultSet.getDate(2)),
                        Publicite.convert(resultSet.getDate(3)), resultSet.getFloat(4),
                        resultSet.getString(5), getPubImageListeDemande(),
                        new BoutiqueService().findById(resultSet.getInt(7)));

                listepub.add(b);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listepub;
    }

    @Override
    public String getPubImageListeDemande() {
        String req = "SELECT path FROM demande_pub  order by id_pub DESC limit 1";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            File thefile = new File("src/tunisia_mall/GUI/img/test1.png");
//            File x=new File("");
            FileOutputStream output = new FileOutputStream(thefile);
            if (resultSet.next()) {
                InputStream input = resultSet.getBinaryStream("path");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                return "src/tunisia_mall/GUI/img/test1.png";
            }

        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public ObservableList<Publicite> displayalldemandepub() {
        ObservableList<Publicite> listepub = FXCollections.observableArrayList();
        String req = "select * from demande_pub";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publicite b = new Publicite(
                        resultSet.getInt("id_pub"),
                        Publicite.convert(resultSet.getDate("date_debut")),
                        Publicite.convert(resultSet.getDate("date_fin")),
                        resultSet.getFloat("prix"),
                        resultSet.getString("page"),
                        resultSet.getString("path"),
                        new BoutiqueService().findById(resultSet.getInt("id_boutique")));
                listepub.add(b);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listepub;
    }

    @Override
    public List<String> liste_nom_demande_pub() {
        List<String> listenompub = FXCollections.observableArrayList();
        String req = "select page from demande_pub";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listenompub.add(resultSet.getString("page"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listenompub;
    }

}
