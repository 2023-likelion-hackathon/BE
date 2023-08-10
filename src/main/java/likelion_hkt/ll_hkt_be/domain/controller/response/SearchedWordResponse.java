package likelion_hkt.ll_hkt_be.domain.controller.response;

import likelion_hkt.ll_hkt_be.domain.etity.SearchedWord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchedWordResponse {
    private String coinedWord;
    private String subWord;
}
