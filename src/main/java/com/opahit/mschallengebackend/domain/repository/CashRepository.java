package com.opahit.mschallengebackend.domain.repository;

import com.opahit.mschallengebackend.domain.model.Cash;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CashRepository extends CrudRepository<Cash, Long> {

    @Query(value = "select sum(c.value) from cash c where DATE(c.date) = :date", nativeQuery = true)
    Optional<Double> getSumValueByDate(@Param("date") LocalDate date);
}
