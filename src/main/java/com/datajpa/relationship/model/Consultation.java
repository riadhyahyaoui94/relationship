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
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private String typeConsultation;

    private String diagnostic;

    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date lastUpdatedAt ;

    @ManyToOne
  //  @JoinColumn(name = "consultation")
    private Consultant consultant;

    @ManyToOne
  //  @JoinColumn(name = "consultation")
    private Patient patient;


    @ManyToOne
   // @JoinColumn(name= "consultations")
    private DossierMedical dossierMedical;

    @OneToOne(mappedBy = "consultation")
    private Ordonnance ordonnance;

  }
