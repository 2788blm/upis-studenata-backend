package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

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
    private Set<Anketa> ankete;

}
