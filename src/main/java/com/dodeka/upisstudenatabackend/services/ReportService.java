package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.*;
import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import com.dodeka.upisstudenatabackend.repositories.AnketaRepository;
import com.dodeka.upisstudenatabackend.repositories.PredmetRepository;
import com.querydsl.core.BooleanBuilder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    ExportReport exportReport;

    @Autowired
    private AnketaRepository anketaRepository;

    @Autowired
    private PredmetRepository predmetRepository;

    public void getStudentsBySubjectReport(HttpServletResponse response, int predmetId, String format) throws IOException {
        // Retrieve data
        String[] columns = {"Broj indeksa", "Ime", "Prezime", "E-mail"};
        Predmet  predmet = predmetRepository.getById(predmetId);
        BooleanBuilder predicate = new BooleanBuilder();
        if (predmetId >= 0) {
            predicate.and(QAnketa.anketa.predmeti.contains(predmet));
        }
        List<Anketa> ankete = new ArrayList<>();
        anketaRepository.findAll(predicate).forEach(ankete::add);
        List<StudentReportDto> studenti = new ArrayList<>();
        for(Anketa anketa : ankete) {
            StudentReportDto studentReportDto = new StudentReportDto(anketa.getStudent().getBrojIndeksa(),
                                                                        anketa.getStudent().getIme(),
                                                                        anketa.getStudent().getPrezime(),
                                                                        anketa.getStudent().getEmail());
            studenti.add(studentReportDto);
        }
        // Choose format
        if(format.equals("xlsx")) {
            exportReport = new ExportReportToXLSX();
        } else if(format.equals("csv")) {
            exportReport = new ExportReportToCSV();
        } else {
            throw new RuntimeException("Format moze biti \"xlsx\" ili \"csv\"");
        }
        // Export to chosen format
       exportReport.exportStudentsBySubjectReport(response, columns, studenti, "Studenti koji slusaju " + predmet.getNaziv());
    }

}
