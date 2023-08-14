package likelion_hkt.ll_hkt_be.gloabal.exception.handler;

import likelion_hkt.ll_hkt_be.gloabal.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(SaveSearchedWordException.class)
    public ResponseEntity<ErrorMessage> saveSearchedWordException(SaveSearchedWordException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }

    @ExceptionHandler(NotExistCoinedWordException.class)
    public ResponseEntity<ErrorMessage> notExistCoinedWordException(NotExistCoinedWordException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }

    @ExceptionHandler(DeleteSearchedWordException.class)
    public ResponseEntity<ErrorMessage> DeleteSearchedWordException(DeleteSearchedWordException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }
}
