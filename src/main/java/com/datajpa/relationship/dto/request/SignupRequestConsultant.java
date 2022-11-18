package com.datajpa.relationship.dto.request;

import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.model.Ordonnance;
import com.datajpa.relationship.model.ServiceConsultation;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Data
public class SignupRequestConsultant {

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



    private Calendar calendrier;

    private String titre;

    private String fonction;

    private List<DossierMedical> dossierMedicals;


    private List<Ordonnance> ordonnances ;

    private ServiceConsultation serviceConsultation;

    private Set<String> roles;


}
