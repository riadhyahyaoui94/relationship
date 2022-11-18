package com.datajpa.relationship.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.AccueilRequestDto;
import com.datajpa.relationship.dto.response.AccueilResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.repository.AccueilRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccueilServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccueilServiceImplTest {
    @MockBean
    private AccueilRepository accueilRepository;

    @Autowired
    private AccueilServiceImpl accueilServiceImpl;

    /**
     * Method under test: {@link AccueilServiceImpl#addAccueil(AccueilRequestDto)}
     */
    @Test
    void testAddAccueil() {
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
        when(this.accueilRepository.save((Accueil) any())).thenReturn(accueil);

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        AccueilResponseDto actualAddAccueilResult = this.accueilServiceImpl.addAccueil(accueilRequestDto);
        assertEquals(123L, actualAddAccueilResult.getId().longValue());
        assertEquals(paiementList, actualAddAccueilResult.getPersonnelMedicals());
        assertEquals(paiementList, actualAddAccueilResult.getPaiementNames());
        assertEquals("Nom", actualAddAccueilResult.getNom());
        verify(this.accueilRepository).save((Accueil) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#addAccueil(AccueilRequestDto)}
     */
    @Test
    void testAddAccueil2() {
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

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        ArrayList<Paiement> paiementList1 = new ArrayList<>();
        paiementList1.add(paiement);

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(paiementList1);
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilRepository.save((Accueil) any())).thenReturn(accueil1);

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        AccueilResponseDto actualAddAccueilResult = this.accueilServiceImpl.addAccueil(accueilRequestDto);
        assertEquals(123L, actualAddAccueilResult.getId().longValue());
        assertEquals(paiementList, actualAddAccueilResult.getPersonnelMedicals());
        List<String> paiementNames = actualAddAccueilResult.getPaiementNames();
        assertEquals(7, paiementNames.size());
        assertEquals("Prestation", paiementNames.get(0));
        assertEquals("1", paiementNames.get(1));
        assertEquals("10.0", paiementNames.get(4));
        assertEquals("10.0", paiementNames.get(5));
        assertEquals("Nom", actualAddAccueilResult.getNom());
        verify(this.accueilRepository).save((Accueil) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#addAccueil(AccueilRequestDto)}
     */
    @Test
    void testAddAccueil3() {
        when(this.accueilRepository.save((Accueil) any())).thenThrow(new IllegalArgumentException("foo"));

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.addAccueil(accueilRequestDto));
        verify(this.accueilRepository).save((Accueil) any());
    }


    /**
     * Method under test: {@link AccueilServiceImpl#getAccueil()}
     */
    @Test
    void testGetAccueil3() {
        when(this.accueilRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.getAccueil());
        verify(this.accueilRepository).findAll();
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueil(Long)}
     */
    @Test
    void testGetAccueil4() {
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
        Optional<Accueil> ofResult = Optional.of(accueil);
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(accueil, this.accueilServiceImpl.getAccueil(123L));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueil(Long)}
     */
    @Test
    void testGetAccueil5() {
        when(this.accueilRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.getAccueil(123L));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueil(Long)}
     */
    @Test
    void testGetAccueil6() {
        when(this.accueilRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.getAccueil(123L));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueilById(Long)}
     */
    @Test
    void testGetAccueilById() {
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
        Optional<Accueil> ofResult = Optional.of(accueil);
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);
        AccueilResponseDto actualAccueilById = this.accueilServiceImpl.getAccueilById(123L);
        assertEquals(123L, actualAccueilById.getId().longValue());
        assertEquals(paiementList, actualAccueilById.getPersonnelMedicals());
        assertEquals(paiementList, actualAccueilById.getPaiementNames());
        assertEquals("Nom", actualAccueilById.getNom());
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueilById(Long)}
     */
    @Test
    void testGetAccueilById2() {
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

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        ArrayList<Paiement> paiementList1 = new ArrayList<>();
        paiementList1.add(paiement);

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(paiementList1);
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Accueil> ofResult = Optional.of(accueil1);
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);
        AccueilResponseDto actualAccueilById = this.accueilServiceImpl.getAccueilById(123L);
        assertEquals(123L, actualAccueilById.getId().longValue());
        assertEquals(paiementList, actualAccueilById.getPersonnelMedicals());
        List<String> paiementNames = actualAccueilById.getPaiementNames();
        assertEquals(7, paiementNames.size());
        assertEquals("Prestation", paiementNames.get(0));
        assertEquals("1", paiementNames.get(1));
        assertEquals("10.0", paiementNames.get(4));
        assertEquals("10.0", paiementNames.get(5));
        assertEquals("Nom", actualAccueilById.getNom());
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueilById(Long)}
     */
    @Test
    void testGetAccueilById3() {
        when(this.accueilRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.getAccueilById(123L));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#getAccueilById(Long)}
     */
    @Test
    void testGetAccueilById4() {
        when(this.accueilRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.getAccueilById(123L));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil() {
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
        Optional<Accueil> ofResult = Optional.of(accueil);
        doNothing().when(this.accueilRepository).delete((Accueil) any());
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);
        AccueilResponseDto actualDeleteAccueilResult = this.accueilServiceImpl.deleteAccueil(123L);
        assertEquals(123L, actualDeleteAccueilResult.getId().longValue());
        assertEquals(paiementList, actualDeleteAccueilResult.getPersonnelMedicals());
        assertEquals(paiementList, actualDeleteAccueilResult.getPaiementNames());
        assertEquals("Nom", actualDeleteAccueilResult.getNom());
        verify(this.accueilRepository).findById((Long) any());
        verify(this.accueilRepository).delete((Accueil) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil2() {
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
        Optional<Accueil> ofResult = Optional.of(accueil);
        doThrow(new IllegalArgumentException("foo")).when(this.accueilRepository).delete((Accueil) any());
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.deleteAccueil(123L));
        verify(this.accueilRepository).findById((Long) any());
        verify(this.accueilRepository).delete((Accueil) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil3() {
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

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        ArrayList<Paiement> paiementList1 = new ArrayList<>();
        paiementList1.add(paiement);

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(paiementList1);
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Accueil> ofResult = Optional.of(accueil1);
        doNothing().when(this.accueilRepository).delete((Accueil) any());
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);
        AccueilResponseDto actualDeleteAccueilResult = this.accueilServiceImpl.deleteAccueil(123L);
        assertEquals(123L, actualDeleteAccueilResult.getId().longValue());
        assertEquals(paiementList, actualDeleteAccueilResult.getPersonnelMedicals());
        List<String> paiementNames = actualDeleteAccueilResult.getPaiementNames();
        assertEquals(7, paiementNames.size());
        assertEquals("Prestation", paiementNames.get(0));
        assertEquals("1", paiementNames.get(1));
        assertEquals("10.0", paiementNames.get(4));
        assertEquals("10.0", paiementNames.get(5));
        assertEquals("Nom", actualDeleteAccueilResult.getNom());
        verify(this.accueilRepository).findById((Long) any());
        verify(this.accueilRepository).delete((Accueil) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil4() {
        doNothing().when(this.accueilRepository).delete((Accueil) any());
        when(this.accueilRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.deleteAccueil(123L));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#editAccueil(Long, AccueilRequestDto)}
     */
    @Test
    void testEditAccueil() {
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
        Optional<Accueil> ofResult = Optional.of(accueil);
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        AccueilResponseDto actualEditAccueilResult = this.accueilServiceImpl.editAccueil(123L, accueilRequestDto);
        assertEquals(123L, actualEditAccueilResult.getId().longValue());
        assertEquals(paiementList, actualEditAccueilResult.getPersonnelMedicals());
        assertEquals(paiementList, actualEditAccueilResult.getPaiementNames());
        assertEquals("Nom", actualEditAccueilResult.getNom());
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#editAccueil(Long, AccueilRequestDto)}
     */
    @Test
    void testEditAccueil2() {
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

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        ArrayList<Paiement> paiementList1 = new ArrayList<>();
        paiementList1.add(paiement);

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(paiementList1);
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Accueil> ofResult = Optional.of(accueil1);
        when(this.accueilRepository.findById((Long) any())).thenReturn(ofResult);

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        AccueilResponseDto actualEditAccueilResult = this.accueilServiceImpl.editAccueil(123L, accueilRequestDto);
        assertEquals(123L, actualEditAccueilResult.getId().longValue());
        assertEquals(paiementList, actualEditAccueilResult.getPersonnelMedicals());
        List<String> paiementNames = actualEditAccueilResult.getPaiementNames();
        assertEquals(7, paiementNames.size());
        assertEquals("Prestation", paiementNames.get(0));
        assertEquals("1", paiementNames.get(1));
        assertEquals("10.0", paiementNames.get(4));
        assertEquals("10.0", paiementNames.get(5));
        assertEquals("Nom", actualEditAccueilResult.getNom());
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#editAccueil(Long, AccueilRequestDto)}
     */
    @Test
    void testEditAccueil3() {
        when(this.accueilRepository.findById((Long) any())).thenReturn(Optional.empty());

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.editAccueil(123L, accueilRequestDto));
        verify(this.accueilRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccueilServiceImpl#editAccueil(Long, AccueilRequestDto)}
     */
    @Test
    void testEditAccueil4() {
        when(this.accueilRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        assertThrows(IllegalArgumentException.class, () -> this.accueilServiceImpl.editAccueil(123L, accueilRequestDto));
        verify(this.accueilRepository).findById((Long) any());
    }
}

