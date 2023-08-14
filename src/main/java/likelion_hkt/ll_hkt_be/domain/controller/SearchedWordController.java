package likelion_hkt.ll_hkt_be.domain.controller;

import likelion_hkt.ll_hkt_be.domain.controller.response.SearchedWordResponse;
import likelion_hkt.ll_hkt_be.domain.service.SearchedWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchedWordController {
    private final SearchedWordService searchedWordService;

    @GetMapping("/searched")
    private ResponseEntity<List<SearchedWordResponse>> getSearchedWordList(){
        List<SearchedWordResponse> searchedWordResponses = searchedWordService.getSearchedWordList();
        return ResponseEntity.ok(searchedWordResponses);
    }


    @GetMapping("/clearSearched")
    private ResponseEntity<List<SearchedWordResponse>> getInitialWordList(){
        searchedWordService.deleteAllSearchedWords();
        List<SearchedWordResponse> searchedWordResponses = searchedWordService.getSearchedWordList();
        return ResponseEntity.ok(searchedWordResponses);
    }
}
