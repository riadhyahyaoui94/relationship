package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.OrdonnanceRequestDto;
import com.datajpa.relationship.dto.response.OrdonnanceResponseDto;
import com.datajpa.relationship.model.Ordonnance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdonnanceService {
    public OrdonnanceResponseDto addOrdonnance(OrdonnanceRequestDto ordonnanceRequestDto);
    public List<OrdonnanceResponseDto> getOrdonnances();
    public OrdonnanceResponseDto getOrdonnanceById(Long ordonnanceId);
    public Ordonnance getOrdonnance(Long ordonnanceId);
    public OrdonnanceResponseDto deleteOrdonnance(Long ordonnanceId);
    public OrdonnanceResponseDto editOrdonnance(Long ordonnanceId, OrdonnanceRequestDto ordonnanceRequestDto);
    public OrdonnanceResponseDto addConsultantToOrdonnance(Long ordonnanceId, Long consultantId);
    public OrdonnanceResponseDto deleteConsultantFromOrdonnance(Long id);
}
