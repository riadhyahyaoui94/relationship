package com.datajpa.relationship.model;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer codeFacture;

    private  String nomAssurance;
    private Double tauxCouverture;

      private String prestation;

    private Double montantPrestation;

    private Date datePaiement;

    @CreationTimestamp
    private Date postedAt;

    @UpdateTimestamp
    private Date lastUpdatedAt;

    public Paiement(Integer codeFacture, String nomAssurance, Double tauxCouverture) {
        this.codeFacture = codeFacture;
        this.nomAssurance = nomAssurance;
        this.tauxCouverture = tauxCouverture;
    }

    @OneToMany(mappedBy = "paiement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Patient> patients ;

    public void addPatient(Patient patient){patients.add(patient);}
    public void removePatient(Patient patient){patients.remove(patient);}
      @ManyToOne
    @JoinColumn(name = "accueil_id")
    private Accueil accueil;

    @ManyToOne
    @JoinColumn(name = "assurance_id")
    private Assurance assurance;

    public Paiement(List<Patient> patients, Accueil accueil, Assurance assurance, List<Facturation> facturations) {
        this.patients = patients;
        this.accueil = accueil;
        this.assurance = assurance;
        this.facturations = facturations;
    }
    @OneToMany(mappedBy = "paiement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Facturation> facturations ;

    public void addFacturation(Facturation facturation){facturations.add(facturation);}
    public void removeFacturation(Facturation facturation){facturations.remove(facturation);}

    @OneToMany(mappedBy = "paiement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PersonnelMedical> personnelMedicals ;
    public void addPersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.add(personnelMedical);
    }
    public void removePersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.remove(personnelMedical);
    }

    public Paiement( Integer codeFacture, String prestation,
                    Float montantPrestation, Date datePaiement, Date postedAt, Date lastUpdatedAt,
                    Accueil accueil, Assurance assurance) {

        this.codeFacture = codeFacture;
        this.prestation = prestation;
        //this.montantPrestation = montantPrestation;
        this.datePaiement = datePaiement;

        this.accueil = accueil;
        this.assurance = assurance;

    }
}
