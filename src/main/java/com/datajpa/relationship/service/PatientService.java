package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.PatientRequestDto;
import com.datajpa.relationship.dto.response.PatientResponseDto;
import com.datajpa.relationship.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    public Patient getPatient(Long patientId);

    public PatientResponseDto addPatient(PatientRequestDto patientRequestDto);

    public List<PatientResponseDto> getPatients();

    public PatientResponseDto getPatientById(Long patientId);

    public PatientResponseDto deletePatient(Long patientId);
    public PatientResponseDto editPatient(Long patientId, PatientRequestDto patientRequestDto);
    public PatientResponseDto addSalleAttenteToPatient(Long patientId, Long salleAttenteId);


    public PatientResponseDto deleteSalleAttenteFromPatient(Long id);

    public PatientResponseDto addPaiementToPatient(Long patientId, Long paiementId);

   public PatientResponseDto deletePaiementFromPatient(Long id);

    public List<PatientResponseDto> getPatientBySalleId(Long salleId);

    public Long CountAssuree();
}





