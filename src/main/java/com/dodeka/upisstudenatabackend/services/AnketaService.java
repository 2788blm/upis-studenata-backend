package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.QAnketa;
import com.dodeka.upisstudenatabackend.dto.AnketaFilter;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
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

    public Slice<Anketa> getAll(String filterTekst, LocalDateTime datumOd, LocalDateTime datumDo, Integer pageNo, Integer pageSize, String sortOrder) {
        BooleanBuilder predicate = new BooleanBuilder();
        if (filterTekst != null) {
            AnketaFilter anketaFilter = new AnketaFilter(filterTekst);
            BooleanExpression email = QAnketa.anketa.student.email.eq(anketaFilter.getEmail());
            BooleanExpression brojIndeksa = QAnketa.anketa.student.brojIndeksa.eq(anketaFilter.getBrojIndeksa());
            BooleanExpression ime = QAnketa.anketa.student.ime.eq(anketaFilter.getIme());
            BooleanExpression prezime = QAnketa.anketa.student.prezime.eq(anketaFilter.getPrezime());
            BooleanExpression filter = email.or(brojIndeksa).or(ime).or(prezime);
            predicate.and(filter);
        }
        if (datumOd != null) {
            predicate.and(QAnketa.anketa.datum.after(datumOd));
        }
        if (datumDo != null) {
            predicate.and(QAnketa.anketa.datum.before(datumDo));
        }
        Pageable pageRequest;
        if(sortOrder.equals("desc")) {
            pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("datum").descending());
        } else if(sortOrder.equals("asc")){
            pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("datum").ascending());
        } else {
            throw new RuntimeException("Niste uneli pravilan poredak za sortiranje!");
        }
        return anketaRepository.findAll(predicate, pageRequest);
    }
}
