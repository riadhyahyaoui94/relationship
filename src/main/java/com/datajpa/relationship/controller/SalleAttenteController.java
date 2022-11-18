package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.AssuranceRequestDto;
import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.AssuranceResponseDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.service.AssuranceService;
import com.datajpa.relationship.service.SalleAttenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/salleAttente")
public class SalleAttenteController {

    private final SalleAttenteService salleAttenteService;
@Autowired
    public SalleAttenteController( SalleAttenteService salleAttenteService) {
    this.salleAttenteService = salleAttenteService;


    }

    @PostMapping("/add")
    public ResponseEntity<SalleAttenteResponseDto> addSalleAttente(
            @RequestBody final SalleAttenteRequestDto salleAttenteRequestDto) {
        SalleAttenteResponseDto salleAttenteResponseDto = salleAttenteService.addSalleAttente(salleAttenteRequestDto);
        return new ResponseEntity<>(salleAttenteResponseDto, HttpStatus.OK);}

    @GetMapping("/get/{id}")
    public ResponseEntity<SalleAttenteResponseDto> getSalleAttente(@PathVariable final Long id) {
       SalleAttenteResponseDto salleAttenteResponseDto= salleAttenteService.getSalleAttenteById(id);
        return new ResponseEntity<>(salleAttenteResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SalleAttenteResponseDto>> getSalleAttentes() {
        List<SalleAttenteResponseDto> salleAttenteResponseDtos = salleAttenteService.getSalleAttentes();
        return new ResponseEntity<>(salleAttenteResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SalleAttenteResponseDto> deleteSalleAttente(@PathVariable final Long id) {
        SalleAttenteResponseDto salleAttenteResponseDto = salleAttenteService.deleteSalleAttente(id);
        return new ResponseEntity<>(salleAttenteResponseDto, HttpStatus.OK);


    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<SalleAttenteResponseDto> editSalleAttente(
            @RequestBody final SalleAttenteRequestDto salleAttenteRequestDto,
            @PathVariable final Long id) {
        SalleAttenteResponseDto salleAttenteResponseDto = salleAttenteService.editSalleAttente(id, salleAttenteRequestDto);
        return new ResponseEntity<>(salleAttenteResponseDto, HttpStatus.OK);
    }
   /* @PostMapping("/addAccueil/{accueilId}/toSalleAttente/{salleAttenteId}")
    public ResponseEntity<SalleAttenteResponseDto> addAccueil(@PathVariable final Long accueilId,
                                                           @PathVariable final Long salleAttenteId) {
       SalleAttenteResponseDto salleAttenteResponseDto = salleAttenteService.addAccueilToSalleAttente(salleAttenteId, accueilId);
        return new ResponseEntity<>(salleAttenteResponseDto, HttpStatus.OK);
    }*/

  /*  @PostMapping("/deleteAccueil/{salleAttenteId}")
    public ResponseEntity<SalleAttenteResponseDto> deleteAccueil(@PathVariable final Long salleAttenteId) {
        SalleAttenteResponseDto salleAttenteResponseDto = salleAttenteService.removeAccueilFromSalleAttente(salleAttenteId);
        return new ResponseEntity<>(salleAttenteResponseDto, HttpStatus.OK);
    }*/

}
