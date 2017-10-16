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
public class Forum {
    private int id_forum;
    private String description;
    private User user;

    public Forum(int id_forum, String description, User user) {
        this.id_forum = id_forum;
        this.description = description;
        this.user = user;
    }

    public Forum(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Forum(String description) {
        this.description = description;
    }

    public int getId_forum() {
        return id_forum;
    }

    public void setId_forum(int id_forum) {
        this.id_forum = id_forum;
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
        if (this.id_forum != other.id_forum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Forum{" + "id_forum=" + id_forum + ", description=" + description + ", user=" + user + '}';
    }
    
    
}
