package likelion_hkt.ll_hkt_be.domain.controller;

import likelion_hkt.ll_hkt_be.domain.controller.request.TranslationRequest;
import likelion_hkt.ll_hkt_be.domain.controller.response.TranslationResponse;
import likelion_hkt.ll_hkt_be.domain.service.ExcelReadService;
import likelion_hkt.ll_hkt_be.domain.service.ParticleAnalyzeService;
import likelion_hkt.ll_hkt_be.domain.service.SearchedWordService;
import likelion_hkt.ll_hkt_be.domain.service.dto.InputStringDto;
import likelion_hkt.ll_hkt_be.domain.service.dto.WordsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class TranslationController {
    private final ExcelReadService excelReadService;
    private final ParticleAnalyzeService particleAnalyzeService;
    private final SearchedWordService searchedWordService;

    private WordsDto wordInfo = new WordsDto();


    @PostMapping("/translate")
    public ResponseEntity<TranslationResponse> askForTranslation(@RequestBody TranslationRequest translationRequest) throws IOException {
        InputStringDto inputStringDto = translationRequest.toDto(); //입력받은 문자열, get으로 가져올 수 잇음.

        String initialAnalyzedSentence= particleAnalyzeService.InitialSentenceAnalysis(inputStringDto.getInputString());

        String[] wordsInSentence = initialAnalyzedSentence.split(" ");
        int wordNum = wordsInSentence.length;


        for(int i=0;i<wordNum;i++){
            String splitWord = wordsInSentence[i];
            System.out.println(splitWord);
            wordInfo = excelReadService.readCoinedExcelData(splitWord);
            if(wordInfo.getSubWord().equals("")){
                wordsInSentence[i] =splitWord;
            }
            wordsInSentence[i] = (wordInfo.getSubWord().equals("")) ? splitWord : wordInfo.getSubWord();
        }

        searchedWordService.saveSearchedWord(wordInfo);

        String translatedSentence = String.join(" ",wordsInSentence);
        TranslationResponse data = getWordsResponse(wordInfo,translatedSentence);

        //최근 기록 저장 구현 필요
        return ResponseEntity.ok(data);
    }


    public TranslationResponse getWordsResponse(WordsDto wordsDto, String translatedSentence){
        return TranslationResponse.builder()
                .coinedWord(wordsDto.getCoinedWord())
                .coinedWordMeaning(wordsDto.getCoinedWordMeaning())
                .coinedWordUrl(wordsDto.getCoinedWord_url())
                .subWord(wordsDto.getSubWord())
                .subWordMeaning(wordsDto.getSubWordMeaning())
                .translatedWord(translatedSentence)
                .build();
    }
}
