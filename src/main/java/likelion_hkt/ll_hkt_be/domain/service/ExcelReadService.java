package likelion_hkt.ll_hkt_be.domain.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class ExcelReadService {

    private String basic_path = "wordDataset/";
    private String fileName = "coined_word.xlsx";


    public Map<String,String> readCoinedExcelData(String inputWord){
         try (FileInputStream file = new FileInputStream(basic_path+fileName);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 선택
            int wordColumnIndex = findColumnIndex(sheet, "word"); // "word" 열(column)의 인덱스를 찾음

            if (wordColumnIndex != -1) {
                for (Row row : sheet) {
                    Cell cell = row.getCell(wordColumnIndex);
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        String wordValue = cell.getStringCellValue();
                        if (wordValue.equals(inputWord)) {
                            System.out.println("Found: " + inputWord);
                            // sword와 일치하는 값을 찾았을 때 원하는 작업을 수행하면 됨
                        }
                    }
                }
            } else {
                System.out.println("Column 'word' not found in the sheet.");
            }
         }catch (Exception e) {
             throw new RuntimeException(e);
         }


        return null;
    }
    private static int findColumnIndex(Sheet sheet, String columnName) {
        Row firstRow = sheet.getRow(0); // 첫 번째 행(row) 가져오기

        if (firstRow != null) {
            for (Cell cell : firstRow) {
                if (cell.getCellType() == CellType.STRING && columnName.equals(cell.getStringCellValue())) {
                    return cell.getColumnIndex(); // 해당 열(column)의 인덱스 반환
                }
            }
        }
        return -1; // 해당 열(column)이 존재하지 않으면 -1 반환
    }
}
