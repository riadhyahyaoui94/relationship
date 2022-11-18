package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.PriseRDV;
import com.datajpa.relationship.model.ServiceConsultation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends CrudRepository<Consultation,Long> {
    List<Consultation> findAllByDossierMedicalIdOrderByDateConsultation(Long id);
    List<Consultation> findAllConsultationByDateConsultationBetween(Date d1, Date d2);
}
