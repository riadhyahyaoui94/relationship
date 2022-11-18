package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.PrestationRequestDto;
import com.datajpa.relationship.dto.response.PrestationResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Facturation;
import com.datajpa.relationship.model.Ordonnance;
import com.datajpa.relationship.model.Prestation;
import com.datajpa.relationship.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PrestationServiceImpl implements PrestationService{
  @Autowired
    public final PrestationRepository prestationRepository;
    public final FacturationService facturationService;

    public PrestationServiceImpl(PrestationRepository prestationRepository, FacturationService facturationService) {
        this.prestationRepository = prestationRepository;
        this.facturationService = facturationService;
    }
@Transactional
    @Override
    public PrestationResponseDto addPrestation(PrestationRequestDto prestationRequestDto) {
    Prestation prestation = new Prestation();
    prestation.setNaturePrestation(prestationRequestDto.getNaturePrestation());
    if (prestationRequestDto.getFacturationId() == null) {
        throw new IllegalArgumentException("prestation at least on facturation");
    }

    Facturation facturation = facturationService.getFacturation(prestationRequestDto.getFacturationId());
    prestation.setFacturation(facturation);
    prestationRepository.save(prestation);
    return mapper.prestationToPrestationResponseDto(prestation);
    }

    @Override
    public List<PrestationResponseDto> getPrestations() {
        List<Prestation> prestations = StreamSupport
                .stream(prestationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.prestationsToPrestationResponseDtos(prestations);
    }

    @Override
    public PrestationResponseDto getPrestationById(Long prestationId) {
        Prestation prestation = getPrestation(prestationId);
        return mapper.prestationToPrestationResponseDto(prestation);
    }

    @Override
    public Prestation getPrestation(Long prestationId) {
        return prestationRepository.findById(prestationId).orElseThrow(() ->
                new IllegalArgumentException(
                        "prestation with id: " + prestationId + " could not be found"));
    }

    @Override
    public PrestationResponseDto deletePrestation(Long prestationId) {
        Prestation prestation = getPrestation(prestationId);
        prestationRepository.delete(prestation);
        return mapper.prestationToPrestationResponseDto(prestation);
    }
@Transactional
    @Override
    public PrestationResponseDto editPrestation(Long prestationId, PrestationRequestDto prestationRequestDto) {
    Prestation prestationToEdit = getPrestation(prestationId);
    prestationToEdit.setNaturePrestation(prestationRequestDto.getNaturePrestation());
       if (prestationRequestDto.getFacturationId() != null) {

        Facturation facturation = facturationService.getFacturation(prestationRequestDto.getFacturationId());
        prestationToEdit.setFacturation(facturation);}
    return mapper.prestationToPrestationResponseDto(prestationToEdit);
    }

    @Override
    public PrestationResponseDto addFacturationToPrestation(Long prestationId, Long facturationId) {
        Prestation prestation = getPrestation(prestationId);
        Facturation facturation = facturationService.getFacturation(facturationId);

        /*if (Objects.nonNull(prestation.getFacturation())){
            throw new RuntimeException("prestation already has a facturation");}
        prestation.setFacturation(facturation);
        facturation.addPrestation(prestation);*/
        return mapper.prestationToPrestationResponseDto(prestation);
    }

    @Override
    public PrestationResponseDto removeFacturationFromPrestation(Long id) {
        Prestation prestation = getPrestation(id);
        Facturation facturation = facturationService.getFacturation(id);
        if (!(Objects.nonNull(prestation.getFacturation()))) {
            throw new IllegalArgumentException("prestation does not have facturation to delete");
        }
       prestation.setFacturation(null);
        /*facturation.removePrestation(prestation);*/
        return mapper.prestationToPrestationResponseDto(prestation);
    }
}
