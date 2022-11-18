package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PatientRepository  extends CrudRepository<Patient,Long> {



    @Query("select p from Patient p left join p.detailsPatient d where p.salleAttente.id=?1 order by d.heurePriseCharge asc nulls first ")
    List<Patient> findAllBySalleAttenteId(long id);

    @Query(" select count(p) from Patient p where p.estAssure=?1")
    Long countAssuree(boolean assurer);

}
