package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ExportReport {

    void exportStudentsBySubjectReport(HttpServletResponse response, String[] columns, List<StudentReportDto> data, String name) throws IOException;

}
