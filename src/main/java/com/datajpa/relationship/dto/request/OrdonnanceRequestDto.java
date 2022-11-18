package com.datajpa.relationship.dto.request;

import com.datajpa.relationship.model.Examen;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrdonnanceRequestDto {

    private Date dateOrdonnance;
    private String natureOrdonnance;
    private String nomPatient;
    private Date postedAt;
    private Date lastUpdatedAt ;
    private Long consultantId;
    private List<Long> examensId;
    private List<Long> medicamentsId;
    private Long consultationId;

}
