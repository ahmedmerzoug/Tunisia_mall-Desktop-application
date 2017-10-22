package tunisia_mall.GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.models.Boutique;

public class AjouterBoutiqueFXMLController {

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_position;

    @FXML
    void AjouterBouton(ActionEvent event) throws IOException {
        IBoutiqueService ibs = new BoutiqueService();
        Boutique b = new Boutique(txt_nom.getText(), txt_type.getText(), txt_position.getText());
        ibs.add(b);

        ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

}
