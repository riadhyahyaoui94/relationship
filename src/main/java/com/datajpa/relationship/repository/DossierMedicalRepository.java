package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.DossierMedical;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DossierMedicalRepository extends CrudRepository<DossierMedical,Long> {
    List<DossierMedical> findAllByPatientId(Long id);
}
