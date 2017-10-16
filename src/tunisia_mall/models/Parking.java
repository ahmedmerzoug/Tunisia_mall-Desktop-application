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
public class Parking {

    private int id_parking;
    private int nb_place_total;
    private final int nb_place_libre=50;
    private String date_debut;
    private String date_fin;

    public Parking(int id_parking, int nb_place_total, String date_debut, String date_fin) {
        this.id_parking = id_parking;
        this.nb_place_total = nb_place_total;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Parking(int nb_place_total, String date_debut, String date_fin) {
        this.nb_place_total = nb_place_total;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_parking() {
        return id_parking;
    }

    public void setId_parking(int id_parking) {
        this.id_parking = id_parking;
    }

    public int getNb_place_total() {
        return nb_place_total;
    }

    public void setNb_place_total(int nb_place_total) {
        this.nb_place_total = nb_place_total;
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
        final Parking other = (Parking) obj;
        if (this.id_parking != other.id_parking) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parking{" + "id_parking=" + id_parking + ", nb_place_total=" + nb_place_total + ", nb_place_libre=" + nb_place_libre + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    

    
}
