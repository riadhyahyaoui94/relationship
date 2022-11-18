package com.datajpa.relationship.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.datajpa.relationship.dto.request.UtilisateurRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.PersonnelMedicalResponseDto;
import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.repository.UtilisateurRepository;
import com.datajpa.relationship.service.UtilisateurService;
import com.datajpa.relationship.service.UtilisateurServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UtilisateurController.class})
@ExtendWith(SpringExtension.class)
class UtilisateurControllerTest {
    @Autowired
    private UtilisateurController utilisateurController;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @MockBean
    private UtilisateurService utilisateurService;

    /**
     * Method under test: {@link UtilisateurController#addUtilisateur(UtilisateurRequestDto)}
     */
    @Test
    void testAddUtilisateur() throws Exception {
        when(this.utilisateurService.addUtilisateur((UtilisateurRequestDto) any()))
                .thenReturn(new UtilisateurResponseDto());

        UtilisateurRequestDto utilisateurRequestDto = new UtilisateurRequestDto();
        utilisateurRequestDto.setEmail("jane.doe@example.org");
        utilisateurRequestDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurRequestDto.setNom("Nom");
        utilisateurRequestDto.setPasseword("Passeword");
        utilisateurRequestDto.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurRequestDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurRequestDto.setPrenom("Prenom");
        utilisateurRequestDto.setTelephone("4105551212");
        utilisateurRequestDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(utilisateurRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/utilisateur/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"username\":null,\"email\":null,\"nom\":null,\"prenom\":null,\"telephone\":null,\"photo\":null,"
                                        + "\"lastUpdatedAt\":null,\"postedAt\":null,\"roles\":null}"));
    }

    /**
     * Method under test: {@link UtilisateurController#getConsultant(Long)}
     */
    @Test
    void testGetConsultant() {

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
        UtilisateurRepository utilisateurRepository = mock(UtilisateurRepository.class);
        when(utilisateurRepository.findConsultantById((Long) any())).thenReturn(consultant);
        ResponseEntity<ConsultantResponseDto> actualConsultant = (new UtilisateurController(
                new UtilisateurServiceImpl(utilisateurRepository))).getConsultant(1L);
        assertTrue(actualConsultant.hasBody());
        assertTrue(actualConsultant.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualConsultant.getStatusCode());
        ConsultantResponseDto body = actualConsultant.getBody();
        assertSame(serviceConsultation, body.getServiceConsultation());
        assertEquals(consultationList, body.getRoles());
        assertEquals("Titre", body.getTitre());
        assertEquals("janedoe", body.getUsername());
        assertEquals("Fonction", body.getFonction());
        assertEquals("Prenom", body.getPrenom());
        assertEquals(consultationList, body.getPriseRDVs());
        assertEquals(123L, body.getId().longValue());
        assertSame(rollingCalendar, body.getCalendrier());
        assertSame(fromResult, body.getLastUpdatedAt());
        assertEquals("Nom", body.getNom());
        assertEquals("4105551212", body.getTelephone());
        assertEquals(consultationList, body.getDossierMedicals());
        assertEquals(consultationList, body.getOrdonnances());
        assertEquals("jane.doe@example.org", body.getEmail());
        assertEquals("alice.liddell@example.org", body.getPhoto());
        assertSame(fromResult1, body.getPostedAt());
        verify(utilisateurRepository).findConsultantById((Long) any());
    }



    /**
     * Method under test: {@link UtilisateurController#getConsultants()}
     */
    @Test
    void testGetConsultants() throws Exception {
        when(this.utilisateurService.getConsultants()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/utilisateur/Consultant/getAll");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UtilisateurController#getConsultants()}
     */
    @Test
    void testGetConsultants2() throws Exception {
        when(this.utilisateurService.getConsultants()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/utilisateur/Consultant/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UtilisateurController#getPersonnelMedicals()}
     */
    @Test
    void testGetPersonnelMedicals() throws Exception {
        when(this.utilisateurService.getPersonnelMedicals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/utilisateur/Personnel/getAll");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UtilisateurController#getPersonnelMedicals()}
     */
    @Test
    void testGetPersonnelMedicals2() throws Exception {
        when(this.utilisateurService.getPersonnelMedicals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/utilisateur/Personnel/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UtilisateurController#deleteUtilisateur(Long)}
     */
    @Test
    void testDeleteUtilisateur() throws Exception {
        when(this.utilisateurService.deleteUtilisateur((Long) any())).thenReturn("Delete Utilisateur");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/utilisateur/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted"));
    }

    /**
     * Method under test: {@link UtilisateurController#deleteUtilisateur(Long)}
     */
    @Test
    void testDeleteUtilisateur2() throws Exception {
        when(this.utilisateurService.deleteUtilisateur((Long) any())).thenReturn("Delete Utilisateur");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/utilisateur/delete/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted"));
    }

    /**
     * Method under test: {@link UtilisateurController#getPersonnelMedical(Long)}
     */
    @Test
    void testGetPersonnelMedical() throws Exception {
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

        PersonnelMedicalResponseDto personnelMedicalResponseDto = new PersonnelMedicalResponseDto();
        personnelMedicalResponseDto.setAccueil(accueil);
        personnelMedicalResponseDto.setEmail("jane.doe@example.org");
        personnelMedicalResponseDto.setFonction("Fonction");
        personnelMedicalResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedicalResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedicalResponseDto.setNom("Nom");
        personnelMedicalResponseDto.setPaiement(paiement);
        personnelMedicalResponseDto.setPassword("iloveyou");
        personnelMedicalResponseDto.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        personnelMedicalResponseDto.setPostedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        personnelMedicalResponseDto.setPrenom("Prenom");
        personnelMedicalResponseDto.setRoles(new ArrayList<>());
        personnelMedicalResponseDto.setSalleAttente(salleAttente);
        personnelMedicalResponseDto.setService("Service");
        personnelMedicalResponseDto.setServiceConsultation(serviceConsultation);
        personnelMedicalResponseDto.setTelephone("4105551212");
        personnelMedicalResponseDto.setTitre("Titre");
        personnelMedicalResponseDto.setUsername("janedoe");
        when(this.utilisateurService.getPersonnelMedicalById((Long) any())).thenReturn(personnelMedicalResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/utilisateur/getPersonnelMedical/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe"
                                        + "@example.org\",\"telephone\":\"4105551212\",\"photo\":\"alice.liddell@example.org\",\"titre\":\"Titre\",\"fonction"
                                        + "\":\"Fonction\",\"service\":\"Service\",\"accueil\":{\"id\":123,\"nom\":\"Nom\",\"nouveauPatient\":true,\"postedAt\":0"
                                        + ",\"lastUpdatedAt\":0,\"paiements\":[]},\"salleAttente\":{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\""
                                        + ":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"paiement\":{\"id\":123,\"codeFacture\":1,\"nomAssurance\":\"Nom"
                                        + " Assurance\",\"tauxCouverture\":10.0,\"prestation\":\"Prestation\",\"montantPrestation\":10.0,\"datePaiement\""
                                        + ":0,\"postedAt\":0,\"lastUpdatedAt\":0,\"patients\":[],\"accueil\":{\"id\":123,\"nom\":\"Nom\",\"nouveauPatient\":true"
                                        + ",\"postedAt\":0,\"lastUpdatedAt\":0,\"paiements\":[],\"personnelMedicals\":[]},\"assurance\":{\"id\":123,\"nomPatient"
                                        + "\":\"Nom Patient\",\"prenomPatient\":\"Prenom Patient\",\"identitePatient\":\"Identite Patient\",\"numAffilation"
                                        + "\":10,\"typePrestation\":\"Type Prestation\",\"montantConvention\":10.0,\"datePrestation\":0,\"postedAt\":0,"
                                        + "\"pastUpdatedAt\":0,\"paiements\":[]},\"facturations\":[],\"personnelMedicals\":[]},\"serviceConsultation\":{"
                                        + "\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"postedAt\":\"1970-01-01 00:00:00\","
                                        + "\"lastUpdatedAt\":\"1970-01-01 00:00:00\"},\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"roles\":[]}"));
    }

    /**
     * Method under test: {@link UtilisateurController#getUtilisateur(Long)}
     */
    @Test
    void testGetUtilisateur() throws Exception {
        when(this.utilisateurService.getUtilisateurById((Long) any())).thenReturn(new UtilisateurResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/utilisateur/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"username\":null,\"email\":null,\"nom\":null,\"prenom\":null,\"telephone\":null,\"photo\":null,"
                                        + "\"lastUpdatedAt\":null,\"postedAt\":null,\"roles\":null}"));
    }

    /**
     * Method under test: {@link UtilisateurController#getUtilisateur(Long)}
     */
    @Test
    void testGetUtilisateur2() throws Exception {
        when(this.utilisateurService.getUtilisateurById((Long) any())).thenReturn(new UtilisateurResponseDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/utilisateur/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"username\":null,\"email\":null,\"nom\":null,\"prenom\":null,\"telephone\":null,\"photo\":null,"
                                        + "\"lastUpdatedAt\":null,\"postedAt\":null,\"roles\":null}"));
    }
}

