package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.DetailsPatientRequestDto;
import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DetailsPatientServiceImpl implements DetailsPatientService {
    private final DetailsPatientRepository detailsPatientRepository;


@Autowired
    public DetailsPatientServiceImpl(DetailsPatientRepository detailsPatientRepository) {
        this.detailsPatientRepository = detailsPatientRepository;


}
    @Autowired
    PatientRepository patientRepository;

    public DetailsPatientResponseDto addDetailsPatient(DetailsPatientRequestDto detailsPatientRequestDto) {
        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient(detailsPatientRequestDto.getEtatDuPatient());
        detailsPatient.setHeurePriseCharge(detailsPatientRequestDto.getHeurePriseCharge());
        detailsPatient.setServiceAffectation(detailsPatientRequestDto.getServiceAffectation());
        detailsPatient.setStatus(detailsPatientRequestDto.getStatus());
if(detailsPatientRequestDto.getPatientId()!=null) {
    Patient patient=patientRepository.findById(detailsPatientRequestDto.getPatientId()).orElse(null);
    detailsPatient.setPatient(patient);

    patient.setDetailsPatient(detailsPatientRepository.save(detailsPatient));
    patientRepository.save(patient);
}


        ;
        return mapper.detailsPatientToDetailsPatientResponseDto(detailsPatient);
    }

    @Override
    public List<DetailsPatientResponseDto> getDetailsPatients() {
        List<DetailsPatient> detailsPatients = StreamSupport
                .stream(detailsPatientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.detailsPatientsToDetailsPatientResponseDtos(detailsPatients);
    }

    @Override
    public DetailsPatientResponseDto getDetailsPatientById(Long detailsPatientId) {
        DetailsPatient detailsPatient = getDetailsPatient(detailsPatientId);
        return mapper.detailsPatientToDetailsPatientResponseDto(detailsPatient);
    }

    @Override
    public DetailsPatient getDetailsPatient(Long detailsPatientId) {
        return detailsPatientRepository.findById(detailsPatientId).orElseThrow(() ->
                new IllegalArgumentException("could not find salle attente with id: " +detailsPatientId));
    }

    @Override
    public DetailsPatientResponseDto deleteDetailsPatient(Long detailsPatientId) {
        DetailsPatient detailsPatient = getDetailsPatient(detailsPatientId);

        detailsPatientRepository.delete(detailsPatient);
        return mapper.detailsPatientToDetailsPatientResponseDto(detailsPatient);
    }

    @Transactional
    @Override
    public DetailsPatientResponseDto editDetailsPatient(Long detailsPatientId, DetailsPatientRequestDto detailsPatientRequestDto) {
        DetailsPatient detailsPatientToEdit = getDetailsPatient(detailsPatientId);
        detailsPatientToEdit.setEtatDuPatient(detailsPatientRequestDto.getEtatDuPatient());
        detailsPatientToEdit.setHeurePriseCharge(detailsPatientRequestDto.getHeurePriseCharge());
        detailsPatientToEdit.setServiceAffectation(detailsPatientRequestDto.getServiceAffectation());
        detailsPatientToEdit.setStatus(detailsPatientRequestDto.getStatus());
        if(detailsPatientRequestDto.getPatientId()!=null) {
            Patient patient=patientRepository.findById(detailsPatientRequestDto.getPatientId()).orElse(null);
            detailsPatientToEdit.setPatient(patient);
        }
        return mapper.detailsPatientToDetailsPatientResponseDto(detailsPatientToEdit);
    }
/*
    @Override
    public SalleAttenteResponseDto addAccueilToSalleAttente(Long salleAttenteId, Long accueilId) {
        SalleAttente salleAttente = getSalleAttente(salleAttenteId);
        Accueil accueil= accueilService.getAccueil(accueilId);

        if (Objects.nonNull(salleAttente.getAccueil())){
            throw new RuntimeException("salle attente already has a accueil");}
        salleAttente.setAccueil(accueil);
        accueil.addSalleAttente(salleAttente);
        return mapper.salleAttenteToSalleAttenteResponseDto(salleAttente);
    }*/
/*
    @Override
    public SalleAttenteResponseDto removeAccueilFromSalleAttente(Long id) {
        SalleAttente salleAttente = getSalleAttente(id);
        Accueil accueil = accueilService.getAccueil(id);
        if (!(Objects.nonNull(salleAttente.getAccueil()))) {
            throw new IllegalArgumentException("salle attente does not have accueil to delete");
        }
        salleAttente.setAccueil(null);
        accueil.removeSalleAttente(salleAttente);
        return mapper.salleAttenteToSalleAttenteResponseDto(salleAttente);
    }*/


}
