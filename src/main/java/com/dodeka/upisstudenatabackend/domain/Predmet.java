package com.dodeka.upisstudenatabackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne/*(cascade = CascadeType.PERSIST)*/
//    @JsonManagedReference
//    @Cascade({CascadeType.SAVE_UPDATE})
    @NotNull
    private SkolskaGodina skolskaGodina;

    @ManyToOne
    private Smer smer;

    private String naziv;

    private int espb;

    private int semestar;

    @Enumerated(EnumType.STRING)
    private TipPredmeta tip;

}
