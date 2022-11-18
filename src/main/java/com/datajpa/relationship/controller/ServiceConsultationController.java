package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.AssuranceRequestDto;
import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.AssuranceResponseDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.dto.response.ServiceConsultationResponseDto;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.service.AssuranceService;
import com.datajpa.relationship.service.ServiceConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/serviceConsultation")
public class ServiceConsultationController {

    private final ServiceConsultationService serviceConsultationService;
@Autowired
    public ServiceConsultationController( ServiceConsultationService serviceConsultationService) {
    this.serviceConsultationService = serviceConsultationService;

    }

    @PostMapping("/add")
    public ResponseEntity<ServiceConsultationResponseDto> addServiceConsultation(
            @RequestBody final ServiceConsultationRequestDto serviceConsultationRequestDto) {

        ServiceConsultationResponseDto serviceConsultationResponseDto = serviceConsultationService.addServiceConsultation(serviceConsultationRequestDto);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);}

    @GetMapping("/get/{id}")
    public ResponseEntity<ServiceConsultationResponseDto> getSeviceConsultation(@PathVariable final Long id) {
      ServiceConsultationResponseDto serviceConsultationResponseDto= serviceConsultationService.getServiceConsultationById(id);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ServiceConsultationResponseDto>> getServiceConsultations() {
        List<ServiceConsultationResponseDto> serviceConsultationResponseDtos = serviceConsultationService.getServiceConsultations();
        return new ResponseEntity<>(serviceConsultationResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceConsultationResponseDto> deleteServiceConsultation(@PathVariable final Long id) {
       ServiceConsultationResponseDto serviceConsultationResponseDto = serviceConsultationService.deleteServiceConsultation(id);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);


    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ServiceConsultationResponseDto> editServiceConsultation(
            @RequestBody final ServiceConsultationRequestDto serviceConsultationRequestDto,
            @PathVariable final Long id) {
        ServiceConsultationResponseDto serviceConsultationResponseDto = serviceConsultationService.editServiceConsultation
                (id, serviceConsultationRequestDto);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addAccueil/{accueilId}/toServiceConsultation/{serviceConsultationId}")
    public ResponseEntity<ServiceConsultationResponseDto> addAccueil(@PathVariable final Long accueilId,
                                                           @PathVariable final Long serviceConsultationId) {
       ServiceConsultationResponseDto serviceConsultationResponseDto = serviceConsultationService.addSalleAttenteToServiceConsultation
                (serviceConsultationId ,accueilId);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);
    }

    @PostMapping("/deleteSalleAttente/{serviceConsultationId}")
    public ResponseEntity<ServiceConsultationResponseDto> deleteAccueil(@PathVariable final Long serviceConsultationId) {
       ServiceConsultationResponseDto serviceConsultationResponseDto = serviceConsultationService.removeSalleAttenteFromServiceConsultation
               (serviceConsultationId);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);
    }

}
