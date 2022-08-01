package com.dodeka.upisstudenatabackend.repositories;

import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkolskaGodinaRepository extends JpaRepository<SkolskaGodina, String> {
}
