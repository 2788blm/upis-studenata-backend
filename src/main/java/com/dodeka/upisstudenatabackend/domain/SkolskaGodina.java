package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkolskaGodina {
    @Id
    String godine; // ne razumem ovo, jel je ovo jedna godina 2016, ili dve 2016/17, i zasto nam ovo uopste treba
}
