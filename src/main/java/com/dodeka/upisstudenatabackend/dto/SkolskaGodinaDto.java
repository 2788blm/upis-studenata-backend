package com.dodeka.upisstudenatabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkolskaGodinaDto implements Comparable<SkolskaGodinaDto>{

    private String godina;


    @Override
    public int compareTo(SkolskaGodinaDto skolskaGodinaDto) {       // po ovome bi trebalo da ih sortira opadajuce
        if (getGodina() == null || skolskaGodinaDto.getGodina() == null) {
            return 0;
        }
        int firstGodina = Integer.parseInt((this.getGodina()).substring(0, 4));
        int secondGodina = Integer.parseInt((skolskaGodinaDto.getGodina()).substring(0, 4));
        if(firstGodina == secondGodina)
            return 0;
        if(firstGodina > secondGodina)
            return -1;
        else
            return 1;
    }
}
