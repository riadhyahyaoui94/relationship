package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ServiceConsultationResponseDto {
    private Long id;
    private String nomService;
    private String typeService;
    @JsonIgnoreProperties({"serviceConsultations","personnelMedicals","patients"})
    private SalleAttente salleAttente;
    @JsonIgnoreProperties({"priseRDVs","consultants","dossierMedicals","personnelMedicals","serviceConsultation","consultations","ordonnances"})
    private List<Consultant> consultants;

    @JsonIgnoreProperties({"patient","consultant","serviceConsultation","examen","facturation"})
    private List<PriseRDV> priseRDVS;
    @JsonIgnoreProperties({"consultant","patient","serviceConsultation","consultations"})

    private List<DossierMedical> dossierMedicals;
    @JsonIgnoreProperties({"salleAttente","dossierMedicals","serviceConsultation","accueil","consultations"})

    private List<PersonnelMedical> personnelMedicals;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

}
