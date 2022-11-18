package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.model.Ordonnance;
import com.datajpa.relationship.model.PriseRDV;
import com.datajpa.relationship.model.ServiceConsultation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class ConsultantResponseDto {
    private Long id;

    private String nom;

    private String prenom;

    private String username;


    private String password;

    private String email;

    private String telephone;

    private String photo;



    private Calendar calendrier;

    private String titre;

    private String fonction;

    @JsonIgnoreProperties({"consultant","patient","serviceConsultation","consultations"})
    private List<DossierMedical> dossierMedicals;

    @JsonIgnoreProperties({"consultant","medicaments","examens","consultation"})

    private List<Ordonnance> ordonnances ;

    @JsonIgnoreProperties({"salleAttente","priseRDVS","consultants","dossierMedicals","personnelMedicals"})
    private ServiceConsultation serviceConsultation;

    @JsonIgnoreProperties({"examen","consultant","patient","serviceConsultation","facturation"})

    private List<PriseRDV> priseRDVs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;

    private List<String> roles;

}
