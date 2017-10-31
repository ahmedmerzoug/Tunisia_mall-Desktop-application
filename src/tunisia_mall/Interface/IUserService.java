package tunisia_mall.Interface;

import javafx.collections.ObservableList;
import tunisia_mall.models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */
public interface IUserService extends IService<User, Integer> {

    User findbyIdRole(int a, String b);

    public ObservableList<User> displayall();

    void addClient(User t);

    User findbyLogin(String s);

    Boolean existLogin(String s);

    boolean findbyRole(String b);

    public ObservableList<User> displayallemp();

    public ObservableList<User> displayallempl(int i);

    public ObservableList<User> displayallclient();

    public ObservableList<String> displayallclientName();

    public ObservableList<User> displayallprop();

    public void desactivercompte(int id);

    public ObservableList<User> findEmployeByName(String search);

    public ObservableList<User> findClientByName(String search);

    public ObservableList<User> findPropByName(String search);

    public User findPropByIdB(int id);
    

      int maxwidin_winnertable();
}
