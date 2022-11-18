package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.AssuranceRequestDto;
import com.datajpa.relationship.dto.response.AssuranceResponseDto;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.repository.AssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssuranceServiceImpl implements AssuranceService {

    public final AssuranceRepository assuranceRepository;
@Autowired
    public AssuranceServiceImpl(AssuranceRepository assuranceRepository) {
        this.assuranceRepository = assuranceRepository;
    }

    @Override
    public Assurance getAssurance(Long assuranceId) {
        return assuranceRepository.findById(assuranceId).orElseThrow(() ->
                new IllegalArgumentException("could not find category with id: " + assuranceId));
    }

    @Override
    public AssuranceResponseDto addAssurance(AssuranceRequestDto assuranceRequestDto) {
        Assurance assurance = new Assurance();
        assurance.setNomPatient(assuranceRequestDto.getNomPatient());
        assuranceRepository.save(assurance);
        return mapper.assuranceToAssuranceResponseDto(assurance);
    }

    @Override
    public AssuranceResponseDto getAssuranceById(Long assuranceId) {
        Assurance assurance = getAssurance(assuranceId);
        return mapper.assuranceToAssuranceResponseDto(assurance);
    }

    @Override
    public List<AssuranceResponseDto> getAssurances() {
        List<Assurance> assurances = StreamSupport
                .stream(assuranceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.assurancesToAssuranceResponseDtos(assurances);
    }

    @Override
    public AssuranceResponseDto deleteAssurance(Long assuranceId) {
        Assurance assurance = getAssurance(assuranceId);
        assuranceRepository.delete(assurance);
        return mapper.assuranceToAssuranceResponseDto(assurance);
    }
@Transactional
    @Override
    public AssuranceResponseDto editAssurance(Long assuranceId, AssuranceRequestDto assuranceRequestDto) {
    Assurance assuranceToEdit = getAssurance(assuranceId);
   assuranceToEdit.setNomPatient(assuranceRequestDto.getNomPatient());
    return mapper.assuranceToAssuranceResponseDto(assuranceToEdit);
    }
}
