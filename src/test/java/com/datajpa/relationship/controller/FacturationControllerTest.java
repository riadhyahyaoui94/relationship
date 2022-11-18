package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
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
import com.datajpa.relationship.service.FacturationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FacturationController.class})
@ExtendWith(SpringExtension.class)
class FacturationControllerTest {
    @Autowired
    private FacturationController facturationController;

    @MockBean
    private FacturationService facturationService;

    /**
     * Method under test: {@link FacturationController#addFacturation(FacturationRequestDto)}
     */
    @Test
    void testAddFacturation() throws Exception {
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

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(null);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(new ServiceConsultation());
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        Examen examen3 = new Examen();
        examen3.setFacturations(new ArrayList<>());
        examen3.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setNomExamen("Nom Examen");
        examen3.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setPriseRDVs(new ArrayList<>());
        examen3.setPrixExamen(10.0d);

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(new Examen());
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(new Paiement());
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(new PriseRDV());
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(new SalleAttente());
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(examen3);
        priseRDV.setFacturation(facturation);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(patient);
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen2);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient1);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(detailsPatient);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation1);
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient2);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation2);

        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setDateFacture(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setExamen(examen);
        facturationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setMontantAPayer(10.0d);
        facturationResponseDto.setMontantPaye(10.0d);
        facturationResponseDto.setNomAgent("Nom Agent");
        facturationResponseDto.setNomPatient("Nom Patient");
        facturationResponseDto.setPaiementName("Paiement Name");
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setPriseRDV(priseRDV1);
        facturationResponseDto.setSommeRecue(10.0d);
        facturationResponseDto.setSommeRendue(10.0d);
        facturationResponseDto.setTypePaiement("Type Paiement");
        when(this.facturationService.addFacturation((FacturationRequestDto) any())).thenReturn(facturationResponseDto);

        FacturationRequestDto facturationRequestDto = new FacturationRequestDto();
        facturationRequestDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult69 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationRequestDto.setDateFacture(Date.from(atStartOfDayResult69.atZone(ZoneId.of("UTC")).toInstant()));
        facturationRequestDto.setExamenId(123L);
        facturationRequestDto.setMontantAPayer(10.0d);
        facturationRequestDto.setMontantPaye(10.0d);
        facturationRequestDto.setNomAgent("Nom Agent");
        facturationRequestDto.setNomPatient("Nom Patient");
        facturationRequestDto.setPaiementId(123L);
        facturationRequestDto.setPriseRDVId(123L);
        facturationRequestDto.setSommeRecue(10.0d);
        facturationRequestDto.setSommeRendue(10.0d);
        facturationRequestDto.setTypePaiement("Type Paiement");
        String content = (new ObjectMapper()).writeValueAsString(facturationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/facturation/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomPatient\":\"Nom Patient\",\"codeFacture\":\"Code Facture\",\"montantAPayer\":10.0,\"montantPaye\""
                                        + ":10.0,\"typePaiement\":\"Type Paiement\",\"dateFacture\":0,\"sommeRecue\":10.0,\"sommeRendue\":10.0,\"nomAgent\":\"Nom"
                                        + " Agent\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"examen\":{\"id\":123,"
                                        + "\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0},\"priseRDV\":{\"id\":123,\"dateRDV"
                                        + "\":0,\"heureRDV\":0,\"motif\":\"Motif\",\"payed\":true,\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"},\"paiementName\":\"Paiement Name\"}"));
    }

    /**
     * Method under test: {@link FacturationController#getFacturations()}
     */
    @Test
    void testGetFacturations() throws Exception {
        when(this.facturationService.getFacturations()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/facturation/getAll");
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FacturationController#getFacturations()}
     */
    @Test
    void testGetFacturations2() throws Exception {
        when(this.facturationService.getFacturations()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/facturation/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FacturationController#deleteFacturation(Long)}
     */
    @Test
    void testDeleteFacturation() throws Exception {
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

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(null);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(new ServiceConsultation());
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        Examen examen3 = new Examen();
        examen3.setFacturations(new ArrayList<>());
        examen3.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setNomExamen("Nom Examen");
        examen3.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setPriseRDVs(new ArrayList<>());
        examen3.setPrixExamen(10.0d);

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(new Examen());
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(new Paiement());
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(new PriseRDV());
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(new SalleAttente());
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(examen3);
        priseRDV.setFacturation(facturation);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(patient);
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen2);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient1);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(detailsPatient);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation1);
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient2);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation2);

        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setDateFacture(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setExamen(examen);
        facturationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setMontantAPayer(10.0d);
        facturationResponseDto.setMontantPaye(10.0d);
        facturationResponseDto.setNomAgent("Nom Agent");
        facturationResponseDto.setNomPatient("Nom Patient");
        facturationResponseDto.setPaiementName("Paiement Name");
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setPriseRDV(priseRDV1);
        facturationResponseDto.setSommeRecue(10.0d);
        facturationResponseDto.setSommeRendue(10.0d);
        facturationResponseDto.setTypePaiement("Type Paiement");
        when(this.facturationService.deleteFacturation((Long) any())).thenReturn(facturationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/facturation/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomPatient\":\"Nom Patient\",\"codeFacture\":\"Code Facture\",\"montantAPayer\":10.0,\"montantPaye\""
                                        + ":10.0,\"typePaiement\":\"Type Paiement\",\"dateFacture\":0,\"sommeRecue\":10.0,\"sommeRendue\":10.0,\"nomAgent\":\"Nom"
                                        + " Agent\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"examen\":{\"id\":123,"
                                        + "\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0},\"priseRDV\":{\"id\":123,\"dateRDV"
                                        + "\":0,\"heureRDV\":0,\"motif\":\"Motif\",\"payed\":true,\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"},\"paiementName\":\"Paiement Name\"}"));
    }

    /**
     * Method under test: {@link FacturationController#editFacturation(FacturationRequestDto, Long)}
     */
    @Test
    void testEditFacturation() throws Exception {
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

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(null);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(new ServiceConsultation());
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        Examen examen3 = new Examen();
        examen3.setFacturations(new ArrayList<>());
        examen3.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setNomExamen("Nom Examen");
        examen3.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setPriseRDVs(new ArrayList<>());
        examen3.setPrixExamen(10.0d);

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(new Examen());
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(new Paiement());
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(new PriseRDV());
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(new SalleAttente());
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(examen3);
        priseRDV.setFacturation(facturation);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(patient);
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen2);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient1);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(detailsPatient);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation1);
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient2);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation2);

        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setDateFacture(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setExamen(examen);
        facturationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setMontantAPayer(10.0d);
        facturationResponseDto.setMontantPaye(10.0d);
        facturationResponseDto.setNomAgent("Nom Agent");
        facturationResponseDto.setNomPatient("Nom Patient");
        facturationResponseDto.setPaiementName("Paiement Name");
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setPriseRDV(priseRDV1);
        facturationResponseDto.setSommeRecue(10.0d);
        facturationResponseDto.setSommeRendue(10.0d);
        facturationResponseDto.setTypePaiement("Type Paiement");
        when(this.facturationService.editFacturation((Long) any(), (FacturationRequestDto) any()))
                .thenReturn(facturationResponseDto);

        FacturationRequestDto facturationRequestDto = new FacturationRequestDto();
        facturationRequestDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult69 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationRequestDto.setDateFacture(Date.from(atStartOfDayResult69.atZone(ZoneId.of("UTC")).toInstant()));
        facturationRequestDto.setExamenId(123L);
        facturationRequestDto.setMontantAPayer(10.0d);
        facturationRequestDto.setMontantPaye(10.0d);
        facturationRequestDto.setNomAgent("Nom Agent");
        facturationRequestDto.setNomPatient("Nom Patient");
        facturationRequestDto.setPaiementId(123L);
        facturationRequestDto.setPriseRDVId(123L);
        facturationRequestDto.setSommeRecue(10.0d);
        facturationRequestDto.setSommeRendue(10.0d);
        facturationRequestDto.setTypePaiement("Type Paiement");
        String content = (new ObjectMapper()).writeValueAsString(facturationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/facturation/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomPatient\":\"Nom Patient\",\"codeFacture\":\"Code Facture\",\"montantAPayer\":10.0,\"montantPaye\""
                                        + ":10.0,\"typePaiement\":\"Type Paiement\",\"dateFacture\":0,\"sommeRecue\":10.0,\"sommeRendue\":10.0,\"nomAgent\":\"Nom"
                                        + " Agent\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"examen\":{\"id\":123,"
                                        + "\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0},\"priseRDV\":{\"id\":123,\"dateRDV"
                                        + "\":0,\"heureRDV\":0,\"motif\":\"Motif\",\"payed\":true,\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"},\"paiementName\":\"Paiement Name\"}"));
    }

    /**
     * Method under test: {@link FacturationController#addPaiement(Long, Long)}
     */
    @Test
    void testAddPaiement() throws Exception {
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

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(null);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(new ServiceConsultation());
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        Examen examen3 = new Examen();
        examen3.setFacturations(new ArrayList<>());
        examen3.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setNomExamen("Nom Examen");
        examen3.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setPriseRDVs(new ArrayList<>());
        examen3.setPrixExamen(10.0d);

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(new Examen());
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(new Paiement());
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(new PriseRDV());
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(new SalleAttente());
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(examen3);
        priseRDV.setFacturation(facturation);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(patient);
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen2);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient1);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(detailsPatient);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation1);
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient2);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation2);

        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setDateFacture(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setExamen(examen);
        facturationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setMontantAPayer(10.0d);
        facturationResponseDto.setMontantPaye(10.0d);
        facturationResponseDto.setNomAgent("Nom Agent");
        facturationResponseDto.setNomPatient("Nom Patient");
        facturationResponseDto.setPaiementName("Paiement Name");
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setPriseRDV(priseRDV1);
        facturationResponseDto.setSommeRecue(10.0d);
        facturationResponseDto.setSommeRendue(10.0d);
        facturationResponseDto.setTypePaiement("Type Paiement");
        when(this.facturationService.addPaiementToFacturation((Long) any(), (Long) any()))
                .thenReturn(facturationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/facturation/addPaiement/{paiementId}/toFacturation/{facturationId}", 123L, 123L);
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomPatient\":\"Nom Patient\",\"codeFacture\":\"Code Facture\",\"montantAPayer\":10.0,\"montantPaye\""
                                        + ":10.0,\"typePaiement\":\"Type Paiement\",\"dateFacture\":0,\"sommeRecue\":10.0,\"sommeRendue\":10.0,\"nomAgent\":\"Nom"
                                        + " Agent\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"examen\":{\"id\":123,"
                                        + "\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0},\"priseRDV\":{\"id\":123,\"dateRDV"
                                        + "\":0,\"heureRDV\":0,\"motif\":\"Motif\",\"payed\":true,\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"},\"paiementName\":\"Paiement Name\"}"));
    }

    /**
     * Method under test: {@link FacturationController#deletePaiement(Long)}
     */
    @Test
    void testDeletePaiement() throws Exception {
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

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(null);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(new ServiceConsultation());
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        Examen examen3 = new Examen();
        examen3.setFacturations(new ArrayList<>());
        examen3.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setNomExamen("Nom Examen");
        examen3.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setPriseRDVs(new ArrayList<>());
        examen3.setPrixExamen(10.0d);

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(new Examen());
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(new Paiement());
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(new PriseRDV());
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(new SalleAttente());
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(examen3);
        priseRDV.setFacturation(facturation);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(patient);
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen2);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient1);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(detailsPatient);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation1);
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient2);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation2);

        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setDateFacture(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setExamen(examen);
        facturationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setMontantAPayer(10.0d);
        facturationResponseDto.setMontantPaye(10.0d);
        facturationResponseDto.setNomAgent("Nom Agent");
        facturationResponseDto.setNomPatient("Nom Patient");
        facturationResponseDto.setPaiementName("Paiement Name");
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setPriseRDV(priseRDV1);
        facturationResponseDto.setSommeRecue(10.0d);
        facturationResponseDto.setSommeRendue(10.0d);
        facturationResponseDto.setTypePaiement("Type Paiement");
        when(this.facturationService.removePaiementFromFacturation((Long) any())).thenReturn(facturationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/facturation/deletePaiement/{facturationId}", 123L);
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomPatient\":\"Nom Patient\",\"codeFacture\":\"Code Facture\",\"montantAPayer\":10.0,\"montantPaye\""
                                        + ":10.0,\"typePaiement\":\"Type Paiement\",\"dateFacture\":0,\"sommeRecue\":10.0,\"sommeRendue\":10.0,\"nomAgent\":\"Nom"
                                        + " Agent\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"examen\":{\"id\":123,"
                                        + "\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0},\"priseRDV\":{\"id\":123,\"dateRDV"
                                        + "\":0,\"heureRDV\":0,\"motif\":\"Motif\",\"payed\":true,\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"},\"paiementName\":\"Paiement Name\"}"));
    }

    /**
     * Method under test: {@link FacturationController#getFacturation(Long)}
     */
    @Test
    void testGetFacturation() throws Exception {
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

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
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
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setNomExamen("Nom Examen");
        examen1.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        examen1.setPriseRDVs(new ArrayList<>());
        examen1.setPrixExamen(10.0d);

        Examen examen2 = new Examen();
        examen2.setFacturations(new ArrayList<>());
        examen2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setNomExamen("Nom Examen");
        examen2.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        examen2.setPriseRDVs(new ArrayList<>());
        examen2.setPrixExamen(10.0d);

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement = new Paiement();
        paiement.setAccueil(accueil);
        paiement.setAssurance(assurance);
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(null);
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(new ServiceConsultation());
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        Examen examen3 = new Examen();
        examen3.setFacturations(new ArrayList<>());
        examen3.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setNomExamen("Nom Examen");
        examen3.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examen3.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        examen3.setPriseRDVs(new ArrayList<>());
        examen3.setPrixExamen(10.0d);

        Facturation facturation = new Facturation();
        facturation.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setDateFacture(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setExamen(new Examen());
        facturation.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setMontantAPayer(10.0d);
        facturation.setMontantPaye(10.0d);
        facturation.setNomAgent("Nom Agent");
        facturation.setPaiement(new Paiement());
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        facturation.setPriseRDV(new PriseRDV());
        facturation.setSommeRecue(10.0d);
        facturation.setSommeRendue(10.0d);
        facturation.setTypePaiement("Type Paiement");

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(new DetailsPatient());
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(new Paiement());
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(new SalleAttente());
        patient.setTauxAssurance(10.0d);

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(new SalleAttente());
        serviceConsultation1.setTypeService("Type Service");

        PriseRDV priseRDV = new PriseRDV();
        priseRDV.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setDateRDV(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setExamen(examen3);
        priseRDV.setFacturation(facturation);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setHeureRDV(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setId(123L);
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setLastUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setMotif("Motif");
        priseRDV.setPatient(patient);
        priseRDV.setPayed(true);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV.setPostedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV.setServiceConsultation(serviceConsultation1);

        Facturation facturation1 = new Facturation();
        facturation1.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setDateFacture(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setExamen(examen2);
        facturation1.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setMontantAPayer(10.0d);
        facturation1.setMontantPaye(10.0d);
        facturation1.setNomAgent("Nom Agent");
        facturation1.setPaiement(paiement);
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturation1.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        facturation1.setPriseRDV(priseRDV);
        facturation1.setSommeRecue(10.0d);
        facturation1.setSommeRendue(10.0d);
        facturation1.setTypePaiement("Type Paiement");

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(patient1);
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(detailsPatient);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");

        PriseRDV priseRDV1 = new PriseRDV();
        priseRDV1.setConsultant(consultant);
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setDateRDV(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setExamen(examen1);
        priseRDV1.setFacturation(facturation1);
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setHeureRDV(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setId(123L);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setLastUpdatedAt(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setMotif("Motif");
        priseRDV1.setPatient(patient2);
        priseRDV1.setPayed(true);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        priseRDV1.setPostedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        priseRDV1.setServiceConsultation(serviceConsultation2);

        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setDateFacture(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setExamen(examen);
        facturationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setMontantAPayer(10.0d);
        facturationResponseDto.setMontantPaye(10.0d);
        facturationResponseDto.setNomAgent("Nom Agent");
        facturationResponseDto.setNomPatient("Nom Patient");
        facturationResponseDto.setPaiementName("Paiement Name");
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        facturationResponseDto.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        facturationResponseDto.setPriseRDV(priseRDV1);
        facturationResponseDto.setSommeRecue(10.0d);
        facturationResponseDto.setSommeRendue(10.0d);
        facturationResponseDto.setTypePaiement("Type Paiement");
        when(this.facturationService.getFacturationById((Long) any())).thenReturn(facturationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/facturation/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.facturationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomPatient\":\"Nom Patient\",\"codeFacture\":\"Code Facture\",\"montantAPayer\":10.0,\"montantPaye\""
                                        + ":10.0,\"typePaiement\":\"Type Paiement\",\"dateFacture\":0,\"sommeRecue\":10.0,\"sommeRendue\":10.0,\"nomAgent\":\"Nom"
                                        + " Agent\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"examen\":{\"id\":123,"
                                        + "\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0},\"priseRDV\":{\"id\":123,\"dateRDV"
                                        + "\":0,\"heureRDV\":0,\"motif\":\"Motif\",\"payed\":true,\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"},\"paiementName\":\"Paiement Name\"}"));
    }
}

