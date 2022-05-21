package com.dodeka.upisstudenatabackend.service;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.Student;
import com.dodeka.upisstudenatabackend.dto.AnketaDto;
import com.dodeka.upisstudenatabackend.repository.AnketaRepository;
import com.dodeka.upisstudenatabackend.repository.PredmetRepository;
import com.dodeka.upisstudenatabackend.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpisService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AnketaRepository anketaRepository;

    @Autowired
    PredmetRepository predmetRepository;


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
        Anketa anketa = Anketa.builder()
                .student(student)
                .godinaStudija(anketaDto.getGodinaStudija())
                .build();
        studentRepository.save(student);
        anketaRepository.save(anketa);
    }

    public List<Predmet> returnAvailableSubjects(AnketaDto anketaDto) {
        List<Predmet> odgovarajuciPredmeti = new ArrayList<>();
        List<Predmet> sviPredmeti = predmetRepository.getAllSubjects();
        for(Predmet predmet : sviPredmeti) {
            boolean pass = true;
            pass &= predmet.getSmer() == anketaDto.getSmer();
            pass &= predmet.getSemestar()/2 <= anketaDto.getGodinaStudija();
            if(pass) odgovarajuciPredmeti.add(predmet);
        }
        return odgovarajuciPredmeti;
    }

    public List<Predmet> saveSubjectsForStudent(int anketaId, List<Predmet> izabraniPredmeti) throws RuntimeException {
        Optional<Anketa> anketaO = anketaRepository.findById(anketaId);
        if (!anketaO.isPresent()) {
            throw new RuntimeException("Anketa does not exist:" + anketaId);
        }
        Anketa anketaDB = anketaO.get();
        anketaDB.getPredmeti().addAll(izabraniPredmeti);
        anketaRepository.save(anketaDB);

        return izabraniPredmeti;
    }

}
