/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import javafx.collections.ObservableList;
import tunisia_mall.models.Produit;

/**
 *
 * @author Amine
 */
public interface IProduitService extends IService<Produit, Integer> {
     public ObservableList<Produit> displayall();
}
