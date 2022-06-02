package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.dto.SkolskaGodinaDto;
import com.dodeka.upisstudenatabackend.repositories.PredmetRepository;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.querydsl.core.BooleanBuilder;
import liquibase.pro.packaged.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredmetService {

    @Autowired
    private PredmetRepository predmetRepository;

    public Object createPredmet(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    public List<Predmet> getAll(String skolskaGodinaId, Integer smerId, String nazivPredmeta) {

    }
}
