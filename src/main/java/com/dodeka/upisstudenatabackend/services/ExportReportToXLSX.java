package com.dodeka.upisstudenatabackend.services;


import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExportReportToXLSX implements ExportReport {

    @Override
    public Object exportStudentsBySubjectReport(String[] columns, List<StudentReportDto> data, String name) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i=0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headCellStyle);
        }

        int rowNum = 1;

        for(StudentReportDto student : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getBrojIndeksa());
            row.createCell(1).setCellValue(student.getIme());
            row.createCell(2).setCellValue(student.getPrezime());
            row.createCell(3).setCellValue(student.getEmail());
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return sheet;
    }
}
