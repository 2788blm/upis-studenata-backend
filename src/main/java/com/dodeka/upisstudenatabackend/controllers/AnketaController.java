package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.Anketa;
import com.dodeka.upisstudenatabackend.services.AnketaService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/upis")
public class AnketaController {

    @Autowired
    private AnketaService anketaService;


    @GetMapping("/izlistajAnkete")
    public ResponseEntity<Slice<Anketa>> getAll(@RequestParam(value = "filterTekst", required = false) String filterTekst,
                                                @RequestParam(value = "smer", required = false) LocalDateTime datumOd,
                                                @RequestParam(value = "deoNaziva", required = false) LocalDateTime datumDo,
                                                @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(value = "sortOrder", required = false, defaultValue = "desc") String sortOrder) {
        return new ResponseEntity<Slice<Anketa>>(anketaService.getAll(filterTekst, datumOd, datumDo, pageNo, pageSize, sortOrder), new HttpHeaders(), HttpStatus.OK);
    }

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
