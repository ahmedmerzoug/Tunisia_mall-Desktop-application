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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        String req = "insert into user (nom,prenom,date_naissance,sexe,username,password,mail,role,numero_telephone,adresse,salaire,date_embauche,date_expiration,path,id_boutique) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            preparedStatement.setString(4, t.getSexe());
            preparedStatement.setString(5, t.getLogin());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, t.getMail());
            preparedStatement.setString(8, t.getRole());
            preparedStatement.setInt(9, t.getNumero_telephone());
            preparedStatement.setString(10, t.getAdresse());
            preparedStatement.setFloat(11, t.getSalaire());
            try {
                preparedStatement.setDate(12, t.convert(t.getDate_embauche()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                preparedStatement.setDate(13, t.convert(t.getDate_expiration()));
            } catch (ParseException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            preparedStatement.setString(14, t.getPath());

            preparedStatement.setInt(15, t.getBoutique().getId_boutique());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User t) {
        String req = "update user set nom=?,prenom=?,date_naissance=?,sexe=?,username=?,password=?,mail=?,role=?,numero_telephone=?,adresse=?,salaire=?,date_embauche=?,date_expiration=?,path=?,id_boutique=? where id_user = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setDate(3, t.convert(t.getDate_naissance()));
            preparedStatement.setString(4, t.getSexe());

            preparedStatement.setString(5, t.getLogin());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, t.getMail());
            preparedStatement.setString(8, t.getRole());
            preparedStatement.setInt(9, t.getNumero_telephone());
            preparedStatement.setString(10, t.getAdresse());
            preparedStatement.setFloat(11, t.getSalaire());
            preparedStatement.setDate(12, t.convert(t.getDate_embauche()));
            preparedStatement.setDate(13, t.convert(t.getDate_expiration()));
            preparedStatement.setString(14, t.getPath());
            preparedStatement.setInt(15, t.getBoutique().getId_boutique());
            preparedStatement.setInt(16, t.getId_user());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        } catch (ParseException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(Integer id_user) {
        String req = "delete from user where id_user =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User findById(Integer id_user) {
        User user = null;
        String req = "select * from user where id_user =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id_user"), resultSet.getString("nom"),
                        resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")),
                        resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"),
                        resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"),
                        resultSet.getString("adresse"), resultSet.getFloat("salaire"),
                        User.convert(resultSet.getDate("date_embauche")),
                        User.convert(resultSet.getDate("date_expiration")),
                        resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User findbyIdRole(int id_user, String role) {
        User user = null;
        String req = "select * from user where id_user =? AND role=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), User.convert(resultSet.getDate(4)), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getFloat(12), User.convert(resultSet.getDate(13)), User.convert(resultSet.getDate(14)), resultSet.getString(15), new BoutiqueService().findById(resultSet.getInt(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), User.convert(resultSet.getDate(4)), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getFloat(12), User.convert(resultSet.getDate(13)), User.convert(resultSet.getDate(14)), resultSet.getString(15), new BoutiqueService().findById(resultSet.getInt(16)));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;

    }

    @Override
    public ObservableList<User> displayall() {
        ObservableList<User> listeUser = FXCollections.observableArrayList();
        String req = "select * from user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               User use = new User(resultSet.getInt("id_user"), resultSet.getString("nom"),
                        resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")),
                        resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"),
                        resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"),
                        resultSet.getString("adresse"), resultSet.getFloat("salaire"),
                        User.convert(resultSet.getDate("date_embauche")),
                        User.convert(resultSet.getDate("date_expiration")),
                        resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));
                listeUser.addAll(use);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listeUser;

    }

    @Override
    public void addClient(User t) {
        String req = "insert into user (nom,prenom,date_naissance,sexe,username,password,email,roles,numero_telephone,adresse,path,enabled,username_canonical,email_canonical) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            preparedStatement.setString(4, t.getSexe());
            preparedStatement.setString(5, t.getLogin());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, t.getMail());
            preparedStatement.setString(8, t.getRole());
            preparedStatement.setInt(9, t.getNumero_telephone());
            preparedStatement.setString(10, t.getAdresse());
            preparedStatement.setString(11, t.getPath());
            preparedStatement.setInt(12, 1);
            preparedStatement.setString(13, t.getLogin());
            preparedStatement.setString(14, t.getMail());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User findbyLogin(String s) {
        User user = null;
        String req = "select * from user where username =? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));
                break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean existLogin(String s) {
        User user = null;
        String req = "select * from user where username =? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean findbyRole(String role) {
        User user = null;
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ObservableList<User> displayallemp() {
        ObservableList<User> listeuser = FXCollections.observableArrayList();
        String req = "select * from user where roles=? ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "Employe");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));

                listeuser.add(u);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public ObservableList<User> displayallempl(int i) {
        ObservableList<User> listeuser = FXCollections.observableArrayList();
        String req = "select * from user where roles=? and id_boutique=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "Employe");
            preparedStatement.setInt(2, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));

                listeuser.add(u);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public ObservableList<User> displayallclient() {
        ObservableList<User> listeuser = FXCollections.observableArrayList();
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "a:0:{}");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));
                listeuser.add(u);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public ObservableList<String> displayallclientName() {
        ObservableList<String> listeuser = FXCollections.observableArrayList();
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;
        String nomprenom;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "client");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));

                nomprenom = u.getNom() + " " + u.getPrenom();
                listeuser.add(nomprenom);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public ObservableList<User> displayallprop() {
        ObservableList<User> listeuser = FXCollections.observableArrayList();
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString("nom"), resultSet.getString("prenom"), User.convert(resultSet.getDate("date_naissance")), resultSet.getString("sexe"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles"), resultSet.getInt("numero_telephone"), resultSet.getString("adresse"), resultSet.getFloat("salaire"), User.convert(resultSet.getDate("date_embauche")), User.convert(resultSet.getDate("date_expiration")), resultSet.getString("path"), new BoutiqueService().findById(resultSet.getInt("id_boutique")));

                listeuser.add(u);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeuser;
    }

    @Override
    public void desactivercompte(int id) {
        String req = "update user set username=? where id_user=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "fklddfdsqsgfsdssddfdkj");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }

    @Override
    public ObservableList<User> findEmployeByName(String search) {
        ObservableList<User> listepromo = FXCollections.observableArrayList();
        String requete = "select * from user where nom=? and roles=?";
        //// "select * from user where username like '"+search+"
        // System.out.println(requete);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, "Employe");
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {

                User pro = new User();
                pro.setId_user(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setPrenom(resultat.getString(3));

                pro.setDate_naissance(resultat.getString(4));
                pro.setSexe(resultat.getString(5));
                pro.setLogin(resultat.getString(6));
                pro.setPassword(resultat.getString(7));
                pro.setMail(resultat.getString(8));
                pro.setRole(resultat.getString(9));
                pro.setNumero_telephone(resultat.getInt(10));
                pro.setAdresse(resultat.getString(11));
                pro.setSalaire(resultat.getFloat(12));
                pro.setDate_embauche(resultat.getString(13));
                pro.setDate_expiration(resultat.getString(14));
                pro.setPath(resultat.getString(15));
                pro.setBoutique(new BoutiqueService().findById(resultat.getInt(16)));

                listepromo.add(pro);
                // System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ObservableList<User> findClientByName(String search) {
        ObservableList<User> listepromo = FXCollections.observableArrayList();
        String requete = "select * from user where nom=? and roles=?";
        //// "select * from user where username like '"+search+"
        // System.out.println(requete);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, "client");
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {

                User pro = new User();
                pro.setId_user(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setPrenom(resultat.getString(3));

                pro.setDate_naissance(resultat.getString(4));
                pro.setSexe(resultat.getString(5));
                pro.setLogin(resultat.getString(6));
                pro.setPassword(resultat.getString(7));
                pro.setMail(resultat.getString(8));
                pro.setRole(resultat.getString(9));
                pro.setNumero_telephone(resultat.getInt(10));
                pro.setAdresse(resultat.getString(11));
                pro.setSalaire(resultat.getFloat(12));
                pro.setDate_embauche(resultat.getString(13));
                pro.setDate_expiration(resultat.getString(14));
                pro.setPath(resultat.getString(15));
                pro.setBoutique(new BoutiqueService().findById(resultat.getInt(16)));

                listepromo.add(pro);
                // System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ObservableList<User> findPropByName(String search) {
        ObservableList<User> listepromo = FXCollections.observableArrayList();
        String requete = "select * from user where nom = ? and roles = ?";
        //// "select * from user where username like '"+search+"
        // System.out.println(requete);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, "shopowner");
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {

                User pro = new User();
                pro.setId_user(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setPrenom(resultat.getString(3));

                pro.setDate_naissance(resultat.getString(4));
                pro.setSexe(resultat.getString(5));
                pro.setLogin(resultat.getString(6));
                pro.setPassword(resultat.getString(7));
                pro.setMail(resultat.getString(8));
                pro.setRole(resultat.getString(9));
                pro.setNumero_telephone(resultat.getInt(10));
                pro.setAdresse(resultat.getString(11));
                pro.setSalaire(resultat.getFloat(12));
                pro.setDate_embauche(resultat.getString(13));
                pro.setDate_expiration(resultat.getString(14));
                pro.setPath(resultat.getString(15));
                pro.setBoutique(new BoutiqueService().findById(resultat.getInt(16)));

                listepromo.add(pro);
                // System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }

    @Override
    public User findPropByIdB(int id) {
        String requete = "select * from user where id_boutique = ? and roles = ?";

        PreparedStatement preparedStatement;
        User pro = new User();

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "shopowner");
            ResultSet resultat = preparedStatement.executeQuery();

            while (resultat.next()) {
                pro.setId_user(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setPrenom(resultat.getString(3));

                pro.setDate_naissance(resultat.getString(4));
                pro.setSexe(resultat.getString(5));
                pro.setLogin(resultat.getString(6));
                pro.setPassword(resultat.getString(7));
                pro.setMail(resultat.getString(8));
                pro.setRole(resultat.getString(9));
                pro.setNumero_telephone(resultat.getInt(10));
                pro.setAdresse(resultat.getString(11));
                pro.setSalaire(resultat.getFloat(12));
                pro.setDate_embauche(resultat.getString(13));
                pro.setDate_expiration(resultat.getString(14));
                pro.setPath(resultat.getString(15));
                pro.setBoutique(new BoutiqueService().findById(resultat.getInt(16)));
                System.out.println(pro);
            }
            return pro;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return pro;
        }
    }
    /////////this methode is created by ahmed merzoug 

    @Override
    public int maxwidin_winnertable() {
        int d = 0;
        String req = "SELECT id_winner FROM winner WHERE id_tab_winner = (SELECT MAX(id_tab_winner) FROM winner)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(req);
                d = resultSet.getInt(1);

            }
/////           d= resultSet.getDate(1);
        } catch (SQLException ex) {
            Logger.getLogger(WinnerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;

    }

}
