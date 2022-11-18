package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.PatientRequestDto;
import com.datajpa.relationship.dto.response.PatientResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.DetailsPatientRepository;
import com.datajpa.relationship.repository.OrdonnanceRepository;
import com.datajpa.relationship.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientServiceImpl implements PatientService {

    public final PatientRepository patientRepository;
    private final PaiementService paiementService;
    private final SalleAttenteService salleAttenteService;
    private final DetailsPatientRepository detailsPatientRepository;
    private final OrdonnanceRepository ordonnanceRepository;
@Autowired
    public PatientServiceImpl(PatientRepository patientRepository,
                              PaiementService paiementService, SalleAttenteService salleAttenteService,DetailsPatientRepository detailsPatientRepository,OrdonnanceRepository ordonnanceRepository) {
    this.patientRepository = patientRepository;
    this.paiementService = paiementService;
this.ordonnanceRepository=ordonnanceRepository;
        this.salleAttenteService = salleAttenteService;
        this.detailsPatientRepository=detailsPatientRepository;
    }


    @Override
    public Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(() ->
                new IllegalArgumentException("could not find patient with id: " + patientId));
    }

    @Override
    public PatientResponseDto addPatient(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setNom(patientRequestDto.getNom());



        patient.setPrenom(patientRequestDto.getPrenom());
        patient.setGenre(patientRequestDto.getGenre());
        patient.setDateNaissance(patientRequestDto.getDateNaissance());
        patient.setNumIdendite(patientRequestDto.getNumIdendite());
        patient.setPhoto(patientRequestDto.getPhoto());
        patient.setDomicile(patientRequestDto.getDomicile());
        patient.setEstAssure(patientRequestDto.getEstAssure());
        patient.setAssurance(patientRequestDto.getAssurance());
        patient.setTauxAssurance(patientRequestDto.getTauxAssurance());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setNumAffeliation(patientRequestDto.getNumAffeliation());



        /*if (patientRequestDto.getPaiementId() == null) {
            throw new IllegalArgumentException("patient need a paiement");
        }*/
        if (patientRequestDto.getSalleAttenteId() != null) {
            SalleAttente salleAttente=salleAttenteService.getSalleAttente(patientRequestDto.getSalleAttenteId());
            patient.setSalleAttente(salleAttente);

        }
       //Paiement paiement = paiementService.getPaiement(patientRequestDto.getPaiementId());
        //patient.setPaiement(paiement);

        patientRepository.save(patient);
        return mapper.patientToPatientResponseDto(patientRepository.save(patient));

    }

    @Override
    public List<PatientResponseDto> getPatients() {
        List<Patient> patients = StreamSupport
                .stream(patientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.patientsToPatientResponseDtos(patients);
    }

    @Override
    public PatientResponseDto getPatientById(Long patientId) {
       Patient patient = getPatient(patientId);
       // System.out.println(patient.getDetailsPatient().toString());
        return mapper.patientToPatientResponseDto(patient);
    }

    @Override
    public List<PatientResponseDto> getPatientBySalleId(Long salleId) {
       List<Patient> patients = patientRepository.findAllBySalleAttenteId(salleId);
       // System.out.println(patient.getDetailsPatient().toString());
        return mapper.patientsToPatientResponseDtos(patients);
    }

    @Override
    public Long CountAssuree() {
        return patientRepository.countAssuree(true);

    }

    @Override
    public PatientResponseDto deletePatient(Long patientId) {
       Patient patient = getPatient(patientId);
       if(patient.getDossierMedicals() !=null) {
           for (DossierMedical d : patient.getDossierMedicals()) {
               for (Consultation c : d.getConsultations()) {
                   c.getOrdonnance().setMedicaments(new ArrayList<Medicament>());
                   c.getOrdonnance().setExamens(new ArrayList<Examen>());
                   ordonnanceRepository.save(c.getOrdonnance());
                   ordonnanceRepository.deleteById(c.getOrdonnance().getId());
               }

           }
       }
       patientRepository.delete(patient);

        return mapper.patientToPatientResponseDto(patient);
    }
@Transactional
    @Override
    public PatientResponseDto editPatient(Long patientId, PatientRequestDto patientRequestDto) {
       Patient patientToEdit = getPatient(patientId);
        patientToEdit.setNom(patientRequestDto.getNom());
    patientToEdit.setPrenom(patientRequestDto.getPrenom());
    patientToEdit.setGenre(patientRequestDto.getGenre());
    patientToEdit.setDateNaissance(patientRequestDto.getDateNaissance());
    patientToEdit.setNumIdendite(patientRequestDto.getNumIdendite());
    patientToEdit.setPhoto(patientRequestDto.getPhoto());
    patientToEdit.setDomicile(patientRequestDto.getDomicile());
    patientToEdit.setEstAssure(patientRequestDto.getEstAssure());
    patientToEdit.setAssurance(patientRequestDto.getAssurance());
    patientToEdit.setTauxAssurance(patientRequestDto.getTauxAssurance());
    patientToEdit.setEmail(patientRequestDto.getEmail());
    patientToEdit.setNumAffeliation(patientRequestDto.getNumAffeliation());
        if (patientRequestDto.getSalleAttenteId() != null && patientRequestDto.getSalleAttenteId() != 0) {
            SalleAttente salleAttente = salleAttenteService.getSalleAttente(patientRequestDto.getSalleAttenteId());
            patientToEdit.setSalleAttente(salleAttente);


        }

        if (patientRequestDto.getPaiementId() != null) {
           Paiement paiement = paiementService.getPaiement(patientRequestDto.getPaiementId());
            patientToEdit.setPaiement(paiement);
        }
        return mapper.patientToPatientResponseDto(patientToEdit);
    }

    @Override
    public PatientResponseDto addSalleAttenteToPatient(Long patientId, Long salleAttenteId ) {
        Patient patient = getPatient(patientId);
        SalleAttente salleAttente = salleAttenteService.getSalleAttente(salleAttenteId);
        if (Objects.nonNull(patient.getSalleAttente())) {
            throw new IllegalArgumentException("Patient already has a salle attente");
        }
        patient.setSalleAttente(salleAttente);
        salleAttente.addPatient(patient);
        return mapper.patientToPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDto deleteSalleAttenteFromPatient(Long id) {
        Patient patient = getPatient(id);
       SalleAttente salleAttente = salleAttenteService.getSalleAttente(id);
        if (Objects.nonNull(patient.getSalleAttente())) {
            throw new IllegalArgumentException("patient already has a salle attente");
        }
        patient.setSalleAttente(salleAttente);
       salleAttente.removePatient(patient);
        return mapper.patientToPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDto addPaiementToPatient(Long patientId, Long paiementId) {
        Patient patient = getPatient(patientId);
        Paiement paiement = paiementService.getPaiement(patientId);
        if (Objects.nonNull(patient.getPaiement())) {
            throw new IllegalArgumentException("paiement already has a paiement");
        }
        patient.setPaiement(paiement);
        paiement.addPatient(patient);
        return mapper.patientToPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDto deletePaiementFromPatient(Long id) {
        Patient patient = getPatient(id);
        Paiement paiement = paiementService.getPaiement(id);
        if (!(Objects.nonNull(patient.getPaiement()))) {
            throw new IllegalArgumentException("patient does not have paiement to delete");
        }
        patient.setPaiement(null);
        paiement.removePatient(patient);
        return mapper.patientToPatientResponseDto(patient);
    }



}
