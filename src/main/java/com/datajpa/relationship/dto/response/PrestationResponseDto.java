package com.datajpa.relationship.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PrestationResponseDto {
    private Long id;
    private String naturePrestation;
    private Float coutPourAssure;
    private Float coutPourNonAssure;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PostedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date LastUpdatedAt;
    private String facturationName;

}
