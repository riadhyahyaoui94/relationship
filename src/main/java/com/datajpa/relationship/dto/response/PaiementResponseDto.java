package com.datajpa.relationship.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
@Data
public class PaiementResponseDto {
    private Long id;
    private Integer codeFacture;
    private  String nomAssurance;
    private Double tauxCouverture;
    private String prestation;
    private Double montantPrestation;
    private Date datePaiement;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PostedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date LastUpdatedAt;
    private List<String> facturationNames;
    private String accueilName;
    private String assuranceName;
    private List<String> patientNames;
}
