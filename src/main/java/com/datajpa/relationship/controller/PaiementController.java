package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.PaiementRequestDto;
import com.datajpa.relationship.dto.response.PaiementResponseDto;
import com.datajpa.relationship.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/paiement")
public class PaiementController {
    private final PaiementService paiementService;
@Autowired
    public PaiementController( PaiementService paiementService) {
    this.paiementService = paiementService;

    }
    @PostMapping("/add")
    public ResponseEntity<PaiementResponseDto> addPaiement(
            @RequestBody final PaiementRequestDto paiementRequestDto) {
       PaiementResponseDto paiementResponseDto = paiementService.addPaiement(paiementRequestDto);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PaiementResponseDto> getPaiement(@PathVariable final Long id) {
        PaiementResponseDto paiementResponseDto = paiementService.getPaiementById(id);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PaiementResponseDto>> getPaiements() {
        List<PaiementResponseDto> paiementResponseDtos = paiementService.getPaiements();
        return new ResponseEntity<>(paiementResponseDtos, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PaiementResponseDto> deletePaiement(@PathVariable final Long id) {
        PaiementResponseDto paiementResponseDto = paiementService.deletePaiement(id);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    private ResponseEntity<PaiementResponseDto> editPaiement(@PathVariable final Long id,
                                                         @RequestBody final PaiementRequestDto paiementRequestDto) {
        PaiementResponseDto paiementResponseDto = paiementService.editPaiement(id, paiementRequestDto);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addAccueil/{accueilId}/to/{paiementId}")
    private ResponseEntity<PaiementResponseDto> addAccueil(@PathVariable final Long accueilId,
                                                         @PathVariable final Long paiementId) {
       PaiementResponseDto paiementResponseDto = paiementService.addAccueilToPaiement
               (paiementId, accueilId);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);
    }
    @PostMapping("/removeAccueil/{id}")
    private ResponseEntity<PaiementResponseDto> removeAccueil(@PathVariable final Long id) {
       PaiementResponseDto paiementResponseDto = paiementService.deleteAccueilFromPaiement(id);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);

    }

       @PostMapping("/addAssurance/{assuranceId}/to/{paiementId}")
    private ResponseEntity<PaiementResponseDto> addAssurance(@PathVariable final Long assuranceId,
                                                           @PathVariable final Long paiementId) {
        PaiementResponseDto paiementResponseDto = paiementService.addAssuranceToPaiement(paiementId, assuranceId);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);
    }
    @PostMapping("/removeAssurance/{id}")
    private ResponseEntity<PaiementResponseDto> removeAssurance(@PathVariable final Long id) {
        PaiementResponseDto paiementResponseDto = paiementService.deleteAssuranceFromPaiement(id);
        return new ResponseEntity<>(paiementResponseDto, HttpStatus.OK);

    }
    public PaiementService getPaiementService() {
        return paiementService;
    }
}
