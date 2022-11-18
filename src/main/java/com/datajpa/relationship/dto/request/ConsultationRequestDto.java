package com.datajpa.relationship.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class ConsultationRequestDto {
    private Long id;

    private Date dateConsultation;
    private String typeConsultation;

    private String diagnostic;


    private Long consultantId;

    private Long patientId;

    private Long dossierMedicalId;
    private Long ordonnanceId;

}
