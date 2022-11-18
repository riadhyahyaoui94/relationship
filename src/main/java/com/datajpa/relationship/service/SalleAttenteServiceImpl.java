package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.SalleAttenteRepository;
import com.datajpa.relationship.repository.ServiceConsultationRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SalleAttenteServiceImpl implements SalleAttenteService {
    private final SalleAttenteRepository salleAttenteRepository;
    private  final AccueilService accueilService;

@Autowired
    public SalleAttenteServiceImpl(SalleAttenteRepository salleAttenteRepository, AccueilService accueilService) {
        this.salleAttenteRepository = salleAttenteRepository;

    this.accueilService = accueilService;
}
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ServiceConsultationRepository serviceConsultationRepository;
    @Override
    public SalleAttenteResponseDto addSalleAttente(SalleAttenteRequestDto salleAttenteRequestDto) {
       SalleAttente salleAttente = new SalleAttente();
        salleAttente.setNom(salleAttenteRequestDto.getNom());
        salleAttente.setEtage(salleAttenteRequestDto.getEtage());
        salleAttenteRepository.save(salleAttente);
        return mapper.salleAttenteToSalleAttenteResponseDto(salleAttente);
    }

    @Override
    public List<SalleAttenteResponseDto> getSalleAttentes() {
        List<SalleAttente> salleAttentes = StreamSupport
                .stream(salleAttenteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        /*System.out.println("qdsfdddddddddddddddddd");
        System.out.println(salleAttentes);
        System.out.println(salleAttentes.get(0).getPersonnelMedicals());
        System.out.println(salleAttentes.get(0).getServiceConsultations());*/
        return mapper.salleAttentesToSalleAttenteResponseDtos(salleAttentes);
    }

    @Override
    public SalleAttenteResponseDto getSalleAttenteById(Long salleAttenteId) {
        SalleAttente salleAttente = getSalleAttente(salleAttenteId);

        return mapper.salleAttenteToSalleAttenteResponseDto(salleAttente);
    }

    @Override
    public SalleAttente getSalleAttente(Long salleAttenteId) {
        return salleAttenteRepository.findById(salleAttenteId).orElseThrow(() ->
                new IllegalArgumentException("could not find salle attente with id: " +salleAttenteId));
    }

    @Override
    public SalleAttenteResponseDto deleteSalleAttente(Long salleAttenteId) {
        SalleAttente salleAttente = getSalleAttente(salleAttenteId);
        for(PersonnelMedical p : salleAttente.getPersonnelMedicals()){
            p.setServiceConsultation(null);
            utilisateurRepository.save(p);
        }
        salleAttente.setPersonnelMedicals(null);

for(ServiceConsultation s : salleAttente.getServiceConsultations()){
            s.setSalleAttente(null);
            serviceConsultationRepository.save(s);
        }
        salleAttente.setServiceConsultations(null);




        salleAttenteRepository.delete(salleAttente);
        return mapper.salleAttenteToSalleAttenteResponseDto(salleAttente);
    }

    @Transactional
    @Override
    public SalleAttenteResponseDto editSalleAttente(Long salleAttenteId, SalleAttenteRequestDto salleAttenteRequestDto) {
        SalleAttente salleAttenteToEdit = getSalleAttente(salleAttenteId);
       salleAttenteToEdit.setNom(salleAttenteRequestDto.getNom());
       salleAttenteToEdit.setEtage(salleAttenteRequestDto.getEtage());
        return mapper.salleAttenteToSalleAttenteResponseDto(salleAttenteToEdit);
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
