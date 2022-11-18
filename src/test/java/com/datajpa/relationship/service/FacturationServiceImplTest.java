package com.datajpa.relationship.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.datajpa.relationship.dto.request.AccueilRequestDto;
import com.datajpa.relationship.dto.request.FacturationRequestDto;
import com.datajpa.relationship.dto.response.FacturationResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.DetailsPatient;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Facturation;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.PriseRDV;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.repository.AccueilRepository;
import com.datajpa.relationship.repository.AssuranceRepository;
import com.datajpa.relationship.repository.DetailsPatientRepository;
import com.datajpa.relationship.repository.ExamenRepository;
import com.datajpa.relationship.repository.FacturationRepository;
import com.datajpa.relationship.repository.PaiementRepository;
import com.datajpa.relationship.repository.PatientRepository;
import com.datajpa.relationship.repository.PriseRDVRepository;
import com.datajpa.relationship.repository.SalleAttenteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FacturationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FacturationServiceImplTest {
    @MockBean
    private DetailsPatientRepository detailsPatientRepository;

    @MockBean
    private ExamenRepository examenRepository;

    @MockBean
    private FacturationRepository facturationRepository;

    @Autowired
    private FacturationServiceImpl facturationServiceImpl;

    @MockBean
    private PaiementService paiementService;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private PriseRDVRepository priseRDVRepository;

    @MockBean
    private SalleAttenteRepository salleAttenteRepository;

    /**
     * Method under test: {@link FacturationServiceImpl#getFacturation(Long)}
     */
    @Test
    void testGetFacturation() {
        Examen examen = new Examen();
        examen.setFacturations(new ArrayList<>());
        examen.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examen.setNomExamen("Nom Examen");
        examen.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examen.setPriseRDVs(new ArrayList<>());
        examen.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

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
        paiement.setAccueil(accueil);
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

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(new SalleAttente());
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        Examen examen1 = new Examen();
        examen1.setFacturations(new ArrayList<>());
        examen1.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(new Accueil());
        paiement1.setAssurance(new Assurance());
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(new Consultant());
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(new Examen());
        priseRDV.setFacturation(new Facturation());
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(new Patient());
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(new ServiceConsultation());

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(examen2);
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(paiement1);
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(priseRDV);
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(new Patient());
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Paiement paiement2 = new Paiement();
        paiement2.setAccueil(new Accueil());
        paiement2.setAssurance(new Assurance());
        paiement2.setCodeFacture(1);
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement2.setDatePaiement(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        paiement2.setFacturations(new ArrayList<>());
        paiement2.setId(123L);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement2.setLastUpdatedAt(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        paiement2.setMontantPrestation(10.0d);
        paiement2.setNomAssurance("Nom Assurance");
        paiement2.setPatients(new ArrayList<>());
        paiement2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement2.setPostedAt(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        paiement2.setPrestation("Prestation");
        paiement2.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(detailsPatient);
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(paiement2);
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(salleAttente);
        patient.setTauxAssurance(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV1);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");
        Optional<Facturation> ofResult = Optional.of(facturation1);
        when(this.facturationRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(facturation1, this.facturationServiceImpl.getFacturation(123L));
        verify(this.facturationRepository).findById((Long) any());
    }



    /**
     * Method under test: {@link FacturationServiceImpl#getFacturationById(Long)}
     */
    @Test
    void testGetFacturationById() {
        Examen examen = new Examen();
        examen.setFacturations(new ArrayList<>());
        examen.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examen.setNomExamen("Nom Examen");
        examen.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examen.setPriseRDVs(new ArrayList<>());
        examen.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

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
        paiement.setAccueil(accueil);
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

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(new SalleAttente());
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        Examen examen1 = new Examen();
        examen1.setFacturations(new ArrayList<>());
        examen1.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(new Accueil());
        paiement1.setAssurance(new Assurance());
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(new Consultant());
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(new Examen());
        priseRDV.setFacturation(new Facturation());
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(new Patient());
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(new ServiceConsultation());

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(examen2);
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(paiement1);
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(priseRDV);
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(new Patient());
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Paiement paiement2 = new Paiement();
        paiement2.setAccueil(new Accueil());
        paiement2.setAssurance(new Assurance());
        paiement2.setCodeFacture(1);
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement2.setDatePaiement(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        paiement2.setFacturations(new ArrayList<>());
        paiement2.setId(123L);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement2.setLastUpdatedAt(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        paiement2.setMontantPrestation(10.0d);
        paiement2.setNomAssurance("Nom Assurance");
        paiement2.setPatients(new ArrayList<>());
        paiement2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement2.setPostedAt(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        paiement2.setPrestation("Prestation");
        paiement2.setTauxCouverture(10.0d);

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(detailsPatient);
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(paiement2);
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(salleAttente);
        patient.setTauxAssurance(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant());
        facturation1.setDateFacture(fromResult);
        facturation1.setExamen(examen);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV1);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");
        Optional<Facturation> ofResult = Optional.of(facturation1);
        when(this.facturationRepository.findById((Long) any())).thenReturn(ofResult);
        FacturationResponseDto actualFacturationById = this.facturationServiceImpl.getFacturationById(123L);
        assertEquals("Code Facture", actualFacturationById.getCodeFacture());
        assertEquals("Type Paiement", actualFacturationById.getTypePaiement());
        assertEquals(10.0d, actualFacturationById.getSommeRendue().doubleValue());
        assertEquals(10.0d, actualFacturationById.getSommeRecue().doubleValue());
        assertSame(priseRDV1, actualFacturationById.getPriseRDV());
        assertSame(fromResult, actualFacturationById.getDateFacture());
        assertEquals(123L, actualFacturationById.getId().longValue());
        assertEquals("Nom Agent", actualFacturationById.getNomAgent());
        assertEquals(examen1, actualFacturationById.getExamen());
        assertEquals(10.0d, actualFacturationById.getMontantAPayer().doubleValue());
        assertEquals(10.0d, actualFacturationById.getMontantPaye().doubleValue());
        verify(this.facturationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FacturationServiceImpl#getFacturations()}
     */
    @Test
    void testGetFacturations3() {
        when(this.facturationRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.facturationServiceImpl.getFacturations());
        verify(this.facturationRepository).findAll();
    }
}

