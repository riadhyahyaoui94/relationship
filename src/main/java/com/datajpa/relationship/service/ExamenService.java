package com.datajpa.relationship.service;

//import com.datajpa.relationship.dto.request.ConsultantRequestDto;
import com.datajpa.relationship.dto.request.ExamenRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Examen;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamenService {
    public ExamenResponseDto addExamen(ExamenRequestDto examenRequestDto);
    public List<ExamenResponseDto> getExamens();

    public ExamenResponseDto getExamenById(Long examenId);
    public Examen getExamen(Long examenId);
    public ExamenResponseDto deleteExamen(Long examenId);
    public ExamenResponseDto editExamen(Long examenId, ExamenRequestDto examenRequestDto);
    public ExamenResponseDto addOrdonnanceToExamen(Long examenId, Long ordonnanceId);
    public ExamenResponseDto removeOrdonnanceFromExamen(Long id);
}


