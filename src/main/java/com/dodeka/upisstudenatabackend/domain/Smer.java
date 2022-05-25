package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Smer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sifra;

    private String naziv;

    private int brGodinaStudija;

    private String zvanje;

    private int espb;

}
