package com.dodeka.upisstudenatabackend.services;


import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExportReportToCSV implements ExportReport {

    @Override
    public XSSFWorkbook exportStudentsBySubjectReport(String[] columns, List<StudentReportDto> data, String name) {
        return null;
    }
}
