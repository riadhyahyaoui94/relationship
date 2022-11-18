package com.datajpa.relationship.dto.request;

import com.datajpa.relationship.model.SalleAttente;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
@Data
public class AccueilRequestDto {
    private String nom;
    private Boolean nouveauPatient;



}
