package com.dodeka.upisstudenatabackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
public class SkolskaGodina {
    
    @Id
    private String godina;  // 2021/2022

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Predmet> predmeti;


}
