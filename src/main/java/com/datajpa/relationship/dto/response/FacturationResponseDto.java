package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.PriseRDV;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class FacturationResponseDto {
    private Long id;
    private String nomPatient;
    private String codeFacture;
    private Double montantAPayer;
    private Double montantPaye;
    private String typePaiement;
    private Date dateFacture;
    private Double sommeRecue;
    private Double sommeRendue;
    private String nomAgent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    @JsonIgnoreProperties({"ordonnances","priseRDVs","facturations"})
    private Examen examen;

    @JsonIgnoreProperties({"examen","consultant","patient","serviceConsultation","facturation"})
    private PriseRDV priseRDV;


    private String paiementName;

}
