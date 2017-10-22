package tunisia_mall.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.models.Boutique;

public class GestionBoutiqueFXMLController implements Initializable {

    @FXML
    private TableView<Boutique> TableBoutique;

    @FXML
    private TableColumn<Boutique, String> Nom_Column;

    @FXML
    private TableColumn<Boutique, String> Type_Column;

    @FXML
    private TableColumn<Boutique, String> Position_Column;

    @FXML
    private Label txt_nom;

    @FXML
    private Label txt_type;

    @FXML
    private Label txt_position;

    void afficher() {
        IBoutiqueService ibs = new BoutiqueService();
        TableBoutique.setItems(ibs.displayall());

        Nom_Column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("type"));
        Position_Column.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    @FXML
    void ajouter(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterBoutiqueFXML.fxml"));
        Scene scene = new Scene(root, 356, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {
        if (!TableBoutique.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression du boutique");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer la boutique:  "
                    + TableBoutique.getSelectionModel().getSelectedItem().getNom());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IBoutiqueService ibs = new BoutiqueService();
                ibs.remove(TableBoutique.getSelectionModel().getSelectedItem().getId_boutique());
                afficher();
            }

        }

    }

    private void ShowBoutiqueDtails(Boutique b) {
        txt_nom.setText(b.getNom());
        txt_type.setText(b.getType());
        txt_position.setText(b.getPosition());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficher();

        TableBoutique.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> ShowBoutiqueDtails(newValue));

    }

}
