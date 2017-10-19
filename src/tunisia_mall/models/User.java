/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
/**
 *
 * @author ahmed
 */
public class User {
    private  int id_user ; 
    private  String nom ; 
    private  String prenom ; 
    private String date_naissance ;
    private String sexe ; 
    private String login ; 
    private String password ; 
    private String mail ; 
    private String role ; 
    private int numero_telephone ; 
    private String adresse ; 
    private float salaire ; 
    private String date_embauche ; 
    private String date_expiration ; 
    private String path ; 
    private Boutique boutique ; 

    public User() {
    }

 
    
    

    public User(String nom, String prenom, String date_naissance, String sexe ,String login, String password, String mail, String role, int numero_telephone, String adresse, float salaire, String date_embauche, String date_expiration, String path) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.sexe =sexe;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.numero_telephone = numero_telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date_embauche = date_embauche;
        this.date_expiration = date_expiration;
        this.path = path;
    }

    public User(int id_user, String nom, String prenom, String date_naissance,String sexe, String login, String password, String mail, String role, int numero_telephone, String adresse, float salaire, String date_embauche, String date_expiration, String path) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.sexe =sexe;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.numero_telephone = numero_telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date_embauche = date_embauche;
        this.date_expiration = date_expiration;
        this.path = path;
    }

    public User(int id_user, String nom, String prenom, String date_naissance, String login, String password, String mail, String role, int numero_telephone, String adresse, float salaire, String date_embauche, String date_expiration, String path, Boutique boutique) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.numero_telephone = numero_telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date_embauche = date_embauche;
        this.date_expiration = date_expiration;
        this.path = path;
        this.boutique = boutique;
    }

    public User(String nom, String prenom, String date_naissance, String login, String password, String mail, String role, int numero_telephone, String adresse, float salaire, String date_embauche, String date_expiration, String path, Boutique boutique) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.numero_telephone = numero_telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date_embauche = date_embauche;
        this.date_expiration = date_expiration;
        this.path = path;
        this.boutique = boutique;
    }

    public User( String nom, String prenom, String date_naissance, String sexe, String login, String password, String mail, String role, int numero_telephone, String adresse, float salaire, String date_embauche, String date_expiration, String path, Boutique boutique) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.sexe = sexe;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.numero_telephone = numero_telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date_embauche = date_embauche;
        this.date_expiration = date_expiration;
        this.path = path;
        this.boutique = boutique;
    }

    public User(int id_user, String nom, String prenom, String date_naissance, String sexe, String login, String password, String mail, String role, int numero_telephone, String adresse, float salaire, String date_embauche, String date_expiration, String path, Boutique boutique) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.sexe = sexe;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.numero_telephone = numero_telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date_embauche = date_embauche;
        this.date_expiration = date_expiration;
        this.path = path;
        this.boutique = boutique;
    }

    public User(int aInt, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(int numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        return Objects.equals(this.login, other.login);
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", sexe=" + sexe + ", login=" + login + ", password=" + password + ", mail=" + mail + ", role=" + role + ", numero_telephone=" + numero_telephone + ", adresse=" + adresse + ", salaire=" + salaire + ", date_embauche=" + date_embauche + ", date_expiration=" + date_expiration + ", path=" + path + ", boutique=" + boutique + '}';
    }

    


    public java.sql.Date convert (String date) throws ParseException{
      
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date1 = sdf1.parse(date);
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
   
    
    return sqlDate ;  
    } 
    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String text = df.format(d);
        return text;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(String date_embauche) {
        this.date_embauche = date_embauche;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

  

   
    
    
   
    
    
    
    
}
