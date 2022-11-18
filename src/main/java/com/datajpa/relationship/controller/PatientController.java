package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.PatientRequestDto;
import com.datajpa.relationship.dto.response.PatientResponseDto;
import com.datajpa.relationship.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/Patient")
public class PatientController {
    private final PatientService patientService;
@Autowired
    public PatientController( PatientService patientService) {
    this.patientService = patientService;

    }
    @PostMapping("/add")
    public ResponseEntity<PatientResponseDto> addPatient(
            @RequestBody final PatientRequestDto patientRequestDto) {
        PatientResponseDto patientResponseDto = patientService.addPatient(patientRequestDto);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PatientResponseDto> getPatient(@PathVariable final Long id) {
       PatientResponseDto patientResponseDto = patientService.getPatientById(id);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        List<PatientResponseDto> patientResponseDtos = patientService.getPatients();
        Collections.reverse(patientResponseDtos);
        return new ResponseEntity<>(patientResponseDtos, HttpStatus.OK);

    }

    @GetMapping("/getCount")
    public ResponseEntity<Long> CountAssuree() {
        Long counter =patientService.CountAssuree();
        return new ResponseEntity<>(counter,HttpStatus.OK) ;
    }

    @GetMapping("/getAllBySalle/{id}")
    public ResponseEntity<List<PatientResponseDto>> getPatientsBySalle(@PathVariable final Long id) {
        List<PatientResponseDto> patientResponseDtos = patientService.getPatientBySalleId(id);
        return new ResponseEntity<>(patientResponseDtos, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PatientResponseDto> deletePatient(@PathVariable final Long id) {
        PatientResponseDto patientResponseDto = patientService.deletePatient(id);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<PatientResponseDto> editPatient(@PathVariable final Long id,
                                                         @RequestBody final PatientRequestDto patientRequestDto) {
        PatientResponseDto patientResponseDto = patientService.editPatient(id, patientRequestDto);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addSalleAttente/{salleAttenteId}/to/{patientId}")
    private ResponseEntity<PatientResponseDto> addSalleAttente(@PathVariable final Long salleAttenteId,
                                                         @PathVariable final Long patientId) {
       PatientResponseDto patientResponseDto = patientService.addSalleAttenteToPatient(patientId,salleAttenteId);
               return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
    }
    @PostMapping("/removeSalleAttente/{id}")
    private ResponseEntity<PatientResponseDto> removeSalleAttente(@PathVariable final Long id) {
        PatientResponseDto patientResponseDto = patientService.deleteSalleAttenteFromPatient(id);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);

    }
    @PostMapping("/addPaiement/{paiementId}/to/{patientId}")
    private ResponseEntity<PatientResponseDto> addPaiement(@PathVariable final Long paiementId,
                                                               @PathVariable final Long patientId) {
        PatientResponseDto patientResponseDto = patientService.addPaiementToPatient(patientId,paiementId);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
    }
    @PostMapping("/removePaiement/{id}")
    private ResponseEntity<PatientResponseDto> removePaiement(@PathVariable final Long id) {
        PatientResponseDto patientResponseDto = patientService.deletePaiementFromPatient(id);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);

    }
}
