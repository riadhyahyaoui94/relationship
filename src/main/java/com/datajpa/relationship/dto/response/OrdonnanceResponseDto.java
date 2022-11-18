package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Medicament;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class OrdonnanceResponseDto {
    public Long Id;

    public String nomPatient;
    private Date  dateOrdonnance;
    private String natureOrdonnance;

    private Date postedAt;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonIgnoreProperties({"ordonnances","priseRDVs","facturations"})

    private List<Examen>examens;
    @JsonIgnoreProperties({"ordonnances"})

    private List<Medicament> medicaments;
    @JsonIgnoreProperties({"priseRDVs", "dossierMedicals","serviceConsultation","ordonnances","consultations","roles","password","username"})
    private Consultant consultant;
    @JsonIgnoreProperties({"patient","consultant","dossierMedical","ordonnance"})

    private Consultation consultation;


}
