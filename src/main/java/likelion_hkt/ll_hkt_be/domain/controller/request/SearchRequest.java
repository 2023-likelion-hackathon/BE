package likelion_hkt.ll_hkt_be.domain.controller.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchRequest {
    private String searchSentence;
}
