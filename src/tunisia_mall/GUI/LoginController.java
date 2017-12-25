package tunisia_mall.GUI;

import com.github.plushaze.traynotification.notification.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.UserService;
import tunisia_mall.Technique.DataSource;
import tunisia_mall.models.User;
import tunisia_mall.util.BCrypt;

public class LoginController {

    private Screen screen = Screen.getPrimary();
    private Rectangle2D windows = screen.getVisualBounds();

    Connection connection;

    @FXML
    private TextField txt_login;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label message;

    public static User LoggedUser;
    @FXML
    private Button fechar;

    public String getTxt_login() {
        System.out.println(txt_login.getText());
        return txt_login.getText();
    }

    public void StartConnection() {
        connection = DataSource.getInsatance().getConnection();
    }

    public boolean isLogin(String user, String pass) throws SQLException {

        StartConnection();
        String query = "select * from user where username = ?";

      //  String query = "select * from user where login = ? and password = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
           
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                LoggedUser = new User();
                LoggedUser.setId_user(resultSet.getInt("id_user"));
                LoggedUser.setNom(resultSet.getString("nom"));
                LoggedUser.setPrenom(resultSet.getString("prenom"));
                LoggedUser.setDate_naissance(resultSet.getString("date_naissance"));
                LoggedUser.setSexe(resultSet.getString("sexe"));
                LoggedUser.setLogin(resultSet.getString("username"));
                LoggedUser.setPassword(resultSet.getString("password"));
                LoggedUser.setMail(resultSet.getString("email"));
                LoggedUser.setRole(resultSet.getString("roles"));
                LoggedUser.setNumero_telephone(resultSet.getInt("numero_telephone"));
                LoggedUser.setAdresse(resultSet.getString("adresse"));
                LoggedUser.setSalaire(resultSet.getFloat("salaire"));
                LoggedUser.setDate_embauche(resultSet.getString("date_embauche"));
                LoggedUser.setDate_expiration(resultSet.getString("date_expiration"));
                LoggedUser.setPath(resultSet.getString("path"));
                LoggedUser.setBoutique(new BoutiqueService().findById(resultSet.getInt("id_boutique")));

                return BCrypt.checkpw(pass, LoggedUser.getPassword());
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    void linkTunisiaMall(ActionEvent event) {
//        site web tunisia mall
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        try {
            if (isLogin(txt_login.getText(), txt_password.getText())) {
//                System.out.println(txt_login.getText());
                User u = new User();
                IUserService ius = new UserService();

                switch (ius.findbyLogin(txt_login.getText()).getRole()) {
                    case "a:1:{i:0;s:10:\"ROLE_ADMIN\";}": {
                        Stage stage = new Stage();
                        ((Node) event.getSource()).getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("adminapp.fxml"));
                        Scene scene = new Scene(root);

                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setX(windows.getMinX());
                        stage.setY(windows.getMinY());
                        stage.setWidth(windows.getWidth());
                        stage.setHeight(windows.getHeight());

                        stage.setScene(scene);
                        stage.show();
                        break;
                    }

                    case "a:0:{}": {
                        Stage stage = new Stage();
                        ((Node) event.getSource()).getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("client_interface_new.fxml"));
                        Scene scene = new Scene(root);

                        /////this code here is added my ahmed merzoug to notify the winner client 

                        if (LoggedUser.getId_user()==ius.maxwidin_winnertable() )
                        {
                            
                            
                            
                            String title = "Congratulations sir";
                            String message = "The winner of the day is: ";
                            Notifications notification = Notifications.SUCCESS;

                            TrayNotification tray = new TrayNotification();
                            tray.setTitle(title);
                            tray.setMessage(message);
                            tray.setNotificationType(NotificationType.NOTICE);
                            tray.showAndWait();
                        }

                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setX(windows.getMinX());
                        stage.setY(windows.getMinY());
                        stage.setWidth(windows.getWidth());
                        stage.setHeight(windows.getHeight());

                        stage.setScene(scene);
                        stage.show();
                        break;
                    }

                    case "a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}": {
                        Stage stage = new Stage();
                        ((Node) event.getSource()).getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("shopownerapp.fxml"));
                        Scene scene = new Scene(root);

                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setX(windows.getMinX());
                        stage.setY(windows.getMinY());
                        stage.setWidth(windows.getWidth());
                        stage.setHeight(windows.getHeight());

                        stage.setScene(scene);
                        stage.show();
                        break;
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("login ou mot de passe incorrecte");
                Optional<ButtonType> result = alert.showAndWait();
            }
            
                 System.out.println("le user est:"+LoggedUser);

            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("newaccount.fxml"));
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(windows.getMinX());
        stage.setY(windows.getMinY());
        stage.setWidth(windows.getWidth());
        stage.setHeight(windows.getHeight());

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void close(MouseEvent event) {
        mainfx.window.close();

    }

    @FXML
    private void fechar(ActionEvent event) {
        Stage stage = (Stage) fechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void fechar(MouseEvent event) {
        Stage stage = (Stage) fechar.getScene().getWindow();
        stage.close();
    }

    
}
