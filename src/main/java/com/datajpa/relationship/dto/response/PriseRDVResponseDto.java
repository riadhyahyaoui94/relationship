package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
public class PriseRDVResponseDto {
    private Long id;
    private String nomPatient;
    private String prenomPatient;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateRDV;
    private Date heureRDV;

    private String motif;
    private Boolean payed;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt ;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonIgnoreProperties({"salleAttente","priseRDVS","consultants","dossierMedicals","personnelMedicals"})
    private ServiceConsultation serviceConsultation;
    @JsonIgnoreProperties({"priseRDVs", "dossierMedicals","paiement","detailsPatient","salleAttente","consultations"})
    private Patient patient;
    @JsonIgnoreProperties({"priseRDVs", "dossierMedicals","serviceConsultation","ordonnances","consultations","roles","password","username"})
    private Consultant consultant;
    @JsonIgnoreProperties({"ordonnances","priseRDVs","facturations"})
    private Examen examen;
    @JsonIgnoreProperties({"priseRDV","examen"})
    private Facturation facturation;

}
