package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class PrestationRequestDto {

    private String naturePrestation;
    private Float coutPourAssure;
    private Float coutPourNonAssure;
    private Date PostedAt = new Date() ;
    private Date LastUpdatedAt = new Date();
    private Long facturationId;
}
