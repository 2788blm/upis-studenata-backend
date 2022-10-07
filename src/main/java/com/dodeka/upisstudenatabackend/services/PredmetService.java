package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.*;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import com.dodeka.upisstudenatabackend.repositories.PredmetRepository;
import com.querydsl.core.BooleanBuilder;
import javassist.NotFoundException;
import liquibase.pro.packaged.Q;
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

    public List<Predmet> getAll(String skolskaGodina, Integer smerId, String deoNaziva) {
        System.out.println(skolskaGodina);
        BooleanBuilder predicate = new BooleanBuilder();
        if (skolskaGodina != null) {
            predicate.and(QPredmet.predmet.skolskaGodina.godina.eq(skolskaGodina));
        }
        if (smerId != null) {
            predicate.and(QPredmet.predmet.smer.id.eq(smerId));
        }
        if (StringUtils.hasText(deoNaziva)) {
            predicate.and(QPredmet.predmet.naziv.contains(deoNaziva));
        }
        List<Predmet> predmeti = new ArrayList<>();
        predmetRepository.findAll(predicate).forEach(predmeti::add);
        System.out.println("a;lkjfds;k");
        return predmeti;
//        return predmetRepository.findAll();
    }

    @Transactional
    public Object createPredmet(Predmet predmet) {
        if(predmet.getId() != null){
            Optional<Predmet> predmetO = predmetRepository.findById(predmet.getId());
            if(!predmetO.isPresent()){
                throw new RuntimeException("Predmet sa id: " + predmet.getId() + " vec postoji");
            }
        }
        BooleanBuilder predicate = new BooleanBuilder();
        if (StringUtils.hasText(predmet.getNaziv())) {
            predicate.and(QPredmet.predmet.naziv.contains(predmet.getNaziv()));
        }
        if (predmet.getSkolskaGodina() != null && predmet.getSkolskaGodina().getGodina() != null) {
            predicate.and(QPredmet.predmet.skolskaGodina.godina.eq(predmet.getSkolskaGodina().getGodina()));
        }
        List<Predmet> predmeti = new ArrayList<>();
        predmetRepository.findAll(predicate).forEach(predmeti::add);
        if(!predmeti.isEmpty()){
            throw new RuntimeException("Predmet sa nazivom: " + predmet.getNaziv() + " u skolskoj godini: " + predmet.getSkolskaGodina().getGodina() + " vec postoji!");
        }
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

        Optional<Predmet> predmetO = predmetRepository.findById(predmetId);
        if (!predmetO.isPresent())
            throw new NotFoundException("Predmet nije nadjen!");

        List<Anketa> ankete = new ArrayList<>();
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QAnketa.anketa.predmeti.contains(predmetO.get()));
        anketaRepository.findAll(predicate).forEach(ankete::add);
        if(!ankete.isEmpty()) {
            throw new RuntimeException("Predmet je vezan za anketu!");
        }

        // Treba proveriti da li je predmet vezan za neku grupu. Sta je grupa?

        predmetRepository.delete(predmetO.get());

    }
}
