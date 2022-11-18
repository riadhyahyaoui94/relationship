package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.request.PaiementRequestDto;
import com.datajpa.relationship.dto.request.PriseRDVRequestDto;
import com.datajpa.relationship.dto.response.PriseRDVResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.PriseRDV;
import com.datajpa.relationship.service.ExamenService;
import com.datajpa.relationship.service.PriseRDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/priseRDV")
public class PriseRDVController {
    private final PriseRDVService priseRDVService;

    @Autowired
    public PriseRDVController( PriseRDVService priseRDVService) {
        this.priseRDVService = priseRDVService;


    }

    @PostMapping("/add")
    public ResponseEntity<PriseRDVResponseDto> addPriseRDV(@RequestBody final PriseRDVRequestDto priseRDVRequestDto) {
        PriseRDVResponseDto priseRDVResponseDto = priseRDVService.addPriseRDV(priseRDVRequestDto);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PriseRDVResponseDto> getPriseRDV(@PathVariable final Long id) {
        PriseRDVResponseDto priseRDV = priseRDVService.getPriseRDVRes(id);
        return new ResponseEntity<>(priseRDV, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PriseRDVResponseDto>> getPriseRDVS() {
        List<PriseRDVResponseDto> priseRDVS = priseRDVService.getPriseRDVS();
        return new ResponseEntity<>(priseRDVS, HttpStatus.OK);
    }
    @GetMapping("/getByDates/{d1}/{d2}")
    public ResponseEntity<List<PriseRDVResponseDto>> getPriseRDVSByDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable Date d2) {
        List<PriseRDVResponseDto> priseRDVS = priseRDVService.getPriseRDVSByDates(d1,d2);
        return new ResponseEntity<>(priseRDVS, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PriseRDVResponseDto> deletePriseRDV(@PathVariable final Long id) {
        PriseRDVResponseDto priseRDVResponseDto = priseRDVService.deletePriseRDV(id);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PriseRDVResponseDto> editPriseRDV(@RequestBody final PriseRDVRequestDto priseRDVRequestDto,
                                               @PathVariable final Long id) {

        PriseRDVResponseDto priseRDVResponseDto = priseRDVService.editPriseRDV(id, priseRDVRequestDto);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
    }


    @GetMapping("/rdvConsultant/{id}")
    public ResponseEntity<List<PriseRDVResponseDto>> getConsultantPriseRDV(@PathVariable final Long id) {
        List<PriseRDVResponseDto> priseRDVResponseDto = priseRDVService.getPriseRDVByConsultantId(id);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
    }

    @GetMapping("/rdvPatient/{id}")
    public ResponseEntity<List<PriseRDVResponseDto>> getPatientPriseRDV(@PathVariable final Long id) {
        List<PriseRDVResponseDto> priseRDVResponseDto = priseRDVService.getPriseRDVByPatientId(id);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addPatient/{patientId}/toPriseRDV/{priseRDVId}")
    public ResponseEntity<PriseRDVResponseDto> addPatient(@PathVariable final Long patientId,
                                           @PathVariable final Long priseRDVId) {
        PriseRDVResponseDto priseRDVResponseDto = priseRDVService.addPatientToPriseRDV(priseRDVId,patientId);
               return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
    }
    @PostMapping("/deletePatient/{priseRDVId}")
    public ResponseEntity<PriseRDVResponseDto> deletePatient(@PathVariable final Long priseRDVId) {
      PriseRDVResponseDto priseRDVResponseDto = priseRDVService.removePatientFromPriseRDV(priseRDVId);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);

    }
    @PostMapping("/addServiceConsultation/{serviceConsultationId}/toPriseRDV/{priseRDVId}")
    public ResponseEntity<PriseRDVResponseDto> addSeviceConsultation(@PathVariable final Long serviceConsultationId,
                                               @PathVariable final Long priseRDVId) {
        PriseRDVResponseDto priseRDVResponseDto = priseRDVService.addServiceConsultationToPriseRDV(priseRDVId,serviceConsultationId);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
    }
    @PostMapping("/deleteServiceConsultation/{priseRDVId}")
    public ResponseEntity<PriseRDVResponseDto> deleteServiceConsultation(@PathVariable final Long priseRDVId) {
        PriseRDVResponseDto priseRDVResponseDto = priseRDVService.removeServiceConsultationFromPriseRDV(priseRDVId);
        return new ResponseEntity<>(priseRDVResponseDto, HttpStatus.OK);
}
}
