/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

/**
 *
 * @author Amine
 */
public class Facture {
    private int id_facture;
    private int quantite;
    private String date;
    private User user;
    private Produit produit;
    private Boutique boutique;

    public Facture(int id_facture, int quantite, String date, User user, Produit produit, Boutique boutique) {
        this.id_facture = id_facture;
        this.quantite = quantite;
        this.date = date;
        this.user = user;
        this.produit = produit;
        this.boutique = boutique;
    }

    public Facture(int quantite, String date, User user, Produit produit, Boutique boutique) {
        this.quantite = quantite;
        this.date = date;
        this.user = user;
        this.produit = produit;
        this.boutique = boutique;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Facture other = (Facture) obj;
        if (this.id_facture != other.id_facture) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_facture=" + id_facture + ", quantite=" + quantite + ", date=" + date + ", user=" + user + ", produit=" + produit + ", boutique=" + boutique + '}';
    }
    
    
}
