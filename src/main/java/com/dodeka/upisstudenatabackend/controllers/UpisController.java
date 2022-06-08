package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.dto.AnketaDto;
import com.dodeka.upisstudenatabackend.services.UpisService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/upis")
public class UpisController {

    @Autowired
    private UpisService upisService;


    @GetMapping("/vratiOdgovarajuceIspite")
    public ResponseEntity<Object> getAvailableSubjects(@RequestParam(name = "smer") String smer,
                                                        @RequestParam(name = "semestar") int semestar) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(upisService.getAvailableSubjects(smer, semestar)); // godina?
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/unesiPodatkeIzAnkete")
    public void addStudentInformations(@RequestBody AnketaDto anketaDto){
        upisService.addStudentInformations(anketaDto);
    }


}
