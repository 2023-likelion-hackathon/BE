package likelion_hkt.ll_hkt_be.domain.controller;

import likelion_hkt.ll_hkt_be.domain.controller.request.TranslationRequest;
import likelion_hkt.ll_hkt_be.domain.controller.response.TranslationResponse;
import likelion_hkt.ll_hkt_be.domain.service.ExcelReadService;
import likelion_hkt.ll_hkt_be.domain.service.ParticleAnalyzeService;
import likelion_hkt.ll_hkt_be.domain.service.SearchedWordService;
import likelion_hkt.ll_hkt_be.domain.service.dto.InputStringDto;
import likelion_hkt.ll_hkt_be.domain.service.dto.WordsDto;
import likelion_hkt.ll_hkt_be.gloabal.exception.NotExistCoinedWordException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class TranslationController {


    private final ExcelReadService excelReadService;
    private final ParticleAnalyzeService particleAnalyzeService;
    private final SearchedWordService searchedWordService;
    private static final Map<String, String> spacedWordsTranslations = new HashMap<>();

    static {
        spacedWordsTranslations.put("라떼는 말이야", "나 때는 말이야");
        spacedWordsTranslations.put("무슨 129", "무슨 일이야");
        spacedWordsTranslations.put("아무 말 대잔치", "아무말하기");
        spacedWordsTranslations.put("의문의 1승", "의도하지 않은 이득");
        spacedWordsTranslations.put("혈중 마라 농도", "마라 음식 섭취 빈도");
        spacedWordsTranslations.put("서로서로 도와요!", "다 같이 해요!");
        spacedWordsTranslations.put("누가 기침소리를 내었어", "누가 그랬어");
    }

    @PostMapping("/translate")
    public ResponseEntity<TranslationResponse> askForTranslation(@RequestBody TranslationRequest translationRequest) throws IOException {
        InputStringDto inputStringDto = translationRequest.toDto();

        String initialAnalyzedSentence = particleAnalyzeService.InitialSentenceAnalysis(inputStringDto.getInputString());

        // 패턴 대체
        Map<String, String> replacementPatterns = new HashMap<>();
        for (Map.Entry<String, String> entry : spacedWordsTranslations.entrySet()) {
            String tempReplacement = "##" + entry.getKey().replaceAll(" ", "") + "##";
            if (initialAnalyzedSentence.contains(entry.getKey())) {
                initialAnalyzedSentence = initialAnalyzedSentence.replace(entry.getKey(), tempReplacement);
            }
            replacementPatterns.put(tempReplacement, entry.getKey());
        }

        List<String> wordsList = new ArrayList<>(Arrays.asList(initialAnalyzedSentence.split(" ")));
        WordsDto lastFoundWordInfo = excelReadService.readCoinedExcelData("");

        for (int i = 0; i < wordsList.size(); i++) {
            String splitWord = wordsList.get(i);
            // 임시 패턴을 원래 패턴으로 복원
            for (Map.Entry<String, String> entry : replacementPatterns.entrySet()) {
                splitWord = splitWord.replace(entry.getKey(), entry.getValue());
            }

            WordsDto currentWordInfo = excelReadService.readCoinedExcelData(splitWord);
            if (!currentWordInfo.getSubWord().equals("")) {
                wordsList.set(i, currentWordInfo.getSubWord());
                lastFoundWordInfo = currentWordInfo;
            }
        }

        // 최종 문장에서 임시 패턴을 원래 패턴으로 복원
        String translatedSentence = String.join(" ", wordsList);
        for (Map.Entry<String, String> entry : replacementPatterns.entrySet()) {
            translatedSentence = translatedSentence.replace(entry.getKey(), entry.getValue());
        }

        if(lastFoundWordInfo.getCoinedWord()!="") {
            searchedWordService.saveSearchedWord(lastFoundWordInfo);
        }

        if(lastFoundWordInfo.getCoinedWordMeaning()==null){
            throw new NotExistCoinedWordException("탐색 가능한 신조어 데이터가 없습니다.");
        }
        TranslationResponse data = getWordsResponse(lastFoundWordInfo, translatedSentence);

        return ResponseEntity.ok(data);
    }


    public TranslationResponse getWordsResponse(WordsDto wordsDto, String translatedSentence){
        return TranslationResponse.builder()
                .coinedWord(wordsDto.getCoinedWord())
                .coinedWordMeaning(wordsDto.getCoinedWordMeaning())
                .coinedWordUrl(wordsDto.getCoinedWord_url())
                .coinedWordExample(wordsDto.getCoinedWordExample())
                .subWord(wordsDto.getSubWord())
                .subWordMeaning(wordsDto.getSubWordMeaning())
                .subWordExample(wordsDto.getSubWordExample())
                .translatedWord(translatedSentence)
                .build();
    }

}
