package cours.projetjee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String numero;
    @Column()
    private Float avance;
    @Column(length = 30)
    private Double montant;

    private LocalDate dateLivraison;

    public Float getAvance() {
        return avance;
    }

    public void setAvance(Float avance) {
        this.avance = avance;
    }

    private LocalDate datecommande;

    @Column(length = 30)
    private String etat;

    @OneToMany(mappedBy="commande")
    private Set<DetailCommande> details_commandes = new HashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Facture> factures = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "custumer_id")
    private User user ;
    private Long id_user;

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Set<DetailCommande> getDetails_commandes() {
        return details_commandes;
    }

    public void setDetails_commandes(Set<DetailCommande> details_commandes) {
        this.details_commandes = details_commandes;
    }

    public Set<Facture> getFactures() {
        return factures;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public LocalDate getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(LocalDate datecommande) {
        this.datecommande = datecommande;
    }

    public Commande() {
    }
}
