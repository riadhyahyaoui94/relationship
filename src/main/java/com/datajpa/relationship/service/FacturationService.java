package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.FacturationRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.FacturationResponseDto;
import com.datajpa.relationship.model.Facturation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacturationService {
    public Facturation getFacturation(Long facturationId);
    public FacturationResponseDto addFacturation(FacturationRequestDto facturationRequestDto);
    public FacturationResponseDto getFacturationById(Long facturationId);
    public List<FacturationResponseDto> getFacturations();
    public FacturationResponseDto deleteFacturation(Long facturationId);
    public FacturationResponseDto editFacturation(Long facturationId, FacturationRequestDto facturationRequestDto);

    public FacturationResponseDto addPaiementToFacturation(Long paiementId, Long facturationId);
    public FacturationResponseDto removePaiementFromFacturation(Long id);
}
