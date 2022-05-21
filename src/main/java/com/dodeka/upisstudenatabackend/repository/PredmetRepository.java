package com.dodeka.upisstudenatabackend.repository;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Integer> {


    List<Predmet> getAllSubjects();
}
