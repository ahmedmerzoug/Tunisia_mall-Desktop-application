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
public class Promotion {
    private int id_promotion;
    private float taux_reduction;
    private String date_debut;
    private String date_fin;
    private Produit produit;

    public Promotion(int id_promotion, float taux_reduction, String date_debut, String date_fin, Produit produit) {
        this.id_promotion = id_promotion;
        this.taux_reduction = taux_reduction;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.produit = produit;
    }

    public Promotion(float taux_reduction, String date_debut, String date_fin, Produit produit) {
        this.taux_reduction = taux_reduction;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.produit = produit;
    }

    public Promotion(int id_promotion, float taux_reduction, String date_debut, String date_fin) {
        this.id_promotion = id_promotion;
        this.taux_reduction = taux_reduction;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Promotion(float taux_reduction, String date_debut, String date_fin) {
        this.taux_reduction = taux_reduction;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public float getTaux_reduction() {
        return taux_reduction;
    }

    public void setTaux_reduction(float taux_reduction) {
        this.taux_reduction = taux_reduction;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
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
        final Promotion other = (Promotion) obj;
        if (this.id_promotion != other.id_promotion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id_promotion=" + id_promotion + ", taux_reduction=" + taux_reduction + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", produit=" + produit + '}';
    }
    
    
}
