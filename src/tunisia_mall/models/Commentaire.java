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
 * @author ahmed
 */
public class Commentaire {
    private int id_commentaire ; 
    private String contenu ; 
    private String date_commentaire ; 
    User user ; 
    Forum forum ;

    public Commentaire() {
    }

    public Commentaire(int id_commentaire, String contenu, String date_commentaire, User user, Forum forum) {
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
        this.date_commentaire = date_commentaire;
        this.user = user;
        this.forum = forum;
    }

    public Commentaire(String contenu, String date_commentaire, User user, Forum forum) {
        this.contenu = contenu;
        this.date_commentaire = date_commentaire;
        this.user = user;
        this.forum = forum;
    }

    
    public Commentaire(int id_commentaire, String contenu, String date_commentaire, User user) {
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
        this.date_commentaire = date_commentaire;
        this.user = user;
    }

    public Commentaire(String contenu, String date_commentaire, User user) {
        this.contenu = contenu;
        this.date_commentaire = date_commentaire;
        this.user = user;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(String date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", contenu=" + contenu + ", date_commentaire=" + date_commentaire + ", user=" + user + '}';
    }
 
    
    public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        return sqlDate;
    }

    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }
    
}
