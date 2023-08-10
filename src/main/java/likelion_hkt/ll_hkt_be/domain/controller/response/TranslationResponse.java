package likelion_hkt.ll_hkt_be.domain.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationResponse {
    private String coinedWord;
    private String coinedWordMeaning;
    private String coinedWordUrl;
    private String coinedWordExample;

    private String subWord;
    private String subWordMeaning;
    private String subWordExample;

    private String translatedWord;

}
