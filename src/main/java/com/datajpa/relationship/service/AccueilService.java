package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.AccueilRequestDto;
import com.datajpa.relationship.dto.response.AccueilResponseDto;
import com.datajpa.relationship.model.Accueil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccueilService {
    public AccueilResponseDto addAccueil(AccueilRequestDto accueilRequestDto);
    public List<AccueilResponseDto> getAccueil();
    public AccueilResponseDto getAccueilById(Long accueilId);
    public Accueil getAccueil(Long accueilId);
    public AccueilResponseDto deleteAccueil(Long accueilId);
    public AccueilResponseDto editAccueil(Long accueilId, AccueilRequestDto accueilRequestDto);

}
