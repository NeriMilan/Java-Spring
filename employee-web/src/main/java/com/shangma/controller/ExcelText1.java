package com.shangma.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelText1 {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("员工表");
        Row row = sheet.createRow(3);
        Cell cell = row.createCell(3);
        cell.setCellValue("喵喵喵");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\JAVASM\\Desktop\\demo1.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();





//        Workbook workbook = new XSSFWorkbook();
//        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\JAVASM\\Desktop\\demo1.xlsx");
//        workbook.write(fileOutputStream);
//        workbook.close();
//        fileOutputStream.close();
    }
}
