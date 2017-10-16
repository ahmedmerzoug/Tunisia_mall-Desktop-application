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
public class Panier {

    private int id_panier;
    private int nb_produit;
    private User user;
    private Produit produit;

    public Panier(int id_panier, int nb_produit, User user, Produit produit) {
        this.id_panier = id_panier;
        this.nb_produit = nb_produit;
        this.user = user;
        this.produit = produit;
    }
    
    

    public Panier(int nb_produit, User user, Produit produit) {
        this.nb_produit = nb_produit;
        this.user = user;
        this.produit = produit;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getNb_produit() {
        return nb_produit;
    }

    public void setNb_produit(int nb_produit) {
        this.nb_produit = nb_produit;
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

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Panier other = (Panier) obj;
        if (this.id_panier != other.id_panier) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", nb_produit=" + nb_produit + ", user=" + user + ", produit=" + produit + '}';
    }
    
    

}
