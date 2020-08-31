package cours.projetjee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produit  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String libelle;
    @Column(length = 30)
    private String etat;
    @Column(length = 30)

    private int prixNormal;

    @Column(length = 30)
    private int prixPromotion;
    private  int quantite;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @JsonIgnore
    @OneToMany(mappedBy = "produit")
    private Set<Stock> stocks = new HashSet<>();

    @Column(length = 1000000)
    private String photoName;

    private int id_categorie;


    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public int getPrixNormal() {
        return prixNormal;
    }

    public void setPrixNormal(int prixNormal) {
        this.prixNormal = prixNormal;
    }

    public int getPrixPromotion() {
        return prixPromotion;
    }

    public void setPrixPromotion(int prixPromotion) {
        this.prixPromotion = prixPromotion;
    }

    public Produit() {
    }
}
