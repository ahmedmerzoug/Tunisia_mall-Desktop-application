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
public class Publicite {

    private int id_pub;
    private String date_debut;
    private String date_fin;
    private float prix;
    private String page;
    private String path;
    private Boutique boutique;

    public Publicite(int id_pub, String date_debut, String date_fin, float prix, String page, String path, Boutique boutique) {
        this.id_pub = id_pub;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.page = page;
        this.path = path;
        this.boutique = boutique;
    }

    public Publicite(String date_debut, String date_fin, float prix, String page, String path, Boutique boutique) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.page = page;
        this.path = path;
        this.boutique = boutique;
    }

    public Publicite(int id_pub, String date_debut, String date_fin, float prix, String page, String path) {
        this.id_pub = id_pub;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.page = page;
        this.path = path;
    }

    public Publicite(String date_debut, String date_fin, float prix, String page, String path) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.page = page;
        this.path = path;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
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
        final Publicite other = (Publicite) obj;
        if (this.id_pub != other.id_pub) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publicite{" + "id_pub=" + id_pub + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + ", page=" + page + ", path=" + path + ", boutique=" + boutique + '}';
    }
    
    

}
