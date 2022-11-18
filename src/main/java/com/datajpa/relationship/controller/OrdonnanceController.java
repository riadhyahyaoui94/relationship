package com.datajpa.relationship.controller;

//import com.datajpa.relationship.dto.request.ConsultantRequestDto;
import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.request.OrdonnanceRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.OrdonnanceResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.service.ExamenService;
import com.datajpa.relationship.service.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/ordonnance")
public class OrdonnanceController {
    private final OrdonnanceService ordonnanceService;

    @Autowired
    public OrdonnanceController(OrdonnanceService ordonnanceService) {
        this.ordonnanceService = ordonnanceService;
         }

    @PostMapping("/add")
    public ResponseEntity<OrdonnanceResponseDto> addOrdonnance(
            @RequestBody final OrdonnanceRequestDto ordonnanceRequestDto) {
        OrdonnanceResponseDto ordonnanceResponseDto = ordonnanceService.addOrdonnance(ordonnanceRequestDto);
        return new ResponseEntity<>(ordonnanceResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrdonnanceResponseDto> getOrdonnance(@PathVariable final Long id) {
       OrdonnanceResponseDto ordonnanceResponseDto = ordonnanceService.getOrdonnanceById(id);
        return new ResponseEntity<>(ordonnanceResponseDto, HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<OrdonnanceResponseDto>> getOrdonnances() {
        List<OrdonnanceResponseDto> ordonnanceResponseDtos = ordonnanceService.getOrdonnances();
        return new ResponseEntity<>(ordonnanceResponseDtos, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrdonnanceResponseDto> deleteOrdonnance(@PathVariable final Long id) {
        OrdonnanceResponseDto ordonnanceResponseDto = ordonnanceService.deleteOrdonnance(id);
        return new ResponseEntity<>(ordonnanceResponseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    private ResponseEntity<OrdonnanceResponseDto> editOrdonnance(@PathVariable final Long id,
                                                                 @RequestBody final OrdonnanceRequestDto ordonnanceRequestDto) {
      OrdonnanceResponseDto ordonnanceResponseDto = ordonnanceService.editOrdonnance(id, ordonnanceRequestDto);
        return new ResponseEntity<>(ordonnanceResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addConsultant/{consultantId}/to/{ordonnanceId}")
    private ResponseEntity<OrdonnanceResponseDto> addConsultant(@PathVariable final Long consultantId,
                                                                         @PathVariable final Long ordonnanceId) {
        OrdonnanceResponseDto ordonnanceResponseDto = ordonnanceService.addConsultantToOrdonnance(ordonnanceId,consultantId);

        return new ResponseEntity<>(ordonnanceResponseDto, HttpStatus.OK);
    }
    @PostMapping("/removeConsltant/{id}")
    private ResponseEntity<OrdonnanceResponseDto> removeConsultant(@PathVariable final Long id) {
        OrdonnanceResponseDto ordonnanceResponseDto= ordonnanceService.deleteConsultantFromOrdonnance(id);

        return new ResponseEntity<>(ordonnanceResponseDto, HttpStatus.OK);

    }

    public OrdonnanceService getOrdonnanceService() {
        return ordonnanceService;
    }
}
