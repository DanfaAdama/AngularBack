package cours.projetjee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Custumer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String code;
    @Column(length = 30)
    private String nom;
    @Column(length = 50)
    private String prenom;
    @Column(length = 20)
    private String tel;

    @Column(length = 200)
    private String adresse;

    @Column(length = 30)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date datenaissance;

    @Column(length = 50)
    private String cni;

    @Column(length = 30)
    private String sexe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }


    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Custumer(String code, String nom, String prenom, String tel, String adresse, String email, Date datenaissance, String CNI, String sexe) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.email = email;
        this.datenaissance = datenaissance;
        this.cni = CNI;
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "Custumer{" +
                "code='" + code + '\'' +
                '}';
    }

    public Custumer() {
    }


}
