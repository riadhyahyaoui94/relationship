package com.datajpa.relationship.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "service_consultation")
public class ServiceConsultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomService;
    private String typeService;
   // private String diagnostic;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt ;

    @ManyToOne
    @JoinColumn(name = "salleAttente_id")
    private SalleAttente salleAttente;

     @OneToMany(mappedBy = "serviceConsultation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     @ToString.Exclude
     private List<Consultant> consultants ;


      @OneToMany(mappedBy = "serviceConsultation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
      @ToString.Exclude
      private List<PriseRDV> priseRDVS;

    @OneToMany(mappedBy = "serviceConsultation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<DossierMedical> dossierMedicals;

    @OneToMany(mappedBy = "serviceConsultation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PersonnelMedical> personnelMedicals;

   /* public ServiceConsultation(List<Consultant> consultants, List<PriseRDV> priseRDVS, List<DossierMedical> dossierMedicals) {
        this.consultants = consultants;
        this.priseRDVS = priseRDVS;
        this.dossierMedicals = dossierMedicals;
    }*/

   /* public ServiceConsultation(List<PersonnelMedical> personnelMedicals) {
        this.personnelMedicals = personnelMedicals;
    }

    public ServiceConsultation(String nomConsultation,Date dateConsultation, String typeConsultation, Boolean ancienPatient,
                               String diagnostic,  String nomConsultant, String fonctionConsultant,Date postedAt,
                               Date lastUpdatedAt ) {

        this.nomConsultation = nomConsultation;
      //  this.dateConsultation = dateConsultation;
        this.typeConsultation = typeConsultation;
        //this.ancienPatient = ancienPatient;

        //this.nomConsultant = nomConsultant;
        //this.fonctionConsultant = fonctionConsultant;
        PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;



     }*/
    public void addDossierMedical(DossierMedical dossierMedical){
        dossierMedicals.add(dossierMedical);
    }
    public void removeDossierMedical(DossierMedical dossierMedical){
        dossierMedicals.remove(dossierMedical);
    }

    public void addConsultant(Consultant consultant){
        consultants.add(consultant);
    }
    public void removeConsultant(Consultant consultant){
        consultants.remove(consultant);
    }

    public void addPriseRdv(PriseRDV priseRDV){
        priseRDVS.add(priseRDV);
    }
    public void removePriseRdv(PriseRDV priseRDV){
        priseRDVS.remove(priseRDV);
    }

    public void addPersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.add(personnelMedical);
    }
    public void removePersonnelMedical(PersonnelMedical personnelMedical) {
        personnelMedicals.remove(personnelMedical);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceConsultation that = (ServiceConsultation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
