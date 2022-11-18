package com.datajpa.relationship.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personnel")
public class PersonnelMedical extends Utilisateur{


    private String titre;

    private String fonction;

    private String service;


    @ManyToOne
    @JoinColumn(name = "accueil_id")
    private Accueil accueil;

    @ManyToOne
    @JoinColumn(name = "salle_attente_id")
    private SalleAttente salleAttente;



    @ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;



    @ManyToOne
    @JoinColumn(name = "service_consultation_id")
    private ServiceConsultation serviceConsultation;




}
