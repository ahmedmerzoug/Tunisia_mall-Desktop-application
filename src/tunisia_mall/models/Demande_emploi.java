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
 * @author Amine
 */
public class Demande_emploi {

    private int id_demande;
    private User user;
    private Offre_emploi offre_emploi;
    private String nom_emp;
    private String prenom_emp;
    private String date_naissance;
    private String adresse;
    private String sexe;
    private String email;
    private String num_tel;
    private String qualification;
    private int experience;

    public Demande_emploi() {
    }

    public Demande_emploi(int id_demande, User user, Offre_emploi offre_emploi, String nom_emp, String prenom_emp, String date_naissance, String adresse, String sexe, String email, String num_tel, String qualification, int experience) {
        this.id_demande = id_demande;
        this.user = user;
        this.offre_emploi = offre_emploi;
        this.nom_emp = nom_emp;
        this.prenom_emp = prenom_emp;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.sexe = sexe;
        this.email = email;
        this.num_tel = num_tel;
        this.qualification = qualification;
        this.experience = experience;
    }

    public int getId_demande() {
        return id_demande;
    }

    public User getUser() {
        return user;
    }

    public Offre_emploi getOffre_emploi() {
        return offre_emploi;
    }

    public String getNom_emp() {
        return nom_emp;
    }

    public String getPrenom_emp() {
        return prenom_emp;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public String getEmail() {
        return email;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getQualification() {
        return qualification;
    }

    public int getExperience() {
        return experience;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOffre_emploi(Offre_emploi offre_emploi) {
        this.offre_emploi = offre_emploi;
    }

    public void setNom_emp(String nom_emp) {
        this.nom_emp = nom_emp;
    }

    public void setPrenom_emp(String prenom_emp) {
        this.prenom_emp = prenom_emp;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Demande_emploi{" + "id_demande=" + id_demande + ", user=" + user + ", offre_emploi=" + offre_emploi + ", nom_emp=" + nom_emp + ", prenom_emp=" + prenom_emp + ", date_naissance=" + date_naissance + ", adresse=" + adresse + ", sexe=" + sexe + ", email=" + email + ", num_tel=" + num_tel + ", qualification=" + qualification + ", experience=" + experience + '}';
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
        final Demande_emploi other = (Demande_emploi) obj;
        if (this.id_demande != other.id_demande) {
            return false;
        }
        return true;
    }
    
    public java.sql.Date convert (String date) throws ParseException{
      
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date1 = sdf1.parse(date);
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
   
    
    return sqlDate ;  
    } 
    
}

