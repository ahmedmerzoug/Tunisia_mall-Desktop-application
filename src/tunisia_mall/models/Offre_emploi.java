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
 * @author Amine
 */
public class Offre_emploi {
    
    private int id_offre;
    private User user;
    private Boutique boutique;
    private String poste;
    private String specialite;
    private float salaire;
    private int nbr_demandé;
    private Date date_expiration;
   // private int id_boutique;

    public Offre_emploi() {
    }

    public Offre_emploi(int id_offre, User user, Boutique boutique, String poste, String specialite, float salaire, int nbr_demandé, Date date_expiration) {
        this.id_offre = id_offre;
        this.user = user;
        this.boutique = boutique;
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbr_demandé = nbr_demandé;
        this.date_expiration = date_expiration;
    }

    public Offre_emploi(int id_offre, User user, Boutique boutique, String poste, String specialite, float salaire, int nbr_demandé) {
        this.id_offre = id_offre;
        this.user = user;
        this.boutique = boutique;
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbr_demandé = nbr_demandé;
    }

    public Offre_emploi(Boutique boutique) {
        this.boutique = boutique;
    }

 
    

  /*  public Offre_emploi(int id_boutique,String poste, String specialite, float salaire, int nbr_demandé, Date date_expiration) {
        this.id_boutique = id_boutique;
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbr_demandé = nbr_demandé;
        this.date_expiration = date_expiration;
        
    }*/

    public Offre_emploi(int id_offre,Boutique boutique, String poste , String specialite,float salaire, int nbr_demandé, Date date_expiration) {
        this.id_offre=id_offre;
        this.boutique = boutique;
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbr_demandé = nbr_demandé;
        this.date_expiration = date_expiration;
        
       
    }
    
    
    
    
    

    public int getId_offre() {
        return id_offre;
    }

    public User getUser() {
        return user;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public String getPoste() {
        return poste;
    }

    public String getSpecialite() {
        return specialite;
    }

    public float getSalaire() {
        return salaire;
    }

    public int getNbr_demandé() {
        return nbr_demandé;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public void setNbr_demandé(int nbr_demandé) {
        this.nbr_demandé = nbr_demandé;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    @Override
    public String toString() {
        return "Offre_emploi{" + "id=" + id_offre + ", user=" + user + ", boutique=" + boutique + ", poste=" + poste + ", specialite=" + specialite + ", salaire=" + salaire + ", nbr_demand\u00e9=" + nbr_demandé + ", date_expiration=" + date_expiration + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Offre_emploi) {
            final Offre_emploi a = (Offre_emploi) o;
            return id_offre == a.id_offre ;
        }
        return false;
    }
  
    
   /*public java.sql.Date convert (String date) throws ParseException{
      
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date1 = sdf1.parse(date);
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
   
    
    return sqlDate ;  
    } */
 
}
