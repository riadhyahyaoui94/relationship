package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.request.PriseRDVRequestDto;
import com.datajpa.relationship.dto.response.PriseRDVResponseDto;
import com.datajpa.relationship.model.PriseRDV;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PriseRDVService {
    public PriseRDVResponseDto addPriseRDV(PriseRDVRequestDto priseRDVRequestDto);
    public List<PriseRDVResponseDto> getPriseRDVS();
    public PriseRDV getPriseRDV(Long priseRDVId);
    public PriseRDVResponseDto getPriseRDVRes(Long priseRDVId);
    public PriseRDVResponseDto deletePriseRDV(Long priseRDVId);
    public PriseRDVResponseDto editPriseRDV(Long priseRDVId, PriseRDVRequestDto priseRDVRequestDto);
    public List<PriseRDVResponseDto> getPriseRDVByConsultantId(Long id);
    public List<PriseRDVResponseDto> getPriseRDVByPatientId(Long id);
    public PriseRDVResponseDto addServiceConsultationToPriseRDV(Long priseRDVId, Long serviceConsultationId);
    public PriseRDVResponseDto removeServiceConsultationFromPriseRDV(Long id);
    public PriseRDVResponseDto addPatientToPriseRDV(Long priseRDVId, Long patientId);
    public PriseRDVResponseDto removePatientFromPriseRDV(Long id);
    public List<PriseRDVResponseDto> getPriseRDVSByDates(Date d1, Date d2);

}

