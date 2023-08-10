package likelion_hkt.ll_hkt_be.gloabal.exception.handler;

import org.springframework.http.HttpStatus;

public class ErrorMessageFactory {

    public static ErrorMessage from(HttpStatus status, int errorCode,String errorMessage){
        return ErrorMessage.builder()
                .status(status.value())
                .errorCode(errorCode)
                .message(errorMessage)
                .build();
    }
}
