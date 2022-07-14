package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.services.ExportReport;
import com.dodeka.upisstudenatabackend.services.ExportReportToCSV;
import com.dodeka.upisstudenatabackend.services.ExportReportToXLSX;
import com.dodeka.upisstudenatabackend.services.ReportService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/izvestaji")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/listaStudenataPoPredmetu")
    public ResponseEntity<Object> getStudentsBySubjectReport(@RequestParam(value = "predmetId") int predmetId,
                                         @RequestParam(value = "smer", required = false, defaultValue = "xlsx") String format) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reportService.getStudentsBySubjectReport(predmetId, format));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
