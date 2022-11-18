package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.FacturationRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.FacturationResponseDto;
import com.datajpa.relationship.service.FacturationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/facturation")
public class FacturationController {

    private final FacturationService facturationService;
@Autowired
    public FacturationController( FacturationService facturationService) {
    this.facturationService = facturationService;

    }

    @PostMapping("/add")
    public ResponseEntity<FacturationResponseDto> addFacturation(
            @RequestBody final FacturationRequestDto facturationRequestDto) {
        FacturationResponseDto facturationResponseDto = facturationService.addFacturation(facturationRequestDto);
        return new ResponseEntity<>(facturationResponseDto, HttpStatus.OK);}

    @GetMapping("/get/{id}")
    public ResponseEntity<FacturationResponseDto> getFacturation(@PathVariable final Long id) {
       FacturationResponseDto facturationResponseDto= facturationService.getFacturationById(id);
        return new ResponseEntity<>(facturationResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FacturationResponseDto>> getFacturations() {
        List<FacturationResponseDto> facturationResponseDtos = facturationService.getFacturations();
        return new ResponseEntity<>(facturationResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FacturationResponseDto> deleteFacturation(@PathVariable final Long id) {
        FacturationResponseDto facturationResponseDto = facturationService.deleteFacturation(id);
        return new ResponseEntity<>(facturationResponseDto, HttpStatus.OK);


    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<FacturationResponseDto> editFacturation(
            @RequestBody final FacturationRequestDto facturationRequestDto,
            @PathVariable final Long id) {
        FacturationResponseDto facturationResponseDto = facturationService.editFacturation(id, facturationRequestDto);
        return new ResponseEntity<>(facturationResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addPaiement/{paiementId}/toFacturation/{facturationId}")
    public ResponseEntity<FacturationResponseDto> addPaiement(@PathVariable final Long paiementId,
                                                           @PathVariable final Long facturationId) {
        FacturationResponseDto facturationResponseDto = facturationService.addPaiementToFacturation(facturationId, paiementId);
        return new ResponseEntity<>(facturationResponseDto, HttpStatus.OK);
    }

    @PostMapping("/deletePaiement/{facturationId}")
    public ResponseEntity<FacturationResponseDto> deletePaiement(@PathVariable final Long facturationId) {
        FacturationResponseDto facturationResponseDto = facturationService.removePaiementFromFacturation(facturationId);
        return new ResponseEntity<>(facturationResponseDto, HttpStatus.OK);
    }
}
