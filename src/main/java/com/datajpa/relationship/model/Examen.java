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
@Table(name = "examen")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomExamen;
    private Double prixExamen;

    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt;

    @ManyToMany
    @JoinColumn(name = "ordonnance_id")
    private List<Ordonnance> ordonnances;

    @OneToMany(mappedBy = "examen",cascade = CascadeType.ALL,fetch = FetchType.LAZY)

    private List<Facturation> facturations;

    @OneToMany(mappedBy = "examen",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PriseRDV> priseRDVs;

    /*public Ordonnance getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(Ordonnance ordonnance) {
        this.ordonnances = ordonnances;
    }*/


    public Examen(String nomExamen, Double prixExamen, Date postedAt, Date lastUpdatedAt) {
        this.nomExamen = nomExamen;
        this.prixExamen = prixExamen;
        /*PostedAt = postedAt;
        LastUpdatedAt = lastUpdatedAt;
*/
    }
}
