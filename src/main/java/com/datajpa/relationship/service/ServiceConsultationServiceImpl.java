package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.ServiceConsultationResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.OrdonnanceRepository;
import com.datajpa.relationship.repository.ServiceConsultationRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceConsultationServiceImpl implements ServiceConsultationService {
    @Autowired
    private ServiceConsultationRepository serviceConsultationRepository;
   @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private SalleAttenteService salleAttenteService;
    @Autowired
    private OrdonnanceRepository ordonnanceRepository;
/*
    public ServiceConsultationServiceImpl(ServiceConsultationRepository serviceConsultationRepository, SalleAttenteService salleAttenteService) {
        this.serviceConsultationRepository = serviceConsultationRepository;
        this.salleAttenteService = salleAttenteService;
    }

    @Autowired
    public ServiceConsultationServiceImpl(ServiceConsultationRepository serviceConsultationRepository) {
        this.serviceConsultationRepository = serviceConsultationRepository;
    }*/


    @Override
    public ServiceConsultation getServiceConsultation(Long serviceConsultationId) {
        return serviceConsultationRepository.findById(serviceConsultationId).orElseThrow(() ->
                new IllegalArgumentException("could not find service with id: " + serviceConsultationId));
    }
    @Transactional
    @Override
    public ServiceConsultationResponseDto addServiceConsultation(ServiceConsultationRequestDto serviceConsultationRequestDto) {
        ServiceConsultation serviceConsultation = new ServiceConsultation();
       serviceConsultation.setNomService(serviceConsultationRequestDto.getNomService());
       serviceConsultation.setTypeService(serviceConsultationRequestDto.getTypeService());
        if (serviceConsultationRequestDto.getSalleAttenteId() == null) {
            throw new IllegalArgumentException("service consultation at least on salle attente");
        }

       SalleAttente salleAttente=salleAttenteService.getSalleAttente(serviceConsultationRequestDto.getSalleAttenteId());
        serviceConsultation.setSalleAttente(salleAttente);


        return mapper.serviceConsultationToServiceConsultationResponseDto(serviceConsultationRepository.save(serviceConsultation));
    }

    @Override
    public ServiceConsultationResponseDto getServiceConsultationById(Long serviceConsultationId) {
        ServiceConsultation serviceConsultation = getServiceConsultation(serviceConsultationId);
        return  mapper.serviceConsultationToServiceConsultationResponseDto(serviceConsultation);
    }

    @Override
    public List<ServiceConsultationResponseDto> getServiceConsultations() {
        List<ServiceConsultation> serviceConsultations = StreamSupport
                .stream(serviceConsultationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.serviceConsultationsToServiceConsultationResponseDtos(serviceConsultations);
    }

    @Override
    public ServiceConsultationResponseDto deleteServiceConsultation(Long serviceConsultationId) {
       ServiceConsultation serviceConsultation = getServiceConsultation(serviceConsultationId);
       for(Consultant c : serviceConsultation.getConsultants()){
           c.setServiceConsultation(null);
          for(Ordonnance o:  c.getOrdonnances()){
              o.setMedicaments(new ArrayList<Medicament>());
              o.setExamens(new ArrayList<Examen>());
              o.setConsultation(null);
              o.setConsultant(null);
              ordonnanceRepository.save(o);
              ordonnanceRepository.deleteById(o.getId());
            ordonnanceRepository.deleteById(o.getId());
          };
           c.setOrdonnances(null);

           utilisateurRepository.save(c);
       }
       for(PersonnelMedical p : serviceConsultation.getPersonnelMedicals()){
           p.setServiceConsultation(null);
           utilisateurRepository.save(p);
       }
       serviceConsultation.setConsultants(null);
       serviceConsultation.setPersonnelMedicals(null);

        serviceConsultationRepository.delete(serviceConsultationRepository.save(serviceConsultation));
        return mapper.serviceConsultationToServiceConsultationResponseDto(serviceConsultation);
    }
    @Transactional
    @Override
    public ServiceConsultationResponseDto editServiceConsultation(Long serviceConsultationId, ServiceConsultationRequestDto
            serviceConsultationRequestDto) {

        ServiceConsultation serviceConsultationToEdit = getServiceConsultation(serviceConsultationId);
       serviceConsultationToEdit.setNomService(serviceConsultationRequestDto.getNomService());
       serviceConsultationToEdit.setTypeService(serviceConsultationRequestDto.getTypeService());
    if (serviceConsultationRequestDto.getSalleAttenteId() == null) {
        throw new IllegalArgumentException("service consultation at least on salle attente");

    }
    SalleAttente salleAttente = salleAttenteService.getSalleAttente(serviceConsultationRequestDto.getSalleAttenteId());
    serviceConsultationToEdit.setSalleAttente(salleAttente);
    System.out.println(serviceConsultationToEdit);
        return mapper.serviceConsultationToServiceConsultationResponseDto(serviceConsultationRepository.save(serviceConsultationToEdit));
    }

@Transactional
    @Override
    public ServiceConsultationResponseDto addSalleAttenteToServiceConsultation(Long salleAttenteId, Long serviceConsultationId) {
        ServiceConsultation serviceConsultation = getServiceConsultation(serviceConsultationId);
        SalleAttente salleAttente= salleAttenteService.getSalleAttente(salleAttenteId);

        if (Objects.nonNull(serviceConsultation.getSalleAttente())){
            throw new RuntimeException("service consultation already has a salle attente");}
        serviceConsultation.setSalleAttente(salleAttente);
        salleAttente.addServiceConsultation(serviceConsultation);
        return mapper.serviceConsultationToServiceConsultationResponseDto(serviceConsultation);
    }
    @Transactional
    @Override
    public ServiceConsultationResponseDto removeSalleAttenteFromServiceConsultation(Long id) {
        ServiceConsultation serviceConsultation = getServiceConsultation(id);
        SalleAttente salleAttente = salleAttenteService.getSalleAttente(id);
        if (!(Objects.nonNull(serviceConsultation.getSalleAttente()))) {
            throw new IllegalArgumentException("service consultation does not have salle attente to delete");
        }
        serviceConsultation.setSalleAttente(null);
        salleAttente.removeServiceConsultation(serviceConsultation);
        return mapper.serviceConsultationToServiceConsultationResponseDto(serviceConsultation);
    }

 }
