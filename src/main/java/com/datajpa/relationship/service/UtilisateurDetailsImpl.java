package com.datajpa.relationship.service;


import com.datajpa.relationship.model.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ToString
public class UtilisateurDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;



  private String nom;

  private String prenom;

  private String telephone;

  private String photo;

  private Date PostedAt;

  private Date LastUpdatedAt;

  private Collection<? extends GrantedAuthority> authorities;

  public UtilisateurDetailsImpl(Long id, String username, String email, String password,String nom,String prenom,String telephone,String photo,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.nom = nom;
    this.password = password;
    this.prenom = prenom;
    this.telephone = telephone;
    this.photo = photo;
    this.authorities = authorities;
  }

  public static UtilisateurDetailsImpl build(Utilisateur utilisateur) {
    List<GrantedAuthority> authorities = utilisateur.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UtilisateurDetailsImpl(
        utilisateur.getId(),
        utilisateur.getUsername(),
        utilisateur.getEmail(),
        utilisateur.getPassword(),
        utilisateur.getNom(),
        utilisateur.getPrenom(),
        utilisateur.getTelephone(),
        utilisateur.getPhoto(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getPhoto() {
    return photo;
  }
  public Date getPostedAt() {
    return PostedAt;
  }

  public Date getLastUpdatedAt() {
    return LastUpdatedAt;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UtilisateurDetailsImpl utilisateur = (UtilisateurDetailsImpl) o;
    return Objects.equals(id, utilisateur.id);
  }
}
