package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    private String email;

    private String ime;

    private String prezime;

    @Enumerated(EnumType.STRING)
    private Smer smer;

    private int brojIndeksa;

    private int godinaUpisa;

    @ManyToOne
    private StudijskaGrupa studijskaGrupa;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anketa> ankete;

}
