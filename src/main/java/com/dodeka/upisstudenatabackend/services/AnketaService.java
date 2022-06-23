package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Optional;

public class AnketaService {

    @Autowired
    private AnketaRepository anketaRepository;

    public Anketa getAnketaById(int anketaId) throws NotFoundException {
        Optional<Anketa> anketaO = anketaRepository.findById(anketaId);
        if(!anketaO.isPresent()){
            throw new NotFoundException("Predmet with id = " + anketaId + " doesn't exists");
        }
        return anketaO.get();
    }

    @Transactional
    public Anketa updateAnketa(Anketa anketa) throws NotFoundException {
        if(anketa == null)
            throw new RuntimeException("Anketa is null!");
        if(anketa.getStudent() == null)
            throw new RuntimeException("Student is null!");
        if(anketa.getGodinaStudija() < 1 || anketa.getGodinaStudija() > 4)
            throw new RuntimeException("Godina studija must be a number from 1 to 4");
        if(CollectionUtils.isEmpty(anketa.getPredmeti()))
            throw new RuntimeException("Predmeti cannot be empty!");

        Optional<Anketa> anketaO = anketaRepository.findById(anketa.getId());
        if(!anketaO.isPresent()){
            throw new NotFoundException("Predmet with id = " + anketa.getId() + " doesn't exists");
        }
        Anketa updatedAnketa = anketaO.get();
        updatedAnketa.setStudent(anketa.getStudent());
        updatedAnketa.setGodinaStudija(anketa.getGodinaStudija());
        updatedAnketa.setEspb(anketa.getEspb());    //  ili se ovo samo izracunava?
        updatedAnketa.setPredmeti(anketa.getPredmeti());

        return anketaRepository.save(updatedAnketa);
    }
}
