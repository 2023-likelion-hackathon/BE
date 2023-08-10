package likelion_hkt.ll_hkt_be.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchedWordDto {
    private String coinedWord;
    private String subWord;
}
