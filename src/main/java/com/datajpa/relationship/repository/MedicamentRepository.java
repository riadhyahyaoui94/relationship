package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Medicament;
import org.springframework.data.repository.CrudRepository;

public interface MedicamentRepository  extends CrudRepository<Medicament,Long> {
}
