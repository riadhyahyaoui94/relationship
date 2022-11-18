package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.AssuranceRequestDto;
import com.datajpa.relationship.dto.response.AssuranceResponseDto;
import com.datajpa.relationship.model.Assurance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssuranceService {
    public Assurance getAssurance(Long assuranceId);
    public AssuranceResponseDto addAssurance(AssuranceRequestDto assuranceRequestDto);
    public AssuranceResponseDto getAssuranceById(Long assuranceId);
    public List<AssuranceResponseDto> getAssurances();
    public AssuranceResponseDto deleteAssurance(Long assuranceId);
    public AssuranceResponseDto editAssurance(Long assuranceId, AssuranceRequestDto assuranceRequestDto);
}
