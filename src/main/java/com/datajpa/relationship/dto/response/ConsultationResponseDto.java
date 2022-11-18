package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
public class ConsultationResponseDto {
    private Long id;

    private Date dateConsultation;

    private String typeConsultation;

    private String diagnostic;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonIgnoreProperties({"priseRDVs","ordonnances","personnelMedicals","serviceConsultation","dossierMedicals","consultations"})

    private Consultant consultant;
    @JsonIgnoreProperties({"salleAttente","priseRDVs","dossierMedicals","detailsPatient","paiement","consultations"})

    private Patient patient;
    @JsonIgnoreProperties({"consultant","patient","serviceConsultation","consultations"})

    private DossierMedical dossierMedical;
    @JsonIgnoreProperties({"consultant","medicaments","examens","consultation"})

    private Ordonnance ordonnance;

}
