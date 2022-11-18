package com.datajpa.relationship.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UtilisateurRequestDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String photo;
    private String username;
    private String passeword;
    private Date PostedAt;
    private Date LastUpdatedAt;

}
