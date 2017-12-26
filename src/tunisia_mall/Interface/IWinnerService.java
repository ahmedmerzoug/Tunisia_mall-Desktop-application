/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import java.sql.Date;
import javafx.collections.ObservableList;

import tunisia_mall.models.User;
import tunisia_mall.models.Winner;

/**
 *
 * @author bn
 */
public interface IWinnerService extends IService<Winner, Integer> {
    
      int findbydate(String date);
    User findbydate(int userId);
    int winnerOfTheDay();
    Date maxwinnerdate ();
     String identifywithrole( String r);
     
     
      public ObservableList<Winner> displayall();
   
    
}
