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
@Table(name = "priseRDV")
public class PriseRDV {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /*private String nomPatient;
    private String prenomPatient;*/
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateRDV;
    private Date heureRDV;

    private String motif;
    private Boolean payed;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    public PriseRDV(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    private Consultant consultant;

    @ManyToOne
    private Examen examen;


    @ManyToOne
    @JoinColumn(name = "service_consultation_id")
    private ServiceConsultation serviceConsultation;

    @OneToOne(mappedBy = "priseRDV")
    private Facturation facturation;

    /*public ServiceConsultation getServiceConsultation() {
        return serviceConsultation;
    }

    public void setServiceConsultation(ServiceConsultation serviceConsultation) {
        this.serviceConsultation = serviceConsultation;
    }*/


    public PriseRDV(String nomPatient, String prenomPatient, Date dateRDV, Date heureRDV,
                    Consultant consultant, String motif,
                    Boolean payed,   Patient patient) {
        /*this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;*/
        this.dateRDV = dateRDV;
        this.heureRDV = heureRDV;
        this.consultant = consultant;
        this.motif = motif;
        this.payed = payed;
        this.patient = patient;
    }
}
