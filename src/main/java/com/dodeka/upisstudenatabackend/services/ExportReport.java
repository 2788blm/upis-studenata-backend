package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface ExportReport {

    XSSFWorkbook exportStudentsBySubjectReport(String[] columns, List<StudentReportDto> data, String name);

}
