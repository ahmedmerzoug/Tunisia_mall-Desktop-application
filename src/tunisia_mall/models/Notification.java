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
public class Notification {
    private int id_notification;
    private String description;

    public Notification(int id_notification, String description) {
        this.id_notification = id_notification;
        this.description = description;
    }

    public Notification(String description) {
        this.description = description;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        final Notification other = (Notification) obj;
        if (this.id_notification != other.id_notification) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notification{" + "id_notification=" + id_notification + ", description=" + description + '}';
    }
    
    
}
