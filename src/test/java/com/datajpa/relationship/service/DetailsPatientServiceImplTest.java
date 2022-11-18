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

import com.datajpa.relationship.dto.request.DetailsPatientRequestDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.DetailsPatient;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.repository.DetailsPatientRepository;
import com.datajpa.relationship.repository.PatientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DetailsPatientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DetailsPatientServiceImplTest {
    @MockBean
    private DetailsPatientRepository detailsPatientRepository;

    @Autowired
    private DetailsPatientServiceImpl detailsPatientServiceImpl;

    @MockBean
    private PatientRepository patientRepository;



    /**
     * Method under test: {@link DetailsPatientServiceImpl#getDetailsPatients()}
     */
    @Test
    void testGetDetailsPatients3() {
        when(this.detailsPatientRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.detailsPatientServiceImpl.getDetailsPatients());
        verify(this.detailsPatientRepository).findAll();
    }

    /**
     * Method under test: {@link DetailsPatientServiceImpl#getDetailsPatientById(Long)}
     */
    @Test
    void testGetDetailsPatientById() {
        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(detailsPatient);
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(paiement);
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(salleAttente);
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient1 = new DetailsPatient();
        detailsPatient1.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant());
        detailsPatient1.setHeurePriseCharge(fromResult);
        detailsPatient1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant());
        detailsPatient1.setLastUpdatedAt(fromResult1);
        detailsPatient1.setPatient(patient1);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant());
        detailsPatient1.setPostedAt(fromResult2);
        detailsPatient1.setServiceAffectation("Service Affectation");
        detailsPatient1.setStatus("Status");
        Optional<DetailsPatient> ofResult = Optional.of(detailsPatient1);
        when(this.detailsPatientRepository.findById((Long) any())).thenReturn(ofResult);
        DetailsPatientResponseDto actualDetailsPatientById = this.detailsPatientServiceImpl.getDetailsPatientById(123L);
        assertEquals("Etat Du Patient", actualDetailsPatientById.getEtatDuPatient());
        assertEquals("Status", actualDetailsPatientById.getStatus());
        assertEquals("Service Affectation", actualDetailsPatientById.getServiceAffectation());
        assertSame(fromResult2, actualDetailsPatientById.getPostedAt());
        assertSame(patient1, actualDetailsPatientById.getPatient());
        assertSame(fromResult1, actualDetailsPatientById.getLastUpdatedAt());
        assertSame(fromResult, actualDetailsPatientById.getHeurePriseCharge());
        assertEquals(123L, actualDetailsPatientById.getId().longValue());
        verify(this.detailsPatientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DetailsPatientServiceImpl#getDetailsPatient(Long)}
     */
    @Test
    void testGetDetailsPatient() {
        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(detailsPatient);
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(paiement);
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(salleAttente);
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient1 = new DetailsPatient();
        detailsPatient1.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setHeurePriseCharge(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setPatient(patient1);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setServiceAffectation("Service Affectation");
        detailsPatient1.setStatus("Status");
        Optional<DetailsPatient> ofResult = Optional.of(detailsPatient1);
        when(this.detailsPatientRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(detailsPatient1, this.detailsPatientServiceImpl.getDetailsPatient(123L));
        verify(this.detailsPatientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DetailsPatientServiceImpl#deleteDetailsPatient(Long)}
     */
    @Test
    void testDeleteDetailsPatient() {

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(detailsPatient);
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(paiement);
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(salleAttente);
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient1 = new DetailsPatient();
        detailsPatient1.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant());
        detailsPatient1.setHeurePriseCharge(fromResult);
        detailsPatient1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant());
        detailsPatient1.setLastUpdatedAt(fromResult1);
        detailsPatient1.setPatient(patient1);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant());
        detailsPatient1.setPostedAt(fromResult2);
        detailsPatient1.setServiceAffectation("Service Affectation");
        detailsPatient1.setStatus("Status");
        Optional<DetailsPatient> ofResult = Optional.of(detailsPatient1);
        DetailsPatientRepository detailsPatientRepository = mock(DetailsPatientRepository.class);
        doNothing().when(detailsPatientRepository).delete((DetailsPatient) any());
        when(detailsPatientRepository.findById((Long) any())).thenReturn(ofResult);
        DetailsPatientResponseDto actualDeleteDetailsPatientResult = (new DetailsPatientServiceImpl(
                detailsPatientRepository)).deleteDetailsPatient(1L);
        assertEquals("Etat Du Patient", actualDeleteDetailsPatientResult.getEtatDuPatient());
        assertEquals("Status", actualDeleteDetailsPatientResult.getStatus());
        assertEquals("Service Affectation", actualDeleteDetailsPatientResult.getServiceAffectation());
        assertSame(fromResult2, actualDeleteDetailsPatientResult.getPostedAt());
        assertSame(patient1, actualDeleteDetailsPatientResult.getPatient());
        assertSame(fromResult1, actualDeleteDetailsPatientResult.getLastUpdatedAt());
        assertSame(fromResult, actualDeleteDetailsPatientResult.getHeurePriseCharge());
        assertEquals(123L, actualDeleteDetailsPatientResult.getId().longValue());
        verify(detailsPatientRepository).findById((Long) any());
        verify(detailsPatientRepository).delete((DetailsPatient) any());
    }

    /**
     * Method under test: {@link DetailsPatientServiceImpl#deleteDetailsPatient(Long)}
     */
    @Test
    void testDeleteDetailsPatient2() {

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(detailsPatient);
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(paiement);
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(salleAttente);
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient1 = new DetailsPatient();
        detailsPatient1.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setHeurePriseCharge(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setPatient(patient1);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setServiceAffectation("Service Affectation");
        detailsPatient1.setStatus("Status");
        Optional<DetailsPatient> ofResult = Optional.of(detailsPatient1);
        DetailsPatientRepository detailsPatientRepository = mock(DetailsPatientRepository.class);
        doThrow(new IllegalArgumentException("foo")).when(detailsPatientRepository).delete((DetailsPatient) any());
        when(detailsPatientRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> (new DetailsPatientServiceImpl(detailsPatientRepository)).deleteDetailsPatient(1L));
        verify(detailsPatientRepository).findById((Long) any());
        verify(detailsPatientRepository).delete((DetailsPatient) any());
    }


    /**
     * Method under test: {@link DetailsPatientServiceImpl#editDetailsPatient(Long, DetailsPatientRequestDto)}
     */
    @Test
    void testEditDetailsPatient2() {

        DetailsPatientRepository detailsPatientRepository = mock(DetailsPatientRepository.class);
        when(detailsPatientRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        DetailsPatientServiceImpl detailsPatientServiceImpl = new DetailsPatientServiceImpl(detailsPatientRepository);

        DetailsPatientRequestDto detailsPatientRequestDto = new DetailsPatientRequestDto();
        detailsPatientRequestDto.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setHeurePriseCharge(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatientRequestDto.setPatientId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatientRequestDto.setServiceAffectation("Service Affectation");
        detailsPatientRequestDto.setStatus("Status");
        assertThrows(IllegalArgumentException.class,
                () -> detailsPatientServiceImpl.editDetailsPatient(1L, detailsPatientRequestDto));
        verify(detailsPatientRepository).findById((Long) any());
    }
}

