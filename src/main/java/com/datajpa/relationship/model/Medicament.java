package com.datajpa.relationship.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medicament")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomMedicament;
    private String dureeDePrise;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt;

    @ManyToMany
    private List<Ordonnance> ordonnances;

   /* public Ordonnance getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(Ordonnance ordonnance) {
        this.ordonnances = ordonnances;
    }*/

    public Medicament(String nomMedicament, String dureeDePrise,
                      Date postedAt, Date lastUpdatedAt) {
        this.nomMedicament = nomMedicament;
        this.dureeDePrise = dureeDePrise;
        /*PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;*/

    }
}
