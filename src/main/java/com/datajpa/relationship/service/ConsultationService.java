package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.ConsultationRequestDto;
import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.ConsultationResponseDto;
import com.datajpa.relationship.dto.response.ConsultationServiceResponseDto;
import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.ConsultationByService;
import com.datajpa.relationship.model.ServiceConsultation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ConsultationService {
    public Consultation getConsultation(Long consultationId);
    public ConsultationResponseDto addConsultation(ConsultationRequestDto consultationRequestDto);
    public ConsultationResponseDto getConsultationById(Long consultationId);
    public List<ConsultationResponseDto> getConsultations();
    public ConsultationResponseDto deleteConsultation(Long consultationId);
    public ConsultationResponseDto editConsultation(Long consultationId, ConsultationRequestDto
            consultationRequestDto);
    public List<ConsultationResponseDto> getConsultationsByDossier(Long id);
    public List<ConsultationResponseDto> getConsultationsByDates(Date d1, Date d2);
    public List<ConsultationServiceResponseDto> getConsultationByService();

}
