package likelion_hkt.ll_hkt_be.domain.service;

import likelion_hkt.ll_hkt_be.domain.controller.response.SearchedWordResponse;
import likelion_hkt.ll_hkt_be.domain.etity.SearchedWord;
import likelion_hkt.ll_hkt_be.domain.repository.SearchedWordRepository;
import likelion_hkt.ll_hkt_be.domain.service.dto.WordsDto;
import likelion_hkt.ll_hkt_be.gloabal.exception.SaveSearchedWordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class SearchedWordService {
    private final SearchedWordRepository searchedWordRepository;

    public void saveSearchedWord(WordsDto wordsDto){
        try {
            SearchedWord searchedWord = SearchedWord.builder()
                    .coinedWord(wordsDto.getCoinedWord())
                    .subWord(wordsDto.getSubWord())
                    .build();
            searchedWordRepository.save(searchedWord);
        }catch(Exception e){
            throw new SaveSearchedWordException("검색 기록 저장에 실패하였습니다");
        }
    }

    public List<SearchedWordResponse> getSearchedWordList(){
        List<SearchedWord> searchedWords = searchedWordRepository.findTop20ByOrderBySearchedId();
        List<SearchedWordResponse> searchedWordResponses =new ArrayList<>();

        searchedWords.forEach(
                (data) ->{
                    searchedWordResponses.add(
                            SearchedWordResponse.builder()
                                    .coinedWord(data.getCoinedWord())
                                    .subWord(data.getSubWord())
                                    .build()
                    );
                }
        );
        return searchedWordResponses;
    }
}
