package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Paiement;
import org.springframework.data.repository.CrudRepository;

public interface PaiementRepository  extends CrudRepository<Paiement,Long> {
}
