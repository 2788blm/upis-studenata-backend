package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.domain.Predmet;
import com.dodeka.upisstudenatabackend.services.AnketaService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upis")
public class AnketaController {

    @Autowired
    private AnketaService anketaService;


    @GetMapping("/getAnketaById/{id}")
    public ResponseEntity<Object> getAnketaById(@PathVariable int anketaId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(anketaService.getAnketaById(anketaId));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/updateAnketa")
    public ResponseEntity<Object> updateAnketa(@RequestBody Anketa anketa) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(anketaService.updateAnketa(anketa));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
