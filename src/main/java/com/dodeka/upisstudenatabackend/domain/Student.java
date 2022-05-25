package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
public class Student {

    @Id
    private String email;

    private String ime;

    private String prezime;

    @ManyToOne
    private Smer smer;

    private int brojIndeksa;

    private int godinaUpisa;

    @ManyToOne
    private StudijskaGrupa studijskaGrupa;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anketa> ankete;

}
