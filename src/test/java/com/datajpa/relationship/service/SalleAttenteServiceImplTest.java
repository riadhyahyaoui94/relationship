package com.datajpa.relationship.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.PersonnelMedical;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.repository.SalleAttenteRepository;
import com.datajpa.relationship.repository.ServiceConsultationRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SalleAttenteServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SalleAttenteServiceImplTest {
    @MockBean
    private AccueilService accueilService;

    @MockBean
    private SalleAttenteRepository salleAttenteRepository;

    @Autowired
    private SalleAttenteServiceImpl salleAttenteServiceImpl;

    @MockBean
    private ServiceConsultationRepository serviceConsultationRepository;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link SalleAttenteServiceImpl#addSalleAttente(SalleAttenteRequestDto)}
     */
    @Test
    void testAddSalleAttente() {
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
        when(this.salleAttenteRepository.save((SalleAttente) any())).thenReturn(salleAttente);

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        SalleAttenteResponseDto actualAddSalleAttenteResult = this.salleAttenteServiceImpl
                .addSalleAttente(salleAttenteRequestDto);
        assertNull(actualAddSalleAttenteResult.getCreatedAt());
        assertNull(actualAddSalleAttenteResult.getServiceConsultations());
        assertNull(actualAddSalleAttenteResult.getPersonnelMedicals());
        assertNull(actualAddSalleAttenteResult.getPatients());
        assertNull(actualAddSalleAttenteResult.getNumOrdre());
        assertEquals("Nom", actualAddSalleAttenteResult.getNom());
        assertNull(actualAddSalleAttenteResult.getLastUpdatedAt());
        assertNull(actualAddSalleAttenteResult.getId());
        assertEquals("Etage", actualAddSalleAttenteResult.getEtage());
        verify(this.salleAttenteRepository).save((SalleAttente) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#addSalleAttente(SalleAttenteRequestDto)}
     */
    @Test
    void testAddSalleAttente2() {
        when(this.salleAttenteRepository.save((SalleAttente) any())).thenThrow(new IllegalArgumentException("foo"));

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        assertThrows(IllegalArgumentException.class,
                () -> this.salleAttenteServiceImpl.addSalleAttente(salleAttenteRequestDto));
        verify(this.salleAttenteRepository).save((SalleAttente) any());
    }



    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttentes()}
     */
    @Test
    void testGetSalleAttentes3() {
        when(this.salleAttenteRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.salleAttenteServiceImpl.getSalleAttentes());
        verify(this.salleAttenteRepository).findAll();
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttenteById(Long)}
     */
    @Test
    void testGetSalleAttenteById() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente.setLastUpdatedAt(fromResult);
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente.setPatients(patientList);
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente.setPostedAt(fromResult1);
        salleAttente.setServiceConsultations(new ArrayList<>());
        Optional<SalleAttente> ofResult = Optional.of(salleAttente);
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(ofResult);
        SalleAttenteResponseDto actualSalleAttenteById = this.salleAttenteServiceImpl.getSalleAttenteById(123L);
        assertSame(fromResult1, actualSalleAttenteById.getCreatedAt());
        assertEquals(patientList, actualSalleAttenteById.getServiceConsultations());
        List<PersonnelMedical> personnelMedicals = actualSalleAttenteById.getPersonnelMedicals();
        assertEquals(patientList, personnelMedicals);
        assertEquals(personnelMedicals, actualSalleAttenteById.getPatients());
        assertEquals(10, actualSalleAttenteById.getNumOrdre().intValue());
        assertEquals("Nom", actualSalleAttenteById.getNom());
        assertSame(fromResult, actualSalleAttenteById.getLastUpdatedAt());
        assertEquals(123L, actualSalleAttenteById.getId().longValue());
        assertEquals("Etage", actualSalleAttenteById.getEtage());
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttenteById(Long)}
     */
    @Test
    void testGetSalleAttenteById2() {
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.salleAttenteServiceImpl.getSalleAttenteById(123L));
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttenteById(Long)}
     */
    @Test
    void testGetSalleAttenteById3() {
        when(this.salleAttenteRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.salleAttenteServiceImpl.getSalleAttenteById(123L));
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttente(Long)}
     */
    @Test
    void testGetSalleAttente() {
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
        Optional<SalleAttente> ofResult = Optional.of(salleAttente);
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(salleAttente, this.salleAttenteServiceImpl.getSalleAttente(123L));
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttente(Long)}
     */
    @Test
    void testGetSalleAttente2() {
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.salleAttenteServiceImpl.getSalleAttente(123L));
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#getSalleAttente(Long)}
     */
    @Test
    void testGetSalleAttente3() {
        when(this.salleAttenteRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.salleAttenteServiceImpl.getSalleAttente(123L));
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#deleteSalleAttente(Long)}
     */
    @Test
    void testDeleteSalleAttente() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente.setLastUpdatedAt(fromResult);
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        ArrayList<PersonnelMedical> personnelMedicalList = new ArrayList<>();
        salleAttente.setPersonnelMedicals(personnelMedicalList);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente.setPostedAt(fromResult1);
        salleAttente.setServiceConsultations(new ArrayList<>());
        Optional<SalleAttente> ofResult = Optional.of(salleAttente);
        doNothing().when(this.salleAttenteRepository).delete((SalleAttente) any());
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(ofResult);
        SalleAttenteResponseDto actualDeleteSalleAttenteResult = this.salleAttenteServiceImpl.deleteSalleAttente(123L);
        assertSame(fromResult1, actualDeleteSalleAttenteResult.getCreatedAt());
        assertNull(actualDeleteSalleAttenteResult.getServiceConsultations());
        assertNull(actualDeleteSalleAttenteResult.getPersonnelMedicals());
        assertEquals(personnelMedicalList, actualDeleteSalleAttenteResult.getPatients());
        assertEquals(10, actualDeleteSalleAttenteResult.getNumOrdre().intValue());
        assertEquals("Nom", actualDeleteSalleAttenteResult.getNom());
        assertSame(fromResult, actualDeleteSalleAttenteResult.getLastUpdatedAt());
        assertEquals(123L, actualDeleteSalleAttenteResult.getId().longValue());
        assertEquals("Etage", actualDeleteSalleAttenteResult.getEtage());
        verify(this.salleAttenteRepository).findById((Long) any());
        verify(this.salleAttenteRepository).delete((SalleAttente) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#deleteSalleAttente(Long)}
     */
    @Test
    void testDeleteSalleAttente2() {
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
        Optional<SalleAttente> ofResult = Optional.of(salleAttente);
        doThrow(new IllegalArgumentException("foo")).when(this.salleAttenteRepository).delete((SalleAttente) any());
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> this.salleAttenteServiceImpl.deleteSalleAttente(123L));
        verify(this.salleAttenteRepository).findById((Long) any());
        verify(this.salleAttenteRepository).delete((SalleAttente) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#deleteSalleAttente(Long)}
     */
    @Test
    void testDeleteSalleAttente3() {
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
        when(this.utilisateurRepository.save((PersonnelMedical) any())).thenReturn(personnelMedical);

        Accueil accueil2 = new Accueil();
        accueil2.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil2.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        accueil2.setNom("Nom");
        accueil2.setNouveauPatient(true);
        accueil2.setPaiements(new ArrayList<>());
        accueil2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil2.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));

        Accueil accueil3 = new Accueil();
        accueil3.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil3.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        accueil3.setNom("Nom");
        accueil3.setNouveauPatient(true);
        accueil3.setPaiements(new ArrayList<>());
        accueil3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil3.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil3);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        SalleAttente salleAttente3 = new SalleAttente();
        salleAttente3.setEtage("Etage");
        salleAttente3.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setNom("Nom");
        salleAttente3.setNumOrdre(10);
        salleAttente3.setPatients(new ArrayList<>());
        salleAttente3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente3);
        serviceConsultation1.setTypeService("Type Service");

        PersonnelMedical personnelMedical1 = new PersonnelMedical();
        personnelMedical1.setAccueil(accueil2);
        personnelMedical1.setEmail("jane.doe@example.org");
        personnelMedical1.setFonction("Fonction");
        personnelMedical1.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedical1.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedical1.setNom("Nom");
        personnelMedical1.setPaiement(paiement1);
        personnelMedical1.setPassword("iloveyou");
        personnelMedical1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedical1.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedical1.setPrenom("Prenom");
        personnelMedical1.setRoles(new HashSet<>());
        personnelMedical1.setSalleAttente(salleAttente2);
        personnelMedical1.setService("Service");
        personnelMedical1.setServiceConsultation(serviceConsultation1);
        personnelMedical1.setTelephone("4105551212");
        personnelMedical1.setTitre("Titre");
        personnelMedical1.setUsername("janedoe");

        ArrayList<PersonnelMedical> personnelMedicalList = new ArrayList<>();
        personnelMedicalList.add(personnelMedical1);

        SalleAttente salleAttente4 = new SalleAttente();
        salleAttente4.setEtage("Etage");
        salleAttente4.setId(123L);
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente4.setLastUpdatedAt(fromResult);
        salleAttente4.setNom("Nom");
        salleAttente4.setNumOrdre(10);
        salleAttente4.setPatients(new ArrayList<>());
        salleAttente4.setPersonnelMedicals(personnelMedicalList);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente4.setPostedAt(fromResult1);
        ArrayList<ServiceConsultation> serviceConsultationList = new ArrayList<>();
        salleAttente4.setServiceConsultations(serviceConsultationList);
        Optional<SalleAttente> ofResult = Optional.of(salleAttente4);
        doNothing().when(this.salleAttenteRepository).delete((SalleAttente) any());
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(ofResult);
        SalleAttenteResponseDto actualDeleteSalleAttenteResult = this.salleAttenteServiceImpl.deleteSalleAttente(123L);
        assertSame(fromResult1, actualDeleteSalleAttenteResult.getCreatedAt());
        assertNull(actualDeleteSalleAttenteResult.getServiceConsultations());
        assertNull(actualDeleteSalleAttenteResult.getPersonnelMedicals());
        assertEquals(serviceConsultationList, actualDeleteSalleAttenteResult.getPatients());
        assertEquals(10, actualDeleteSalleAttenteResult.getNumOrdre().intValue());
        assertEquals("Nom", actualDeleteSalleAttenteResult.getNom());
        assertSame(fromResult, actualDeleteSalleAttenteResult.getLastUpdatedAt());
        assertEquals(123L, actualDeleteSalleAttenteResult.getId().longValue());
        assertEquals("Etage", actualDeleteSalleAttenteResult.getEtage());
        verify(this.utilisateurRepository).save((PersonnelMedical) any());
        verify(this.salleAttenteRepository).findById((Long) any());
        verify(this.salleAttenteRepository).delete((SalleAttente) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#editSalleAttente(Long, SalleAttenteRequestDto)}
     */
    @Test
    void testEditSalleAttente() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente.setLastUpdatedAt(fromResult);
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente.setPatients(patientList);
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        salleAttente.setPostedAt(fromResult1);
        salleAttente.setServiceConsultations(new ArrayList<>());
        Optional<SalleAttente> ofResult = Optional.of(salleAttente);
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(ofResult);

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        SalleAttenteResponseDto actualEditSalleAttenteResult = this.salleAttenteServiceImpl.editSalleAttente(123L,
                salleAttenteRequestDto);
        assertSame(fromResult1, actualEditSalleAttenteResult.getCreatedAt());
        assertEquals(patientList, actualEditSalleAttenteResult.getServiceConsultations());
        List<PersonnelMedical> personnelMedicals = actualEditSalleAttenteResult.getPersonnelMedicals();
        assertEquals(patientList, personnelMedicals);
        assertEquals(personnelMedicals, actualEditSalleAttenteResult.getPatients());
        assertEquals(10, actualEditSalleAttenteResult.getNumOrdre().intValue());
        assertEquals("Nom", actualEditSalleAttenteResult.getNom());
        assertSame(fromResult, actualEditSalleAttenteResult.getLastUpdatedAt());
        assertEquals(123L, actualEditSalleAttenteResult.getId().longValue());
        assertEquals("Etage", actualEditSalleAttenteResult.getEtage());
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#editSalleAttente(Long, SalleAttenteRequestDto)}
     */
    @Test
    void testEditSalleAttente2() {
        when(this.salleAttenteRepository.findById((Long) any())).thenReturn(Optional.empty());

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        assertThrows(IllegalArgumentException.class,
                () -> this.salleAttenteServiceImpl.editSalleAttente(123L, salleAttenteRequestDto));
        verify(this.salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalleAttenteServiceImpl#editSalleAttente(Long, SalleAttenteRequestDto)}
     */
    @Test
    void testEditSalleAttente3() {
        when(this.salleAttenteRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        assertThrows(IllegalArgumentException.class,
                () -> this.salleAttenteServiceImpl.editSalleAttente(123L, salleAttenteRequestDto));
        verify(this.salleAttenteRepository).findById((Long) any());
    }
}

