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
public class OffreEmploi {
    private int id_offre_emploi;
    private String poste;
    private String specialite;
    private float salaire;
    private int nbr_demande;
    private String date_expiration;

    public OffreEmploi(int id_offre_emploi, String poste, String specialite, float salaire, int nbr_demande, String date_expiration) {
        this.id_offre_emploi = id_offre_emploi;
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbr_demande = nbr_demande;
        this.date_expiration = date_expiration;
    }

    public OffreEmploi(String poste, String specialite, float salaire, int nbr_demande, String date_expiration) {
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbr_demande = nbr_demande;
        this.date_expiration = date_expiration;
    }

    public int getId_offre_emploi() {
        return id_offre_emploi;
    }

    public void setId_offre_emploi(int id_offre_emploi) {
        this.id_offre_emploi = id_offre_emploi;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public int getNbr_demande() {
        return nbr_demande;
    }

    public void setNbr_demande(int nbr_demande) {
        this.nbr_demande = nbr_demande;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
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
        final OffreEmploi other = (OffreEmploi) obj;
        if (this.id_offre_emploi != other.id_offre_emploi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OffreEmploi{" + "id_offre_emploi=" + id_offre_emploi + ", poste=" + poste + ", specialite=" + specialite + ", salaire=" + salaire + ", nbr_demande=" + nbr_demande + ", date_expiration=" + date_expiration + '}';
    }
    
    
    
}
