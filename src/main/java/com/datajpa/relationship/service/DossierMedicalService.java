package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.DossierMedicalRequestDto;
import com.datajpa.relationship.dto.response.DossierMedicalResponseDto;
import com.datajpa.relationship.model.DossierMedical;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DossierMedicalService {
    public DossierMedicalResponseDto addDossierMedical(DossierMedicalRequestDto dossierMedicalRequestDto);
    public List<DossierMedicalResponseDto> getDossierMedicals();
    public List<DossierMedicalResponseDto> getPatientDossierMedicals(Long id);
    public DossierMedical getDossierMedical(Long dossierMedicalId);
    public DossierMedicalResponseDto getDossierMedicalById(Long dossierMedicalId);
    public DossierMedicalResponseDto deleteDossierMedical(Long dossierMedicalId);
    public DossierMedicalResponseDto editDossierMedical(Long dossierMedicalId, DossierMedicalRequestDto dossierMedicalRequestDto);
    public DossierMedicalResponseDto addPatientToDossierMedical(Long dossierMedicalId, Long patientId);
    public DossierMedicalResponseDto removePatientFromDossierMedical(Long id);
    public DossierMedicalResponseDto addServiceConsultationToDossierMedical(Long dossierMedicalId, Long seviceMedicalId);
    public DossierMedicalResponseDto removeServiceConsultationFromDossierMedical(Long id);

    public DossierMedicalResponseDto addConsultantToDossierMedical(Long dossierMedicalId, Long consultantId);
    public DossierMedicalResponseDto removeConsultantFromDossierMedical(Long id);



}

