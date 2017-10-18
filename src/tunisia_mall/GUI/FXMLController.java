package tunisia_mall.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.models.Boutique;

public class FXMLController {

    @FXML
    private TextField position_txt;

    @FXML
    private TextField type_txt;

    @FXML
    private TextField nom_txt;

    @FXML
    void onactionclick(ActionEvent event) {
        IBoutiqueService ibs = new BoutiqueService(); 
      Boutique b = new Boutique(nom_txt.getText() ,type_txt.getText(), position_txt.getText());
      ibs.add(b);
        
        

    }

}
