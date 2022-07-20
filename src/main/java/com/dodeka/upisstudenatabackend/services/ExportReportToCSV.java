package com.dodeka.upisstudenatabackend.services;


import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportReportToCSV implements ExportReport {

    @Override
    public void exportStudentsBySubjectReport(HttpServletResponse response, String[] columns, List<StudentReportDto> data, String name) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Naziv fajla", name);

        CSVPrinter csvPrinter = new CSVPrinter((Appendable) response.getOutputStream(), CSVFormat.DEFAULT); // ne znam da li je ovo ok posto je kastovano

        for (String column : columns) {
            csvPrinter.printRecord(column);
        }

        for (StudentReportDto student : data) {
            csvPrinter.printRecord(student.getBrojIndeksa(), student.getIme(), student.getPrezime(), student.getEmail());
        }

        csvPrinter.flush();
        csvPrinter.close();

    }
}
