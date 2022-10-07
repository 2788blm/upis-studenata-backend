package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.dto.SkolskaGodinaDto;
import com.dodeka.upisstudenatabackend.repositories.SkolskaGodinaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SkolskaGodinaService {

    @Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;


    public List<SkolskaGodinaDto> getAll() {
        List<SkolskaGodina> skolskeGodine = new ArrayList<>(skolskaGodinaRepository.findAll());
        List<SkolskaGodinaDto> skolskeGodineDto = new ArrayList<>();
        for (SkolskaGodina skolskaGodina : skolskeGodine) {
            skolskeGodineDto.add(new SkolskaGodinaDto(skolskaGodina.getGodina()));
        }
        Collections.sort(skolskeGodineDto);
        return skolskeGodineDto;
    }

    @Transactional
    public SkolskaGodina addSkolskaGodina(SkolskaGodina skolskaGodina) throws NotFoundException {
        Optional<SkolskaGodina> skolskaGodinaO = skolskaGodinaRepository.findById(skolskaGodina.getGodina());
        if(skolskaGodinaO.isPresent()){
            throw new NotFoundException("Skolska godina" + skolskaGodina.getGodina() + " vec postoji!");
        }
        return skolskaGodinaRepository.save(skolskaGodina);
    }
}
