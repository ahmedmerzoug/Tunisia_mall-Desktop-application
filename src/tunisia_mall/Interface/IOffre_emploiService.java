/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import java.util.List;
import tunisia_mall.models.Offre_emploi;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public interface IOffre_emploiService extends IService<Offre_emploi, Integer>{
        List<Offre_emploi> getByUser (User user);

}
