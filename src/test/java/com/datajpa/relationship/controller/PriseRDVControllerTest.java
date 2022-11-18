package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.datajpa.relationship.dto.request.PriseRDVRequestDto;
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
import com.datajpa.relationship.repository.DetailsPatientRepository;
import com.datajpa.relationship.repository.PaiementRepository;
import com.datajpa.relationship.repository.PatientRepository;
import com.datajpa.relationship.repository.PriseRDVRepository;
import com.datajpa.relationship.repository.SalleAttenteRepository;
import com.datajpa.relationship.service.PaiementServiceImpl;
import com.datajpa.relationship.service.PatientServiceImpl;
import com.datajpa.relationship.service.PriseRDVService;
import com.datajpa.relationship.service.PriseRDVServiceImpl;
import com.datajpa.relationship.service.SalleAttenteServiceImpl;
import com.datajpa.relationship.service.ServiceConsultationServiceImpl;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PriseRDVController.class})
@ExtendWith(SpringExtension.class)
class PriseRDVControllerTest {
    @Autowired
    private PriseRDVController priseRDVController;

    @MockBean
    private PriseRDVService priseRDVService;


    /**
     * Method under test: {@link PriseRDVController#getConsultantPriseRDV(Long)}
     */
    @Test
    void testGetConsultantPriseRDV() throws Exception {
        when(this.priseRDVService.getPriseRDVByConsultantId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/priseRDV/rdvConsultant/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.priseRDVController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PriseRDVController#getConsultantPriseRDV(Long)}
     */
    @Test
    void testGetConsultantPriseRDV2() throws Exception {
        when(this.priseRDVService.getPriseRDVByConsultantId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/priseRDV/rdvConsultant/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.priseRDVController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

