package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import org.springframework.data.repository.CrudRepository;

public interface ServiceConsultationRepository extends CrudRepository<ServiceConsultation,Long> {
}
