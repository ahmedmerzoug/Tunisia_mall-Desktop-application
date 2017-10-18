/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import java.util.List;
import tunisia_mall.models.Demande_emploi;
import tunisia_mall.models.User;

/**
 *
 * @author Amine
 */
public interface IDemande_emploiService extends IService <Demande_emploi, Integer> {
        List<Demande_emploi> getByUser (User user);

}
