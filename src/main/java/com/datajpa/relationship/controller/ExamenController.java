package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.DossierMedicalRequestDto;
import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.service.DossierMedicalService;
import com.datajpa.relationship.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/examen")
public class ExamenController {
    private final ExamenService examenService;

    @Autowired
    public ExamenController(ExamenService examenService) {
        this.examenService = examenService;

    }

    @PostMapping("/add")
    public ResponseEntity<ExamenResponseDto> addExamen(@RequestBody final ExamenRequestDto examenRequestDto) {
        ExamenResponseDto examen = examenService.addExamen(examenRequestDto);
        return new ResponseEntity<>(examen, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExamenResponseDto> getExamen(@PathVariable final Long id) {
        ExamenResponseDto examen = examenService.getExamenById(id);
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ExamenResponseDto>> getExamens() {
        List<ExamenResponseDto> examens = examenService.getExamens();
        return new ResponseEntity<>(examens, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ExamenResponseDto> deleteExamen(@PathVariable final Long id) {
        ExamenResponseDto examen = examenService.deleteExamen(id);
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ExamenResponseDto> editExamen(@RequestBody final ExamenRequestDto examenRequestDto,
                                             @PathVariable final Long id) {
        ExamenResponseDto examen = examenService.editExamen(id, examenRequestDto);
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }

    @PostMapping("/addOrdonnance/{ordonnanceId}/toExamen/{examenId}")
    public ResponseEntity<ExamenResponseDto> addOrdonnance(@PathVariable final Long ordonnanceId,
                                                @PathVariable final Long examenId) {
        ExamenResponseDto examen = examenService.addOrdonnanceToExamen(examenId, ordonnanceId);
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }

    @PostMapping("/deleteOrdonnance/{examenId}")
    public ResponseEntity<ExamenResponseDto> deleteOrdonnance(@PathVariable final Long examenId) {
        ExamenResponseDto examen = examenService.removeOrdonnanceFromExamen(examenId);
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }
}
