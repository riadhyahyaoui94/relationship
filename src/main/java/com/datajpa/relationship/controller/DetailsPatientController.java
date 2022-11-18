package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.DetailsPatientRequestDto;
import com.datajpa.relationship.dto.request.DetailsPatientRequestDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.service.DetailsPatientService;
import com.datajpa.relationship.service.SalleAttenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/detailsPatient")
public class DetailsPatientController {

    private final DetailsPatientService detailsPatientService;
@Autowired
    public DetailsPatientController(DetailsPatientService detailsPatientService) {
    this.detailsPatientService = detailsPatientService;


    }

    @PostMapping("/add")
    public ResponseEntity<DetailsPatientResponseDto> addDetailsPatient(
            @RequestBody final DetailsPatientRequestDto detailsPatientRequestDto) {
        DetailsPatientResponseDto detailsPatientResponseDto = detailsPatientService.addDetailsPatient(detailsPatientRequestDto);
        return new ResponseEntity<>(detailsPatientResponseDto, HttpStatus.OK);}

    @GetMapping("/get/{id}")
    public ResponseEntity<DetailsPatientResponseDto> getSalleAttente(@PathVariable final Long id) {
       DetailsPatientResponseDto detailsPatientResponseDto= detailsPatientService.getDetailsPatientById(id);
        return new ResponseEntity<>(detailsPatientResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DetailsPatientResponseDto>> getSalleAttentes() {
        List<DetailsPatientResponseDto> detailsPatientResponseDtos = detailsPatientService.getDetailsPatients();
        return new ResponseEntity<>(detailsPatientResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DetailsPatientResponseDto> deleteSalleAttente(@PathVariable final Long id) {
        DetailsPatientResponseDto detailsPatientResponseDto = detailsPatientService.deleteDetailsPatient(id);
        return new ResponseEntity<>(detailsPatientResponseDto, HttpStatus.OK);


    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<DetailsPatientResponseDto> editSalleAttente(
            @RequestBody final DetailsPatientRequestDto detailsPatientRequestDto,
            @PathVariable final Long id) {
        DetailsPatientResponseDto detailsPatientResponseDto = detailsPatientService.editDetailsPatient(id, detailsPatientRequestDto);
        return new ResponseEntity<>(detailsPatientResponseDto, HttpStatus.OK);
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
