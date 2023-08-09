package likelion_hkt.ll_hkt_be.domain.controller;

import likelion_hkt.ll_hkt_be.domain.controller.request.TranslationRequest;
import likelion_hkt.ll_hkt_be.domain.controller.response.TranslationResponse;
import likelion_hkt.ll_hkt_be.domain.service.ExcelReadService;
import likelion_hkt.ll_hkt_be.domain.service.dto.InputStringDto;
import likelion_hkt.ll_hkt_be.domain.service.dto.WordsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class SearchResultController {
    private final ExcelReadService excelReadService;

    @PostMapping("/translate")
    public ResponseEntity<TranslationResponse> askForTranslation(@RequestBody TranslationRequest translationRequest) throws IOException {
        InputStringDto inputStr = translationRequest.toDto(); //입력받은 문자열, get으로 가져올 수 잇음.

        String inputCoinedWord="갑분싸";

        // 일치하는 단어 찾기
        TranslationResponse data = excelReadService.getWordsResponse(excelReadService.readCoinedExcelData(inputCoinedWord));

        //최근 기록 저장 구현 필요
        return ResponseEntity.ok(data);
    }

}
