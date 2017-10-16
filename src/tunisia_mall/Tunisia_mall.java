/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall;

import java.sql.Date;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.User;

/**
 *
 * @author ahmed
 */
public class Tunisia_mall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        IBoutiqueService iBoutiqueService = new BoutiqueService();
        Boutique b = new Boutique("tibou", "vetement", "1ere etage");
       // Boutique b1 = new Boutique();
   // b1.setId_boutique(15);

       b.setId_boutique(2);
     iBoutiqueService.add(b);
//        System.out.println(b);

        IUserService iUserService = new UserService();
        User e = new User("mraihi", "amine", "03/08/1995", "amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);
       // e.setId_user(5);
        //System.out.println(e);
       iUserService.add(e);

    }

}
