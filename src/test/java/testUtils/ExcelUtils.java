package testUtils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    public static List<Map<String, String>> getExcelData(String filePath, String sheetName) {

        List<Map<String, String>> dataList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return dataList;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return dataList;
            }

            int colCount = headerRow.getLastCellNum();
            int lastRow = sheet.getLastRowNum();

            for (int i = 1; i <= lastRow; i++) {

                Row row = sheet.getRow(i);
                if (row == null || isRowEmpty(row)) continue;

                Map<String, String> rowData = new LinkedHashMap<>();

                for (int j = 0; j < colCount; j++) {

                    String key = formatter.formatCellValue(headerRow.getCell(j)).trim();
                    String value = formatter.formatCellValue(row.getCell(j)).trim();

                    rowData.put(key, value);
                }

                dataList.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static boolean isRowEmpty(Row row) {
        for (int c = 0; c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
