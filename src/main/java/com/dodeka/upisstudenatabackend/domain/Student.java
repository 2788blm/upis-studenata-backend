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
    String email;

    @ManyToOne
    StudijskaGrupa studijskaGrupa;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Anketa> ankete;

    // ugovorOStudiranju?


}
