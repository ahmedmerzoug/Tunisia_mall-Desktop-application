/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall;

import java.sql.Date;
import static java.sql.JDBCType.NULL;
import tunisia_mall.Interface.ICarteFideliteService;
import tunisia_mall.Interface.IBoutiqueService;
import tunisia_mall.Services.CarteFideliteService;
import tunisia_mall.Interface.IUserService;
import tunisia_mall.Interface.IForumService;
import tunisia_mall.Interface.IJardinEnfantService;
import tunisia_mall.Interface.IReclamationService;
import tunisia_mall.Interface.IFactureService;
import tunisia_mall.Interface.IPubliciteService;
import tunisia_mall.Services.BoutiqueService;
import tunisia_mall.Services.Demande_emploiService;
import tunisia_mall.Services.FactureService;
import tunisia_mall.Services.PubliciteService;
import tunisia_mall.Services.ForumService;
import tunisia_mall.Services.JardinEnfantService;
import tunisia_mall.Services.Offre_emploiService;
import tunisia_mall.Services.ProduitService;
import tunisia_mall.Services.ReclamationService;
import tunisia_mall.Services.UserService;
import tunisia_mall.models.Boutique;
import tunisia_mall.models.CarteFidelite;
import tunisia_mall.models.Forum;
import tunisia_mall.models.User;
import tunisia_mall.models.*;

/**
 *
 * @author ahmed
 */
public class Tunisia_mall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        /*  IBoutiqueService iBoutiqueService = new BoutiqueService();
        Boutique b = new Boutique("tibou", "vetement", "1ere etage");

       b.setId_boutique(5);
     iBoutiqueService.add(b);
//        System.out.println(b);

        IUserService iUserService = new UserService();
         for (User u :  iUserService.getAll()) {
            System.out.println(u);
        }
       
        User e = new User("ahmed", "amine", "03/08/1995","homme", "amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);

        iUserService.findById(1);
        iUserService.findbyIdRole(1, "role");
        iUserService.remove(1);

<<<<<<< HEAD
       iUserService.add(e);
       iUserService.getAll();
       iUserService.update(e);
         */
        IBoutiqueService iBoutiqueService = new BoutiqueService();
        Boutique b = new Boutique("ahmed", "vetement", "1ere etage");

        //iBoutiqueService.add(b);
        IUserService iUserService = new UserService();
       // b.setId_boutique(51);
       User e = new User(20,"nom", "prenom", "22/12/1995", "sexe", "login", "password", "mail", "Client", 0, "adresse", 0, "22/12/1995", "22/12/1995","");

        iUserService.add(e);

        IPubliciteService iPubliciteService = new PubliciteService();
        Publicite p = new Publicite("12/14/2012", "12/12/2014", 0, "dss", "sdd", b);
        //iPubliciteService.add(p);

        IJardinEnfantService iJardinEnfantService = new JardinEnfantService();
        JardinEnfant j = new JardinEnfant(12, 4, "12/12/2012", e);
       // iJardinEnfantService.add(j);
        Produit pp = new Produit();
        ////   pp.setId_produit(1);
        IFactureService iFactureService = new FactureService();
        //// Facture f = new Facture(e, pp, b, 0.1f, "12/12/2000");
        /// iFactureService.add(f);

        /*
        ICarteFideliteService iCarteFideliteService = new CarteFideliteService();
      e.setId_user(1);
       
        CarteFidelite c = new CarteFidelite(1,"12/12/1994");
       iCarteFideliteService.add(c);
        ////iCarteFideliteService.update(c);
        
           User e = new User("ahmed", "amine", "03/08/1995","homme", "amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);

IForumService iForumService = new ForumService();
        Forum f = new Forum("tibou");

   
     iForumService.add(f); */
        //   }
//}
        //   for (User u :  iUserService.getAll()) {
        //       System.out.println(u);
        // }
        //     User e = new User("ahmed", "amine", "03/08/1995","homme", "amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);
        //   iUserService.findById(1);
        //    iUserService.findbyIdRole(1, "role");
        //    iUserService.remove(1);
        //   iUserService.add(e);
        //   iUserService.getAll();
        //  iUserService.update(e);
        ////User e = new User("ahmed", "amine", "03/08/1995","homme", "amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);
/*
        ICarteFideliteService iCarteFideliteService = new CarteFideliteService();
      ////// e.setId_user(1);
       
        CarteFidelite c = new CarteFidelite(1,"12/12/1994");
       iCarteFideliteService.add(c);
        ////iCarteFideliteService.update(c);
        
        
        
        
        
        
        
        
        
         */
 /*
         User e = new User("ahmed", "amine", "03/08/1995","homme", "amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);

IForumService iForumService = new ForumService();
        Forum f = new Forum("tibou");

   
     iForumService.add(f);
         */
 /*
jasssem

User a = new User ();
     
      IBoutiqueService iBoutiqueService = new BoutiqueService();
        Boutique b = new Boutique("tibou", "vetement", "1ere etage",a);
       // Boutique b1 = new Boutique();
   // b1.setId_boutique(15);

      b.setId_boutique(2);
     iBoutiqueService.add(b);
//        System.out.println(b);

        User u = new User("mraihi", "amine", "03/08/1995", "homme","amine", "mraihi", "amine.mraihi", "role", 5085234, "adresse", 12.5f, "12/12/2012", "12/12/2012", "",b);
       // u.setId_user(5);
        //System.out.println(u);
        
       iUserService.add(u);
       
        
        u.setId_user(3);
        iUserService.add(u);
        IReclamationService iReclamationService=new ReclamationService();
        Reclamation r=new Reclamation(1,"facture","error",u);
        //iReclamationService.add(r);
        //iReclamationService.update(r);
        iReclamationService.remove(19);
        IJardinEnfantService iJardinEnfantService=new JardinEnfantService();
        JardinEnfant j=new JardinEnfant(1,40, 30, "10/09/2017",u);
        //iJardinEnfantService.add(j);
        
      //iJardinEnfantService.update(j);
      iJardinEnfantService.remove(3);
     for (Reclamation em : iReclamationService.getAll()) {
            System.out.println(em);
        }
         */
 
      /******************************************* Tesnim ******************************************/
      
      
      UserService us = new UserService();
        Offre_emploiService oe = new Offre_emploiService();
        Demande_emploiService de = new Demande_emploiService();
        BoutiqueService bo = new BoutiqueService();
        FactureService fa =new FactureService();
        ProduitService pr = new ProduitService();
        
        /*Boutique b1= new Boutique(8," nom"," type", "position");
        User u= new User(1,"nom", "prenom", "12/05/2017","sexe", "login", "password", "mail", "client", 0, "adresse", 0, "12/05/2017","12/05/2017", "path");
       
     //b.setId_boutique(8);
     //bo.add(b1);
   Offre_emploi o = new Offre_emploi(2, u, b, "chef", "resto", 1500, 5, "12/08/2010");
   Demande_emploi d = new Demande_emploi(0, u, o, "bensalah", "ahmed", "01/09/1991", "Tunis", "M", "ahmed@gmail.com", "23589410", "bac+4", 2);
   
  // us.add(u);
   //bo.add(b1);
  // oe.add(o);
 //de.add(d);
  Produit p1 = new Produit(0, "nom", "type", 0, 0, 0, 0, "path", "description", b);
  Facture f = new Facture(0, u, p1, b, 50.5f,"15/05/2017");
  //pr.add(p1);
 */
 
 
    }
}
