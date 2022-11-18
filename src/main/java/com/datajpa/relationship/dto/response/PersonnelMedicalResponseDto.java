package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PersonnelMedicalResponseDto {

    private Long id;

    private String nom;

    private String prenom;

    private String username;


    private String password;

    private String email;

    private String telephone;

    private String photo;

    private String titre;

    private String fonction;

    private String service;
    @JsonIgnoreProperties({"paiments","personnelMedicals"})

    private Accueil accueil;

    @JsonIgnoreProperties({"serviceConsultations","personnelMedicals","patients"})
    private SalleAttente salleAttente;

    private Paiement paiement;

    @JsonIgnoreProperties({"salleAttente","priseRDVS","consultants","dossierMedicals","personnelMedicals"})
    private ServiceConsultation serviceConsultation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;

    private List<String> roles;

}
