package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.PaiementRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.PaiementResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaiementServiceImpl implements PaiementService {

    public final PaiementRepository paiementRepository;
    private final AccueilService accueilService;
       private final AssuranceService assuranceService;

    @Autowired
    public PaiementServiceImpl(PaiementRepository paiementRepository,
                               AccueilService accueilService,  AssuranceService assuranceService) {
        this.paiementRepository = paiementRepository;
        this.accueilService = accueilService;
        this.assuranceService = assuranceService;
    }

    @Override
    public PaiementResponseDto addPaiement(PaiementRequestDto paiementRequestDto) {
        Paiement paiement = new Paiement();
        paiement.setCodeFacture(paiementRequestDto.getCodeFacture());
        if (paiementRequestDto.getAccueilId() == null) {
            throw new IllegalArgumentException("paiement at least on accueil");
        }
        if (paiementRequestDto.getAssuranceId() == null) {
            throw new IllegalArgumentException("paiement at least on assurance");
        }
        Accueil accueil = accueilService.getAccueil(paiementRequestDto.getAccueilId());
        paiement.setAccueil(accueil);
        Assurance assurance = assuranceService.getAssurance(paiementRequestDto.getAssuranceId());
        paiement.setAssurance(assurance);

        Paiement paiement1 = paiementRepository.save(paiement);
        return mapper.paiementToPaiementResponseDto(paiement);
    }

    @Override
    public List<PaiementResponseDto> getPaiements() {
        List<Paiement> paiements = StreamSupport
                .stream(paiementRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.paiementsToPaiementResponseDtos(paiements);
    }

    @Override
    public PaiementResponseDto getPaiementById(Long paiementId) {
        Paiement paiement = getPaiement(paiementId);
        return mapper.paiementToPaiementResponseDto(paiement);
    }

    @Override
    public Paiement getPaiement(Long paiementId) {
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(() ->
                new IllegalArgumentException("cannot find paiement with id: " + paiementId));
        return paiement;

    }

    @Override
    public PaiementResponseDto deletePaiement(Long paiementId) {
        Paiement paiement = getPaiement(paiementId);
        paiementRepository.delete(paiement);
        return mapper.paiementToPaiementResponseDto(paiement);
    }

    @Transactional
    @Override
    public PaiementResponseDto editPaiement(Long paiementId, PaiementRequestDto paiementRequestDto) {
        Paiement paiementToEdit = getPaiement(paiementId);
        paiementToEdit.setCodeFacture(paiementRequestDto.getCodeFacture());


        if (paiementRequestDto.getAssuranceId() != null) {
            Assurance assurance = assuranceService.getAssurance(paiementRequestDto.getAssuranceId());
            paiementToEdit.setAssurance(assurance);
        }
        if (paiementRequestDto.getAccueilId() != null) {
            Accueil accueil = accueilService.getAccueil(paiementRequestDto.getAccueilId());
            paiementToEdit.setAccueil(accueil);
        }
        return mapper.paiementToPaiementResponseDto(paiementToEdit);
    }

    @Override
    public PaiementResponseDto addAccueilToPaiement(Long paiementId, Long accueilId) {
        Paiement paiement = getPaiement(paiementId);
        Accueil accueil = accueilService.getAccueil(accueilId);
        if (Objects.nonNull(paiement.getAccueil())) {
            throw new IllegalArgumentException("paiement already has a accueil");
        }
        paiement.setAccueil(accueil);
        accueil.addPaiement(paiement);
        return mapper.paiementToPaiementResponseDto(paiement);
    }
    @Override
    public PaiementResponseDto deleteAccueilFromPaiement(Long id) {
        Paiement paiement = getPaiement(id);
        Accueil accueil = accueilService.getAccueil(id);
        if (!(Objects.nonNull(paiement.getAccueil()))) {
            throw new IllegalArgumentException("paiement does not have accueil to delete");
        }
        paiement.setAccueil(null);
        accueil.removePaiement(paiement);
        return mapper.paiementToPaiementResponseDto(paiement);
    }


    @Override
    public PaiementResponseDto addAssuranceToPaiement(Long paiementId, Long assuranceId) {
        Paiement paiement = getPaiement(paiementId);
        Assurance assurance = assuranceService.getAssurance(assuranceId);
        if (Objects.nonNull(paiement.getAssurance())) {
            throw new IllegalArgumentException("paiement already has a assurance");
        }
        paiement.setAssurance(assurance);
        assurance.addPaiement(paiement);
        return mapper.paiementToPaiementResponseDto(paiement);
    }
    @Override
    public PaiementResponseDto deleteAssuranceFromPaiement(Long id) {
        Paiement paiement = getPaiement(id);
        Assurance assurance = assuranceService.getAssurance(id);
        if (!(Objects.nonNull(paiement.getAssurance()))) {
            throw new IllegalArgumentException("paiement already has a assurance");
        }
        paiement.setAssurance(null);
        assurance.removePaiement(paiement);
        return mapper.paiementToPaiementResponseDto(paiement);
    }
}
