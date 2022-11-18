package com.datajpa.relationship.controller;

//import com.datajpa.relationship.dto.request.ConsultantRequestDto;
import com.datajpa.relationship.dto.request.DossierMedicalRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.DossierMedicalResponseDto;
import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/dossierMedical")
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;

    @Autowired
    public DossierMedicalController(DossierMedicalService dossierMedicalService) {
        this.dossierMedicalService = dossierMedicalService;
    }

    @PostMapping("/add")
    public ResponseEntity<DossierMedicalResponseDto> addDossierMedical(@RequestBody final DossierMedicalRequestDto
                                                                                   dossierMedicalRequestDto) {
        DossierMedicalResponseDto dossierMedicalResponseDto = dossierMedicalService.addDossierMedical(dossierMedicalRequestDto);
        return new ResponseEntity<>(dossierMedicalResponseDto, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DossierMedicalResponseDto> getDossierMedical(@PathVariable final Long id) {
        DossierMedicalResponseDto dossierMedicalResponseDto = dossierMedicalService.getDossierMedicalById(id);
        return new ResponseEntity<>(dossierMedicalResponseDto, HttpStatus.OK);
    }
    @GetMapping("/patientdossiers/{id}")
    public ResponseEntity<List<DossierMedicalResponseDto>> getPatientDossierMedicals(@PathVariable final Long id) {
        List<DossierMedicalResponseDto> dossierMedicalResponseDtos = dossierMedicalService.getPatientDossierMedicals(id);
        return new ResponseEntity<>(dossierMedicalResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DossierMedicalResponseDto>> getDossierMedicals() {
        List<DossierMedicalResponseDto> dossierMedicalResponseDtos = dossierMedicalService.getDossierMedicals();
        return new ResponseEntity<>(dossierMedicalResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DossierMedicalResponseDto> deleteZipcode(@PathVariable final Long id) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.deleteDossierMedical(id);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DossierMedicalResponseDto> editDossierMedical(@RequestBody final DossierMedicalRequestDto dossierMedicalRequestDto,
                                                                        @PathVariable final Long id) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.editDossierMedical(id, dossierMedicalRequestDto);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping("/addServiceConsultation/{serviceConsultationId}/toDossierMedical/{dossierMedicalId}")
    public ResponseEntity<DossierMedicalResponseDto> addServiceConsultation(@PathVariable final Long serviceConsultationId,
                                                                            @PathVariable final Long dossierMedicalId) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.addServiceConsultationToDossierMedical
                (dossierMedicalId, serviceConsultationId);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping("/deleteServiceConsultation/{dossierMedicalId}")
    public ResponseEntity<DossierMedicalResponseDto> deleteDossierMedical(@PathVariable final Long dossierMedicalId) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.removeServiceConsultationFromDossierMedical(dossierMedicalId);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping("/addPatient/{patientId}/toDossierMedical/{dossierMedicalId}")
    public ResponseEntity<DossierMedicalResponseDto> addPatient(@PathVariable final Long patientId,
                                                                @PathVariable final Long dossierMedicalId) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.addPatientToDossierMedical
                (dossierMedicalId, patientId);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping("/deletePatient/{dossierMedicalId}")
    public ResponseEntity<DossierMedicalResponseDto> deletePatient(@PathVariable final Long dossierMedicalId) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.removePatientFromDossierMedical(dossierMedicalId);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping("/addConsultant/{consultantId}/toDossierMedical/{dossierMedicalId}")
    public ResponseEntity<DossierMedicalResponseDto> addConsultant(@PathVariable final Long consultantId,
                                                                   @PathVariable final Long dossierMedicalId) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.addConsultantToDossierMedical
                (dossierMedicalId, consultantId);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping("/deleteConsultant/{dossierMedicalId}")
    public ResponseEntity<DossierMedicalResponseDto> deleteConsultant(@PathVariable final Long dossierMedicalId) {
        DossierMedicalResponseDto dossierMedical = dossierMedicalService.removeConsultantFromDossierMedical(dossierMedicalId);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

}
