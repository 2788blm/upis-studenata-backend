package com.dodeka.upisstudenatabackend.repositories;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnketaRepository extends JpaRepository<Anketa, Integer>, QuerydslPredicateExecutor<Anketa> {



}
