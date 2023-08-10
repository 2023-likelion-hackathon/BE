package likelion_hkt.ll_hkt_be.domain.service;

import likelion_hkt.ll_hkt_be.domain.controller.response.TranslationResponse;
import likelion_hkt.ll_hkt_be.domain.service.dto.WordsDto;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelReadService {

    private String basic_path = "wordDataset/";
    private String fileName = "wordDataset.xlsx";

    private String getFilePath(){
        return basic_path+fileName;
    }


    public WordsDto readCoinedExcelData(String inputWord) throws IOException{
        InputStream inputStream = new ClassPathResource(getFilePath()).getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet coined_sheet = workbook.getSheetAt(0);// 엑셀파일에서 첫 번째 시트 선택
        XSSFSheet sub_sheet = workbook.getSheetAt(1);// 엑셀파일에서 두 번째 시트 선택
        int rowNum = coined_sheet.getPhysicalNumberOfRows(); // data 개수

        for(int rowidx=0; rowidx<rowNum; rowidx++) {
            XSSFRow coined_sheet_row = coined_sheet.getRow(rowidx);
            XSSFRow sub_sheet_row = sub_sheet.getRow(rowidx);

            if(coined_sheet_row != null && coined_sheet_row.getCell(0).toString().equals(inputWord)) {
                String coinedWord = CheckNullPointException(coined_sheet_row.getCell(0));
                String coinedWordMeaning = CheckNullPointException(coined_sheet_row.getCell(1));
                String coinedWordUrl = CheckNullPointException(coined_sheet_row.getCell(2));
                String coinedWordExample = CheckNullPointException(coined_sheet_row.getCell(3));

                String subWord = CheckNullPointException(sub_sheet_row.getCell(0));
                String subWordMeaning = CheckNullPointException(sub_sheet_row.getCell(1));
                String subWordExample = CheckNullPointException(sub_sheet_row.getCell(2));



                WordsDto wordsDto = WordsDto.builder()
                        .coinedWord(coinedWord)
                        .coinedWordMeaning(coinedWordMeaning)
                        .coinedWord_url(coinedWordUrl)
                        .coinedWordExample(coinedWordExample)
                        .subWord(subWord)
                        .subWordMeaning(subWordMeaning)
                        .subWordExample(subWordExample)
                        .build();
                return wordsDto;
            }
        }
        return  WordsDto.builder()
                .coinedWord("")
                .subWord("")
                .build();
    }



    public String CheckNullPointException(XSSFCell cell){

        return cell!=null ? cell.toString() : "";
    }
}
