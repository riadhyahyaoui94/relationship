package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.ConsultationRequestDto;
import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.ConsultationResponseDto;
import com.datajpa.relationship.dto.response.ConsultationServiceResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private DossierMedicalService dossierService;
    @Autowired
    private ServiceConsultationRepository serviceConsultationRepository;

    @Override
    public Consultation getConsultation(Long consultationId) {
        return consultationRepository.findById(consultationId).orElseThrow(() ->
                new IllegalArgumentException("could not find consultation with id: " + consultationId));
    }
    @Transactional
    @Override
    public ConsultationResponseDto addConsultation(ConsultationRequestDto consultationRequestDto) {
        Consultation consultation = new Consultation();

       consultation.setTypeConsultation(consultationRequestDto.getTypeConsultation());
       consultation.setDateConsultation(consultationRequestDto.getDateConsultation());
       consultation.setDiagnostic(consultationRequestDto.getDiagnostic());

        if (consultationRequestDto.getPatientId() == null) {
            throw new IllegalArgumentException("consultation have one Patient");
        }

        Patient patient=patientService.getPatient(consultationRequestDto.getPatientId());
        consultation.setPatient(patient);

        if (consultationRequestDto.getDossierMedicalId() == null) {
            throw new IllegalArgumentException("consultation have one Dosiier");
        }

        DossierMedical dossier=dossierService.getDossierMedical(consultationRequestDto.getDossierMedicalId());
        consultation.setDossierMedical(dossier);

        if (consultationRequestDto.getConsultantId() == null) {
            throw new IllegalArgumentException("consultation have one Dosiier");
        }

        Consultant consultant=utilisateurRepository.findConsultantById(consultationRequestDto.getConsultantId());
        consultation.setConsultant(consultant);


        return mapper.consultationToServiceConsultationResponseDto(consultationRepository.save(consultation));
    }



    @Override
    public ConsultationResponseDto getConsultationById(Long consultationId) {
        Consultation consultation = getConsultation(consultationId);
        return  mapper.consultationToServiceConsultationResponseDto(consultation);
    }

    @Override
    public List<ConsultationResponseDto> getConsultations() {
        List<Consultation> consultations = StreamSupport
                .stream(consultationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.consultationsToServiceConsultationResponseDtos(consultations);
    }
    @Override
    public List<ConsultationServiceResponseDto> getConsultationByService() {
        List<Consultation> consultations = StreamSupport
                .stream(consultationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<ConsultationByService> conserv=new ArrayList<>(50);
        List<ServiceConsultation> serv = (List<ServiceConsultation>) serviceConsultationRepository.findAll();
        int x=0;
        for(ServiceConsultation s : serv){
            ConsultationByService cserv=new ConsultationByService();
            cserv.setName(s.getNomService());
            conserv.add(cserv);
            for(Consultation consultation :consultations){
                if(consultation.getDossierMedical().getServiceConsultation().getNomService().equals(s.getNomService())){
                  //  conserv.get(x).setNames(s.getNomService());
                    conserv.get(x).getConsultations().add(consultation);

                }
            }
            x++;
        }

        return mapper.consultationsServiceToConsultationsServiceResponseDtos(conserv);
    }

     @Override
    public List<ConsultationResponseDto> getConsultationsByDates(Date d1,Date d2) {
        List<Consultation> consultations = StreamSupport
                .stream(consultationRepository.findAllConsultationByDateConsultationBetween(d1,d2).spliterator(), false)
                .collect(Collectors.toList());

         /*List<Consultation> consultationsDay=new ArrayList<>();
         Date day = new Date();
         for(Consultation c : consultations){
             if(c.getDateConsultation().getDay()== day.getDay() && c.getDateConsultation().getMonth()== day.getMonth()&& c.getDateConsultation().getYear()== day.getYear()){
                 consultationsDay.add(c);
             }

         }*/
        return mapper.consultationsToServiceConsultationResponseDtos(consultations);
    }


    @Override
    public List<ConsultationResponseDto> getConsultationsByDossier(Long id) {
        List<Consultation> consultations = StreamSupport
                .stream(consultationRepository.findAllByDossierMedicalIdOrderByDateConsultation(id).spliterator(), false)
                .collect(Collectors.toList());

        return mapper.consultationsToServiceConsultationResponseDtos(consultations);
    }

    @Override
    public ConsultationResponseDto deleteConsultation(Long consultationId) {
       Consultation consultation = getConsultation(consultationId);
        consultationRepository.delete(consultation);
        return mapper.consultationToServiceConsultationResponseDto(consultation);
    }
@Transactional
    @Override
    public ConsultationResponseDto editConsultation(Long consultationId, ConsultationRequestDto
            consultationRequestDto) {
        Consultation consultationToEdit = getConsultation(consultationId);

    consultationToEdit.setTypeConsultation(consultationRequestDto.getTypeConsultation());
    consultationToEdit.setDateConsultation(consultationRequestDto.getDateConsultation());
    consultationToEdit.setDiagnostic(consultationRequestDto.getDiagnostic());

    if (consultationRequestDto.getPatientId() == null) {
        throw new IllegalArgumentException("consultation have one Patient");
    }

    Patient patient=patientService.getPatient(consultationRequestDto.getPatientId());
    consultationToEdit.setPatient(patient);

    if (consultationRequestDto.getDossierMedicalId() == null) {
        throw new IllegalArgumentException("consultation have one Dosiier");
    }

    DossierMedical dossier=dossierService.getDossierMedical(consultationRequestDto.getDossierMedicalId());
    consultationToEdit.setDossierMedical(dossier);

    if (consultationRequestDto.getConsultantId() == null) {
        throw new IllegalArgumentException("consultation have one Dosiier");
    }

    Consultant consultant=utilisateurRepository.findConsultantById(consultationRequestDto.getConsultantId());
    consultationToEdit.setConsultant(consultant);

    return mapper.consultationToServiceConsultationResponseDto(consultationToEdit);
    }



 }
