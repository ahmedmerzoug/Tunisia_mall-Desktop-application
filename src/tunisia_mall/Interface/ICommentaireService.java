/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import javafx.collections.ObservableList;
import tunisia_mall.models.Commentaire;

/**
 *
 * @author bn
 */
public interface ICommentaireService extends IService<Commentaire, Integer> {
     ObservableList<Commentaire> displayall();
     ObservableList<Commentaire> displayallbyidforum(Integer aa);
}
