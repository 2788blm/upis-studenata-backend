package com.dodeka.upisstudenatabackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "predmet")
@Entity
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 150)
    private String naziv;

    private int espb;

    private int semestar;

    @Enumerated(EnumType.STRING)
    private TipPredmeta tip;

    @ManyToMany
    @JoinTable(
            name = "predmeti_ankete",
            joinColumns = @JoinColumn(name = "predmet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "anketa_id", referencedColumnName = "id")
    )
    private Set<Anketa> ankete;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    @ManyToMany(mappedBy = "predmeti")
    @JsonIgnore
    private Set<Smer> smerovi;

    @ManyToOne
    private StudijskaGrupa studijskaGrupa;

}
