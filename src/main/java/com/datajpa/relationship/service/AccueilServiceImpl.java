package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.AccueilRequestDto;
import com.datajpa.relationship.dto.response.AccueilResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.repository.AccueilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccueilServiceImpl implements AccueilService {

    private  final AccueilRepository accueilRepository;
@Autowired
    public AccueilServiceImpl(AccueilRepository accueilRepository) {
        this.accueilRepository = accueilRepository;
    }

    @Override
    public AccueilResponseDto addAccueil(AccueilRequestDto accueilRequestDto) {
        Accueil accueil = new Accueil();
        accueil.setNom(accueilRequestDto.getNom());

        return mapper.accueilToAccueilResponseDto(accueilRepository.save(accueil));

    }

    @Override
    public List<AccueilResponseDto> getAccueil() {

        List<Accueil> accueils = StreamSupport
                .stream(accueilRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.accueilsToAccueilResponseDtos(accueils);

    }

    @Override
    public AccueilResponseDto getAccueilById(Long accueilId) {
        Accueil  accueil = getAccueil(accueilId);
        return mapper.accueilToAccueilResponseDto(accueil);

    }

    @Override
    public Accueil getAccueil(Long accueilId) {
        return accueilRepository.findById(accueilId).orElseThrow(() ->
                new IllegalArgumentException("could not find category with id: " + accueilId));
    }

    @Override
    public AccueilResponseDto deleteAccueil(Long accueilId) {
        Accueil accueil = getAccueil(accueilId);
        accueilRepository.delete(accueil);
        return mapper.accueilToAccueilResponseDto(accueil);
    }

@Transactional
    @Override
    public AccueilResponseDto editAccueil(Long accueilId, AccueilRequestDto accueilRequestDto) {
    Accueil accueilToEdit = getAccueil(accueilId);
    accueilToEdit.setNom(accueilRequestDto.getNom());
    return mapper.accueilToAccueilResponseDto(accueilToEdit);
}
    }

