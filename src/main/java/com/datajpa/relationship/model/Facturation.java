package com.datajpa.relationship.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "facturation")
public class Facturation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String codeFacture;

    private Double montantAPayer;

    private Double montantPaye;

    private String typePaiement;
    private String nomAgent;


    private Date dateFacture;

    private Double sommeRecue;

    private Double sommeRendue;

    @CreationTimestamp
    private Date postedAt;

    @UpdateTimestamp
    private Date lastUpdatedAt;


     @ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;

    @ManyToOne
    private Examen examen ;

    @OneToOne
    PriseRDV priseRDV;

   /* public Facturation(List<Prestation> prestations) {
        this.prestations = prestations;
    }*/

    public Facturation(String codeFacture, Double montantAPayer, Double montantPaye,
                                        String typePaiement, Date dateFacture, Double sommeRecue,
                       Double sommeRendue, Date postedAt, Date lastUpdatedAt) {
        this.codeFacture = codeFacture;
        this.montantAPayer = montantAPayer;
        this.montantPaye = montantPaye;
        this.typePaiement = typePaiement;
        this.dateFacture = dateFacture;
        this.sommeRecue = sommeRecue;
        this.sommeRendue = sommeRendue;
        /*PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;*/

        }
    /*public void addPrestation(Prestation prestation){prestations.add(prestation);}
    public void removePrestation(Prestation prestation){prestations.remove(prestation);}*/

}
