package com.datajpa.relationship.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Table(name = "salleAttente")
public class SalleAttente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String nom;
    private  String etage;
    private Integer numOrdre;

@CreationTimestamp
     private Date postedAt;
@UpdateTimestamp
    private Date lastUpdatedAt;

    @OneToMany(mappedBy = "salleAttente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ServiceConsultation> serviceConsultations;

    public void addServiceConsultation(ServiceConsultation serviceConsultation) {
        serviceConsultations.add(serviceConsultation);  }
    public void removeServiceConsultation(ServiceConsultation serviceConsultation) {
        serviceConsultations.remove(serviceConsultation);  }

    @OneToMany(mappedBy = "salleAttente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Patient> patients;

    public void addPatient(Patient patient){
        patients.add(patient);
    }
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    @OneToMany(mappedBy = "salleAttente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PersonnelMedical> personnelMedicals;
    public void addPersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.add(personnelMedical);
    }
    public void removePersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.remove(personnelMedical);
    }

    public SalleAttente(String nom, Integer numOrdre, /*Date postedAt, Date lastUpdatedAt,*/
                Accueil accueil, List<ServiceConsultation> serviceConsultations, List<Patient> patients) {
        this.nom = nom;
        this.numOrdre = numOrdre;
        /*PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;*/
        this.serviceConsultations = serviceConsultations;
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SalleAttente that = (SalleAttente) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
