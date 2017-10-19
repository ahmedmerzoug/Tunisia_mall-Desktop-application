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

        IBoutiqueService iBoutiqueService = new BoutiqueService();
        Boutique b = new Boutique("ahmed", "vetement", "1ere etage");

        //iBoutiqueService.add(b);
        IUserService iUserService = new UserService();
        b.setId_boutique(1);
        User e = new User(20, "nom", "prenom", "22/12/1995", "sexe", "login", "password", "mail", "Client", 0, "adresse", 0, "22/12/1995", "22/12/1995", "", b);

        iUserService.add(e);

    }
}
