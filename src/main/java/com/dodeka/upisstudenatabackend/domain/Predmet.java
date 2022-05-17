package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int predmetId;

    private String nazivPredmeta;

    private int brESPB;

    private int semestar;   // kako da odradim min max value,

    private TipPredmeta tip;

    @ManyToOne
    private GrupaPredmeta grupaPredmeta;

    @ManyToOne
    private SkolskaGodina skolskaGodina;


    enum TipPredmeta {
        OBAVEZAN, IZBORNI
    }

}
