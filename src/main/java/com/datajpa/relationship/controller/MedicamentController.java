package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.request.MedicamentRequestDto;
import com.datajpa.relationship.dto.response.MedicamentResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Medicament;
import com.datajpa.relationship.service.ExamenService;
import com.datajpa.relationship.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/medicament")
public class MedicamentController {
    private final MedicamentService medicamentService;

    @Autowired
    public MedicamentController( MedicamentService medicamentService) {
        this.medicamentService = medicamentService;

    }

    @PostMapping("/add")
    public ResponseEntity<MedicamentResponseDto> addMedicament(@RequestBody final MedicamentRequestDto medicamentRequestDto) {
        MedicamentResponseDto medicament = medicamentService.addMedicament(medicamentRequestDto);
        return new ResponseEntity<>(medicament, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicamentResponseDto> getMedicament(@PathVariable final Long id) {
        MedicamentResponseDto medicament = medicamentService.getMedicamentById(id);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicamentResponseDto>> getMedicaments() {
        List<MedicamentResponseDto> medicaments = medicamentService.getMedicaments();
        return new ResponseEntity<>(medicaments, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MedicamentResponseDto> deleteMedicament(@PathVariable final Long id) {
        MedicamentResponseDto medicament = medicamentService.deleteMedicament(id);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<MedicamentResponseDto> editMedicament(@RequestBody final MedicamentRequestDto medicamentRequestDto,
                                               @PathVariable final Long id) {
        MedicamentResponseDto medicament = medicamentService.editMedicament(id, medicamentRequestDto);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }
    @PostMapping("/addOrdonnance/{ordonnanceId}/toMedicament/{medicamentId}")
    public ResponseEntity<MedicamentResponseDto> addOrdonnance(@PathVariable final Long ordonnanceId,
                                           @PathVariable final Long medicamentId) {
       MedicamentResponseDto medicament = medicamentService.addOrdonnanceToMedicament(medicamentId,ordonnanceId);
               return new ResponseEntity<>(medicament, HttpStatus.OK);
    }
    @PostMapping("/deleteOrdonnance/{medicamentId}")
    public ResponseEntity<MedicamentResponseDto> deleteOrdonnance(@PathVariable final Long medicamentId) {
     MedicamentResponseDto medicament = medicamentService.removeOrdonnanceFromMedicament(medicamentId);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }}

