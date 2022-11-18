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
@Table(name = "assurance")
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomPatient;
    private String prenomPatient;
    private String identitePatient;
    private Integer numAffilation;
    private String typePrestation;
    private Double montantConvention;
    private Date datePrestation;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date pastUpdatedAt ;

    @OneToMany(mappedBy = "assurance",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Paiement> paiements;

    public Assurance(String nomPatient, String prenomPatient, String identitePatient, Integer numAffilation,
                     String typePrestation, Double montantConvention, Date datePrestation,
                     Date postedAt, Date lastUpdatedAt, List<Paiement> paiements) {
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.identitePatient = identitePatient;
        this.numAffilation = numAffilation;
        this.typePrestation = typePrestation;
        this.montantConvention = montantConvention;
        this.datePrestation = datePrestation;

        this.paiements = paiements;
    }

    public void addPaiement(Paiement paiement) {
        paiements.add(paiement);
    }
    public void removePaiement(Paiement paiement) {
        paiements.remove(paiement);
    }



}
