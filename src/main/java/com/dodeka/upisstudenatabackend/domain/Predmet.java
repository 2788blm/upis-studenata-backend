package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    @Enumerated(EnumType.STRING)
    private Smer smer;

    private String naziv;

    private int espb;

    private int semestar;

    @Enumerated(EnumType.STRING)
    private TipPredmeta tip;

}
