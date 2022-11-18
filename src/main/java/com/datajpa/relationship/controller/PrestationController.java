package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.request.PrestationRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.PrestationResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Prestation;
import com.datajpa.relationship.service.ExamenService;
import com.datajpa.relationship.service.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/prestation")
public class PrestationController {
    private final PrestationService prestationService;

    @Autowired
    public PrestationController(PrestationService prestationService) {
        this.prestationService = prestationService;

    }

    @PostMapping("/add")
    public ResponseEntity<PrestationResponseDto> addPrestation(@RequestBody final PrestationRequestDto prestationRequestDto) {
        PrestationResponseDto prestationResponseDto = prestationService.addPrestation(prestationRequestDto);
        return new ResponseEntity<>(prestationResponseDto, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Prestation> getPrestation(@PathVariable final Long id) {
        Prestation prestation = prestationService.getPrestation(id);
        return new ResponseEntity<>(prestation, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PrestationResponseDto>> getPrestations() {
        List<PrestationResponseDto> prestations = prestationService.getPrestations();
        return new ResponseEntity<>(prestations, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PrestationResponseDto> deletePrestation(@PathVariable final Long id) {
        PrestationResponseDto prestationResponseDto = prestationService.deletePrestation(id);
        return new ResponseEntity<>(prestationResponseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<PrestationResponseDto> editPrestation(@RequestBody final PrestationRequestDto prestationRequestDto,
                                             @PathVariable final Long id) {
        PrestationResponseDto prestationResponseDto = prestationService.editPrestation(id, prestationRequestDto);
        return new ResponseEntity<>(prestationResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addFacturation/{facturationId}/toPrestation/{prestationId}")
    public ResponseEntity<PrestationResponseDto> addFacturation(@PathVariable final Long facturationId,
                                                @PathVariable final Long prestationId) {
        PrestationResponseDto prestationResponseDto= prestationService.addFacturationToPrestation(facturationId, prestationId);
        return new ResponseEntity<>(prestationResponseDto, HttpStatus.OK);
    }

    @PostMapping("/deleteFacturation/{prestationId}")
    public ResponseEntity<PrestationResponseDto> deleteFacturation(@PathVariable final Long prestationId) {
        PrestationResponseDto prestationResponseDto = prestationService.removeFacturationFromPrestation(prestationId);
        return new ResponseEntity<>(prestationResponseDto, HttpStatus.OK);
    }
}
