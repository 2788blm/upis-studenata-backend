package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Builder
public class SkolskaGodina {
    
    @Id
    private String godina;  // 2021/2022

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Predmet> predmeti;
}
