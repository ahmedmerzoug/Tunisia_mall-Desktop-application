/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ahmed
 */
public class mainfx extends Application {

    private Screen screen = Screen.getPrimary();
    private Rectangle2D windows = screen.getVisualBounds();

    public static Stage window;

    @Override
    public void start(final Stage stage) throws Exception {
        window = stage;

        Parent root = FXMLLoader.load(getClass().getResource("loadingscreen.fxml"));

        Scene scene = new Scene(root);

//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setX(windows.getMinX());
//        stage.setY(windows.getMinY());
//        stage.setWidth(windows.getWidth());
//        stage.setHeight(windows.getHeight());

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void signinWindow() {

        try {
            Stage stage = new Stage();
//                        ((Node) event.getSource()).getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                        Scene scene = new Scene(root);

                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setX(windows.getMinX());
                        stage.setY(windows.getMinY());
                        stage.setWidth(windows.getWidth());
                        stage.setHeight(windows.getHeight());

                        stage.setScene(scene);
                        stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
