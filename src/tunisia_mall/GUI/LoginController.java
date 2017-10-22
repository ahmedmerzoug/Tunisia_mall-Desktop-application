package tunisia_mall.GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tunisia_mall.Technique.DataSource;

public class LoginController {

    Connection connection;

    @FXML
    private TextField txt_login;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label message;

    public void StartConnection() {
        connection = DataSource.getInsatance().getConnection();
    }

    public boolean isLogin(String user, String pass) throws SQLException {

        StartConnection();

        String query = "select * from user where login = ? and password = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
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
                Stage primaryStage = new Stage();
                ((Node) event.getSource()).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("adminapp.fxml"));
                Scene scene = new Scene(root, 1280, 800);
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("login ou mot de passe incorrecte");
                Optional<ButtonType> result = alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
