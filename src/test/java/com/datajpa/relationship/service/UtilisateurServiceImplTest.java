package com.datajpa.relationship.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.datajpa.relationship.dto.request.SignupRequest;
import com.datajpa.relationship.dto.request.SignupRequestConsultant;
import com.datajpa.relationship.dto.request.SignupRequestPersonnelMedical;
import com.datajpa.relationship.dto.request.UtilisateurRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.PersonnelMedicalResponseDto;
import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.model.ERole;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.PersonnelMedical;
import com.datajpa.relationship.model.Role;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.model.Utilisateur;
import com.datajpa.relationship.repository.RoleRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UtilisateurServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UtilisateurServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurServiceImpl utilisateurServiceImpl;

    /**
     * Method under test: {@link UtilisateurServiceImpl#addUtilisateur(UtilisateurRequestDto)}
     */
    @Test
    void testAddUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setPrenom("Prenom");
        utilisateur.setRoles(new HashSet<>());
        utilisateur.setTelephone("4105551212");
        utilisateur.setUsername("janedoe");
        when(this.utilisateurRepository.save((Utilisateur) any())).thenReturn(utilisateur);

        UtilisateurRequestDto utilisateurRequestDto = new UtilisateurRequestDto();
        utilisateurRequestDto.setEmail("jane.doe@example.org");
        utilisateurRequestDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurRequestDto.setNom("Nom");
        utilisateurRequestDto.setPasseword("Passeword");
        utilisateurRequestDto.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurRequestDto.setPrenom("Prenom");
        utilisateurRequestDto.setTelephone("4105551212");
        utilisateurRequestDto.setUsername("janedoe");
        UtilisateurResponseDto actualAddUtilisateurResult = this.utilisateurServiceImpl
                .addUtilisateur(utilisateurRequestDto);
        assertNull(actualAddUtilisateurResult.getEmail());
        assertNull(actualAddUtilisateurResult.getUsername());
        assertNull(actualAddUtilisateurResult.getTelephone());
        assertTrue(actualAddUtilisateurResult.getRoles().isEmpty());
        assertNull(actualAddUtilisateurResult.getPrenom());
        assertNull(actualAddUtilisateurResult.getPostedAt());
        assertNull(actualAddUtilisateurResult.getPhoto());
        assertEquals("Nom", actualAddUtilisateurResult.getNom());
        assertNull(actualAddUtilisateurResult.getLastUpdatedAt());
        assertNull(actualAddUtilisateurResult.getId());
        verify(this.utilisateurRepository).save((Utilisateur) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurs()}
     */
    @Test
    void testGetUtilisateurs() {
        when(this.utilisateurRepository.findUtilisateursByRoles((Optional<Role>) any())).thenReturn(new ArrayList<>());

        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);
        Optional<Role> ofResult = Optional.of(role);
        when(this.roleRepository.findByName((ERole) any())).thenReturn(ofResult);
        assertTrue(this.utilisateurServiceImpl.getUtilisateurs().isEmpty());
        verify(this.utilisateurRepository).findUtilisateursByRoles((Optional<Role>) any());
        verify(this.roleRepository).findByName((ERole) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurs()}
     */
    @Test
    void testGetUtilisateurs2() {
        when(this.utilisateurRepository.findUtilisateursByRoles((Optional<Role>) any())).thenReturn(new ArrayList<>());
        when(this.roleRepository.findByName((com.datajpa.relationship.model.ERole) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.getUtilisateurs());
        verify(this.roleRepository).findByName((com.datajpa.relationship.model.ERole) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurs()}
     */
    @Test
    void testGetUtilisateurs3() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setPrenom("Prenom");
        utilisateur.setRoles(new HashSet<>());
        utilisateur.setTelephone("4105551212");
        utilisateur.setUsername("janedoe");

        ArrayList<Utilisateur> utilisateurList = new ArrayList<>();
        utilisateurList.add(utilisateur);
        when(this.utilisateurRepository.findUtilisateursByRoles((Optional<Role>) any())).thenReturn(utilisateurList);

        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);
        Optional<Role> ofResult = Optional.of(role);
        when(this.roleRepository.findByName((ERole) any())).thenReturn(ofResult);
        assertEquals(1, this.utilisateurServiceImpl.getUtilisateurs().size());
        verify(this.utilisateurRepository).findUtilisateursByRoles((Optional<Role>) any());
        verify(this.roleRepository).findByName((ERole) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurs()}
     */
    @Test
    void testGetUtilisateurs4() {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setPrenom("Prenom");
        utilisateur.setRoles(roleSet);
        utilisateur.setTelephone("4105551212");
        utilisateur.setUsername("janedoe");

        ArrayList<Utilisateur> utilisateurList = new ArrayList<>();
        utilisateurList.add(utilisateur);
        when(this.utilisateurRepository.findUtilisateursByRoles((Optional<Role>) any())).thenReturn(utilisateurList);

        Role role1 = new Role();
        role1.setId(1);
        role1.setName(ERole.ROLE_ADMIN);
        Optional<Role> ofResult = Optional.of(role1);
        when(this.roleRepository.findByName((ERole) any())).thenReturn(ofResult);
        assertEquals(1, this.utilisateurServiceImpl.getUtilisateurs().size());
        verify(this.utilisateurRepository).findUtilisateursByRoles((Optional<Role>) any());
        verify(this.roleRepository).findByName((ERole) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateur(Long)}
     */
    @Test
    void testGetUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateur.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateur.setPrenom("Prenom");
        utilisateur.setRoles(new HashSet<>());
        utilisateur.setTelephone("4105551212");
        utilisateur.setUsername("janedoe");
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(this.utilisateurRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(utilisateur, this.utilisateurServiceImpl.getUtilisateur(123L));
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateur(Long)}
     */
    @Test
    void testGetUtilisateur2() {
        when(this.utilisateurRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.utilisateurServiceImpl.getUtilisateur(123L));
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateur(Long)}
     */
    @Test
    void testGetUtilisateur3() {
        when(this.utilisateurRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.getUtilisateur(123L));
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#deleteUtilisateur(Long)}
     */
    @Test
    void testDeleteUtilisateur() {
        doNothing().when(this.utilisateurRepository).deleteById((Long) any());
        assertEquals("user deleted", this.utilisateurServiceImpl.deleteUtilisateur(123L));
        verify(this.utilisateurRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#deleteUtilisateur(Long)}
     */
    @Test
    void testDeleteUtilisateur2() {
        doThrow(new RuntimeException("An error occurred")).when(this.utilisateurRepository).deleteById((Long) any());
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.deleteUtilisateur(123L));
        verify(this.utilisateurRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurById(Long)}
     */
    @Test
    void testGetUtilisateurById() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        utilisateur.setLastUpdatedAt(fromResult);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        utilisateur.setPostedAt(fromResult1);
        utilisateur.setPrenom("Prenom");
        utilisateur.setRoles(new HashSet<>());
        utilisateur.setTelephone("4105551212");
        utilisateur.setUsername("janedoe");
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(this.utilisateurRepository.findById((Long) any())).thenReturn(ofResult);
        UtilisateurResponseDto actualUtilisateurById = this.utilisateurServiceImpl.getUtilisateurById(123L);
        assertEquals("jane.doe@example.org", actualUtilisateurById.getEmail());
        assertEquals("janedoe", actualUtilisateurById.getUsername());
        assertEquals("4105551212", actualUtilisateurById.getTelephone());
        assertTrue(actualUtilisateurById.getRoles().isEmpty());
        assertEquals("Prenom", actualUtilisateurById.getPrenom());
        assertSame(fromResult1, actualUtilisateurById.getPostedAt());
        assertEquals("alice.liddell@example.org", actualUtilisateurById.getPhoto());
        assertEquals("Nom", actualUtilisateurById.getNom());
        assertSame(fromResult, actualUtilisateurById.getLastUpdatedAt());
        assertEquals(123L, actualUtilisateurById.getId().longValue());
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurById(Long)}
     */
    @Test
    void testGetUtilisateurById2() {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        utilisateur.setLastUpdatedAt(fromResult);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        utilisateur.setPostedAt(fromResult1);
        utilisateur.setPrenom("Prenom");
        utilisateur.setRoles(roleSet);
        utilisateur.setTelephone("4105551212");
        utilisateur.setUsername("janedoe");
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(this.utilisateurRepository.findById((Long) any())).thenReturn(ofResult);
        UtilisateurResponseDto actualUtilisateurById = this.utilisateurServiceImpl.getUtilisateurById(123L);
        assertEquals("jane.doe@example.org", actualUtilisateurById.getEmail());
        assertEquals("janedoe", actualUtilisateurById.getUsername());
        assertEquals("4105551212", actualUtilisateurById.getTelephone());
        List<String> roles = actualUtilisateurById.getRoles();
        assertEquals(1, roles.size());
        assertEquals("ROLE_ADMIN", roles.get(0));
        assertEquals("Prenom", actualUtilisateurById.getPrenom());
        assertSame(fromResult1, actualUtilisateurById.getPostedAt());
        assertEquals("alice.liddell@example.org", actualUtilisateurById.getPhoto());
        assertEquals("Nom", actualUtilisateurById.getNom());
        assertSame(fromResult, actualUtilisateurById.getLastUpdatedAt());
        assertEquals(123L, actualUtilisateurById.getId().longValue());
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurById(Long)}
     */
    @Test
    void testGetUtilisateurById3() {
        when(this.utilisateurRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.utilisateurServiceImpl.getUtilisateurById(123L));
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getUtilisateurById(Long)}
     */
    @Test
    void testGetUtilisateurById4() {
        when(this.utilisateurRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.getUtilisateurById(123L));
        verify(this.utilisateurRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editUtilisateur(SignupRequest)}
     */
    @Test
    void testEditUtilisateur() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        ArrayList<Consultation> consultationList = new ArrayList<>();
        consultant.setConsultations(consultationList);
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setLastUpdatedAt(fromResult);
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setPostedAt(fromResult1);
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(serviceConsultation1);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");
        when(this.utilisateurRepository.save((Consultant) any())).thenReturn(consultant1);
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("jane.doe@example.org");
        signupRequest.setId(123L);
        signupRequest.setNom("Nom");
        signupRequest.setPassword("iloveyou");
        signupRequest.setPhoto("alice.liddell@example.org");
        signupRequest.setPrenom("Prenom");
        signupRequest.setRoles(new HashSet<>());
        signupRequest.setTelephone("4105551212");
        signupRequest.setUsername("janedoe");
        UtilisateurResponseDto actualEditUtilisateurResult = this.utilisateurServiceImpl.editUtilisateur(signupRequest);
        assertEquals("jane.doe@example.org", actualEditUtilisateurResult.getEmail());
        assertEquals("janedoe", actualEditUtilisateurResult.getUsername());
        assertEquals("4105551212", actualEditUtilisateurResult.getTelephone());
        assertEquals(consultationList, actualEditUtilisateurResult.getRoles());
        assertEquals("Prenom", actualEditUtilisateurResult.getPrenom());
        assertSame(fromResult1, actualEditUtilisateurResult.getPostedAt());
        assertEquals("alice.liddell@example.org", actualEditUtilisateurResult.getPhoto());
        assertEquals("Nom", actualEditUtilisateurResult.getNom());
        assertSame(fromResult, actualEditUtilisateurResult.getLastUpdatedAt());
        assertEquals(123L, actualEditUtilisateurResult.getId().longValue());
        verify(this.utilisateurRepository).findConsultantById((Long) any());
        verify(this.utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editUtilisateur(SignupRequest)}
     */
    @Test
    void testEditUtilisateur2() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");
        when(this.utilisateurRepository.save((Consultant) any())).thenThrow(new RuntimeException("An error occurred"));
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("jane.doe@example.org");
        signupRequest.setId(123L);
        signupRequest.setNom("Nom");
        signupRequest.setPassword("iloveyou");
        signupRequest.setPhoto("alice.liddell@example.org");
        signupRequest.setPrenom("Prenom");
        signupRequest.setRoles(new HashSet<>());
        signupRequest.setTelephone("4105551212");
        signupRequest.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.editUtilisateur(signupRequest));
        verify(this.utilisateurRepository).findConsultantById((Long) any());
        verify(this.utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editUtilisateur(SignupRequest)}
     */
    @Test
    void testEditUtilisateur3() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setLastUpdatedAt(fromResult);
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setPostedAt(fromResult1);
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(roleSet);
        consultant1.setServiceConsultation(serviceConsultation1);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");
        when(this.utilisateurRepository.save((Consultant) any())).thenReturn(consultant1);
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("jane.doe@example.org");
        signupRequest.setId(123L);
        signupRequest.setNom("Nom");
        signupRequest.setPassword("iloveyou");
        signupRequest.setPhoto("alice.liddell@example.org");
        signupRequest.setPrenom("Prenom");
        signupRequest.setRoles(new HashSet<>());
        signupRequest.setTelephone("4105551212");
        signupRequest.setUsername("janedoe");
        UtilisateurResponseDto actualEditUtilisateurResult = this.utilisateurServiceImpl.editUtilisateur(signupRequest);
        assertEquals("jane.doe@example.org", actualEditUtilisateurResult.getEmail());
        assertEquals("janedoe", actualEditUtilisateurResult.getUsername());
        assertEquals("4105551212", actualEditUtilisateurResult.getTelephone());
        List<String> roles = actualEditUtilisateurResult.getRoles();
        assertEquals(1, roles.size());
        assertEquals("ROLE_ADMIN", roles.get(0));
        assertEquals("Prenom", actualEditUtilisateurResult.getPrenom());
        assertSame(fromResult1, actualEditUtilisateurResult.getPostedAt());
        assertEquals("alice.liddell@example.org", actualEditUtilisateurResult.getPhoto());
        assertEquals("Nom", actualEditUtilisateurResult.getNom());
        assertSame(fromResult, actualEditUtilisateurResult.getLastUpdatedAt());
        assertEquals(123L, actualEditUtilisateurResult.getId().longValue());
        verify(this.utilisateurRepository).findConsultantById((Long) any());
        verify(this.utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getConsultants()}
     */
    @Test
    void testGetConsultants() {
        when(this.utilisateurRepository.findConsultants()).thenReturn(new ArrayList<>());
        assertTrue(this.utilisateurServiceImpl.getConsultants().isEmpty());
        verify(this.utilisateurRepository).findConsultants();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getConsultants()}
     */
    @Test
    void testGetConsultants2() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        ArrayList<Consultant> consultantList = new ArrayList<>();
        consultantList.add(consultant);
        when(this.utilisateurRepository.findConsultants()).thenReturn(consultantList);
        assertEquals(1, this.utilisateurServiceImpl.getConsultants().size());
        verify(this.utilisateurRepository).findConsultants();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getConsultants()}
     */
    @Test
    void testGetConsultants3() {
        when(this.utilisateurRepository.findConsultants()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.getConsultants());
        verify(this.utilisateurRepository).findConsultants();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getConsultants()}
     */
    @Test
    void testGetConsultants4() {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(roleSet);
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        ArrayList<Consultant> consultantList = new ArrayList<>();
        consultantList.add(consultant);
        when(this.utilisateurRepository.findConsultants()).thenReturn(consultantList);
        assertEquals(1, this.utilisateurServiceImpl.getConsultants().size());
        verify(this.utilisateurRepository).findConsultants();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getConsultantById(Long)}
     */
    @Test
    void testGetConsultantById() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        RollingCalendar rollingCalendar = new RollingCalendar("2020-03-01");
        consultant.setCalendrier(rollingCalendar);
        ArrayList<Consultation> consultationList = new ArrayList<>();
        consultant.setConsultations(consultationList);
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        consultant.setLastUpdatedAt(fromResult);
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        consultant.setPostedAt(fromResult1);
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);
        ConsultantResponseDto actualConsultantById = this.utilisateurServiceImpl.getConsultantById(123L);
        assertSame(rollingCalendar, actualConsultantById.getCalendrier());
        assertEquals("janedoe", actualConsultantById.getUsername());
        assertEquals("Titre", actualConsultantById.getTitre());
        assertEquals(consultationList, actualConsultantById.getDossierMedicals());
        assertEquals("Fonction", actualConsultantById.getFonction());
        assertEquals(consultationList, actualConsultantById.getOrdonnances());
        assertEquals("4105551212", actualConsultantById.getTelephone());
        assertEquals(123L, actualConsultantById.getId().longValue());
        assertEquals("alice.liddell@example.org", actualConsultantById.getPhoto());
        assertSame(fromResult1, actualConsultantById.getPostedAt());
        assertEquals("jane.doe@example.org", actualConsultantById.getEmail());
        assertSame(fromResult, actualConsultantById.getLastUpdatedAt());
        assertEquals("Prenom", actualConsultantById.getPrenom());
        assertEquals(consultationList, actualConsultantById.getPriseRDVs());
        assertEquals("Nom", actualConsultantById.getNom());
        assertEquals(consultationList, actualConsultantById.getRoles());
        assertSame(serviceConsultation, actualConsultantById.getServiceConsultation());
        verify(this.utilisateurRepository).findConsultantById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getConsultantById(Long)}
     */
    @Test
    void testGetConsultantById2() {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        RollingCalendar rollingCalendar = new RollingCalendar("2020-03-01");
        consultant.setCalendrier(rollingCalendar);
        ArrayList<Consultation> consultationList = new ArrayList<>();
        consultant.setConsultations(consultationList);
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        consultant.setLastUpdatedAt(fromResult);
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        consultant.setPostedAt(fromResult1);
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(roleSet);
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);
        ConsultantResponseDto actualConsultantById = this.utilisateurServiceImpl.getConsultantById(123L);
        assertSame(rollingCalendar, actualConsultantById.getCalendrier());
        assertEquals("janedoe", actualConsultantById.getUsername());
        assertEquals("Titre", actualConsultantById.getTitre());
        assertEquals(consultationList, actualConsultantById.getDossierMedicals());
        assertEquals("Fonction", actualConsultantById.getFonction());
        assertEquals(consultationList, actualConsultantById.getOrdonnances());
        assertEquals("4105551212", actualConsultantById.getTelephone());
        assertEquals(123L, actualConsultantById.getId().longValue());
        assertEquals("alice.liddell@example.org", actualConsultantById.getPhoto());
        assertSame(fromResult1, actualConsultantById.getPostedAt());
        assertEquals("jane.doe@example.org", actualConsultantById.getEmail());
        assertSame(fromResult, actualConsultantById.getLastUpdatedAt());
        assertEquals("Prenom", actualConsultantById.getPrenom());
        assertEquals(consultationList, actualConsultantById.getPriseRDVs());
        assertEquals("Nom", actualConsultantById.getNom());
        List<String> roles = actualConsultantById.getRoles();
        assertEquals(1, roles.size());
        assertEquals("ROLE_ADMIN", roles.get(0));
        assertSame(serviceConsultation, actualConsultantById.getServiceConsultation());
        verify(this.utilisateurRepository).findConsultantById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editConsultant(SignupRequestConsultant)}
     */
    @Test
    void testEditConsultant() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        RollingCalendar rollingCalendar = new RollingCalendar("2020-03-01");
        consultant1.setCalendrier(rollingCalendar);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setLastUpdatedAt(fromResult);
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setPostedAt(fromResult1);
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(serviceConsultation1);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");
        when(this.utilisateurRepository.save((Consultant) any())).thenReturn(consultant1);
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        SignupRequestConsultant signupRequestConsultant = new SignupRequestConsultant();
        signupRequestConsultant.setCalendrier(new RollingCalendar("2020-03-01"));
        ArrayList<DossierMedical> dossierMedicalList = new ArrayList<>();
        signupRequestConsultant.setDossierMedicals(dossierMedicalList);
        signupRequestConsultant.setEmail("jane.doe@example.org");
        signupRequestConsultant.setFonction("Fonction");
        signupRequestConsultant.setId(123L);
        signupRequestConsultant.setNom("Nom");
        signupRequestConsultant.setOrdonnances(new ArrayList<>());
        signupRequestConsultant.setPassword("iloveyou");
        signupRequestConsultant.setPhoto("alice.liddell@example.org");
        signupRequestConsultant.setPrenom("Prenom");
        signupRequestConsultant.setRoles(new HashSet<>());
        signupRequestConsultant.setServiceConsultation(serviceConsultation2);
        signupRequestConsultant.setTelephone("4105551212");
        signupRequestConsultant.setTitre("Titre");
        signupRequestConsultant.setUsername("janedoe");
        ConsultantResponseDto actualEditConsultantResult = this.utilisateurServiceImpl
                .editConsultant(signupRequestConsultant);
        assertSame(rollingCalendar, actualEditConsultantResult.getCalendrier());
        assertEquals("janedoe", actualEditConsultantResult.getUsername());
        assertEquals("Titre", actualEditConsultantResult.getTitre());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getDossierMedicals());
        assertEquals("Fonction", actualEditConsultantResult.getFonction());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getOrdonnances());
        assertEquals("4105551212", actualEditConsultantResult.getTelephone());
        assertEquals(123L, actualEditConsultantResult.getId().longValue());
        assertEquals("alice.liddell@example.org", actualEditConsultantResult.getPhoto());
        assertSame(fromResult1, actualEditConsultantResult.getPostedAt());
        assertEquals("jane.doe@example.org", actualEditConsultantResult.getEmail());
        assertSame(fromResult, actualEditConsultantResult.getLastUpdatedAt());
        assertEquals("Prenom", actualEditConsultantResult.getPrenom());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getPriseRDVs());
        assertEquals("Nom", actualEditConsultantResult.getNom());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getRoles());
        assertEquals(serviceConsultation2, actualEditConsultantResult.getServiceConsultation());
        verify(this.utilisateurRepository).findConsultantById((Long) any());
        verify(this.utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editConsultant(SignupRequestConsultant)}
     */
    @Test
    void testEditConsultant2() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");
        when(this.utilisateurRepository.save((Consultant) any())).thenThrow(new RuntimeException("An error occurred"));
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        SignupRequestConsultant signupRequestConsultant = new SignupRequestConsultant();
        signupRequestConsultant.setCalendrier(new RollingCalendar("2020-03-01"));
        signupRequestConsultant.setDossierMedicals(new ArrayList<>());
        signupRequestConsultant.setEmail("jane.doe@example.org");
        signupRequestConsultant.setFonction("Fonction");
        signupRequestConsultant.setId(123L);
        signupRequestConsultant.setNom("Nom");
        signupRequestConsultant.setOrdonnances(new ArrayList<>());
        signupRequestConsultant.setPassword("iloveyou");
        signupRequestConsultant.setPhoto("alice.liddell@example.org");
        signupRequestConsultant.setPrenom("Prenom");
        signupRequestConsultant.setRoles(new HashSet<>());
        signupRequestConsultant.setServiceConsultation(serviceConsultation1);
        signupRequestConsultant.setTelephone("4105551212");
        signupRequestConsultant.setTitre("Titre");
        signupRequestConsultant.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.editConsultant(signupRequestConsultant));
        verify(this.utilisateurRepository).findConsultantById((Long) any());
        verify(this.utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editConsultant(SignupRequestConsultant)}
     */
    @Test
    void testEditConsultant3() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        RollingCalendar rollingCalendar = new RollingCalendar("2020-03-01");
        consultant1.setCalendrier(rollingCalendar);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setLastUpdatedAt(fromResult);
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        consultant1.setPostedAt(fromResult1);
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(roleSet);
        consultant1.setServiceConsultation(serviceConsultation1);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");
        when(this.utilisateurRepository.save((Consultant) any())).thenReturn(consultant1);
        when(this.utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        SignupRequestConsultant signupRequestConsultant = new SignupRequestConsultant();
        signupRequestConsultant.setCalendrier(new RollingCalendar("2020-03-01"));
        ArrayList<DossierMedical> dossierMedicalList = new ArrayList<>();
        signupRequestConsultant.setDossierMedicals(dossierMedicalList);
        signupRequestConsultant.setEmail("jane.doe@example.org");
        signupRequestConsultant.setFonction("Fonction");
        signupRequestConsultant.setId(123L);
        signupRequestConsultant.setNom("Nom");
        signupRequestConsultant.setOrdonnances(new ArrayList<>());
        signupRequestConsultant.setPassword("iloveyou");
        signupRequestConsultant.setPhoto("alice.liddell@example.org");
        signupRequestConsultant.setPrenom("Prenom");
        signupRequestConsultant.setRoles(new HashSet<>());
        signupRequestConsultant.setServiceConsultation(serviceConsultation2);
        signupRequestConsultant.setTelephone("4105551212");
        signupRequestConsultant.setTitre("Titre");
        signupRequestConsultant.setUsername("janedoe");
        ConsultantResponseDto actualEditConsultantResult = this.utilisateurServiceImpl
                .editConsultant(signupRequestConsultant);
        assertSame(rollingCalendar, actualEditConsultantResult.getCalendrier());
        assertEquals("janedoe", actualEditConsultantResult.getUsername());
        assertEquals("Titre", actualEditConsultantResult.getTitre());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getDossierMedicals());
        assertEquals("Fonction", actualEditConsultantResult.getFonction());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getOrdonnances());
        assertEquals("4105551212", actualEditConsultantResult.getTelephone());
        assertEquals(123L, actualEditConsultantResult.getId().longValue());
        assertEquals("alice.liddell@example.org", actualEditConsultantResult.getPhoto());
        assertSame(fromResult1, actualEditConsultantResult.getPostedAt());
        assertEquals("jane.doe@example.org", actualEditConsultantResult.getEmail());
        assertSame(fromResult, actualEditConsultantResult.getLastUpdatedAt());
        assertEquals("Prenom", actualEditConsultantResult.getPrenom());
        assertEquals(dossierMedicalList, actualEditConsultantResult.getPriseRDVs());
        assertEquals("Nom", actualEditConsultantResult.getNom());
        List<String> roles = actualEditConsultantResult.getRoles();
        assertEquals(1, roles.size());
        assertEquals("ROLE_ADMIN", roles.get(0));
        assertEquals(serviceConsultation2, actualEditConsultantResult.getServiceConsultation());
        verify(this.utilisateurRepository).findConsultantById((Long) any());
        verify(this.utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getPersonnelMedicals()}
     */
    @Test
    void testGetPersonnelMedicals() {
        when(this.utilisateurRepository.findPersonnelMedicals()).thenReturn(new ArrayList<>());
        assertTrue(this.utilisateurServiceImpl.getPersonnelMedicals().isEmpty());
        verify(this.utilisateurRepository).findPersonnelMedicals();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getPersonnelMedicals()}
     */
    @Test
    void testGetPersonnelMedicals2() {
        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil1);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");

        PersonnelMedical personnelMedical = new PersonnelMedical();
        personnelMedical.setAccueil(accueil);
        personnelMedical.setEmail("jane.doe@example.org");
        personnelMedical.setFonction("Fonction");
        personnelMedical.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedical.setLastUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedical.setNom("Nom");
        personnelMedical.setPaiement(paiement);
        personnelMedical.setPassword("iloveyou");
        personnelMedical.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedical.setPostedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedical.setPrenom("Prenom");
        personnelMedical.setRoles(new HashSet<>());
        personnelMedical.setSalleAttente(salleAttente);
        personnelMedical.setService("Service");
        personnelMedical.setServiceConsultation(serviceConsultation);
        personnelMedical.setTelephone("4105551212");
        personnelMedical.setTitre("Titre");
        personnelMedical.setUsername("janedoe");

        ArrayList<PersonnelMedical> personnelMedicalList = new ArrayList<>();
        personnelMedicalList.add(personnelMedical);
        when(this.utilisateurRepository.findPersonnelMedicals()).thenReturn(personnelMedicalList);
        assertEquals(1, this.utilisateurServiceImpl.getPersonnelMedicals().size());
        verify(this.utilisateurRepository).findPersonnelMedicals();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getPersonnelMedicals()}
     */
    @Test
    void testGetPersonnelMedicals3() {
        when(this.utilisateurRepository.findPersonnelMedicals()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.utilisateurServiceImpl.getPersonnelMedicals());
        verify(this.utilisateurRepository).findPersonnelMedicals();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getPersonnelMedicals()}
     */
    @Test
    void testGetPersonnelMedicals4() {
        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil1);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");

        PersonnelMedical personnelMedical = new PersonnelMedical();
        personnelMedical.setAccueil(accueil);
        personnelMedical.setEmail("jane.doe@example.org");
        personnelMedical.setFonction("Fonction");
        personnelMedical.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedical.setLastUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedical.setNom("Nom");
        personnelMedical.setPaiement(paiement);
        personnelMedical.setPassword("iloveyou");
        personnelMedical.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedical.setPostedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedical.setPrenom("Prenom");
        personnelMedical.setRoles(roleSet);
        personnelMedical.setSalleAttente(salleAttente);
        personnelMedical.setService("Service");
        personnelMedical.setServiceConsultation(serviceConsultation);
        personnelMedical.setTelephone("4105551212");
        personnelMedical.setTitre("Titre");
        personnelMedical.setUsername("janedoe");

        ArrayList<PersonnelMedical> personnelMedicalList = new ArrayList<>();
        personnelMedicalList.add(personnelMedical);
        when(this.utilisateurRepository.findPersonnelMedicals()).thenReturn(personnelMedicalList);
        assertEquals(1, this.utilisateurServiceImpl.getPersonnelMedicals().size());
        verify(this.utilisateurRepository).findPersonnelMedicals();
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#getPersonnelMedicalById(Long)}
     */
    @Test
    void testGetPersonnelMedicalById() {
        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        ArrayList<Paiement> paiementList = new ArrayList<>();
        accueil.setPaiements(paiementList);
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil1);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");

        PersonnelMedical personnelMedical = new PersonnelMedical();
        personnelMedical.setAccueil(accueil);
        personnelMedical.setEmail("jane.doe@example.org");
        personnelMedical.setFonction("Fonction");
        personnelMedical.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant());
        personnelMedical.setLastUpdatedAt(fromResult);
        personnelMedical.setNom("Nom");
        personnelMedical.setPaiement(paiement);
        personnelMedical.setPassword("iloveyou");
        personnelMedical.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant());
        personnelMedical.setPostedAt(fromResult1);
        personnelMedical.setPrenom("Prenom");
        personnelMedical.setRoles(new HashSet<>());
        personnelMedical.setSalleAttente(salleAttente);
        personnelMedical.setService("Service");
        personnelMedical.setServiceConsultation(serviceConsultation);
        personnelMedical.setTelephone("4105551212");
        personnelMedical.setTitre("Titre");
        personnelMedical.setUsername("janedoe");
        when(this.utilisateurRepository.findPersonnelMedicalById((Long) any())).thenReturn(personnelMedical);
        PersonnelMedicalResponseDto actualPersonnelMedicalById = this.utilisateurServiceImpl.getPersonnelMedicalById(123L);
        assertEquals(accueil1, actualPersonnelMedicalById.getAccueil());
        assertEquals("janedoe", actualPersonnelMedicalById.getUsername());
        assertEquals("jane.doe@example.org", actualPersonnelMedicalById.getEmail());
        assertEquals(123L, actualPersonnelMedicalById.getId().longValue());
        assertEquals("4105551212", actualPersonnelMedicalById.getTelephone());
        assertEquals("Titre", actualPersonnelMedicalById.getTitre());
        assertEquals("alice.liddell@example.org", actualPersonnelMedicalById.getPhoto());
        assertSame(fromResult, actualPersonnelMedicalById.getLastUpdatedAt());
        assertSame(fromResult1, actualPersonnelMedicalById.getPostedAt());
        assertEquals("Prenom", actualPersonnelMedicalById.getPrenom());
        assertSame(serviceConsultation, actualPersonnelMedicalById.getServiceConsultation());
        assertEquals("Service", actualPersonnelMedicalById.getService());
        assertEquals("Fonction", actualPersonnelMedicalById.getFonction());
        assertEquals("Nom", actualPersonnelMedicalById.getNom());
        assertEquals(paiementList, actualPersonnelMedicalById.getRoles());
        assertEquals(salleAttente1, actualPersonnelMedicalById.getSalleAttente());
        assertSame(paiement, actualPersonnelMedicalById.getPaiement());
        verify(this.utilisateurRepository).findPersonnelMedicalById((Long) any());
    }

    /**
     * Method under test: {@link UtilisateurServiceImpl#editPersonnel(SignupRequestPersonnelMedical)}
     */
    @Test
    void testEditPersonnel() {

        UtilisateurRepository utilisateurRepository = mock(UtilisateurRepository.class);
        when(utilisateurRepository.findPersonnelMedicalById((Long) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        UtilisateurServiceImpl utilisateurServiceImpl = new UtilisateurServiceImpl(utilisateurRepository);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil1);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");

        SignupRequestPersonnelMedical signupRequestPersonnelMedical = new SignupRequestPersonnelMedical();
        signupRequestPersonnelMedical.setAccueil(accueil);
        signupRequestPersonnelMedical.setEmail("jane.doe@example.org");
        signupRequestPersonnelMedical.setFonction("Fonction");
        signupRequestPersonnelMedical.setId(123L);
        signupRequestPersonnelMedical.setNom("Nom");
        signupRequestPersonnelMedical.setPaiement(paiement);
        signupRequestPersonnelMedical.setPassword("iloveyou");
        signupRequestPersonnelMedical.setPhoto("alice.liddell@example.org");
        signupRequestPersonnelMedical.setPrenom("Prenom");
        signupRequestPersonnelMedical.setRoles(new HashSet<>());
        signupRequestPersonnelMedical.setSalleAttente(salleAttente);
        signupRequestPersonnelMedical.setService("Service");
        signupRequestPersonnelMedical.setServiceConsultation(serviceConsultation);
        signupRequestPersonnelMedical.setTelephone("4105551212");
        signupRequestPersonnelMedical.setTitre("Titre");
        signupRequestPersonnelMedical.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> utilisateurServiceImpl.editPersonnel(signupRequestPersonnelMedical));
        verify(utilisateurRepository).findPersonnelMedicalById((Long) any());
    }
}

