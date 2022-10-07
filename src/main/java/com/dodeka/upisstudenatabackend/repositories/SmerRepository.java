package com.dodeka.upisstudenatabackend.repositories;

import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmerRepository extends JpaRepository<Smer, Integer> {

    Optional<Object> findBySifra(String sifra);

}
