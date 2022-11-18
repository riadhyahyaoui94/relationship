package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Facturation;
import org.springframework.data.repository.CrudRepository;

public interface FacturationRepository  extends CrudRepository<Facturation,Long> {
}
