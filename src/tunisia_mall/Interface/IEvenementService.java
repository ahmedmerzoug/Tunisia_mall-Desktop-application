/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import java.util.List;
import javafx.collections.ObservableList;
import tunisia_mall.models.Evenement;

/**
 *
 * @author Amine
 */
public interface IEvenementService extends IService<Evenement, Integer> {

    public ObservableList<Evenement> displayall();

    List<String> liste_nom_event();

}
