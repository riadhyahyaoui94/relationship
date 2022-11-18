package com.datajpa.relationship.dto.request;

import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequestPersonnelMedical {

    private Long id;
    private String nom;

    private String prenom;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String telephone;

    private String photo;

    private String titre;

    private String fonction;

    private String service;

    private Accueil accueil;

    private SalleAttente salleAttente;

    private Paiement paiement;

    private ServiceConsultation serviceConsultation;

    private Set<String> roles;



}
