package com.datajpa.relationship.model;

import com.datajpa.relationship.dto.response.ConsultantResponseDto;
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
@Table(name = "ordonnance")
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    public String nomPatient;
    private Date  dateOrdonnance;
    private String natureOrdonnance;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt ;


    @ManyToOne
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;
    @ManyToMany(mappedBy = "ordonnances", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Examen> examens ;

    @ManyToMany(mappedBy = "ordonnances", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicament> medicaments;
    @OneToOne
    private Consultation consultation;

    public Ordonnance(List<Examen> examens, List<Medicament> medicaments) {
        this.examens = examens;
        this.medicaments = medicaments;
    }

    public Ordonnance(Date dateOrdonnance, String natureOrdonnance, Date postedAt, Date lastUpdatedAt,
                      Consultant consultant, List<Examen> examens, List<Medicament> medicaments) {
        this.dateOrdonnance = dateOrdonnance;
        this.natureOrdonnance = natureOrdonnance;
        this.postedAt = postedAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.consultant = consultant;
        this.examens = examens;
        this.medicaments = medicaments;
    }
    public void addExamen(Examen examen){
        examens.add(examen);
    }
    public void removeExamen(Examen examen){
        examens.remove(examen);
    }

    public void addMedicament(Medicament medicament){
        medicaments.add(medicament);
    }
    public void removeMedicament(Medicament medicament){
        medicaments.remove(medicament);
    }
    public Consultant getConsultant() {
        return consultant;
    }



    public void setConsultant(Consultant consultant) {this.consultant=consultant;
    }
}
