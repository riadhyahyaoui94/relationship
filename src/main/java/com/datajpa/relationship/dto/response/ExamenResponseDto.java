package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Ordonnance;
import com.datajpa.relationship.model.PriseRDV;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamenResponseDto {
    private Long id;
    private String nomExamen;
    private Double prixExamen;

  //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    @JsonIgnoreProperties({"consultant","medicaments","examens","consultation"})
    private List<Ordonnance> ordonnances;
    @JsonIgnoreProperties({"examen","consultant","patient","serviceConsultation","facturation"})
    private List<PriseRDV> priseRDVs;

}
