package com.datajpa.relationship.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Accueil")
public class Accueil {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
     private Boolean nouveauPatient;
     @CreationTimestamp
    private Date postedAt;
     @UpdateTimestamp
    private Date lastUpdatedAt;


       @OneToMany(mappedBy = "accueil",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Paiement> paiements ;

    @OneToMany(mappedBy = "accueil",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PersonnelMedical> personnelMedicals  ;

    /*public void addSalleAttente(SalleAttente salleAttente) {
        salleAttentes.add(salleAttente);
    }
    public void removeSalleAttente(SalleAttente salleAttente) {
        salleAttentes.remove(salleAttente);
    }*/
    public void addPaiement(Paiement paiement) {
        paiements.add(paiement);
    }
    public void removePaiement(Paiement paiement) {
        paiements.remove(paiement);
    }

    public void addPersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.add(personnelMedical);
    }
    public void removePersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.remove(personnelMedical);
    }

    public Accueil(List<PersonnelMedical> personnelMedicals) {
        this.personnelMedicals = personnelMedicals;
    }

    public Accueil(String nom, Boolean nouveauPatient, Date postedAt, Date lastUpdatedAt, List<Paiement> paiements) {
        this.nom = nom;
        this.nouveauPatient = nouveauPatient;

        this.paiements = paiements;
    }
}
