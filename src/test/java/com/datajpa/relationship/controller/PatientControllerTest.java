package com.datajpa.relationship.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.PatientRequestDto;
import com.datajpa.relationship.dto.response.PatientResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.DetailsPatient;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.repository.AccueilRepository;
import com.datajpa.relationship.repository.AssuranceRepository;
import com.datajpa.relationship.repository.DetailsPatientRepository;
import com.datajpa.relationship.repository.PaiementRepository;
import com.datajpa.relationship.repository.PatientRepository;
import com.datajpa.relationship.repository.SalleAttenteRepository;
import com.datajpa.relationship.service.AccueilServiceImpl;
import com.datajpa.relationship.service.AssuranceServiceImpl;
import com.datajpa.relationship.service.PaiementServiceImpl;
import com.datajpa.relationship.service.PatientService;
import com.datajpa.relationship.service.PatientServiceImpl;
import com.datajpa.relationship.service.SalleAttenteServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
class PatientControllerTest {
    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientService patientService;

    /**
     * Method under test: {@link PatientController#addPatient(PatientRequestDto)}
     */
    @Test
    void testAddPatient() {

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

        Accueil accueil1 = new Accueil();
        accueil1.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        accueil1.setNom("Nom");
        accueil1.setNouveauPatient(true);
        accueil1.setPaiements(new ArrayList<>());
        accueil1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil1.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance1 = new Assurance();
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setDatePrestation(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setId(123L);
        assurance1.setIdentitePatient("Identite Patient");
        assurance1.setMontantConvention(10.0d);
        assurance1.setNomPatient("Nom Patient");
        assurance1.setNumAffilation(10);
        assurance1.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance1.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        assurance1.setPrenomPatient("Prenom Patient");
        assurance1.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil1);
        paiement1.setAssurance(assurance1);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant());
        patient2.setDateNaissance(fromResult);
        patient2.setDetailsPatient(detailsPatient1);
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant());
        patient2.setLastUpdatedAt(fromResult1);
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(paiement1);
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant());
        patient2.setPostedAt(fromResult2);
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(salleAttente1);
        patient2.setTauxAssurance(10.0d);
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.save((Patient) any())).thenReturn(patient2);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente2.setPatients(patientList);
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());
        SalleAttenteRepository salleAttenteRepository = mock(SalleAttenteRepository.class);
        when(salleAttenteRepository.findById((Long) any())).thenReturn(Optional.of(salleAttente2));
        SalleAttenteServiceImpl salleAttenteService = new SalleAttenteServiceImpl(salleAttenteRepository,
                new AccueilServiceImpl(mock(AccueilRepository.class)));

        PaiementRepository paiementRepository = mock(PaiementRepository.class);
        AccueilServiceImpl accueilService = new AccueilServiceImpl(mock(AccueilRepository.class));
        PatientController patientController = new PatientController(new PatientServiceImpl(patientRepository,
                new PaiementServiceImpl(paiementRepository, accueilService,
                        new AssuranceServiceImpl(mock(AssuranceRepository.class))),
                salleAttenteService, mock(DetailsPatientRepository.class)));
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<PatientResponseDto> actualAddPatientResult = patientController
                .addPatient(new PatientRequestDto("Nom", "Prenom", "Genre",
                        Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()), 3, "alice.liddell@example.org",
                        "Domicile", true, "Assurance", 10.0d, "jane.doe@example.org", 123L, 123L, 123L, 123L));
        assertTrue(actualAddPatientResult.hasBody());
        assertTrue(actualAddPatientResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAddPatientResult.getStatusCode());
        PatientResponseDto body = actualAddPatientResult.getBody();
        assertEquals("Prenom", body.getPrenom());
        assertSame(fromResult2, body.getPostedAt());
        assertEquals("alice.liddell@example.org", body.getPhoto());
        assertEquals(paiement, body.getPaiement());
        assertEquals(3, body.getNumIdendite().intValue());
        assertEquals(salleAttente2, body.getSalleAttente());
        assertEquals(10.0d, body.getTauxAssurance().doubleValue());
        assertEquals("Domicile", body.getDomicile());
        assertEquals(patientList, body.getDossierMedical());
        assertEquals("Assurance", body.getAssurance());
        assertEquals("jane.doe@example.org", body.getEmail());
        assertTrue(body.getEstAssure());
        assertEquals(patientList, body.getPriseRDVs());
        assertSame(fromResult, body.getDateNaissance());
        assertEquals("Genre", body.getGenre());
        assertEquals(123L, body.getId().longValue());
        assertSame(detailsPatient1, body.getDetailsPatient());
        assertSame(fromResult1, body.getLastUpdatedAt());
        assertEquals("Nom", body.getNom());
        verify(patientRepository, atLeast(1)).save((Patient) any());
        verify(salleAttenteRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientController#getPatients()}
     */
    @Test
    void testGetPatients() throws Exception {
        when(this.patientService.getPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/getAll");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#getPatients()}
     */
    @Test
    void testGetPatients2() throws Exception {
        when(this.patientService.getPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#CountAssuree()}
     */
    @Test
    void testCountAssuree() throws Exception {
        when(this.patientService.CountAssuree()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/getCount");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link PatientController#CountAssuree()}
     */
    @Test
    void testCountAssuree2() throws Exception {
        when(this.patientService.CountAssuree()).thenReturn(3L);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/getCount");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link PatientController#deletePatient(Long)}
     */
    @Test
    void testDeletePatient() throws Exception {
        when(this.patientService.deletePatient((Long) any())).thenReturn(new PatientResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/Patient/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nom\":null,\"prenom\":null,\"genre\":null,\"dateNaissance\":null,\"numIdendite\":null,\"photo\":null"
                                        + ",\"domicile\":null,\"estAssure\":null,\"assurance\":null,\"tauxAssurance\":null,\"email\":null,\"postedAt\":null"
                                        + ",\"lastUpdatedAt\":null,\"dossierMedical\":null,\"priseRDVs\":null,\"paiement\":null,\"salleAttente\":null,"
                                        + "\"detailsPatient\":null}"));
    }

    /**
     * Method under test: {@link PatientController#getPatient(Long)}
     */
    @Test
    void testGetPatient() throws Exception {
        when(this.patientService.getPatientById((Long) any())).thenReturn(new PatientResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nom\":null,\"prenom\":null,\"genre\":null,\"dateNaissance\":null,\"numIdendite\":null,\"photo\":null"
                                        + ",\"domicile\":null,\"estAssure\":null,\"assurance\":null,\"tauxAssurance\":null,\"email\":null,\"postedAt\":null"
                                        + ",\"lastUpdatedAt\":null,\"dossierMedical\":null,\"priseRDVs\":null,\"paiement\":null,\"salleAttente\":null,"
                                        + "\"detailsPatient\":null}"));
    }

    /**
     * Method under test: {@link PatientController#getPatient(Long)}
     */
    @Test
    void testGetPatient2() throws Exception {
        when(this.patientService.getPatientById((Long) any())).thenReturn(new PatientResponseDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nom\":null,\"prenom\":null,\"genre\":null,\"dateNaissance\":null,\"numIdendite\":null,\"photo\":null"
                                        + ",\"domicile\":null,\"estAssure\":null,\"assurance\":null,\"tauxAssurance\":null,\"email\":null,\"postedAt\":null"
                                        + ",\"lastUpdatedAt\":null,\"dossierMedical\":null,\"priseRDVs\":null,\"paiement\":null,\"salleAttente\":null,"
                                        + "\"detailsPatient\":null}"));
    }

    /**
     * Method under test: {@link PatientController#getPatientsBySalle(Long)}
     */
    @Test
    void testGetPatientsBySalle() throws Exception {
        when(this.patientService.getPatientBySalleId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/getAllBySalle/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#getPatientsBySalle(Long)}
     */
    @Test
    void testGetPatientsBySalle2() throws Exception {
        when(this.patientService.getPatientBySalleId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/getAllBySalle/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

