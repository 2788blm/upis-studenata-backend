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
@Table(name = "smer")
@Entity
public class Smer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 50)
    private String sifra;

    @Column(length = 150)
    private String naziv;

    @Column(name = "br_godina_studija")
    private int brGodinaStudija;    // koliko godina smer traje

    @Column(length = 150)
    private String zvanje;

    private int espb;

    @OneToMany(mappedBy = "smer")
    private Set<Student> studenti;

    @ManyToMany
    @JoinTable(
            name = "smerovi_predmeti",
            joinColumns = @JoinColumn(name = "smer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "predmet_id", referencedColumnName = "id")
    )
    private Set<Predmet> predmeti;

    @Column(name = "skolske_godine")
    @ManyToMany(mappedBy = "smerovi")
    private Set<SkolskaGodina> skolskeGodine;

    @Column(name = "studijske_grupe")
    @OneToMany(mappedBy = "smer")
    private Set<StudijskaGrupa> studijskeGrupe;


}
