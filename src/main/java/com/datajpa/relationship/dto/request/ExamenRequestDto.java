package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class ExamenRequestDto {

    private String nomExamen;
    private Double prixExamen;
    private Date postedAt;
    private Date lastUpdatedAt;
    private Long ordonnanceId;
}
