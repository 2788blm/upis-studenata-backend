package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Builder
public class StudijskaGrupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    private String naziv;


}
