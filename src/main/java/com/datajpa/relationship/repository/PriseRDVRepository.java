package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.PriseRDV;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PriseRDVRepository  extends CrudRepository<PriseRDV,Long> {

    List<PriseRDV> findPriseRDVByConsultantIdOrderByDateRDV(long id);
    List<PriseRDV> findAllPriseRDVByDateRDVBetween(Date d1, Date d2);

    @Query("select p from PriseRDV p where p.patient.id=?1 order by p.payed desc ,p.dateRDV")
    List<PriseRDV> findPriseRDVByPatientIdOrderByDateRDVPayed(long id);

}
