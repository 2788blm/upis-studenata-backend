package com.dodeka.upisstudenatabackend.repositories;

import com.dodeka.upisstudenatabackend.domain.KursnaLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface KursnaListaRepository extends JpaRepository<KursnaLista, LocalDate> {

}
