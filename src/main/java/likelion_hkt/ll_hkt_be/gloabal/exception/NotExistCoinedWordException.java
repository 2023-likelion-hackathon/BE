package likelion_hkt.ll_hkt_be.gloabal.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class NotExistCoinedWordException extends NullPointerException{
    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private final int errorCode = 1;
    private final String errorMessage;
}
