package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.repositories.SmerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SmerService {
    
    @Autowired
    private SmerRepository smerRepository;

    @Transactional
    public Smer createSmer(Smer smer) throws Exception {
        if(smerRepository.findBySifra(smer.getSifra()).isPresent()){
            throw new Exception("Smer sa ovom sifrom vec postoji!");
        }
        if(!StringUtils.hasText(smer.getNaziv())) {
            throw new RuntimeException("Smer mora imati naziv");
        }
        if(smer.getBrGodinaStudija() < 1) {
            throw new RuntimeException("Broj godina manji od 1!");
        }
        if(!StringUtils.hasText(smer.getZvanje())) {
            throw new RuntimeException("Smer mora imati zvanje!");
        }
        if(smer.getEspb() < 0) {
            throw new RuntimeException("Broj espb mora biti veci od 0!");
        }
        return smerRepository.save(smer);
    }

    @Transactional
    public void deletePredmet(Integer smerId) throws NotFoundException {
        Optional<Smer> smerO = smerRepository.findById(smerId);
        if (!smerO.isPresent())
            throw new NotFoundException("Smer nije nadjen!");
        smerRepository.delete(smerO.get());
    }

    public List<Smer> getAll() {
        System.out.println(";ALDKJA;LKD;LDKJF;LKAJD;FLAKJ");
        System.out.println(smerRepository.findAll());
        return smerRepository.findAll();
    }
}
