package com.datajpa.relationship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultant extends Utilisateur {


    private Calendar calendrier;

    private String titre;

    private String fonction;



    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DossierMedical> dossierMedicals;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ordonnance> ordonnances ;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations ;

    @ManyToOne
    @JoinColumn(name = "service_consultation_id")
    private ServiceConsultation serviceConsultation;

    @OneToMany(mappedBy = "consultant",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PriseRDV> priseRDVs;

    public Consultant(String username, String email, String encode, Calendar calendrier) {
        this.calendrier=calendrier;
    }


    public ServiceConsultation getServiceConsultation() {
        return serviceConsultation;
    }

    public void setServiceConsultation(ServiceConsultation serviceConsultation) {
        this.serviceConsultation = serviceConsultation;
    }


    public Consultant(Calendar calendrier, Date postedAt, Date lastUpdatedAt,
                      List<Ordonnance> ordonnances,
                      List<DossierMedical> dossierMedicals) {

        this.calendrier = calendrier;

        this.ordonnances = ordonnances;

        this.dossierMedicals = dossierMedicals;
    }

    public void addDossierMedical(DossierMedical dossierMedical){
        dossierMedicals.add(dossierMedical);
    }
    public void removeDossierMedical(DossierMedical dossierMedical){
        dossierMedicals.remove(dossierMedical);
    }

    public void addOrdonnance(Ordonnance ordonnance){
        ordonnances.add(ordonnance);
    }
    public void removeOrdonnance(Ordonnance ordonnance){
        ordonnances.remove(ordonnance);
    }

}
