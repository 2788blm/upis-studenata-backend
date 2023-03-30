package com.dodeka.upisstudenatabackend.dto;

import com.dodeka.upisstudenatabackend.domain.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
public class AnketaDto {

    private String email;

    private String ime;

    private String prezime;

    private String brojIndeksa;

    private Smer smer;

    private GodinaStudija godinaStudija;

    private TipUpisa tipUpisa;

    private StudijskaGrupa studijskaGrupa;

    private Set<Predmet> predmeti;

}
