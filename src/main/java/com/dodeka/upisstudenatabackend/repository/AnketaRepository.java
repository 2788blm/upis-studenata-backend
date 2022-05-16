package com.dodeka.upisstudenatabackend.repository;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnketaRepository extends JpaRepository<Anketa, Integer> {



}
