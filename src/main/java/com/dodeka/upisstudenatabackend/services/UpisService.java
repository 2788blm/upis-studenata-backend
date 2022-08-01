package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.Student;
import com.dodeka.upisstudenatabackend.dto.AnketaDto;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import com.dodeka.upisstudenatabackend.repositories.PredmetRepository;
import com.dodeka.upisstudenatabackend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpisService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AnketaRepository anketaRepository;

    @Autowired
    private PredmetRepository predmetRepository;


    public List<Predmet> getAvailableSubjects(String smer, int semestar) {
        List<Predmet> odgovarajuciPredmeti = new ArrayList<>();
        List<Predmet> sviPredmeti = predmetRepository.findAll();
        for(Predmet predmet : sviPredmeti) {
            boolean pass = true;
            pass &= predmet.getSmer().getNaziv().equals(smer);
            pass &= predmet.getSemestar()/2 <= semestar;
            if(pass) odgovarajuciPredmeti.add(predmet);
        }
        return odgovarajuciPredmeti;
    }

    @Transactional
    public void addStudentInformations(AnketaDto anketaDto) {
        Student student = Student.builder()
                .email(anketaDto.getEmail())
                .ime(anketaDto.getIme())
                .prezime(anketaDto.getPrezime())
                .smer(anketaDto.getSmer())
                .brojIndeksa(anketaDto.getBrojIndeksa())
                .godinaUpisa(anketaDto.getGodinaUpisa())
                .studijskaGrupa(anketaDto.getStudijskaGrupa())
                .build();
        Anketa anketa = new Anketa(student, anketaDto.getGodinaStudija(), anketaDto.getPredmeti());
        studentRepository.save(student);
        anketaRepository.save(anketa);
    }


}
