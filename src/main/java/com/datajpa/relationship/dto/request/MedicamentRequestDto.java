package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class MedicamentRequestDto {
    private String nomMedicament;
    private String dureeDePrise;
    private Date postedAt ;
    private Date lastUpdatedAt ;
    private Long ordonnanceId;
}
