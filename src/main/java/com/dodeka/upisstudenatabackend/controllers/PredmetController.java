package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.services.PredmetService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
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
    public List<Predmet> getAll(@RequestParam(value = "skolskaGodina") SkolskaGodina skolskaGodina,
                                @RequestParam(value = "smer", required = false) Smer smer,
                                @RequestParam(value = "deoNaziva", required = false) String deoNaziva) {
        return predmetService.getAll(skolskaGodina, smer, deoNaziva);
    }

    @PostMapping("/kreirajPredmet")
    public ResponseEntity<Object> createPredmet(@RequestBody @Valid Predmet predmet){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(predmetService.createPredmet(predmet));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/predmet/{id}")
    public ResponseEntity<Object> getPredmetById(@PathVariable int predmetId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(predmetService.getPredmetById(predmetId));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/izmeniPredmet")
    public ResponseEntity<Object> updatePredmet(@RequestBody Predmet predmet) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(predmetService.updatePredmet(predmet));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/obrisiPredmet/{id}")
    public ResponseEntity<Integer> deletePredmet(@PathVariable int predmetId){
        try {
            predmetService.deletePredmet(predmetId);
            return new ResponseEntity<>(predmetId, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(predmetId, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(predmetId, HttpStatus.BAD_REQUEST);
        }
    }

}
