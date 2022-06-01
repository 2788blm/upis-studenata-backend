package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.repositories.SkolskaGodinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SkolskaGodinaService {

    @Autowired
    SkolskaGodinaRepository skolskaGodinaRepository;


    public List<SkolskaGodina> getAll() {
        List<SkolskaGodina> skolskeGodine = new ArrayList<>(skolskaGodinaRepository.findAll());

//        sortiranje

        return skolskeGodine;
    }

//    private List<SkolskaGodina> sortSkolskeGodine(List<SkolskaGodina> skolskeGodine) {
//        List<SkolskaGodina> sortiraneSkolskeGodine = new ArrayList<>();
//
//
//        return sortiraneSkolskeGodine;
//    }

}
