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
public class Facture {
    private int id_facture;
    private User user;
    private Produit produit;
    private Boutique boutique;
    private float prix;
    private String date;

    public Facture() {
    }

    public Facture(int id_facture, User user, Produit produit, Boutique boutique, float prix, String date) {
        this.id_facture = id_facture;
        this.user = user;
        this.produit = produit;
        this.boutique = boutique;
        this.prix = prix;
        this.date = date;
    }

    public Facture(User user, Produit produit, Boutique boutique, float prix, String date) {
        this.user = user;
        this.produit = produit;
        this.boutique = boutique;
        this.prix = prix;
        this.date = date;
    }

    
  

    public int getId_facture() {
        return id_facture;
    }

    public User getUser() {
        return user;
    }

    public Produit getProduit() {
        return produit;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public float getPrix() {
        return prix;
    }

    public String getDate() {
        return date;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_facture=" + id_facture + ", user=" + user + ", produit=" + produit + ", boutique=" + boutique + ", prix=" + prix + ", date=" + date + '}';
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
        final Facture other = (Facture) obj;
        if (this.id_facture != other.id_facture) {
            return false;
        }
        return true;
    }

    public java.sql.Date convert (String date) throws ParseException{
      
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date1 = sdf1.parse(date);
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
   
    
    return sqlDate ;  
    }
    
      public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String text = df.format(d);
        return text;
    }
   
}
