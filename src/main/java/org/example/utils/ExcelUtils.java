package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static String getCellData(String filePath, String sheetName, int rowNumber, int columnNumber) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);

        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNumber);

        Cell cell = row.getCell(columnNumber);

        String data = cell.toString();

        workbook.close();
        file.close();

        return data;
    }
}