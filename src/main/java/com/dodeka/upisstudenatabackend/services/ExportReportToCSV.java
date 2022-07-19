package com.dodeka.upisstudenatabackend.services;


import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ExportReportToCSV implements ExportReport {

    @Override
    public void exportStudentsBySubjectReport(HttpServletResponse response, String[] columns, List<StudentReportDto> data, String name) {

    }
}
