package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import com.dodeka.upisstudenatabackend.repositories.PredmetRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PredmetService {

    @Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private AnketaRepository anketaRepository;

    public List<Predmet> getAll(SkolskaGodina skolskaGodina, Smer smer, String deoNaziva) {
        List<Predmet> predmeti = new ArrayList<>();

        // kako pretraziti po ovim parametrima, querydsl?

        return predmeti;
    }

    @Transactional
    public Object createPredmet(Predmet predmet) {
        return predmetRepository.save(predmet);
    }


    public Predmet getPredmetById(int predmetId) throws NotFoundException {
        Optional<Predmet> predmetO = predmetRepository.findById(predmetId);
        if(!predmetO.isPresent()){
            throw new NotFoundException("Predmet with id = " + predmetId + " doesn't exists");
        }
        return predmetO.get();
    }

    @Transactional
    public Predmet updatePredmet(Predmet predmet) throws NotFoundException {
        if(predmet.getSmer() == null)
            throw new RuntimeException("Smer cannot be empty");
        if (StringUtils.hasText(predmet.getNaziv()))
            throw new RuntimeException("Name cannot be empty");
        if(predmet.getEspb() < 0)
            throw new RuntimeException("Espb cannot be negative");
        if(predmet.getSemestar() <= 0 || predmet.getSemestar() > 8)
            throw new RuntimeException("Semestar must be a number from 1 to 8");
        if(predmet.getTip() == null)
            throw new RuntimeException("Espb cannot be negative");

        Optional<Predmet> predmetO = predmetRepository.findById(predmet.getId());
        if(!predmetO.isPresent()){
            throw new NotFoundException("Predmet with id = " + predmet.getId() + " doesn't exists");
        }
        Predmet updatedPredmet = predmetO.get();
        updatedPredmet.setSkolskaGodina(predmet.getSkolskaGodina());
        updatedPredmet.setSmer(predmet.getSmer());
        updatedPredmet.setNaziv(predmet.getNaziv());
        updatedPredmet.setEspb(predmet.getEspb());
        updatedPredmet.setSemestar(predmet.getSemestar());
        updatedPredmet.setTip(predmet.getTip());

        return predmetRepository.save(updatedPredmet);
    }

    @Transactional
    public void deletePredmet(int predmetId) throws NotFoundException, RuntimeException{
        // Treba da proverim da li se predmet nalazi u nekoj anketi ili grupi
        Optional<Predmet> predmetO = predmetRepository.findById(predmetId);
        if (!predmetO.isPresent())
            throw new NotFoundException("Predmet with id = " + predmetId + " doesn't exists");
        List<Anketa> ankete = anketaRepository.findAll();
        for(Anketa anketa : ankete) {
            if(anketa.getPredmeti().contains(predmetO.get())){
                throw new RuntimeException("Predmet je vezan za anketu!");
            }
        }
        predmetRepository.delete(predmetO.get());
    }
}
