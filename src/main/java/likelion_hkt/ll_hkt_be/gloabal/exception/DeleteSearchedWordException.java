package likelion_hkt.ll_hkt_be.gloabal.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class DeleteSearchedWordException extends RuntimeException {
    private final HttpStatus status = HttpStatus.CONFLICT;
    private final int errorCode = 3;
    private final String errorMessage;
}
