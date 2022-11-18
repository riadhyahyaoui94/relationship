package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.MedicamentRequestDto;
import com.datajpa.relationship.dto.response.MedicamentResponseDto;
import com.datajpa.relationship.model.Examen;
import com.datajpa.relationship.model.Medicament;
import com.datajpa.relationship.model.Ordonnance;
import com.datajpa.relationship.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MedicamentServiceImpl implements MedicamentService {

    public final MedicamentRepository  medicamentRepository;
    public final OrdonnanceService  ordonnanceService;
@Autowired
    public MedicamentServiceImpl(MedicamentRepository medicamentRepository, OrdonnanceService ordonnanceService) {
        this.medicamentRepository = medicamentRepository;
        this.ordonnanceService = ordonnanceService;
    }

    @Override
    public MedicamentResponseDto addMedicament(MedicamentRequestDto medicamentRequestDto) {
        Medicament  medicament = new Medicament();
        medicament.setNomMedicament(medicamentRequestDto.getNomMedicament());
        medicament.setDureeDePrise(medicamentRequestDto.getDureeDePrise());
       /* if (medicamentRequestDto.getOrdonnanceId() == null) {
            throw new IllegalArgumentException("medicament at least on ordonnance");
                  }
       Ordonnance ordonnance = ordonnanceService.getOrdonnance(medicamentRequestDto.getOrdonnanceId());
        medicament.setOrdonnances(ordonnance);*/

        return mapper.medicamentToMedicamentResponseDto(medicamentRepository.save(medicament));
    }

    @Override
    public List<MedicamentResponseDto> getMedicaments() {
      List<Medicament> medicaments= StreamSupport
                .stream(medicamentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
      return mapper.medicamentsToMedicamentResponseDtos(medicaments);
    }

    @Override
    public MedicamentResponseDto getMedicamentById(Long medicamentId) {
        Medicament medicament = getMedicament(medicamentId);
        return mapper.medicamentToMedicamentResponseDto(medicament);
    }

    @Override
    public Medicament getMedicament(Long medicamentId) {
        return medicamentRepository.findById(medicamentId).orElseThrow(() ->
                new IllegalArgumentException(
                        "medicament with id: " + medicamentId + " could not be found"));
    }

    @Override
    public MedicamentResponseDto deleteMedicament(Long medicamentId) {
        Medicament medicament = getMedicament(medicamentId);
        medicamentRepository.delete(medicament);
        return mapper.medicamentToMedicamentResponseDto(medicament);
    }
   @Transactional
   @Override
   public MedicamentResponseDto editMedicament(Long medicamentId, MedicamentRequestDto medicamentRequestDto) {
        Medicament medicamentToEdit = getMedicament(medicamentId);
        medicamentToEdit.setNomMedicament(medicamentRequestDto.getNomMedicament());
        medicamentToEdit.setDureeDePrise(medicamentRequestDto.getDureeDePrise());
        /*if (medicamentRequestDto.getOrdonnanceId() != null) {
            Ordonnance ordonnance = ordonnanceService.getOrdonnance(medicamentRequestDto.getOrdonnanceId());
            medicamentToEdit.setOrdonnance(ordonnance);}*/
            return mapper.medicamentToMedicamentResponseDto(medicamentToEdit);
        }


@Transactional
    @Override
    public MedicamentResponseDto addOrdonnanceToMedicament(Long medicamentId, Long ordonnanceId) {
    Medicament medicament = getMedicament(medicamentId);
    Ordonnance ordonnance = ordonnanceService.getOrdonnance(ordonnanceId);
  /*  if (Objects.nonNull(medicament.getOrdonnance())) {
        throw new IllegalArgumentException("medicament already has a prescription");
    }
    medicament.setOrdonnance(ordonnance);*/
    ordonnance.addMedicament(medicament);
    return mapper.medicamentToMedicamentResponseDto(medicament);
    }
    @Transactional
    @Override
    public MedicamentResponseDto removeOrdonnanceFromMedicament(Long id) {
    Medicament medicament = getMedicament(id);
        Ordonnance ordonnance = ordonnanceService.getOrdonnance(id);
    /*if (!Objects.nonNull(medicament.getOrdonnance())) {
        throw new IllegalArgumentException("medicament does not have a prescription to delete");
    }
    medicament.setOrdonnance(null);*/
    ordonnance.removeMedicament(medicament);
    return mapper.medicamentToMedicamentResponseDto(medicament);
    }

}
