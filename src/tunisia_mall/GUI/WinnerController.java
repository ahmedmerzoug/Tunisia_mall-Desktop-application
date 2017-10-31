




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import com.github.plushaze.traynotification.animations.Animations;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.sun.nio.sctp.Notification;
import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import static sun.text.normalizer.NormalizerImpl.convert;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Interface.IWinnerService;
import tunisia_mall.Services.UserService;
import tunisia_mall.Services.WinnerService;

import tunisia_mall.models.User;
import tunisia_mall.models.Winner;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class WinnerController implements Initializable {

    @FXML
    private TextField wintxt;
    @FXML
    private Button btnwinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
  public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }

    @FXML
    private void randombutt(ActionEvent event) throws ParseException {  
        IWinnerService win = new WinnerService();
        IUserService us = new UserService();
        User user = us.findById(win.winnerOfTheDay());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
Calendar cal = Calendar.getInstance();

System.out.println(dateFormat.format(cal.getTime()));

            Winner pp = new Winner(dateFormat.format(cal.getTime()),user.getId_user() );
 
   
    String DD = convert(win.maxwinnerdate()); int MAXWINN = DD.length();
    String bb = dateFormat.format(cal.getTime());    int AUTRE = bb.length(); 
        System.out.println("bb"+AUTRE);
        System.out.println("dd"+MAXWINN);
      ////  String carac = bb+"";
      ///  int lasthope = carac.length();
      ////  System.out.println(lasthope);
        System.out.println("ahmedkkkkkkkkkkkkkk"+DD);
        System.out.println("aaaaaaaaaaaaaaaaaaa"+dateFormat.format(cal.getTime()));
  if (bb.equals(DD))
  {
        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("sorry");
                alert.setHeaderText("we have already have a winner for today");
                Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK) {
                 wintxt.clear();
                     
                 }
   }   
  else {
       System.out.println("lllllllllllllllllllllllllllllllllllllllllllll");
       win.add(pp);
         wintxt.setText("The winner of the day is: \n"+user.toString());
        
       String accessToken ="EAACEdEose0cBAI8TkdZBbKKLSVLPuVb0ET9KtZAFKwYZA0aSIHGzoBmAqcHhWNjKd3sZATK5kN7mXeyr737QbGYZBw1xfKoacMqTgVAReKmcRxI8gxkCph8aZBFZBwQOu50ZBo1oMMZAsjVSDpTdI5EaZBqShv5o4pCCJIUXoRHXZAMNOD8zYIWixCypf20dZBPCEGwZD" ;
      FacebookClient fbClien = new DefaultFacebookClient(accessToken);
      FacebookType response = fbClien.publish("me/feed",FacebookType.class,Parameter.with("message", "idim ya 5ra"));

    
  }
  
  

    /* 
 String title = "Congratulations sir";
        String message = "The winner of the day is: ";
        Notifications notification = Notifications.SUCCESS;
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndWait();
    
    
  
   /*
      Image whatsAppImg = new Image("https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/whatsapp-128.png");
        
         tray.setTray("Title", "Message", whatsAppImg, Paint.valueOf("#2A9A84"),AnimationType.SLIDE);
        tray.showAndDismiss(Duration.seconds(4));
      */ 
   
   
    }
}
    
