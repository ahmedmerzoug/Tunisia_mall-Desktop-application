/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.Interface;

import javafx.collections.ObservableList;
import tunisia_mall.models.Reclamation;

/**
 *
 * @author Amine
 */
public interface IReclamationService extends IService<Reclamation, Integer> {

    public ObservableList<Reclamation> displayallR();

    public ObservableList<Reclamation> displayallR(int i);

    public ObservableList<String> displayallclientName();

    public ObservableList<String> displayallclientNameCP();
}
