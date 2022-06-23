package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    @ManyToOne
    private Smer smer;

    private String naziv;

    private int espb;

    private int semestar;

    @Enumerated(EnumType.STRING)
    private TipPredmeta tip;

}
