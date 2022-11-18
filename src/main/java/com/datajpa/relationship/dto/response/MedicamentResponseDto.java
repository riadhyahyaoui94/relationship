package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.Ordonnance;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MedicamentResponseDto {
    private Long id;
    private String nomMedicament;
    private String dureeDePrise;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;
    @JsonIgnoreProperties({"consultant","medicaments","examens","consultation"})

    private List<Ordonnance> ordonnances;

}
