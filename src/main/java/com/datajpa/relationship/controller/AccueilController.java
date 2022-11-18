package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.AccueilRequestDto;

import com.datajpa.relationship.dto.response.AccueilResponseDto;

import com.datajpa.relationship.service.AccueilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceuil")
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') ")
public class AccueilController {

    private final AccueilService accueilService;
@Autowired
    public AccueilController(AccueilService accueilService) {
        this.accueilService = accueilService;
    }
    @PostMapping("/add")
    public ResponseEntity<AccueilResponseDto> addAccueil(
            @RequestBody final AccueilRequestDto accueilRequestDto) {
        AccueilResponseDto accueilResponseDto = accueilService.addAccueil(accueilRequestDto);
        return new ResponseEntity<>(accueilResponseDto, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<AccueilResponseDto> getAccueil(@PathVariable final Long id) {
        AccueilResponseDto accueilResponseDto = accueilService.getAccueilById(id);
        return new ResponseEntity<>(accueilResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AccueilResponseDto>> getAccueils() {
        List<AccueilResponseDto> accueilResponseDtos = accueilService.getAccueil();
        return new ResponseEntity<>(accueilResponseDtos, HttpStatus.OK);
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccueilResponseDto> deleteAccueil(@PathVariable final Long id) {
        AccueilResponseDto accueilResponseDto = accueilService.deleteAccueil(id);
        return new ResponseEntity<>(accueilResponseDto, HttpStatus.OK);
}
    @PutMapping("/edit/{id}")
    public ResponseEntity<AccueilResponseDto> editAccueil(
            @RequestBody final AccueilRequestDto accueilRequestDto,
            @PathVariable final Long id) {
        AccueilResponseDto accueilResponseDto = accueilService.editAccueil(id, accueilRequestDto);
        return new ResponseEntity<>(accueilResponseDto, HttpStatus.OK);
    }
}
