package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.DossierMedicalRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.DossierMedicalResponseDto;
import com.datajpa.relationship.dto.response.OrdonnanceResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class DossierMedicalServiceImpl implements DossierMedicalService {
    public final DossierMedicalRepository dossierMedicalRepository;
    private final ServiceConsultationService serviceConsultationService;
    private final UtilisateurService utilisateurService;
    private final PatientService  patientService;
@Autowired
    public DossierMedicalServiceImpl(DossierMedicalRepository dossierMedicalRepository, ServiceConsultationService
            serviceConsultationService, UtilisateurService utilisateurService, PatientService patientService) {
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.serviceConsultationService = serviceConsultationService;
        this.utilisateurService = utilisateurService;
    this.patientService = patientService;
}
@Transactional
    @Override
    public DossierMedicalResponseDto addDossierMedical(DossierMedicalRequestDto dossierMedicalRequestDto) {
        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setNomPatient(dossierMedicalRequestDto.getNomPatient());
        dossierMedical.setPrenomPatient(dossierMedicalRequestDto.getPrenomPatient());
        dossierMedical.setConsultationEffectuee(dossierMedicalRequestDto.getConsultationEffectuee());
        dossierMedical.setResultatPrestation(dossierMedicalRequestDto.getResultatPrestation());
        if (dossierMedicalRequestDto.getConsultantId() == null) {
            throw new IllegalArgumentException("dossier medical need a consultant");
        }
        if (dossierMedicalRequestDto.getServiceconsultationId() == null) {
            throw new IllegalArgumentException("dossier medical need a service consultation");
        }
        if (dossierMedicalRequestDto.getPatientId() == null) {
            throw new IllegalArgumentException("dossier medical need a patient");
        }
        Consultant consultant =mapper.consultantResponseDtoToConsultant( utilisateurService.getConsultantById(dossierMedicalRequestDto.getConsultantId()));
     dossierMedical.setConsultant(consultant);
      dossierMedicalRepository.save(dossierMedical);
       ServiceConsultation serviceConsultation = serviceConsultationService.getServiceConsultation(dossierMedicalRequestDto.getServiceconsultationId());
        dossierMedical.setServiceConsultation(serviceConsultation);
    dossierMedicalRepository.save(dossierMedical);
       Patient patient =patientService.getPatient(dossierMedicalRequestDto.getPatientId());
        dossierMedical.setPatient(patient);
    dossierMedicalRepository.save(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }
       @Override
    public List<DossierMedicalResponseDto> getDossierMedicals() {
                List<DossierMedical> dossierMedicals= StreamSupport
                .stream(dossierMedicalRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
                return mapper.dossierMedicalsToDossierMedicalResponseDtos(dossierMedicals);
    }
    @Override
    public List<DossierMedicalResponseDto> getPatientDossierMedicals(Long id) {
                List<DossierMedical> dossierMedicals= StreamSupport
                .stream(dossierMedicalRepository.findAllByPatientId(id).spliterator(), false)
                .collect(Collectors.toList());
                return mapper.dossierMedicalsToDossierMedicalResponseDtos(dossierMedicals);
    }
       @Override
    public DossierMedical getDossierMedical(Long dossierMedicalId) {
        return dossierMedicalRepository.findById(dossierMedicalId).orElseThrow(() ->
                new IllegalArgumentException(
                        "dossier medical with id: " + dossierMedicalId + " could not be found"));
    }

    @Override
    public DossierMedicalResponseDto getDossierMedicalById(Long dossierMedicalId) {
            DossierMedical dossierMedical= getDossierMedical(dossierMedicalId);
             return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
        }


    @Override
    public DossierMedicalResponseDto deleteDossierMedical(Long dossierMedicalId) {
       DossierMedical  dossierMedical = getDossierMedical(dossierMedicalId);
        dossierMedicalRepository.delete(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }

    @Override
    public DossierMedicalResponseDto editDossierMedical(Long dossierMedicalId, DossierMedicalRequestDto dossierMedicalRequestDto) {
        DossierMedical dossierMedicalToEdit = getDossierMedical(dossierMedicalId);
        dossierMedicalToEdit.setNomPatient(dossierMedicalRequestDto.getNomPatient());
        dossierMedicalToEdit.setPrenomPatient(dossierMedicalRequestDto.getPrenomPatient());
        dossierMedicalToEdit.setConsultationEffectuee(dossierMedicalRequestDto.getConsultationEffectuee());
        dossierMedicalToEdit.setResultatPrestation(dossierMedicalRequestDto.getResultatPrestation());
        if (dossierMedicalRequestDto.getPatientId() != null) {
            Patient patient=patientService.getPatient(dossierMedicalRequestDto.getPatientId());
             dossierMedicalToEdit.setPatient(patient);
        }
        if (dossierMedicalRequestDto.getServiceconsultationId() != null) {
            ServiceConsultation serviceConsultation=serviceConsultationService.getServiceConsultation
                    (dossierMedicalRequestDto.getServiceconsultationId());
            dossierMedicalToEdit.setServiceConsultation(serviceConsultation);
        }
        if (dossierMedicalRequestDto.getConsultantId() != null) {
            Consultant consultant =mapper.consultantResponseDtoToConsultant( utilisateurService.getConsultantById(dossierMedicalRequestDto.getConsultantId()));
             dossierMedicalToEdit.setConsultant(consultant);
        }

        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedicalToEdit);}
    @Override
    public DossierMedicalResponseDto addPatientToDossierMedical(Long dossierMedicalId, Long patientId) {
        DossierMedical  dossierMedical = getDossierMedical( dossierMedicalId);
        Patient patient = patientService.getPatient(patientId);
        if (Objects.nonNull(dossierMedical.getPatient())) {
            throw new IllegalArgumentException("dossier medical already has a patient");
        }
       dossierMedical.setPatient(patient);
        patient.addDossierMedical(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }
        @Override
    public DossierMedicalResponseDto removePatientFromDossierMedical(Long id) {
        DossierMedical dossierMedical = getDossierMedical(id);
        Patient patient=patientService.getPatient(id);
        if (!Objects.nonNull(dossierMedical.getPatient())) {
            throw new IllegalArgumentException("dossier medical does not have a patient");
        }
       dossierMedical.setPatient(patient);
        patient.removeDossierMedical(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }


    @Override
    public DossierMedicalResponseDto addServiceConsultationToDossierMedical(Long dossierMedicalId, Long seviceMedicalId) {
        DossierMedical  dossierMedical = getDossierMedical( dossierMedicalId);
        ServiceConsultation serviceConsultation = serviceConsultationService.getServiceConsultation(dossierMedicalId);
        if (Objects.nonNull(dossierMedical.getServiceConsultation())) {
            throw new IllegalArgumentException("dossier medical already has a service consultation");
        }
        dossierMedical.setServiceConsultation(serviceConsultation);
        serviceConsultation.addDossierMedical(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }

    @Override
    public DossierMedicalResponseDto removeServiceConsultationFromDossierMedical(Long id) {
        DossierMedical dossierMedical = getDossierMedical(id);
        ServiceConsultation serviceConsultation=serviceConsultationService.getServiceConsultation(id);
        if (!Objects.nonNull(dossierMedical.getServiceConsultation())) {
            throw new IllegalArgumentException("dossier medical does not have a service consultation");
        }
        dossierMedical.setServiceConsultation(serviceConsultation);
        serviceConsultation.removeDossierMedical(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }

    @Override
    public DossierMedicalResponseDto addConsultantToDossierMedical(Long dossierMedicalId, Long consultantId) {
        DossierMedical  dossierMedical = getDossierMedical( dossierMedicalId);
        Consultant consultant = mapper.consultantResponseDtoToConsultant( utilisateurService.getConsultantById(consultantId));
        if (Objects.nonNull(dossierMedical.getConsultant())) {
            throw new IllegalArgumentException("dossier medical already has a consultant");
        }
        dossierMedical.setConsultant(consultant);
        consultant.addDossierMedical(dossierMedical);
        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }

    @Override
    public DossierMedicalResponseDto removeConsultantFromDossierMedical(Long id) {
        DossierMedical dossierMedical = getDossierMedical(id);
        Consultant consultant = mapper.consultantResponseDtoToConsultant( utilisateurService.getConsultantById(id));
        if (!Objects.nonNull(dossierMedical.getConsultant())) {
            throw new IllegalArgumentException("dossier medical does not have a consultant");
        }
        dossierMedical.setConsultant(consultant);
        consultant.removeDossierMedical(dossierMedical);

        return mapper.dossierMedicalToDossierMedicalResponseDto(dossierMedical);
    }

    public ServiceConsultationService getServiceConsultationService() {
        return serviceConsultationService;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public PatientService getPatientService() {
        return patientService;
    }
}
