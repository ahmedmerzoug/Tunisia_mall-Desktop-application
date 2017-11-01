/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.User;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class NewaccountController implements Initializable {

    private Screen screen = Screen.getPrimary();
    private Rectangle2D windows = screen.getVisualBounds();

    @FXML
    private Button ajouter;
    @FXML
    private Text alertmdp;
    @FXML
    private Text alertmdplogin;
    @FXML
    private Text alerttel;
    @FXML
    private Text alert;
    @FXML
    private JFXTextField nomtf;
    @FXML
    private JFXTextField prenomtf;
    @FXML
    private JFXTextField txtmail;
    @FXML
    private JFXTextField txtlogin;
    @FXML
    private JFXDatePicker txtdate;
    @FXML
    private JFXComboBox<String> txtsexe;
    @FXML
    private JFXTextField txtadresse;
    @FXML
    private JFXTextField txtnumero;
    @FXML
    private JFXPasswordField mdptf;
    @FXML
    private JFXButton image;
    @FXML
    private ImageView imageview;

    IUserService ius = new UserService();
    User u = new User();

    static String getImageUrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> data = FXCollections.observableArrayList("homme", "femme");
        txtsexe.setItems(data);
    }

    @FXML
    private void addimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {
            getImageUrl = selectedfile.getAbsolutePath();
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            imageview.setImage(ima);
//            System.out.println(selectedfile.get);
        } else {
            System.out.println("file does not exist");
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        if (!nomtf.getText().equals("") && !prenomtf.getText().equals("") && !txtmail.getText().equals("")
                && !txtlogin.getText().equals("") && !txtdate.getEditor().getText().equals("") && !txtadresse.getText().equals("") && !txtnumero.getText().equals("")
                && !mdptf.getText().equals("")) {

            if (ius.existLogin(txtlogin.getText())) {
                System.out.println("ajout impossible, login existe deja");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("erreur");
                alert.setHeaderText("login existe dans la base de donnée");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                u = new User(nomtf.getText(), prenomtf.getText(), txtdate.getEditor().getText(), txtsexe.getValue(),
                        txtlogin.getText(), mdptf.getText(), txtmail.getText(), "client", Integer.parseInt(txtnumero.getText()), txtadresse.getText(), getImageUrl);

                ius.addClient(u);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("inscription");
                alert.setHeaderText("succés d'inscription, passez à interface login afin de s'authentifier");
                Optional<ButtonType> result = alert.showAndWait();
                SendMail.sendmail(txtmail.getText(), "bienvenu à Tunisia Mall", "test message");
                if (result.get() == ButtonType.OK) {
                    Stage stage = new Stage();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setX(windows.getMinX());
                    stage.setY(windows.getMinY());
                    stage.setWidth(windows.getWidth());
                    stage.setHeight(windows.getHeight());

                    stage.setScene(scene);
                    stage.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("erreur champs vides");
            alert.setHeaderText("il ya des champs vides");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void ConnectViaFacebook(ActionEvent event) {
    }

    @FXML
    private void signin(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(windows.getMinX());
        stage.setY(windows.getMinY());
        stage.setWidth(windows.getWidth());
        stage.setHeight(windows.getHeight());

        stage.setScene(scene);
        stage.show();
    }

}
