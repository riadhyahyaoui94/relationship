package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.OrdonnanceRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.OrdonnanceResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.ConsultationRepository;
import com.datajpa.relationship.repository.ExamenRepository;
import com.datajpa.relationship.repository.MedicamentRepository;
import com.datajpa.relationship.repository.OrdonnanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrdonnanceServiceImpl implements OrdonnanceService {

    private final OrdonnanceRepository ordonnanceRepository;
    private final UtilisateurService utilisateurService;
@Autowired
    public OrdonnanceServiceImpl(OrdonnanceRepository ordonnanceRepository,UtilisateurService utilisateurService) {
        this.ordonnanceRepository = ordonnanceRepository;
        this.utilisateurService=utilisateurService;
    }
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    MedicamentRepository medicamentRepository;
    @Autowired
    ConsultationRepository consultationRepository;

    @Override
    public OrdonnanceResponseDto addOrdonnance(OrdonnanceRequestDto ordonnanceRequestDto) {
        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setNomPatient(ordonnanceRequestDto.getNomPatient());
        ordonnance.setDateOrdonnance(ordonnanceRequestDto.getDateOrdonnance());
        ordonnance.setNatureOrdonnance(ordonnanceRequestDto.getNatureOrdonnance());
        Consultation consultation = consultationRepository.findById(ordonnanceRequestDto.getConsultationId()).orElse(null);
        ordonnance.setConsultation(consultation);

        if (ordonnanceRequestDto.getConsultantId() != null) {
            Consultant consultant = mapper.consultantResponseDtoToConsultant(utilisateurService.getConsultantById(ordonnanceRequestDto.getConsultantId()));
            ordonnance.setConsultant(consultant);
        }
        Ordonnance ordonnance2 = ordonnanceRepository.save(ordonnance);
        List<Examen> examens=new ArrayList<>();

        if (ordonnanceRequestDto.getExamensId() != null) {
            for(Long id:ordonnanceRequestDto.getExamensId()){
                Examen examen=examenRepository.findById(id).orElse(null);
                List<Ordonnance> ordo ;
                ordo=examen.getOrdonnances();
                ordo.add(ordonnance);
                examen.setOrdonnances(ordo);

                examens.add(examenRepository.save(examen));
            }
            ordonnance2.setExamens(examens);
        }

        if (ordonnanceRequestDto.getMedicamentsId() != null) {
            List<Medicament> medicaments=new ArrayList<>();
            for(Long id:ordonnanceRequestDto.getMedicamentsId()){
                Medicament medicament=medicamentRepository.findById(id).orElse(null);
                List<Ordonnance> ordo ;
                ordo=medicament.getOrdonnances();
                ordo.add(ordonnance);
                medicament.setOrdonnances(ordo);
                medicaments.add(medicamentRepository.save(medicament));
            }
            ordonnance2.setMedicaments(medicaments);
        }


        return mapper.ordonnanceToOrdonnanceResponseDto(ordonnanceRepository.save(ordonnance2));

    }

    @Override
    public List<OrdonnanceResponseDto> getOrdonnances() {
        List<Ordonnance> ordonnances = StreamSupport
                .stream(ordonnanceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.ordonnancesToOrdonnanceResponseDtos(ordonnances);
    }

    @Override
    public OrdonnanceResponseDto getOrdonnanceById(Long ordonnanceId) {
        Ordonnance ordonnance = getOrdonnance(ordonnanceId);
        return mapper.ordonnanceToOrdonnanceResponseDto(ordonnance);
    }

    @Override
    public Ordonnance getOrdonnance(Long ordonnanceId) {
        return ordonnanceRepository.findById(ordonnanceId).orElseThrow(() ->
                new IllegalArgumentException("could not find ordonnance with id: " + ordonnanceId));
    }

    @Override
    public OrdonnanceResponseDto deleteOrdonnance(Long ordonnanceId) {
        Ordonnance ordonnance = getOrdonnance(ordonnanceId);
        ordonnance.setConsultant(null);
        ordonnance.setExamens(null);
        ordonnance.setMedicaments(null);
        ordonnanceRepository.save(ordonnance);
        ordonnanceRepository.delete(ordonnance);
        return mapper.ordonnanceToOrdonnanceResponseDto(ordonnance);
    }
@Transactional
    @Override
    public OrdonnanceResponseDto editOrdonnance(Long ordonnanceId, OrdonnanceRequestDto ordonnanceRequestDto) {
    Ordonnance ordonnanceToEdit = getOrdonnance(ordonnanceId);
    ordonnanceToEdit.setNomPatient(ordonnanceRequestDto.getNomPatient());
    ordonnanceToEdit.setNatureOrdonnance(ordonnanceRequestDto.getNatureOrdonnance());
    ordonnanceToEdit.setDateOrdonnance(ordonnanceRequestDto.getDateOrdonnance());
    Consultation consultation = consultationRepository.findById(ordonnanceRequestDto.getConsultationId()).orElse(null);
    ordonnanceToEdit.setConsultation(consultation);
    if (ordonnanceRequestDto.getConsultantId() != null) {
        Consultant consultant = mapper.consultantResponseDtoToConsultant(utilisateurService.getConsultantById(ordonnanceRequestDto.getConsultantId()));
        ordonnanceToEdit.setConsultant(consultant);
    }

    List<Examen> examens=ordonnanceToEdit.getExamens();

    if (ordonnanceRequestDto.getExamensId() != null) {
        for(Long id:ordonnanceRequestDto.getExamensId()){
            Examen examen=examenRepository.findById(id).orElse(null);
            examens.add(examen);
        }
        ordonnanceToEdit.setExamens(examens);
    }

    if (ordonnanceRequestDto.getMedicamentsId() != null) {
        List<Medicament> medicaments=ordonnanceToEdit.getMedicaments();
        for(Long id:ordonnanceRequestDto.getMedicamentsId()){
            Medicament medicament=medicamentRepository.findById(id).orElse(null);
            medicaments.add(medicament);
        }
        ordonnanceToEdit.setMedicaments(medicaments);
    }



    return mapper.ordonnanceToOrdonnanceResponseDto(ordonnanceToEdit);
    }

    @Override
    public OrdonnanceResponseDto addConsultantToOrdonnance(Long ordonnanceId, Long consultantId) {
        Ordonnance ordonnance = getOrdonnance(ordonnanceId);
       Consultant consultant = mapper.consultantResponseDtoToConsultant(utilisateurService.getConsultantById(consultantId));
        if (Objects.nonNull(ordonnance.getConsultant())){
            throw new RuntimeException("ordonnance already has a consultant");
        }
        ordonnance.setConsultant(consultant);
       consultant.addOrdonnance(ordonnance);
        return mapper.ordonnanceToOrdonnanceResponseDto(ordonnance);
    }

    @Override
    public OrdonnanceResponseDto deleteConsultantFromOrdonnance(Long id) {
        Ordonnance ordonnance = getOrdonnance(id);
        Consultant consultant=mapper.consultantResponseDtoToConsultant(utilisateurService.getConsultantById(id));
        if (Objects.nonNull(ordonnance.getConsultant())) {
            throw new IllegalArgumentException("ordonnance already has a consultant");}
        ordonnance.setConsultant(consultant);
        consultant.removeOrdonnance(ordonnance);
        return mapper.ordonnanceToOrdonnanceResponseDto(ordonnance);
    }}



