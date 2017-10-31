/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Amine
 */
public class CarteFidelite {
    private int id_carte_fidelite;
    private int nb_point;
    private String date_creation;
    private User user;
    
    public CarteFidelite() {
    }


    public CarteFidelite(int id_carte_fidelite, int nb_point, String date_creation, User user) {
        this.id_carte_fidelite = id_carte_fidelite;
        this.nb_point = nb_point;
        this.date_creation = date_creation;
        this.user = user;
    }

    public CarteFidelite(int nb_point, String date_creation, User user) {
        this.nb_point = nb_point;
        this.date_creation = date_creation;
        this.user = user;
    }

    public CarteFidelite(int nb_point, String date_creation) {
        this.nb_point = nb_point;
        this.date_creation = date_creation;
    }

  
    public int getId_carte_fidelite() {
        return id_carte_fidelite;
    }

    public void setId_carte_fidelite(int id_carte_fidelite) {
        this.id_carte_fidelite = id_carte_fidelite;
    }

    public int getNb_point() {
        return nb_point;
    }

    public void setNb_point(int nb_point) {
        this.nb_point = nb_point;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarteFidelite other = (CarteFidelite) obj;
        if (this.id_carte_fidelite != other.id_carte_fidelite) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarteFidelite{" + "id_carte_fidelite=" + id_carte_fidelite + ", nb_point=" + nb_point + ", date_creation=" + date_creation + ", user=" + user + '}';
    }
    
   public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String text = df.format(d);
        return text;
    }
     
      public java.sql.Date convert (String date) throws ParseException{
      
    SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
    java.util.Date date1 = sdf1.parse(date);
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
   
    
    return sqlDate ;  
    } 
    
    
}
