package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.dto.AnketaDto;
import com.dodeka.upisstudenatabackend.services.UpisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/upis")
public class UpisController {

    @Autowired
    UpisService upisService;


    @PostMapping
    public List<Predmet> addStudentInformations(@RequestBody AnketaDto anketaDto){
            upisService.addStudentInformations(anketaDto);
            return upisService.returnAvailableSubjects(anketaDto);
    }


    /* Kako da znam koji student je odabrao predmete?
    * Da li da trazim id zadnje ankete u bazi odmah nakon unosenja podataka sa fronta,
    * ili da prosledim email studenta pa da onda trazim poslednju anketu,
    * ili na neki treci nacin?
    * */


    @PutMapping("{anketaId}")
    public @ResponseBody ResponseEntity<Object> addSubjectsForStudent(@PathVariable int anketaId, @RequestBody List<Predmet> izabraniPredmeti) {    // zasto uopste vracam bilo sta?
        try {
            return ResponseEntity.status(HttpStatus.OK).body(upisService.saveSubjectsForStudent(anketaId, izabraniPredmeti));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



}
