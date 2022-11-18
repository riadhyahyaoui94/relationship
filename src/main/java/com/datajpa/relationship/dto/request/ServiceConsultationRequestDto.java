package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceConsultationRequestDto {
    private String nomService;
    private String typeService;



    private Long salleAttenteId;

}
