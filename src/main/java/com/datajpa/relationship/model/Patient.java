package com.datajpa.relationship.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String genre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private Integer numIdendite;
    private String photo;
    private String domicile;
    private Boolean estAssure;
    private String  assurance;
    private Double tauxAssurance;

    private String email;
    private String numAffeliation;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;

    @OneToOne
    private DetailsPatient detailsPatient;

    public Patient(DetailsPatient detailsPatient) {
        this.detailsPatient = detailsPatient;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DossierMedical> dossierMedicals = new ArrayList<>();

    public void addDossierMedical(DossierMedical dossierMedical){
        dossierMedicals.add(dossierMedical);
    }
    public void removeDossierMedical(DossierMedical dossierMedical){
        dossierMedicals.remove(dossierMedical);
    }


    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PriseRDV> priseRDVs;
    public void addPriseRDV(PriseRDV priseRDV) {
        priseRDVs.add(priseRDV);
    }
    public void removePriseRDV(PriseRDV priseRDV) {
        priseRDVs.remove(priseRDV);
    }


    @ManyToOne
    @JoinColumn(name = "salle_attente_id")
    private SalleAttente salleAttente;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations ;

    public Patient(String nom, String prenom, String genre, Date dateNaissance, Integer numIdendite,
        String photo, String domicile, Boolean estAssure, String assurance, Double tauxAssurance,
                   String email,/* Date postedAt, Date lastUpdatedAt,*/
                   List<DossierMedical> dossierMedicals, List<PriseRDV> priseRDVs, SalleAttente salleAttente) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.numIdendite = numIdendite;
        this.photo = photo;
        this.domicile = domicile;
        this.estAssure = estAssure;
        this.assurance = assurance;
        this.tauxAssurance = tauxAssurance;
        this.email = email;
       /* PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;*/
        this.dossierMedicals = dossierMedicals;
        this.priseRDVs = priseRDVs;
        this.salleAttente = salleAttente;
    }
}
