package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.SkolskaGodina;
import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.services.SkolskaGodinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skolskaGodina")
public class SkolskaGodinaController {

    @Autowired
    SkolskaGodinaService skolskaGodinaService;


    @GetMapping("/izlistajSkolskeGodine")
    public List<SkolskaGodina> getAll() {
        return skolskaGodinaService.getAll();
    }

}
