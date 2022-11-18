package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.request.PrestationRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.PrestationResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Prestation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrestationService {
    public PrestationResponseDto addPrestation(PrestationRequestDto prestationRequestDto);
    public List<PrestationResponseDto> getPrestations();

    public PrestationResponseDto getPrestationById(Long prestationId);
    public Prestation getPrestation(Long prestationId);
    public PrestationResponseDto deletePrestation(Long prestationId);
    public PrestationResponseDto editPrestation(Long prestationId, PrestationRequestDto prestationRequestDto);
    public PrestationResponseDto addFacturationToPrestation(Long prestationId, Long facturationId);
    public PrestationResponseDto removeFacturationFromPrestation(Long id);
}


