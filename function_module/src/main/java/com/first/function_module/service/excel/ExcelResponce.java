package com.first.function_module.service.excel;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ExcelResponce {

    public void prepareBookingReport(String adress,Integer dayOfBooking,String Fio, Double summ) {

        File file = new File("C:\\Users\\alex\\Downloads\\rentApartment\\rentApartment\\function_module\\src\\main\\resources\\files\\BookingExcelLogging.xlsx");

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook book = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet1 = book.getSheet("Booking");

            int rowCount = sheet1.getLastRowNum()-sheet1.getFirstRowNum();


            Row newrow = sheet1.createRow(rowCount+1);



            Cell name = newrow.createCell(0);
            name.setCellValue(adress);

            Cell days =newrow.createCell(1);
            days.setCellValue(dayOfBooking);

            Cell fio =newrow.createCell(2);
            fio.setCellValue(Fio);

            Cell bokkingSum =newrow.createCell(3);
            bokkingSum.setCellValue(summ);

            sheet1.autoSizeColumn(3);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            book.write(fileOutputStream);
            fileOutputStream.flush();

            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("Проблема с выгрузкой отчета");
        }
    }
}
