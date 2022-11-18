package com.datajpa.relationship.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dossierMedical")
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomPatient;
    private String prenomPatient;
    private Boolean ancienPatient;
    private String consultationEffectuee;
    private String resultatPrestation;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt;
    @ManyToOne
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;

    @OneToMany(mappedBy = "dossierMedical",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "service_consultation_id")
    private ServiceConsultation serviceConsultation;

    public ServiceConsultation getServiceConsultation() {
        return serviceConsultation;
    }

    public void setServiceConsultation(ServiceConsultation serviceConsultation) {
        this.serviceConsultation = serviceConsultation;
    }


    public DossierMedical(String nomPatient, String prenomPatient, String consultationEffectuee, String resultatPrestation, Date postedAt,
                          Date lastUpdatedAt, Consultant consultant,  Patient patient) {
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.consultationEffectuee = consultationEffectuee;
        this.resultatPrestation = resultatPrestation;
        /*PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;*/
        this.consultant = consultant;
        this.patient = patient;
    }


    }


