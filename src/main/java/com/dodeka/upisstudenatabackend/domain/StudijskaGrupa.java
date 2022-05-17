package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudijskaGrupa {
    @Id
    int grupaId;

    @OneToMany(mappedBy = "studijskaGrupa")
    Student student;
}
