package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Ordonnance;
import org.springframework.data.repository.CrudRepository;

public interface OrdonnanceRepository extends CrudRepository<Ordonnance,Long> {
}
