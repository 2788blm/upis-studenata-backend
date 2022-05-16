package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int predmetId;

    // Sta sa onom godinom u toku koje se odrzava da radim, jel to postoji ovde kao polje, ili kao deo naziva predmeta, ili nesto trece?

    private String nazivPredmeta;

    private int brESPB;

    private int semestar;   // kako da odradim min max value,

    private TipPredmeta tip;


    enum TipPredmeta {
        OBAVEZAN, IZBORNI
    }

}
