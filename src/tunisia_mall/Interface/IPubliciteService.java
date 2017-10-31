/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import java.util.List;
import javafx.collections.ObservableList;
import tunisia_mall.models.Publicite;

/**
 *
 * @author Amine
 */
public interface IPubliciteService extends IService<Publicite, Integer> {

    ObservableList<Publicite> displayall();

    ObservableList<Publicite> displayallDemandePub();

    String getPubImage();

    List<String> liste_nom_pub();

    void remove_demande_pub(Integer r);

    String getPubImageListeDemande();

}
