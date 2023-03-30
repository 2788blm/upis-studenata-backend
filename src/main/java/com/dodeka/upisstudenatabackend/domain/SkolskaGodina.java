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
@Table(name = "skolska_godina")
@Entity
public class SkolskaGodina {

    @Id
    @Column(length = 9)
    private String godina;  // 2021/2022

    @OneToMany(mappedBy = "skolskaGodina")
    private Set<Predmet> predmeti;

    @ManyToMany
    @JoinTable(
            name = "skolske_godine_smerovi",
            joinColumns = @JoinColumn(name = "skolska_godina_id", referencedColumnName = "godina"),
            inverseJoinColumns = @JoinColumn(name = "smer_id", referencedColumnName = "id")
    )
    private Set<Smer> smerovi;

    @Column(name = "studijske_grupe")
    @OneToMany(mappedBy = "skolskaGodina")
    private Set<StudijskaGrupa> studijskeGrupe;

}
