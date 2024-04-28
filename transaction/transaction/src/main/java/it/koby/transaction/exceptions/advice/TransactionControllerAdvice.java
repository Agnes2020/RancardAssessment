package it.koby.transaction.exceptions.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.koby.transaction.exceptions.InvalidTransactionException;
import it.koby.transaction.exceptions.TransactionNotFoundException;
import lombok.Builder;

@ControllerAdvice
public class TransactionControllerAdvice {

    @Builder
    private record  InvalidTransactionResponse(String title, List<?> invalidData) {}
    
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<?> handleTransactionNotFoundException(TransactionNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<?> handleInvalidTransactionException(InvalidTransactionException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            InvalidTransactionResponse.builder()
            .title(exception.getMessage())
            .invalidData(exception.getVoilatedConstraints())
            .build()
        );
    }
}
