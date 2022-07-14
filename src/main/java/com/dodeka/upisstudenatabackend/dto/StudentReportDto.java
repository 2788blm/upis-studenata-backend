package com.dodeka.upisstudenatabackend.dto;

import lombok.Data;

@Data
public class StudentReportDto {

    private String brojIndeksa;

    private String ime;

    private String prezime;

    private String email;


    public StudentReportDto(String brojIndeksa, String ime, String prezime, String email) {
        this.brojIndeksa = brojIndeksa;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

}
