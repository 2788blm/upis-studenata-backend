package com.dodeka.upisstudenatabackend.dto;

import lombok.Data;

@Data
public class AnketaFilter {

    private String email;

    private String brojIndeksa;

    private String ime;

    private String prezime;


    public AnketaFilter(String filterText) {
        String[] words = filterText.split("\\s"); //splits the string based on whitespace
        email = words[0];
        brojIndeksa = words[1];
        ime = words[2];
        prezime = words[3];
    }


}

