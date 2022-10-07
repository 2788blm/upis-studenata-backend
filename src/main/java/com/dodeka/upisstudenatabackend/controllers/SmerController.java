package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.domain.Smer;
import com.dodeka.upisstudenatabackend.services.SmerService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smerovi")
public class SmerController {

    @Autowired
    private SmerService smerService;

    @PostMapping("/ubaciSmer")
    public ResponseEntity<Object> createSmer(@RequestBody @Valid Smer smer){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(smerService.createSmer(smer));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/obrisiSmer/{smerId}")
    public ResponseEntity<Integer> deletePredmet(@PathVariable Integer smerId){
        try {
            smerService.deletePredmet(smerId);
            return new ResponseEntity<>(smerId, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(smerId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/izlistajSmerove")
    public List<Smer> getAll() {
        return smerService.getAll();
    }

}
