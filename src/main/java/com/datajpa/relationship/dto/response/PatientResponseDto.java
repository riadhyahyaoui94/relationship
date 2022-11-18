package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class PatientResponseDto {


    private Long id;
    private String nom;
    private String prenom;
    private String genre;

    //@Temporal(TemporalType.DATE)

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    @JsonIgnoreProperties({"consultant","patient","serviceConsultation","consultations"})

    private List<DossierMedical> dossierMedical;

    @JsonIgnoreProperties({"examen","consultant","patient","serviceConsultation","facturation"})

    private List<PriseRDV> priseRDVs;
    private Paiement paiement;
    @JsonIgnoreProperties({"serviceConsultations","personnelMedicals","patients"})
    private SalleAttente salleAttente;

    @JsonIgnoreProperties({"patient"})
    private DetailsPatient detailsPatient;

    public PatientResponseDto(Long id, String nom, String prenom, String genre, Date dateNaissance, Integer numIdendite, String photo, String domicile, Boolean estAssure, String assurance, Double tauxAssurance, String email) {
        this.id = id;
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
    }
}
