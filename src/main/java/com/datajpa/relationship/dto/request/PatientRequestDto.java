package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
@Data

public class PatientRequestDto {
    private String nom;
    private String prenom;
    private String genre;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateNaissance;
    private Integer numIdendite;
    private String photo;
    private String domicile;
    private Boolean estAssure;
    private String  assurance;
    private Double tauxAssurance;
    private String email;
    private String numAffeliation;
    private Long paiementId;
    private Long salleAttenteId;
    private Long seviceConsultationId;
    private Long detailPatientId;


    public PatientRequestDto(String nom, String prenom, String genre, Date dateNaissance, Integer numIdendite, String photo, String domicile, Boolean estAssure, String assurance, Double tauxAssurance, String email, Long paiementId, Long salleAttenteId, Long seviceConsultationId, Long detailPatientId) {
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
        this.paiementId = paiementId;
        this.salleAttenteId = salleAttenteId;
        this.seviceConsultationId = seviceConsultationId;
        this.detailPatientId = detailPatientId;
    }



}
