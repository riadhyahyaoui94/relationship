package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConsultationServiceResponseDto {


    @JsonIgnoreProperties({"patient","consultant","dossierMedical","ordonnance"})
    private List<Consultation> consultations;

    private String names;


}
