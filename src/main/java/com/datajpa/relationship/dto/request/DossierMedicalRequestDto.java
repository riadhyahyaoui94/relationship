package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class DossierMedicalRequestDto {
    private String nomPatient;
    private String prenomPatient;
    private String consultationEffectuee;
    private String resultatPrestation;
    private Date postedAt;
    private Date lastUpdatedAt;
    private Long serviceconsultationId;
    private Long consultantId;
    private Long patientId;
}
