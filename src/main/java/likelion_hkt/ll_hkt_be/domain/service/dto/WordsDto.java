package likelion_hkt.ll_hkt_be.domain.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordsDto {
    private String coinedWord;
    private String coinedWordMeaning;
    private String coinedWord_url;
    private String coinedWordExample;


    private String subWord;
    private String subWordMeaning;
    private String subWordExample;

}
