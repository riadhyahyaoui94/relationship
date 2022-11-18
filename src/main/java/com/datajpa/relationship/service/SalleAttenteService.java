package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.model.SalleAttente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalleAttenteService {
    public SalleAttenteResponseDto addSalleAttente(SalleAttenteRequestDto salleAttenteRequestDto);
    public List<SalleAttenteResponseDto> getSalleAttentes();
    public SalleAttenteResponseDto getSalleAttenteById(Long salleAttenteId);
    public SalleAttente getSalleAttente(Long salleAttenteId);
    public SalleAttenteResponseDto deleteSalleAttente(Long salleAttenteId);

    SalleAttenteResponseDto editSalleAttente(Long salleAttenteId, SalleAttenteRequestDto salleAttenteRequestDto);

    //public SalleAttenteResponseDto addAccueilToSalleAttente(Long salleAttenteId, Long accueilId);
    //public SalleAttenteResponseDto removeAccueilFromSalleAttente(Long id);
}
