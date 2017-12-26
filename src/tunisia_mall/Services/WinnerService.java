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
import tunisia_mall.Interface.IWinnerService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.User;
import tunisia_mall.models.Winner;

/**
 *
 * @author ahmed
 */
public class WinnerService implements IWinnerService {

    Connection connection;

    public WinnerService() {
        connection = DataSource.getInsatance().getConnection();
    }

    private List<Integer> integers = new ArrayList<>();

    
    public User executeDBQuery(String query) {
        User user = null;
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public Boolean isValidWinnerUser(int w_id) {
        User userWinner = null;
        boolean f_return = false;
       
              userWinner = executeDBQuery("SELECT * from user where id_user = " + (w_id));
        System.out.println(userWinner);
        /*Here we will get the list of the last 7 winners*/
        List<Winner> winners = new ArrayList<>();
        String req = "SELECT DISTINCT * FROM winner ORDER BY winner_date DESC LIMIT 7";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Winner winner = new Winner(resultSet.getInt(1),convert(resultSet.getDate(2)), resultSet.getInt(3));
                winners.add(winner);
                System.out.println("dfef");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        /*Here we will check if the randomly found winner user 
        wasn't in the list of the last 7 winners (one user shouldn't win twice a week)*/
        Boolean userfound = false;
        int index = 0;
        while (userfound == false && index < winners.size()) {
            if (winners.get(index).getId_userwinner() == w_id) {
                userfound = true;
            }

            index++;
        }
        //check idrandomUserId
        if (userfound == false && userWinner != null) {
            f_return = true;
        }

        return f_return;
    }

    @Override
    public int winnerOfTheDay() {
        
        User userMax = null;
        User userWinner = null;
        int Min = 1;
        int randomUserId;
        int Max = executeDBQuery("SELECT MAX(id_user) FROM user").getId_user();

        do {
            randomUserId = Min + (int) (Math.random() * ((Max - Min) + 1));
        } while (!isValidWinnerUser(randomUserId));

        userWinner = executeDBQuery("SELECT * from user where id_user = " + (randomUserId));
        //Add the userWinner.getId_user to the winner ta
        
        
        
        return userWinner.getId_user();
    }

   
    
    @Override
    public void add(Winner t) {

        String req = "insert into winner (winner_date,id_winner) values (?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
             preparedStatement.setDate(1,(t. convert (t.getDate())));
            preparedStatement.setInt(2, t.getId_userwinner());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(WinnerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WinnerService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    @Override
    public int findbydate(String date) {
        int winnerId = 0;
        String req = "select * from winner where winner_date =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(2, convert(date));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(WinnerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /*could be to be deleted foreign key thinks*/
    @Override
    public User findbydate(int id_user) {
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
    public void update(Winner t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Winner findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Winner> getAll() {
        List<Winner> winners = new ArrayList<>();
        String req = "select * from user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Winner winner = new Winner(resultSet.getInt(1),convert(resultSet.getDate(2)), resultSet.getInt(3));
                winners.add(winner);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return winners;
    }

    @Override
    public Date maxwinnerdate() {
        Date d = null ;
        String req = "SELECT MAX(winner_date) FROM winner";
        PreparedStatement preparedStatement;
        try {
          preparedStatement = connection.prepareStatement(req); 
            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                 System.out.println(req);
              d= resultSet.getDate(1);

            }
/////           d= resultSet.getDate(1);
        } catch (SQLException ex) {
            Logger.getLogger(WinnerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
        
         
    }

    @Override
    public String identifywithrole(String r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    



}
