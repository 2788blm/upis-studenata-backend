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
    private String godina;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Predmet> predmeti;
}
