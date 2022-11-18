package com.datajpa.relationship.repository;

import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

   // @Query("select u from Utilisateur u , u.roles r, PersonnelMedical p where 'ROLE_ADMIN' in (r.name)")
    List<Utilisateur> findUtilisateursByRoles(Optional<Role> u);

    @Override
    Iterable<Utilisateur> findAll();

    @Query("select c from Consultant c")
    List<Consultant> findConsultants();

    @Query("select c from Consultant c where c.id = ?1")
    Consultant findConsultantById(Long id);


    @Query("select p from PersonnelMedical p")
    List<PersonnelMedical> findPersonnelMedicals();

    @Query("select c from PersonnelMedical c where c.id = ?1")
    PersonnelMedical findPersonnelMedicalById(Long id);
}
