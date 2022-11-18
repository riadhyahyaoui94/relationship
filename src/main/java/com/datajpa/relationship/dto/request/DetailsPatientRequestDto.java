package com.datajpa.relationship.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DetailsPatientRequestDto {

    private String status;
    private String serviceAffectation;
    private String etatDuPatient;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date heurePriseCharge;
    private Long patientId;
    private Date postedAt;
    private Date lastUpdatedAt;
}
