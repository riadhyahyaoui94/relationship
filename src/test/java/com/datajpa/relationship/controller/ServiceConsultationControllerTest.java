package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.ServiceConsultationResponseDto;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.service.ServiceConsultationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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

@ContextConfiguration(classes = {ServiceConsultationController.class})
@ExtendWith(SpringExtension.class)
class ServiceConsultationControllerTest {
    @Autowired
    private ServiceConsultationController serviceConsultationController;

    @MockBean
    private ServiceConsultationService serviceConsultationService;

    /**
     * Method under test: {@link ServiceConsultationController#addAccueil(Long, Long)}
     */
    @Test
    void testAddAccueil() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.addSalleAttenteToServiceConsultation((Long) any(), (Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/serviceConsultation/addAccueil/{accueilId}/toServiceConsultation/{serviceConsultationId}", 123L, 123L);
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#addServiceConsultation(ServiceConsultationRequestDto)}
     */
    @Test
    void testAddServiceConsultation() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.addServiceConsultation((ServiceConsultationRequestDto) any()))
                .thenReturn(serviceConsultationResponseDto);

        ServiceConsultationRequestDto serviceConsultationRequestDto = new ServiceConsultationRequestDto();
        serviceConsultationRequestDto.setNomService("Nom Service");
        serviceConsultationRequestDto.setSalleAttenteId(123L);
        serviceConsultationRequestDto.setTypeService("Type Service");
        String content = (new ObjectMapper()).writeValueAsString(serviceConsultationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/serviceConsultation/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#getServiceConsultations()}
     */
    @Test
    void testGetServiceConsultations() throws Exception {
        when(this.serviceConsultationService.getServiceConsultations()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/serviceConsultation/getAll");
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#getServiceConsultations()}
     */
    @Test
    void testGetServiceConsultations2() throws Exception {
        when(this.serviceConsultationService.getServiceConsultations()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/serviceConsultation/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#deleteServiceConsultation(Long)}
     */
    @Test
    void testDeleteServiceConsultation() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.deleteServiceConsultation((Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/serviceConsultation/delete/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#deleteServiceConsultation(Long)}
     */
    @Test
    void testDeleteServiceConsultation2() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.deleteServiceConsultation((Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/serviceConsultation/delete/{id}",
                123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#editServiceConsultation(ServiceConsultationRequestDto, Long)}
     */
    @Test
    void testEditServiceConsultation() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.editServiceConsultation((Long) any(), (ServiceConsultationRequestDto) any()))
                .thenReturn(serviceConsultationResponseDto);

        ServiceConsultationRequestDto serviceConsultationRequestDto = new ServiceConsultationRequestDto();
        serviceConsultationRequestDto.setNomService("Nom Service");
        serviceConsultationRequestDto.setSalleAttenteId(123L);
        serviceConsultationRequestDto.setTypeService("Type Service");
        String content = (new ObjectMapper()).writeValueAsString(serviceConsultationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/serviceConsultation/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#addAccueil(Long, Long)}
     */
    @Test
    void testAddAccueil2() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.addSalleAttenteToServiceConsultation((Long) any(), (Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/serviceConsultation/addAccueil/{accueilId}/toServiceConsultation/{serviceConsultationId}", 123L, 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.removeSalleAttenteFromServiceConsultation((Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/serviceConsultation/deleteSalleAttente/{serviceConsultationId}", 123L);
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil2() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.removeSalleAttenteFromServiceConsultation((Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/serviceConsultation/deleteSalleAttente/{serviceConsultationId}", 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#getSeviceConsultation(Long)}
     */
    @Test
    void testGetSeviceConsultation() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.getServiceConsultationById((Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/serviceConsultation/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }

    /**
     * Method under test: {@link ServiceConsultationController#getSeviceConsultation(Long)}
     */
    @Test
    void testGetSeviceConsultation2() throws Exception {
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

        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setConsultants(new ArrayList<>());
        serviceConsultationResponseDto.setDossierMedicals(new ArrayList<>());
        serviceConsultationResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto
                .setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setNomService("Nom Service");
        serviceConsultationResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultationResponseDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultationResponseDto.setPriseRDVS(new ArrayList<>());
        serviceConsultationResponseDto.setSalleAttente(salleAttente);
        serviceConsultationResponseDto.setTypeService("Type Service");
        when(this.serviceConsultationService.getServiceConsultationById((Long) any()))
                .thenReturn(serviceConsultationResponseDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/serviceConsultation/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.serviceConsultationController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomService\":\"Nom Service\",\"typeService\":\"Type Service\",\"salleAttente\":{\"id\":123,\"nom\":\"Nom"
                                        + "\",\"etage\":\"Etage\",\"numOrdre\":10,\"postedAt\":0,\"lastUpdatedAt\":0},\"consultants\":[],\"priseRDVS\":[],"
                                        + "\"dossierMedicals\":[],\"personnelMedicals\":[],\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01"
                                        + " 00:00:00\"}"));
    }
}

