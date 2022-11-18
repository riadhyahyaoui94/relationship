package com.datajpa.relationship.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "details_patient")
public class DetailsPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String serviceAffectation;
    private String etatDuPatient;

    private Date heurePriseCharge;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt;

    @OneToOne(mappedBy = "detailsPatient")
    Patient patient;

    public DetailsPatient(Patient patient) {
        this.patient = patient;
    }

    public DetailsPatient(String status, String serviceAffectation,
                          String etatDuPatient, Date heurePriseCharge, Date postedAt, Date lastUpdatedAt) {
        this.status = status;
        this.serviceAffectation = serviceAffectation;
        this.etatDuPatient = etatDuPatient;
        this.heurePriseCharge = heurePriseCharge;

    }
}
