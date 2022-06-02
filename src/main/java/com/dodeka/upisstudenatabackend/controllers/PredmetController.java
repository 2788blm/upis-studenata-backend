package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.dto.SkolskaGodinaDto;
import com.dodeka.upisstudenatabackend.services.PredmetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predmeti")
public class PredmetController {

    @Autowired
    private PredmetService predmetService;


    @GetMapping("/izlistajPredmete")
    public List<Predmet> getAll(@RequestParam(value = "skolskaGodinaId", required = true) String skolskaGodinaId,
                                         @RequestParam(value = "smer", required = false) int smerId,
                                         @RequestParam(value = "nazivPredmeta", required = false) String nazivPredmeta) {
        return predmetService.getAll(skolskaGodinaId, smerId, nazivPredmeta);
    }

    @PostMapping("/kreirajPredmet")
    public ResponseEntity<Object> createPredmet(@RequestBody @Valid Predmet predmet){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(predmetService.createPredmet(predmet));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}
