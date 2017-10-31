/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ahmed
 */
public class Forum {
    private int id_topic;
    private String categorie ; 
    private String description;
    private String datetopic ; 
    private User user;

    public Forum(int id_topic, String categorie, String description, String datetopic, User user) {
        this.id_topic = id_topic;
        this.categorie = categorie;
        this.description = description;
        this.datetopic = datetopic;
        this.user = user;
    }

 
  
    public String getDatetopic() {
        return datetopic;
    }

    public void setDatetopic(String datetopic) {
        this.datetopic = datetopic;
        
    }


    public Forum(String categorie, String description) {
        this.categorie = categorie;
        this.description = description;
    }

    
    public Forum(int id_topic, String categorie, String description, User user) {
        this.id_topic = id_topic;
        this.categorie = categorie;
        this.description = description;
        this.user = user;
    }

    public Forum(String categorie, String description, String datetopic, User user) {
        this.categorie = categorie;
        this.description = description;
        this.datetopic = datetopic;
        this.user = user;
    }

    public Forum(String categorie, String description, User user) {
        this.categorie = categorie;
        this.description = description;
        this.user = user;
    }

    public Forum() {
    }

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Forum{" + "id_topic=" + id_topic + ", categorie=" + categorie + ", description=" + description + ", datetopic=" + datetopic + ", user=" + user + '}';
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
        final Forum other = (Forum) obj;
        if (this.id_topic != other.id_topic) {
            return false;
        }
        return true;
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
    


