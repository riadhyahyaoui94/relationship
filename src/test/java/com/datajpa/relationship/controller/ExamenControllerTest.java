package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.service.ExamenService;
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

@ContextConfiguration(classes = {ExamenController.class})
@ExtendWith(SpringExtension.class)
class ExamenControllerTest {
    @Autowired
    private ExamenController examenController;

    @MockBean
    private ExamenService examenService;

    /**
     * Method under test: {@link ExamenController#addExamen(ExamenRequestDto)}
     */
    @Test
    void testAddExamen() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.addExamen((ExamenRequestDto) any())).thenReturn(examenResponseDto);

        ExamenRequestDto examenRequestDto = new ExamenRequestDto();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        examenRequestDto.setNomExamen("Nom Examen");
        examenRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        examenRequestDto.setPrixExamen(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(examenRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/examen/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#getExamens()}
     */
    @Test
    void testGetExamens() throws Exception {
        when(this.examenService.getExamens()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/examen/getAll");
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ExamenController#getExamens()}
     */
    @Test
    void testGetExamens2() throws Exception {
        when(this.examenService.getExamens()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/examen/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ExamenController#deleteExamen(Long)}
     */
    @Test
    void testDeleteExamen() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.deleteExamen((Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/examen/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#deleteExamen(Long)}
     */
    @Test
    void testDeleteExamen2() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.deleteExamen((Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/examen/delete/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#editExamen(ExamenRequestDto, Long)}
     */
    @Test
    void testEditExamen() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.editExamen((Long) any(), (ExamenRequestDto) any())).thenReturn(examenResponseDto);

        ExamenRequestDto examenRequestDto = new ExamenRequestDto();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        examenRequestDto.setNomExamen("Nom Examen");
        examenRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        examenRequestDto.setPrixExamen(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(examenRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/examen/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#addOrdonnance(Long, Long)}
     */
    @Test
    void testAddOrdonnance() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.addOrdonnanceToExamen((Long) any(), (Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/examen/addOrdonnance/{ordonnanceId}/toExamen/{examenId}", 123L, 123L);
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#addOrdonnance(Long, Long)}
     */
    @Test
    void testAddOrdonnance2() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.addOrdonnanceToExamen((Long) any(), (Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/examen/addOrdonnance/{ordonnanceId}/toExamen/{examenId}", 123L, 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#deleteOrdonnance(Long)}
     */
    @Test
    void testDeleteOrdonnance() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.removeOrdonnanceFromExamen((Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/examen/deleteOrdonnance/{examenId}",
                123L);
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#deleteOrdonnance(Long)}
     */
    @Test
    void testDeleteOrdonnance2() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.removeOrdonnanceFromExamen((Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/examen/deleteOrdonnance/{examenId}", 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#getExamen(Long)}
     */
    @Test
    void testGetExamen() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.getExamenById((Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/examen/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }

    /**
     * Method under test: {@link ExamenController#getExamen(Long)}
     */
    @Test
    void testGetExamen2() throws Exception {
        ExamenResponseDto examenResponseDto = new ExamenResponseDto();
        examenResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setNomExamen("Nom Examen");
        examenResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        examenResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        examenResponseDto.setPriseRDVs(new ArrayList<>());
        examenResponseDto.setPrixExamen(10.0d);
        when(this.examenService.getExamenById((Long) any())).thenReturn(examenResponseDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/examen/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.examenController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomExamen\":\"Nom Examen\",\"prixExamen\":10.0,\"postedAt\":0,\"lastUpdatedAt\":0,\"ordonnances\":[]"
                                        + ",\"priseRDVs\":[]}"));
    }
}

