package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkolskaGodina {
    @Id
    private String godina; // ne razumem ovo, jel je ovo jedna godina 2016, ili dve 2016/17 ?

    @OneToMany(mappedBy = "predmetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Predmet> predmeti;
}
