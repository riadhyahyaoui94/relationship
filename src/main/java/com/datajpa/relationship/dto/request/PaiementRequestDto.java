package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.text.DecimalFormat;
import java.util.Date;
@Data
public class PaiementRequestDto {

    private Integer codeFacture;

    private  String nomAssurance;
    private Double tauxCouverture;

    private String prestation;

    private Double montantPrestation;
    private Date datePaiement;
    private Date PostedAt = new Date() ;
    private Date LastUpdatedAt = new Date();
    private Long accueilId;
    private Long assuranceId;
}
