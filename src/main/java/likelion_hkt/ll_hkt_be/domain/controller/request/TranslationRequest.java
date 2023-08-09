package likelion_hkt.ll_hkt_be.domain.controller.request;

import jakarta.validation.constraints.NotBlank;
import likelion_hkt.ll_hkt_be.domain.service.dto.InputStringDto;
import likelion_hkt.ll_hkt_be.domain.service.dto.WordsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequest {

    @NotBlank
    private String inputString;

    public InputStringDto toDto(){
        return InputStringDto.builder()
                .inputString(this.inputString)
                .build();
    }

}
