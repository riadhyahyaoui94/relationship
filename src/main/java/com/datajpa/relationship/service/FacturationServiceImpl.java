package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.FacturationRequestDto;
import com.datajpa.relationship.dto.response.FacturationResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FacturationServiceImpl implements FacturationService {

    public final FacturationRepository facturationRepository;
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    PriseRDVRepository priseRDVRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SalleAttenteRepository salleRepository;
    @Autowired
    DetailsPatientRepository detailsPatientRepository;

    public final PaiementService paiementService;
@Autowired
    public FacturationServiceImpl(FacturationRepository facturationRepository, PaiementService paiementService) {
        this.facturationRepository = facturationRepository;
    this.paiementService = paiementService;
}

    @Override
    public Facturation getFacturation(Long facturationId) {
        return facturationRepository.findById(facturationId).orElseThrow(() ->
                new IllegalArgumentException("could not find facturation with id: " + facturationId));

    }

    @Override
    public FacturationResponseDto addFacturation(FacturationRequestDto facturationRequestDto) {
        Facturation facturation = new Facturation();
        facturation.setCodeFacture(facturationRequestDto.getCodeFacture());
        facturation.setDateFacture(facturationRequestDto.getDateFacture());
        facturation.setMontantAPayer(facturationRequestDto.getMontantAPayer());
        facturation.setMontantPaye(facturationRequestDto.getMontantPaye());
        facturation.setSommeRecue(facturationRequestDto.getSommeRecue());
        facturation.setSommeRendue(facturationRequestDto.getSommeRendue());
        facturation.setTypePaiement(facturationRequestDto.getTypePaiement());
        facturation.setNomAgent(facturationRequestDto.getNomAgent());
        if(facturationRequestDto.getExamenId()!=null){
            Examen examen= examenRepository.findById(facturationRequestDto.getExamenId()).orElse(null);
            facturation.setExamen(examen);
        }
        if(facturationRequestDto.getPriseRDVId()!=null){
            PriseRDV p= priseRDVRepository.findById(facturationRequestDto.getPriseRDVId()).orElse(null);
            p.getPatient().setSalleAttente(p.getServiceConsultation().getSalleAttente());
            patientRepository.save(p.getPatient());
            p.getServiceConsultation().getSalleAttente().getPatients().add(p.getPatient());
            salleRepository.save(p.getServiceConsultation().getSalleAttente());

if(p.getPatient().getDetailsPatient()!=null) {
    DetailsPatient details = detailsPatientRepository.findById(p.getPatient().getDetailsPatient().getId()).orElse(null);

    if (details != null) {
        details.setHeurePriseCharge(null);
        detailsPatientRepository.save(details);
    }
}
            facturation.setPriseRDV(p);
            p.setFacturation(facturationRepository.save(facturation));
            priseRDVRepository.save(p);
        }

        /*if (facturationRequestDto.getPaiementId() == null) {
            throw new IllegalArgumentException("facturation at least on paiement");
        }
        Paiement paiement=paiementService.getPaiement(facturationRequestDto.getPaiementId());
        facturation.setPaiement(paiement);*/

        return mapper.facturationToFacturationResponseDto(facturationRepository.save(facturation));
    }

         @Override
    public FacturationResponseDto getFacturationById(Long facturationId) {
        Facturation facturation = getFacturation(facturationId);
        return mapper.facturationToFacturationResponseDto(facturation);
    }

    @Override
    public List<FacturationResponseDto> getFacturations() {
        List<Facturation> facturations = StreamSupport
                .stream(facturationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.facturationsToFacturationResponseDtos(facturations);
    }

    @Override
    public FacturationResponseDto deleteFacturation(Long facturationId) {
        Facturation facturation = getFacturation(facturationId);
        facturationRepository.delete(facturation);
        return mapper.facturationToFacturationResponseDto(facturation);
    }
@Transactional
    @Override
    public FacturationResponseDto editFacturation(Long facturationId, FacturationRequestDto facturationRequestDto) {
    Facturation facturationToEdit = getFacturation(facturationId);
    facturationToEdit.setCodeFacture(facturationRequestDto.getCodeFacture());

    if (facturationRequestDto.getPaiementId() !=null){
        Paiement paiement =paiementService.getPaiement(facturationRequestDto.getPaiementId());
        facturationToEdit.setPaiement(paiement);
    }
    return mapper.facturationToFacturationResponseDto(facturationToEdit);
    }

    @Override
    public FacturationResponseDto addPaiementToFacturation(Long paiementId, Long facturationId) {
        Facturation facturation = getFacturation(facturationId);
        Paiement paiement= paiementService.getPaiement(paiementId);
         if (Objects.nonNull(facturation.getPaiement())){
            throw new RuntimeException("facturation already has a paiement");}
        facturation.setPaiement(paiement);
        paiement.addFacturation(facturation);
        return mapper.facturationToFacturationResponseDto(facturation);
    }


    @Override
    public FacturationResponseDto removePaiementFromFacturation(Long id) {
       Facturation facturation  = getFacturation(id);
        Paiement  paiement = paiementService.getPaiement(id);
        if (!(Objects.nonNull(facturation.getPaiement()))) {
            throw new IllegalArgumentException("facturation does not have paiement to delete");
        }
        facturation.setPaiement(null);
        paiement.removeFacturation(facturation);
        return mapper.facturationToFacturationResponseDto(facturation);
    }
}
