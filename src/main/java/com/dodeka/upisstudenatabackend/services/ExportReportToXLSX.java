package com.dodeka.upisstudenatabackend.services;


import com.dodeka.upisstudenatabackend.dto.StudentReportDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportReportToXLSX implements ExportReport {

    @Override
    public void exportStudentsBySubjectReport(HttpServletResponse response, String[] columns, List<StudentReportDto> data, String name) throws IOException {

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

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Naziv fajla", name);
        workbook.write(response.getOutputStream());
        workbook.close();

    }
}
