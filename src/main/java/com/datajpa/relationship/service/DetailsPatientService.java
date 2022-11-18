package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.DetailsPatientRequestDto;
import com.datajpa.relationship.dto.request.SalleAttenteRequestDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.dto.response.SalleAttenteResponseDto;
import com.datajpa.relationship.model.DetailsPatient;
import com.datajpa.relationship.model.SalleAttente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailsPatientService {
    public DetailsPatientResponseDto addDetailsPatient(DetailsPatientRequestDto detailsPatientRequestDto);
    public List<DetailsPatientResponseDto> getDetailsPatients();
    public DetailsPatientResponseDto getDetailsPatientById(Long detailsPatientId);
    public DetailsPatient getDetailsPatient(Long detailsPatientId);
    public DetailsPatientResponseDto deleteDetailsPatient(Long detailsPatientId);

    DetailsPatientResponseDto editDetailsPatient(Long detailsPatientId, DetailsPatientRequestDto detailsPatientRequestDto);

}
