package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.AssuranceRequestDto;
import com.datajpa.relationship.dto.response.AssuranceResponseDto;
import com.datajpa.relationship.service.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/assurance")
public class AssuranceController {

    private final AssuranceService assuranceService;
@Autowired
    public AssuranceController( AssuranceService assuranceService) {
    this.assuranceService = assuranceService;

    }

    @PostMapping("/add")
    public ResponseEntity<AssuranceResponseDto> addAssurance(
            @RequestBody final AssuranceRequestDto assuranceRequestDto) {
        AssuranceResponseDto assuranceResponseDto = assuranceService.addAssurance(assuranceRequestDto);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);}

    @GetMapping("/get/{id}")
    public ResponseEntity<AssuranceResponseDto> getAssurance(@PathVariable final Long id) {
       AssuranceResponseDto assuranceResponseDto= assuranceService.getAssuranceById(id);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AssuranceResponseDto>> getAssurances() {
        List<AssuranceResponseDto> assuranceResponseDtos = assuranceService.getAssurances();
        return new ResponseEntity<>(assuranceResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AssuranceResponseDto> deleteAssurance(@PathVariable final Long id) {
        AssuranceResponseDto assuranceResponseDto = assuranceService.deleteAssurance(id);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);


    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<AssuranceResponseDto> editAssurance(
            @RequestBody final AssuranceRequestDto assuranceRequestDto,
            @PathVariable final Long id) {
        AssuranceResponseDto assuranceResponseDto = assuranceService.editAssurance(id, assuranceRequestDto);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);
    }

}
