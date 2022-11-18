package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.SignupRequest;
import com.datajpa.relationship.dto.request.SignupRequestConsultant;
import com.datajpa.relationship.dto.request.SignupRequestPersonnelMedical;
import com.datajpa.relationship.dto.request.UtilisateurRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.PersonnelMedicalResponseDto;
import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService {


    public UtilisateurResponseDto addUtilisateur(UtilisateurRequestDto utilisateurRequestDto);
    public List<UtilisateurResponseDto> getUtilisateurs();
    public UtilisateurResponseDto getUtilisateurById(Long utilisateurId);
    public PersonnelMedicalResponseDto getPersonnelMedicalById(Long personnelId);
    public ConsultantResponseDto getConsultantById(Long consultantId);

    public Utilisateur getUtilisateur(Long utilisateurId);
    public String deleteUtilisateur(Long utilisateurId);
    public UtilisateurResponseDto editUtilisateur(SignupRequest utilisateurRequestDto);
    public ConsultantResponseDto editConsultant( SignupRequestConsultant consultantRequestDto);
    public PersonnelMedicalResponseDto editPersonnel(SignupRequestPersonnelMedical personnelMedicalRequestDto);
    public List<ConsultantResponseDto> getConsultants();
    public List<PersonnelMedicalResponseDto> getPersonnelMedicals();

}
