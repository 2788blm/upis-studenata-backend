package com.dodeka.upisstudenatabackend.dto;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.domain.StudijskaGrupa;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
public class AnketaDto {

    private String email;

    private String ime;

    private String prezime;

    private Smer smer;

    private String brojIndeksa;

    private int godinaUpisa;

    private StudijskaGrupa studijskaGrupa;

    private int godinaStudija;

    private Set<Predmet> predmeti;

}
