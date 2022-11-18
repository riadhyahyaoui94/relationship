package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.MedicamentRequestDto;
import com.datajpa.relationship.dto.response.MedicamentResponseDto;
import com.datajpa.relationship.service.MedicamentService;
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

@ContextConfiguration(classes = {MedicamentController.class})
@ExtendWith(SpringExtension.class)
class MedicamentControllerTest {
    @Autowired
    private MedicamentController medicamentController;

    @MockBean
    private MedicamentService medicamentService;

    /**
     * Method under test: {@link MedicamentController#addMedicament(MedicamentRequestDto)}
     */
    @Test
    void testAddMedicament() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.addMedicament((MedicamentRequestDto) any())).thenReturn(medicamentResponseDto);

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(medicamentRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicament/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#getMedicaments()}
     */
    @Test
    void testGetMedicaments() throws Exception {
        when(this.medicamentService.getMedicaments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicament/getAll");
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MedicamentController#getMedicaments()}
     */
    @Test
    void testGetMedicaments2() throws Exception {
        when(this.medicamentService.getMedicaments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/medicament/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MedicamentController#deleteMedicament(Long)}
     */
    @Test
    void testDeleteMedicament() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.deleteMedicament((Long) any())).thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicament/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#deleteMedicament(Long)}
     */
    @Test
    void testDeleteMedicament2() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.deleteMedicament((Long) any())).thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/medicament/delete/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#editMedicament(MedicamentRequestDto, Long)}
     */
    @Test
    void testEditMedicament() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.editMedicament((Long) any(), (MedicamentRequestDto) any()))
                .thenReturn(medicamentResponseDto);

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(medicamentRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicament/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#addOrdonnance(Long, Long)}
     */
    @Test
    void testAddOrdonnance() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.addOrdonnanceToMedicament((Long) any(), (Long) any()))
                .thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/medicament/addOrdonnance/{ordonnanceId}/toMedicament/{medicamentId}", 123L, 123L);
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#addOrdonnance(Long, Long)}
     */
    @Test
    void testAddOrdonnance2() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.addOrdonnanceToMedicament((Long) any(), (Long) any()))
                .thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/medicament/addOrdonnance/{ordonnanceId}/toMedicament/{medicamentId}", 123L, 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#deleteOrdonnance(Long)}
     */
    @Test
    void testDeleteOrdonnance() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.removeOrdonnanceFromMedicament((Long) any())).thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/medicament/deleteOrdonnance/{medicamentId}", 123L);
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#deleteOrdonnance(Long)}
     */
    @Test
    void testDeleteOrdonnance2() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.removeOrdonnanceFromMedicament((Long) any())).thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/medicament/deleteOrdonnance/{medicamentId}", 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#getMedicament(Long)}
     */
    @Test
    void testGetMedicament() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.getMedicamentById((Long) any())).thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicament/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }

    /**
     * Method under test: {@link MedicamentController#getMedicament(Long)}
     */
    @Test
    void testGetMedicament2() throws Exception {
        MedicamentResponseDto medicamentResponseDto = new MedicamentResponseDto();
        medicamentResponseDto.setDureeDePrise("Duree De Prise");
        medicamentResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentResponseDto.setNomMedicament("Nom Medicament");
        medicamentResponseDto.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentService.getMedicamentById((Long) any())).thenReturn(medicamentResponseDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/medicament/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.medicamentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nomMedicament\":\"Nom Medicament\",\"dureeDePrise\":\"Duree De Prise\",\"postedAt\":\"1970-01-01"
                                        + " 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"ordonnances\":[]}"));
    }
}

