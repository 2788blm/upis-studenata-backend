package com.dodeka.upisstudenatabackend.repository;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Integer> {
}
