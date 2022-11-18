package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.PaiementRequestDto;
import com.datajpa.relationship.dto.response.PaiementResponseDto;
import com.datajpa.relationship.model.Paiement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaiementService {
    public PaiementResponseDto addPaiement(PaiementRequestDto paiementRequestDto);
    public List<PaiementResponseDto> getPaiements();
    public PaiementResponseDto getPaiementById(Long paiementId);
    public Paiement getPaiement(Long paiementId);
    public PaiementResponseDto deletePaiement(Long paiementId);
    public PaiementResponseDto editPaiement(Long paiementId, PaiementRequestDto paiementRequestDto);
    public PaiementResponseDto addAccueilToPaiement(Long paiementId, Long accueilId);


    public PaiementResponseDto deleteAccueilFromPaiement(Long id);

       public PaiementResponseDto addAssuranceToPaiement(Long paiementId, Long assuranceId);

    public PaiementResponseDto deleteAssuranceFromPaiement(Long id);
}
