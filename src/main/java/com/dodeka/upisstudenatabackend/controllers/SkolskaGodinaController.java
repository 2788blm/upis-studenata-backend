package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.dto.SkolskaGodinaDto;
import com.dodeka.upisstudenatabackend.services.SkolskaGodinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skolskaGodina")
public class SkolskaGodinaController {

    @Autowired
    SkolskaGodinaService skolskaGodinaService;


    @GetMapping("/izlistajSkolskeGodine")
    public List<SkolskaGodinaDto> getAll() {
        return skolskaGodinaService.getAll();
    }

    @PostMapping("/dodajSkolskuGodinu")
    public ResponseEntity<Object> addSkolskaGodina(@RequestBody @Valid SkolskaGodina skolskaGodina){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(skolskaGodinaService.addSkolskaGodina(skolskaGodina));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
