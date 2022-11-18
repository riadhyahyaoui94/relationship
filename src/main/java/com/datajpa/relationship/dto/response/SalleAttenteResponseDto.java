package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.PersonnelMedical;
import com.datajpa.relationship.model.ServiceConsultation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class SalleAttenteResponseDto {
    private Long id;
    private  String nom;
    private  String etage;
    private Integer numOrdre;

    @JsonIgnoreProperties({"salleAttente","priseRDVS","consultants","dossierMedicals","personnelMedicals"})
    private List<ServiceConsultation> serviceConsultations;
    @JsonIgnoreProperties({"priseRDVs", "dossierMedicals","paiement","detailsPatient","salleAttente","consultations"})


    private List<Patient> patients;
    @JsonIgnoreProperties({"salleAttente","serviceConsultation","accueil"})

    private List<PersonnelMedical> personnelMedicals;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;



}
