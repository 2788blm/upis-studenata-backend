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
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(columnNames = {"broj_indeksa"})})
@Entity
public class Student {

    @Id
    @Column(length = 150)
    private String email;

    @Column(length = 50)
    private String ime;

    @Column(length = 100)
    private String prezime;

    @Column(name = "broj_indeksa", length = 10)
    private String brojIndeksa;

    @ManyToOne
    private Smer smer;

    @ManyToOne
    private StudijskaGrupa studijskaGrupa;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Anketa> ankete;

}
