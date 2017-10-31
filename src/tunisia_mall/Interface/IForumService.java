/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import javafx.collections.ObservableList;
import tunisia_mall.models.Forum;

/**
 *
 * @author Amine
 */
public interface IForumService extends IService<Forum, Integer>{
   ObservableList<Forum> displayall();
   public ObservableList<Forum>  findbycategorie(String cat);
   int recherchecommentaire(); 
}
