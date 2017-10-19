package tunisia_mall.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tunisia_mall.Technique.DataSource;

public class FXMLController {

    Connection connection;

    @FXML
    private TextField username_txt;

    @FXML
    private PasswordField password_txt;

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
    void onactionclick(ActionEvent event) {
        try {
            if (isLogin(username_txt.getText(), password_txt.getText())) {
                message.setText("youre ok");
            } else {
                message.setText("youre NOT ok");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
