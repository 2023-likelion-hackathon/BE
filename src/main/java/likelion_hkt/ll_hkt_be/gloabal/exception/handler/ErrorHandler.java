package likelion_hkt.ll_hkt_be.gloabal.exception.handler;

import likelion_hkt.ll_hkt_be.gloabal.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(SaveSearchedWordException.class)
    public ResponseEntity<ErrorMessage> studentIdDuplicatedException(SaveSearchedWordException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }
}
