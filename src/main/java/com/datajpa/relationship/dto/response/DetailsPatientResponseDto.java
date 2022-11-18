package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DetailsPatientResponseDto {
    private Long id;
    private String status;
    private String serviceAffectation;
    private String etatDuPatient;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date heurePriseCharge;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    @JsonIgnoreProperties({"priseRDVs", "dossierMedicals","paiement","detailsPatient","salleAttente","consultations"})
    private Patient patient;

}
