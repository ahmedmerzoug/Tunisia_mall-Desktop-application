/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

/**
 *
 * @author ahmed
 */
public class Boutique {
    private int id_boutique ; 
    private String nom ; 
    private String type ; 
    private String position ; 
   

    public Boutique() {
    }
    

    public Boutique(int id_boutique, String nom, String type, String position) {
        this.id_boutique = id_boutique;
        this.nom = nom;
        this.type = type;
        this.position = position;
       
    }

    public Boutique(String nom, String type, String position) {
        this.nom = nom;
        this.type = type;
        this.position = position;
        
    }

    public Boutique(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    public int getId_boutique() {
        return id_boutique;
    }

    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        final Boutique other = (Boutique) obj;
        return this.id_boutique == other.id_boutique;
    }

    @Override
    public String toString() {
        return "Boutique{" + "id_boutique=" + id_boutique + ", nom=" + nom + ", type=" + type + ", position=" + position + '}'+"\n";
    }
    
    
    
    
}
