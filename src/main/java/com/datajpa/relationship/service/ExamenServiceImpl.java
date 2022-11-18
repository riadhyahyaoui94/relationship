package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExamenServiceImpl implements ExamenService {

    private final ExamenRepository examenRepository;
    private final OrdonnanceService ordonnanceService;

    @Autowired
    public ExamenServiceImpl(ExamenRepository examenRepository, OrdonnanceService ordonnanceService) {
        this.examenRepository = examenRepository;
        this.ordonnanceService = ordonnanceService;
    }

    @Transactional
    @Override
    public ExamenResponseDto addExamen(ExamenRequestDto examenRequestDto) {
        Examen examen = new Examen();
        examen.setNomExamen(examenRequestDto.getNomExamen());
        examen.setPrixExamen(examenRequestDto.getPrixExamen());
       /* if (examenRequestDto.getOrdonnanceId() != null) {
            throw new IllegalArgumentException("examen at least on ordonnance");
        }
*/
        /*Ordonnance ordonnance = ordonnanceService.getOrdonnance(examenRequestDto.getOrdonnanceId());
        examen.setOrdonnances(ordonnance);*/
        examenRepository.save(examen);
        return mapper.examenToExamenResponseDto(examen);
    }

    @Override
    public List<ExamenResponseDto> getExamens() {
        List<Examen> examens = StreamSupport
                .stream(examenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.examensToExamenResponseDtos(examens);
    }

    @Override
    public ExamenResponseDto getExamenById(Long examenId) {
        Examen examen = getExamen(examenId);
        return mapper.examenToExamenResponseDto(examen);
    }

    @Override
    public Examen getExamen(Long examenId) {
        return examenRepository.findById(examenId).orElseThrow(() ->
                new IllegalArgumentException(
                        "examen with id: " + examenId + " could not be found"));
    }

    @Override
    public ExamenResponseDto deleteExamen(Long examenId) {
        Examen examen = getExamen(examenId);
        examenRepository.delete(examen);
        return mapper.examenToExamenResponseDto(examen);
    }

    @Transactional
    @Override
    public ExamenResponseDto editExamen(Long examenId, ExamenRequestDto examenRequestDto) {
        Examen examenToEdit = getExamen(examenId);
        examenToEdit.setNomExamen(examenRequestDto.getNomExamen());
        examenToEdit.setPrixExamen(examenRequestDto.getPrixExamen());
        /*if (examenRequestDto.getOrdonnanceId() != null) {

            Ordonnance ordonnance = ordonnanceService.getOrdonnance(examenRequestDto.getOrdonnanceId());
            examenToEdit.setOrdonnance(ordonnance);}*/
            return mapper.examenToExamenResponseDto(examenToEdit);
        }

    @Override
    public ExamenResponseDto addOrdonnanceToExamen(Long examenId, Long ordonnanceId) {
        Examen examen = getExamen(examenId);
        Ordonnance ordonnance= ordonnanceService.getOrdonnance(ordonnanceId);

       /* if (Objects.nonNull(examen.getOrdonnance())){
            throw new RuntimeException("examen already has a ordonnance");}
            examen.setOrdonnances(ordonnance);
            ordonnance.addExamen(examen);*/
            return mapper.examenToExamenResponseDto(examen);

    }


    @Override
    public ExamenResponseDto removeOrdonnanceFromExamen(Long id) {
        Examen examen = getExamen(id);
        Ordonnance ordonnance = ordonnanceService.getOrdonnance(id);
        /*if (!(Objects.nonNull(examen.getOrdonnance()))) {
            throw new IllegalArgumentException("examen does not have ordonnance to delete");
        }
        examen.setOrdonnance(null);
        ordonnance.removeExamen(examen);*/
        return mapper.examenToExamenResponseDto(examen);
    }




}
