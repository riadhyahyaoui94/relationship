package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.service.SalleAttenteService;
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

@ContextConfiguration(classes = {SalleAttenteController.class})
@ExtendWith(SpringExtension.class)
class SalleAttenteControllerTest {
    @Autowired
    private SalleAttenteController salleAttenteController;

    @MockBean
    private SalleAttenteService salleAttenteService;

    /**
     * Method under test: {@link SalleAttenteController#addSalleAttente(SalleAttenteRequestDto)}
     */
    @Test
    void testAddSalleAttente() throws Exception {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setEtage("Etage");
        salleAttenteResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setNom("Nom");
        salleAttenteResponseDto.setNumOrdre(10);
        salleAttenteResponseDto.setPatients(new ArrayList<>());
        salleAttenteResponseDto.setPersonnelMedicals(new ArrayList<>());
        salleAttenteResponseDto.setServiceConsultations(new ArrayList<>());
        when(this.salleAttenteService.addSalleAttente((SalleAttenteRequestDto) any())).thenReturn(salleAttenteResponseDto);

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        String content = (new ObjectMapper()).writeValueAsString(salleAttenteRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/salleAttente/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\":10,\"serviceConsultations\":[],\"patients\":[],"
                                        + "\"personnelMedicals\":[],\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"createdAt\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link SalleAttenteController#getSalleAttentes()}
     */
    @Test
    void testGetSalleAttentes() throws Exception {
        when(this.salleAttenteService.getSalleAttentes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/salleAttente/getAll");
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SalleAttenteController#getSalleAttentes()}
     */
    @Test
    void testGetSalleAttentes2() throws Exception {
        when(this.salleAttenteService.getSalleAttentes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/salleAttente/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SalleAttenteController#deleteSalleAttente(Long)}
     */
    @Test
    void testDeleteSalleAttente() throws Exception {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setEtage("Etage");
        salleAttenteResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setNom("Nom");
        salleAttenteResponseDto.setNumOrdre(10);
        salleAttenteResponseDto.setPatients(new ArrayList<>());
        salleAttenteResponseDto.setPersonnelMedicals(new ArrayList<>());
        salleAttenteResponseDto.setServiceConsultations(new ArrayList<>());
        when(this.salleAttenteService.deleteSalleAttente((Long) any())).thenReturn(salleAttenteResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/salleAttente/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\":10,\"serviceConsultations\":[],\"patients\":[],"
                                        + "\"personnelMedicals\":[],\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"createdAt\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link SalleAttenteController#deleteSalleAttente(Long)}
     */
    @Test
    void testDeleteSalleAttente2() throws Exception {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setEtage("Etage");
        salleAttenteResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setNom("Nom");
        salleAttenteResponseDto.setNumOrdre(10);
        salleAttenteResponseDto.setPatients(new ArrayList<>());
        salleAttenteResponseDto.setPersonnelMedicals(new ArrayList<>());
        salleAttenteResponseDto.setServiceConsultations(new ArrayList<>());
        when(this.salleAttenteService.deleteSalleAttente((Long) any())).thenReturn(salleAttenteResponseDto);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/salleAttente/delete/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\":10,\"serviceConsultations\":[],\"patients\":[],"
                                        + "\"personnelMedicals\":[],\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"createdAt\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link SalleAttenteController#editSalleAttente(SalleAttenteRequestDto, Long)}
     */
    @Test
    void testEditSalleAttente() throws Exception {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setEtage("Etage");
        salleAttenteResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setNom("Nom");
        salleAttenteResponseDto.setNumOrdre(10);
        salleAttenteResponseDto.setPatients(new ArrayList<>());
        salleAttenteResponseDto.setPersonnelMedicals(new ArrayList<>());
        salleAttenteResponseDto.setServiceConsultations(new ArrayList<>());
        when(this.salleAttenteService.editSalleAttente((Long) any(), (SalleAttenteRequestDto) any()))
                .thenReturn(salleAttenteResponseDto);

        SalleAttenteRequestDto salleAttenteRequestDto = new SalleAttenteRequestDto();
        salleAttenteRequestDto.setEtage("Etage");
        salleAttenteRequestDto.setNom("Nom");
        salleAttenteRequestDto.setNumOrdre(10);
        String content = (new ObjectMapper()).writeValueAsString(salleAttenteRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/salleAttente/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\":10,\"serviceConsultations\":[],\"patients\":[],"
                                        + "\"personnelMedicals\":[],\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"createdAt\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link SalleAttenteController#getSalleAttente(Long)}
     */
    @Test
    void testGetSalleAttente() throws Exception {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setEtage("Etage");
        salleAttenteResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setNom("Nom");
        salleAttenteResponseDto.setNumOrdre(10);
        salleAttenteResponseDto.setPatients(new ArrayList<>());
        salleAttenteResponseDto.setPersonnelMedicals(new ArrayList<>());
        salleAttenteResponseDto.setServiceConsultations(new ArrayList<>());
        when(this.salleAttenteService.getSalleAttenteById((Long) any())).thenReturn(salleAttenteResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/salleAttente/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\":10,\"serviceConsultations\":[],\"patients\":[],"
                                        + "\"personnelMedicals\":[],\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"createdAt\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link SalleAttenteController#getSalleAttente(Long)}
     */
    @Test
    void testGetSalleAttente2() throws Exception {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setEtage("Etage");
        salleAttenteResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttenteResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttenteResponseDto.setNom("Nom");
        salleAttenteResponseDto.setNumOrdre(10);
        salleAttenteResponseDto.setPatients(new ArrayList<>());
        salleAttenteResponseDto.setPersonnelMedicals(new ArrayList<>());
        salleAttenteResponseDto.setServiceConsultations(new ArrayList<>());
        when(this.salleAttenteService.getSalleAttenteById((Long) any())).thenReturn(salleAttenteResponseDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/salleAttente/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.salleAttenteController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"etage\":\"Etage\",\"numOrdre\":10,\"serviceConsultations\":[],\"patients\":[],"
                                        + "\"personnelMedicals\":[],\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"createdAt\":\"1970-01-01 00:00:00\"}"));
    }
}

