/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Amine
 */
public class Evenement {

    private int id_evenement;
    private String nom;
    private String description;
    private String date;
    private String path;
    private User user;

    public Evenement(int id_evenement, String nom, String description, String date, String path, User user) {
        this.id_evenement = id_evenement;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.path = path;
        this.user = user;
    }

    public Evenement(String nom, String description, String date, String path, User user) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.path = path;
        this.user = user;
    }

    public Evenement(String nom, String description, String date, String path) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.path = path;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        final Evenement other = (Evenement) obj;
        if (this.id_evenement != other.id_evenement) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", nom=" + nom + ", description=" + description + ", date=" + date + ", path=" + path + ", user=" + user + '}'+"\n";
    }

    public java.sql.Date convert(String date) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
        return sqlDate;
    }

    public static String convert(java.sql.Date d) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String text = df.format(d);
        return text;
    }

}
