/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

import java.util.Objects;

/**
 *
 * @author Amine
 */
public class Reclamation {

    private int id_reclamation;
    private String type;
    private String text;
    private User userreclamant;
    private User userreclame;

    public Reclamation(int id_reclamation, String type, String text, User userreclamant, User userreclame) {
        this.id_reclamation = id_reclamation;
        this.type = type;
        this.text = text;
        this.userreclamant = userreclamant;
        this.userreclame = userreclame;
    }

    public Reclamation(String type, String text, User userreclamant, User userreclame) {
        this.type = type;
        this.text = text;
        this.userreclamant = userreclamant;
        this.userreclame = userreclame;
    }

    public Reclamation(int id_reclamation, String type, String text) {
        this.id_reclamation = id_reclamation;
        this.type = type;
        this.text = text;
    }

    public Reclamation(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUserreclamant() {
        return userreclamant;
    }

    public void setUserreclamant(User userreclamant) {
        this.userreclamant = userreclamant;
    }

    public User getUserreclame() {
        return userreclame;
    }

    public void setUserreclame(User userreclame) {
        this.userreclame = userreclame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_reclamation;
        hash = 53 * hash + Objects.hashCode(this.type);
        hash = 53 * hash + Objects.hashCode(this.text);
        hash = 53 * hash + Objects.hashCode(this.userreclamant);
        hash = 53 * hash + Objects.hashCode(this.userreclame);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id_reclamation != other.id_reclamation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", type=" + type + ", text=" + text + ", userreclamant=" + userreclamant + ", userreclame=" + userreclame + '}';
    }

}
