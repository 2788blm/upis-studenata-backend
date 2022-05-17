package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    private String email;

    @ManyToOne
    private GrupaZaSlusanjeNastave grupaZaSlusanjeNastave;

    @OneToMany(mappedBy = "emailStudenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anketa> ankete;

    // ugovorOStudiranju?


}
