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
public class Produit {

    private int id_produit;
    private String nom;
    private String type;
    private float prix;
    private int quantite;
    private float prix_achat_gros;
    private int nb_vente;
    private String path;
    private String description;
    private int boutique;

    public Produit() {
    }

    public Produit(String nom, String type, float prix, int quantite, float prix_achat_gros, String path, String description) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.path = path;
        this.description = description;
    }

  

    public Produit(String nom, String type, float prix, int quantite, float prix_achat_gros, String path, String description, int boutique) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.path = path;
        this.description = description;
        this.boutique = boutique;
    }

    public Produit(String nom, String type, float prix, int quantite, float prix_achat_gros, String description) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.description = description;
    }
    

    public Produit(int id_produit, String nom, String type, float prix, int quantite, float prix_achat_gros, int nb_vente, String path, String description, int boutique) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.nb_vente = nb_vente;
        this.path = path;
        this.description = description;
        this.boutique = boutique;
    }

    public Produit(String nom, String type, float prix, int quantite, float prix_achat_gros, int nb_vente, String path, String description, int boutique) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.nb_vente = nb_vente;
        this.path = path;
        this.description = description;
        this.boutique = boutique;
    }

    public Produit(int id_produit, String nom, String type, float prix, int quantite, float prix_achat_gros, int nb_vente, String path, String description) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.nb_vente = nb_vente;
        this.path = path;
        this.description = description;
    }

    public Produit(String nom, String type, float prix, int quantite, float prix_achat_gros, int nb_vente, String path, String description) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prix_achat_gros = prix_achat_gros;
        this.nb_vente = nb_vente;
        this.path = path;
        this.description = description;
    }

   

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix_achat_gros() {
        return prix_achat_gros;
    }

    public void setPrix_achat_gros(float prix_achat_gros) {
        this.prix_achat_gros = prix_achat_gros;
    }

    public int getNb_vente() {
        return nb_vente;
    }

    public void setNb_vente(int nb_vente) {
        this.nb_vente = nb_vente;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBoutique() {
        return boutique;
    }

    public void setBoutique(int boutique ) {
        this.boutique = boutique;
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
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom=" + nom + ", type=" + type + ", prix=" + prix + ", quantite=" + quantite + ", prix_achat_gros=" + prix_achat_gros + ", nb_vente=" + nb_vente + ", path=" + path + ", description=" + description + ", boutique=" + boutique + '}';
    }
    
    
           
}
