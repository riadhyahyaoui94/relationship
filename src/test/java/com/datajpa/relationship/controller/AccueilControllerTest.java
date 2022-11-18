package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.AccueilRequestDto;
import com.datajpa.relationship.dto.response.AccueilResponseDto;
import com.datajpa.relationship.service.AccueilService;
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

@ContextConfiguration(classes = {AccueilController.class})
@ExtendWith(SpringExtension.class)
class AccueilControllerTest {
    @Autowired
    private AccueilController accueilController;

    @MockBean
    private AccueilService accueilService;

    /**
     * Method under test: {@link AccueilController#addAccueil(AccueilRequestDto)}
     */
    @Test
    void testAddAccueil() throws Exception {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
        accueilResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueilResponseDto.setNom("Nom");
        accueilResponseDto.setPaiementNames(new ArrayList<>());
        accueilResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilService.addAccueil((AccueilRequestDto) any())).thenReturn(accueilResponseDto);

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        String content = (new ObjectMapper()).writeValueAsString(accueilRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/acceuil/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"paiementNames"
                                        + "\":[],\"personnelMedicals\":[]}"));
    }

    /**
     * Method under test: {@link AccueilController#getAccueils()}
     */
    @Test
    void testGetAccueils() throws Exception {
        when(this.accueilService.getAccueil()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/acceuil/getAll");
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccueilController#getAccueils()}
     */
    @Test
    void testGetAccueils2() throws Exception {
        when(this.accueilService.getAccueil()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/acceuil/getAll");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccueilController#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil() throws Exception {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
        accueilResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueilResponseDto.setNom("Nom");
        accueilResponseDto.setPaiementNames(new ArrayList<>());
        accueilResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilService.deleteAccueil((Long) any())).thenReturn(accueilResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/acceuil/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"paiementNames"
                                        + "\":[],\"personnelMedicals\":[]}"));
    }

    /**
     * Method under test: {@link AccueilController#deleteAccueil(Long)}
     */
    @Test
    void testDeleteAccueil2() throws Exception {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
        accueilResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueilResponseDto.setNom("Nom");
        accueilResponseDto.setPaiementNames(new ArrayList<>());
        accueilResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilService.deleteAccueil((Long) any())).thenReturn(accueilResponseDto);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/acceuil/delete/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"paiementNames"
                                        + "\":[],\"personnelMedicals\":[]}"));
    }

    /**
     * Method under test: {@link AccueilController#editAccueil(AccueilRequestDto, Long)}
     */
    @Test
    void testEditAccueil() throws Exception {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
        accueilResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueilResponseDto.setNom("Nom");
        accueilResponseDto.setPaiementNames(new ArrayList<>());
        accueilResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilService.editAccueil((Long) any(), (AccueilRequestDto) any())).thenReturn(accueilResponseDto);

        AccueilRequestDto accueilRequestDto = new AccueilRequestDto();
        accueilRequestDto.setNom("Nom");
        accueilRequestDto.setNouveauPatient(true);
        String content = (new ObjectMapper()).writeValueAsString(accueilRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/acceuil/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"paiementNames"
                                        + "\":[],\"personnelMedicals\":[]}"));
    }

    /**
     * Method under test: {@link AccueilController#getAccueil(Long)}
     */
    @Test
    void testGetAccueil() throws Exception {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
        accueilResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueilResponseDto.setNom("Nom");
        accueilResponseDto.setPaiementNames(new ArrayList<>());
        accueilResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilService.getAccueilById((Long) any())).thenReturn(accueilResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/acceuil/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"paiementNames"
                                        + "\":[],\"personnelMedicals\":[]}"));
    }

    /**
     * Method under test: {@link AccueilController#getAccueil(Long)}
     */
    @Test
    void testGetAccueil2() throws Exception {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
        accueilResponseDto.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        accueilResponseDto.setNom("Nom");
        accueilResponseDto.setPaiementNames(new ArrayList<>());
        accueilResponseDto.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueilResponseDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.accueilService.getAccueilById((Long) any())).thenReturn(accueilResponseDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/acceuil/get/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.accueilController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nom\":\"Nom\",\"postedAt\":\"1970-01-01 00:00:00\",\"lastUpdatedAt\":\"1970-01-01 00:00:00\",\"paiementNames"
                                        + "\":[],\"personnelMedicals\":[]}"));
    }
}

