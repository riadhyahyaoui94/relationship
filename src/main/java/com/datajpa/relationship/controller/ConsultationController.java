package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.ConsultationRequestDto;
import com.datajpa.relationship.dto.response.ConsultationResponseDto;
import com.datajpa.relationship.dto.response.ConsultationServiceResponseDto;
import com.datajpa.relationship.model.ConsultationByService;
import com.datajpa.relationship.service.ConsultationService;
import com.datajpa.relationship.service.ServiceConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationService consultationService;
@Autowired
    public ConsultationController(ConsultationService consultationService) {
    this.consultationService = consultationService;

    }

    @PostMapping("/add")
    public ResponseEntity<ConsultationResponseDto> addConsultation(
            @RequestBody final ConsultationRequestDto consultationRequestDto) {

        ConsultationResponseDto serviceConsultationResponseDto = consultationService.addConsultation(consultationRequestDto);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);}

    @GetMapping("/get/{id}")
    public ResponseEntity<ConsultationResponseDto> getConsultation(@PathVariable final Long id) {
      ConsultationResponseDto serviceConsultationResponseDto= consultationService.getConsultationById(id);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ConsultationResponseDto>> getConsultations() {
        List<ConsultationResponseDto> consultationResponseDtos = consultationService.getConsultations();
        return new ResponseEntity<>(consultationResponseDtos, HttpStatus.OK);
    }
    @GetMapping("/getByDates/{d1}/{d2}")
    public ResponseEntity<List<ConsultationResponseDto>> getConsultationsByDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable Date d2) {
        List<ConsultationResponseDto> consultationResponseDtos = consultationService.getConsultationsByDates(d1,d2);
        return new ResponseEntity<>(consultationResponseDtos, HttpStatus.OK);
    }
    @GetMapping("/getByServices")
    public ResponseEntity<List<ConsultationServiceResponseDto>> getConsultationsByServices() {
        List<ConsultationServiceResponseDto> consultations = consultationService.getConsultationByService();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<ConsultationResponseDto>> getConsultationsByDossier(@PathVariable final Long id) {
        List<ConsultationResponseDto> consultationResponseDtos = consultationService.getConsultationsByDossier(id);
        return new ResponseEntity<>(consultationResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ConsultationResponseDto> deleteConsultation(@PathVariable final Long id) {
       ConsultationResponseDto serviceConsultationResponseDto = consultationService.deleteConsultation(id);
        return new ResponseEntity<>(serviceConsultationResponseDto, HttpStatus.OK);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ConsultationResponseDto> editServiceConsultation(
            @RequestBody final ConsultationRequestDto consultationRequestDto,
            @PathVariable final Long id) {
        ConsultationResponseDto consultationResponseDto = consultationService.editConsultation
                (id, consultationRequestDto);
        return new ResponseEntity<>(consultationResponseDto, HttpStatus.OK);
    }

}
