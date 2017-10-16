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
public class JardinEnfant {
    private int id_jardinenfant;
    private final int nb_place_total=50;
    private int nb_place_libre;
    private String date_reservation;
    private User user;

    public JardinEnfant(int id_jardinenfant, int nb_place_libre, String date_reservation, User user) {
        this.id_jardinenfant = id_jardinenfant;
        this.nb_place_libre = nb_place_libre;
        this.date_reservation = date_reservation;
        this.user = user;
    }

    public JardinEnfant(int nb_place_libre, String date_reservation, User user) {
        this.nb_place_libre = nb_place_libre;
        this.date_reservation = date_reservation;
        this.user = user;
    }

    public JardinEnfant(int nb_place_libre, String date_reservation) {
        this.nb_place_libre = nb_place_libre;
        this.date_reservation = date_reservation;
    }

    public int getId_jardinenfant() {
        return id_jardinenfant;
    }

    public void setId_jardinenfant(int id_jardinenfant) {
        this.id_jardinenfant = id_jardinenfant;
    }

    public int getNb_place_libre() {
        return nb_place_libre;
    }

    public void setNb_place_libre(int nb_place_libre) {
        this.nb_place_libre = nb_place_libre;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        final JardinEnfant other = (JardinEnfant) obj;
        if (this.id_jardinenfant != other.id_jardinenfant) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JardinEnfant{" + "id_jardinenfant=" + id_jardinenfant + ", nb_place_total=" + nb_place_total + ", nb_place_libre=" + nb_place_libre + ", date_reservation=" + date_reservation + ", user=" + user + '}';
    }
    
    
}
