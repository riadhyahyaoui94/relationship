package com.datajpa.relationship.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "prestation")
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naturePrestation;
    private Float coutPourAssure;//double
    private Float coutPourNonAssure;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt ;


    @ManyToOne
    @JoinColumn(name = "facturation_id")
    private Facturation facturation;

    public Facturation getFacturation() {
        return facturation;
    }
    public void setFacturation(Facturation facturation) {
        this.facturation = facturation;
    }

    public Prestation(String naturePrestation, Float coutPourAssure,
                      Float coutPourNonAssure, Date postedAt, Date lastUpdatedAt, Facturation facturation) {
        this.naturePrestation = naturePrestation;
        this.coutPourAssure = coutPourAssure;
        this.coutPourNonAssure = coutPourNonAssure;

        this.facturation = facturation;
    }
}
