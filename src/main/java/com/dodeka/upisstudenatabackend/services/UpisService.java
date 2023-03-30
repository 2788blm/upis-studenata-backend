package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.*;
import com.dodeka.upisstudenatabackend.dto.AnketaDto;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import com.dodeka.upisstudenatabackend.repositories.SmerRepository;
import com.dodeka.upisstudenatabackend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpisService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AnketaRepository anketaRepository;

    @Autowired
    private SmerRepository smerRepository;


//    public List<Predmet> getAvailableSubjects(String nazivSmera, int semestar) throws NullPointerException{
//        List<Smer> smerovi = smerRepository.findByNaziv(nazivSmera);
//        Smer odgovarajuciSmer = null;
//        for(Smer smer : smerovi) {
//            for(SkolskaGodina skolskaGodina : smer.getSkolskeGodine()) {
//                String godina = String.valueOf(LocalDate.now().getYear());
//                if(skolskaGodina.getGodina().contains(godina)) break;
//            }
//            odgovarajuciSmer = smer;
//        }
//
//        return (List<Predmet>) odgovarajuciSmer.getPredmeti().stream().filter(p -> p.getSemestar() == semestar);
//    }

    @Transactional
    public void addStudentInformations(AnketaDto anketaDto) {
        Student student = Student.builder()
                .email(anketaDto.getEmail())
                .ime(anketaDto.getIme())
                .prezime(anketaDto.getPrezime())
                .smer(anketaDto.getSmer())
                .brojIndeksa(anketaDto.getBrojIndeksa())
                .studijskaGrupa(anketaDto.getStudijskaGrupa())
                .build();
        Anketa anketa = new Anketa(student, anketaDto.getGodinaStudija(), anketaDto.getTipUpisa(), anketaDto.getPredmeti());
        studentRepository.save(student);
        anketaRepository.save(anketa);
    }


}
