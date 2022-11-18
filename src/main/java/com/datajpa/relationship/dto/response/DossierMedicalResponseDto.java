package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.ServiceConsultation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class DossierMedicalResponseDto {
    private Long id;
    private String nomPatient;
    private String prenomPatient;
    private String consultationEffectuee;
    private String resultatPrestation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonIgnoreProperties({"salleAttente","priseRDVs","dossierMedicals","detailsPatient","paiement","consultations"})
    private Patient patient;
    @JsonIgnoreProperties({"priseRDVs","ordonnances","personnelMedicals","serviceConsultation","dossierMedicals","consultations"})
    private Consultant consultant;
    @JsonIgnoreProperties({"salleAttente","priseRDVS","consultants","dossierMedicals","personnelMedicals"})
    private ServiceConsultation serviceConsultation;
    @JsonIgnoreProperties({"patient","consultant","dossierMedical","ordonnance"})
    private List<Consultation> consultations;
}
