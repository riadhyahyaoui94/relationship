package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.SignupRequest;
import com.datajpa.relationship.dto.request.SignupRequestConsultant;
import com.datajpa.relationship.dto.request.SignupRequestPersonnelMedical;
import com.datajpa.relationship.dto.request.UtilisateurRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.PersonnelMedicalResponseDto;
import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.repository.UtilisateurRepository;
import com.datajpa.relationship.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/utilisateur")

public class UtilisateurController {
   private final UtilisateurService utilisateurService;
@Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;

    }
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @PostMapping("/add")
    public ResponseEntity<UtilisateurResponseDto> addUtilisateur(
            @RequestBody final UtilisateurRequestDto utilisateurRequestDto) {
        UtilisateurResponseDto utilisateurResponseDto = utilisateurService.addUtilisateur(utilisateurRequestDto);
        return new ResponseEntity<>(utilisateurResponseDto, HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<UtilisateurResponseDto> getUtilisateur(@PathVariable final Long id) {
       UtilisateurResponseDto utilisateurResponseDto = utilisateurService.getUtilisateurById(id);
        return new ResponseEntity<>(utilisateurResponseDto, HttpStatus.OK);

    }

    @GetMapping("/getConsultant/{id}")
    public ResponseEntity<ConsultantResponseDto> getConsultant(@PathVariable final Long id) {
       ConsultantResponseDto consultantResponseDto = utilisateurService.getConsultantById(id);
        return new ResponseEntity<>(consultantResponseDto, HttpStatus.OK);

    }

    @GetMapping("/getPersonnelMedical/{id}")
    public ResponseEntity<PersonnelMedicalResponseDto> getPersonnelMedical(@PathVariable final Long id) {
        PersonnelMedicalResponseDto personnelResponseDto = utilisateurService.getPersonnelMedicalById(id);
        return new ResponseEntity<>(personnelResponseDto, HttpStatus.OK);

    }



    @GetMapping("/getAll")
    public ResponseEntity<List<UtilisateurResponseDto>> getUtilisateurs() {
        List<UtilisateurResponseDto> utilisateurResponseDtos = utilisateurService.getUtilisateurs();
        return new ResponseEntity<>(utilisateurResponseDtos, HttpStatus.OK);

    }
    @GetMapping("Consultant/getAll")
    public ResponseEntity<List<ConsultantResponseDto>> getConsultants() {
        List<ConsultantResponseDto> consultantResponseDtos = utilisateurService.getConsultants();
        return new ResponseEntity<>(consultantResponseDtos, HttpStatus.OK);

    }

    @GetMapping("Personnel/getAll")
    public ResponseEntity<List<PersonnelMedicalResponseDto>> getPersonnelMedicals() {
        List<PersonnelMedicalResponseDto> personnelMedicalResponseDtos = utilisateurService.getPersonnelMedicals();
        return new ResponseEntity<>(personnelMedicalResponseDtos, HttpStatus.OK);

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable final Long id) {
        /*UtilisateurResponseDto utilisateurResponseDto = */
        utilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/edit/Admin")
    private ResponseEntity<UtilisateurResponseDto> editUtilisateur(@RequestBody SignupRequest utilisateurRequestDto) {
        UtilisateurResponseDto utilisateurResponseDto = utilisateurService.editUtilisateur(utilisateurRequestDto);
        return new ResponseEntity<>(utilisateurResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/Consultant")
    private ResponseEntity<ConsultantResponseDto> editUtilisateur( @RequestBody SignupRequestConsultant consultantRequestDto) {
        ConsultantResponseDto consultantResponseDto = utilisateurService.editConsultant(consultantRequestDto);
        return new ResponseEntity<>(consultantResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/Personnel")
    private ResponseEntity<PersonnelMedicalResponseDto> editUtilisateur( @RequestBody SignupRequestPersonnelMedical personnelMedicalRequestDto) {
        PersonnelMedicalResponseDto personnelMedicalResponseDto = utilisateurService.editPersonnel(personnelMedicalRequestDto);
        return new ResponseEntity<>(personnelMedicalResponseDto, HttpStatus.OK);
    }




}
