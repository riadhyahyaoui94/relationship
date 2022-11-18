package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class PriseRDVRequestDto {
    private String nomPatient;
    private String prenomPatient;
    private Date dateRDV;
    private Date heureRDV;

    private String motif;
    private Boolean payed;
    private Date postedAt  ;
    private Date lastUpdatedAt ;
    private Long serviceconsultationId;
    private Long patientId;
    private Long consultantId;
    private Long examenId;
}
