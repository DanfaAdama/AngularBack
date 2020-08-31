package cours.projetjee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15)
    private int quantiteStock;
    @Column(length = 30)
    private int quantiteSeuil;
    @Column(length = 30)
    private int prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private int id_produit;

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public int getQuantiteSeuil() {
        return quantiteSeuil;
    }

    public void setQuantiteSeuil(int quantiteSeuil) {
        this.quantiteSeuil = quantiteSeuil;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Stock() {
    }
}
