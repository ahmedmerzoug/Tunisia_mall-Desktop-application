/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import javafx.collections.ObservableList;
import tunisia_mall.models.CarteFidelite;

/**
 *
 * @author Amine
 */
public interface ICarteFideliteService extends IService<CarteFidelite, Integer>{
    
    public ObservableList<CarteFidelite> displayall();
    public ObservableList<CarteFidelite> findCartebyID(Integer search);
    
}
