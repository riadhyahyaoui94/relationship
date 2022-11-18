package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.response.PriseRDVResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.ExamenRepository;
import com.datajpa.relationship.repository.PatientRepository;
import com.datajpa.relationship.repository.PriseRDVRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.datajpa.relationship.dto.request.PriseRDVRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PriseRDVServiceImpl implements PriseRDVService {
    private final PriseRDVRepository priseRDVRepository;
    private final ServiceConsultationService serviceConsultationService;
    private final  PatientService patientService;
@Autowired
    public PriseRDVServiceImpl( PriseRDVRepository priseRDVRepository,
                               ServiceConsultationService serviceConsultationService, PatientService patientService) {
    this.priseRDVRepository = priseRDVRepository;

    this.serviceConsultationService = serviceConsultationService;
        this.patientService = patientService;
    }
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    PatientRepository patientRepository;


    @Transactional
    @Override
    public PriseRDVResponseDto addPriseRDV(PriseRDVRequestDto priseRDVRequestDto) {
        PriseRDV priseRDV = new PriseRDV();
        /*priseRDV.setNomPatient(priseRDVRequestDto.getNomPatient());
        priseRDV.setPrenomPatient(priseRDVRequestDto.getPrenomPatient());*/
        priseRDV.setDateRDV(priseRDVRequestDto.getDateRDV());
        priseRDV.setHeureRDV(priseRDVRequestDto.getHeureRDV());
        priseRDV.setPayed(priseRDVRequestDto.getPayed());
        priseRDV.setMotif(priseRDVRequestDto.getMotif());

        if (priseRDVRequestDto.getPatientId() != null) {
            Patient patient=patientService.getPatient(priseRDVRequestDto.getPatientId());
            priseRDV.setPatient(patient);

        }
        if (priseRDVRequestDto.getServiceconsultationId() != null) {
            ServiceConsultation  serviceConsultation = serviceConsultationService.getServiceConsultation
                    (priseRDVRequestDto.getServiceconsultationId());
            priseRDV.setServiceConsultation(serviceConsultation);
        }
        if (priseRDVRequestDto.getConsultantId() != null) {
            Consultant  consultant = utilisateurRepository.findConsultantById(priseRDVRequestDto.getConsultantId());

            priseRDV.setConsultant(consultant);
        }
        if (priseRDVRequestDto.getExamenId() != null) {
            Examen  examen = examenRepository.findById(priseRDVRequestDto.getExamenId()).orElse(null);

            priseRDV.setExamen(examen);
        }

         return mapper.priseRDVToPriseRDVResponseDto(priseRDVRepository.save(priseRDV));

    }

       @Override
    public List<PriseRDVResponseDto> getPriseRDVS() {
      List<PriseRDV> priseRDVS= StreamSupport
                .stream(priseRDVRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
      return  mapper.priseRDVSToPriseRDVResponseDtos(priseRDVS);
    }

  @Override
    public List<PriseRDVResponseDto> getPriseRDVSByDates(Date d1,Date d2) {
      List<PriseRDV> priseRDVS= StreamSupport
                .stream(priseRDVRepository.findAllPriseRDVByDateRDVBetween(d1,d2).spliterator(), false)
                .collect(Collectors.toList());
      /*List<PriseRDV> priseMonth=new ArrayList<>();
      Date month = new Date();
      for(PriseRDV p : priseRDVS){
          if(p.getDateRDV().getMonth()== month.getMonth()){
              priseMonth.add(p);
          }

      }*/

      return  mapper.priseRDVSToPriseRDVResponseDtos(priseRDVS);
    }

    @Override
    public PriseRDV getPriseRDV(Long priseRDVId) {
        return priseRDVRepository.findById(priseRDVId).orElseThrow(() ->
                new IllegalArgumentException(
                        "RDV with id: " + priseRDVId+ " could not be found"));
    }
     @Override
    public PriseRDVResponseDto getPriseRDVRes(Long priseRDVId) {
        PriseRDV p = new PriseRDV();
        return mapper.priseRDVToPriseRDVResponseDto( priseRDVRepository.findById(priseRDVId).orElse(p));
    }


    @Override
    public List<PriseRDVResponseDto> getPriseRDVByConsultantId(Long id) {

      List<PriseRDV> priseRDVS= StreamSupport
              .stream(priseRDVRepository.findPriseRDVByConsultantIdOrderByDateRDV(id).spliterator(), false)
              .collect(Collectors.toList());


        return mapper.priseRDVSToPriseRDVResponseDtos(priseRDVS);
    }
 @Override
    public List<PriseRDVResponseDto> getPriseRDVByPatientId(Long id) {

      List<PriseRDV> priseRDVS= StreamSupport
              .stream(priseRDVRepository.findPriseRDVByPatientIdOrderByDateRDVPayed(id).spliterator(), false)
              .collect(Collectors.toList());


        return mapper.priseRDVSToPriseRDVResponseDtos(priseRDVS);
    }

    @Override
    public PriseRDVResponseDto deletePriseRDV(Long priseRDVId) {
        PriseRDV priseRDV = getPriseRDV(priseRDVId);
        priseRDV.setServiceConsultation(null);

        priseRDV.setConsultant(null);
        priseRDV.setFacturation(null);
        priseRDV.setExamen(null);

        priseRDV.getPatient().setSalleAttente(null);
        System.out.println(priseRDV.getPatient());
        patientRepository.save(priseRDV.getPatient());
        priseRDV.setPatient(null);
        priseRDVRepository.save(priseRDV);
        priseRDVRepository.delete(priseRDV);
        return mapper.priseRDVToPriseRDVResponseDto(priseRDV);
    }
    @Transactional
    @Override
    public PriseRDVResponseDto editPriseRDV(Long priseRDVId, PriseRDVRequestDto priseRDVRequestDto) {
       PriseRDV priseRDVToEdit = getPriseRDV(priseRDVId);

       /* priseRDVToEdit.setNomPatient(priseRDVRequestDto.getNomPatient());*/
        if(priseRDVRequestDto.getDateRDV()!=null) {
            priseRDVToEdit.setDateRDV(priseRDVRequestDto.getDateRDV());
        }
        if(priseRDVRequestDto.getPayed()!=null) {
            priseRDVToEdit.setPayed(priseRDVRequestDto.getPayed());
        }
        if (priseRDVRequestDto.getServiceconsultationId() != null) {
            ServiceConsultation serviceConsultation=serviceConsultationService.getServiceConsultation(priseRDVRequestDto.getServiceconsultationId());
            priseRDVToEdit.setServiceConsultation(serviceConsultation);
        }
        if (priseRDVRequestDto.getPatientId()!= null) {
           Patient patient=patientService.getPatient(priseRDVRequestDto.getPatientId());
           priseRDVToEdit.setPatient(patient);
        }
      return mapper.priseRDVToPriseRDVResponseDto(priseRDVToEdit);
    }

    @Transactional
    @Override
    public PriseRDVResponseDto addServiceConsultationToPriseRDV(Long priseRDVId, Long serviceConsultationId) {
        PriseRDV  priseRDV = getPriseRDV( priseRDVId);
        ServiceConsultation serviceConsultation = serviceConsultationService.getServiceConsultation(serviceConsultationId);

        if (Objects.nonNull(priseRDV.getServiceConsultation())) {
            throw new IllegalArgumentException("priseRDV already has a service consultation");
        }
          priseRDV.setServiceConsultation(serviceConsultation);
        serviceConsultation.addPriseRdv(priseRDV);
         return  mapper.priseRDVToPriseRDVResponseDto(priseRDV);
    }

    @Override
    public PriseRDVResponseDto removeServiceConsultationFromPriseRDV(Long id) {
        PriseRDV priseRDV = getPriseRDV(id);
        ServiceConsultation serviceConsultation=serviceConsultationService.getServiceConsultation(id);
        if (!Objects.nonNull(priseRDV.getServiceConsultation())) {
            throw new IllegalArgumentException("priseRDV does not have a service consultation");
        }
        priseRDV.setServiceConsultation(serviceConsultation);
        serviceConsultation.removePriseRdv(priseRDV);
        return mapper.priseRDVToPriseRDVResponseDto(priseRDV);
    }


    @Transactional
    @Override
    public PriseRDVResponseDto addPatientToPriseRDV(Long priseRDVId, Long patientId) {
        PriseRDV  priseRDV = getPriseRDV( priseRDVId);
        Patient patient = patientService.getPatient(patientId);
        if (Objects.nonNull(priseRDV.getPatient())) {
            throw new IllegalArgumentException("priseRDV already has a patient");
        }
        priseRDV.setPatient(patient);
        patient.addPriseRDV(priseRDV);
        return  mapper.priseRDVToPriseRDVResponseDto(priseRDV);

    }

    @Transactional
    @Override
    public PriseRDVResponseDto removePatientFromPriseRDV(Long id) {
        PriseRDV priseRDV = getPriseRDV(id);
        Patient patient =patientService.getPatient(id);
        if (!Objects.nonNull(priseRDV.getPatient())) {
            throw new IllegalArgumentException("priseRDV does not have a patient");
        }
        priseRDV.setPatient(patient);
        patient.removePriseRDV(priseRDV);
        return mapper.priseRDVToPriseRDVResponseDto(priseRDV);
    }


    public ServiceConsultationService getServiceConsultationService() {
        return serviceConsultationService;
    }

    public PatientService getPatientService() {
        return patientService;
    }
}
