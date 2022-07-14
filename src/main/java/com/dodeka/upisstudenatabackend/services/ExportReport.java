package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.dto.StudentReportDto;

import java.util.List;

public interface ExportReport {

    Object exportStudentsBySubjectReport(String[] columns, List<StudentReportDto> data, String name);

}
