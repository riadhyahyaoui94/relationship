package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.ExamenResponseDto;
import com.datajpa.relationship.dto.response.ServiceConsultationResponseDto;
import com.datajpa.relationship.model.ServiceConsultation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceConsultationService {
    public ServiceConsultation getServiceConsultation(Long serviceConsultationId);
    public ServiceConsultationResponseDto addServiceConsultation(ServiceConsultationRequestDto serviceConsultationRequestDto);
    public ServiceConsultationResponseDto getServiceConsultationById(Long serviceConsultationId);
    public List<ServiceConsultationResponseDto> getServiceConsultations();
    public ServiceConsultationResponseDto deleteServiceConsultation(Long seviceConsultationId);
    public ServiceConsultationResponseDto editServiceConsultation(Long SeviceConsultationId, ServiceConsultationRequestDto
            serviceConsultationRequestDto);

    public ServiceConsultationResponseDto addSalleAttenteToServiceConsultation(Long salleAttenteId, Long serviceConsultationId);
    public ServiceConsultationResponseDto removeSalleAttenteFromServiceConsultation(Long id);
}
