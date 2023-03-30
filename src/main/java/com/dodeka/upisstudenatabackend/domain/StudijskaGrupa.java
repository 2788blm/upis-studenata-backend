package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "studijska_grupa")
@Entity
public class StudijskaGrupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String naziv;

    private int semestar;

    @OneToMany(mappedBy = "studijskaGrupa", fetch = FetchType.EAGER)
    private Set<Predmet> predmeti;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    @OneToMany(mappedBy = "studijskaGrupa")
    private Set<Student> studenti;

    @ManyToOne
    private Smer smer;

}
