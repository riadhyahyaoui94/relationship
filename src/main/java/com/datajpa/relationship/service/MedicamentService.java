package com.datajpa.relationship.service;

//import com.datajpa.relationship.dto.request.ConsultantRequestDto;
import com.datajpa.relationship.dto.request.MedicamentRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.MedicamentResponseDto;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Medicament;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicamentService {
    public MedicamentResponseDto addMedicament(MedicamentRequestDto medicamentRequestDto);
    public List<MedicamentResponseDto> getMedicaments();
    public MedicamentResponseDto getMedicamentById(Long medicamentId);
    public Medicament getMedicament(Long medicamentId);
    public MedicamentResponseDto deleteMedicament(Long MedicamentId);
    public MedicamentResponseDto editMedicament(Long medicamentId, MedicamentRequestDto medicamentRequestDto);
    public MedicamentResponseDto addOrdonnanceToMedicament(Long medicamentId, Long ordonnanceId);
    public MedicamentResponseDto removeOrdonnanceFromMedicament(Long id);
}
