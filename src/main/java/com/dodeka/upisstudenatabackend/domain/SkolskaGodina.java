package com.dodeka.upisstudenatabackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkolskaGodina {

    @Id
    private String godina;  // 2021/2022

    @OneToMany(mappedBy = "skolskaGodina", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonBackReference
    private List<Predmet> predmeti;


}
