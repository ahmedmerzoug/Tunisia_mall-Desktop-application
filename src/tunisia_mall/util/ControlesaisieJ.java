/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import tunisia_mall.Technique.DataSource;

/**
 *
 * @author ASUS
 */
public class ControlesaisieJ {

    Connection connection;

    public ControlesaisieJ() {
        connection = DataSource.getInsatance().getConnection();
    }

    public boolean testnomprenom(String nom) {
        //************************ nom et prenom contiennent que des alphabets ***************************
        String masque = "^[a-zA-Z]+[a-z]";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(nom);
        return controler.matches();
    }

    public int testdateEMB_dateEXP(String d1, String d2) throws ParseException {
        //******************* test date embauche inférieur a la date expiration*****************************
        java.util.Date dateEMB = convert(d1);
        java.util.Date dateEXP = convert(d2);
        return dateEMB.compareTo(dateEXP);
    }

    /**
     * ************************ Password 8
     * chiffres**************************************************************
     */
    public boolean testpassword(String password) {
        return (password.length() > 8);
    }
//********************************Numero telephone contient 8 chiffres **************************************/

    public boolean GSM(String num) {
        String masque = "[0-9]{8}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(num);
        if (controler.matches()) {
            return true;
        }
        return false;
    }
//********************* salaire doit etre des chiffres et compris entre 2 et 8 chiffres *****************************

    public boolean salaire(String salaire) {
        String masque = "[0-9]{2-8}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(salaire);
        return controler.matches();
    }
//********************** test format mail *****************************************************************************

    public boolean mailformat(String mail) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(mail);
        return controler.matches();

    }
// *********************************** test login format ************************************************************

    public boolean testlogin(String login) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(login);
        return controler.matches();
    }
//********************************** test login inutilisé ***********************************************************

    public boolean loginvalide(String login) {
        boolean test = true;
        String req = "select * from user ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String LoginRecup = resultSet.getString("login");
                if (login.equals(LoginRecup)) {
                    test = false;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return test;
    }
//************************************* convertion de String à Date  ********************************

    public java.util.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);

        return date1;
    }
}
