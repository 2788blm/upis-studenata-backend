package com.dodeka.upisstudenatabackend.services;


import com.dodeka.upisstudenatabackend.dto.StudentReportDto;

import java.util.List;

public class ExportReportToCSV implements ExportReport {

    @Override
    public Object exportStudentsBySubjectReport(String[] columns, List<StudentReportDto> data, String name) {
        return null;
    }
}
