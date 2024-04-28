package it.koby.transaction.exceptions.advice;

import java.util.List;
import java.util.Set;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.koby.transaction.exceptions.InvalidTransactionException;
import it.koby.transaction.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;

@ControllerAdvice
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    @Builder
    private record InvalidParams(String cause, String field) {
    }

    @Builder
    private record  InvalidTransactionResponse(String title, List<?> invalidData) {}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
    }

    // TODO: May my ancestors forgive me for this. I reached here before thinking it
    // through.
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationExceptionViaInvalidTransactionException(ConstraintViolationException exception)
            throws InvalidTransactionException {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<InvalidParams> validations = violations.stream().map(
                error -> InvalidParams
                        .builder()
                        .cause(error.getMessage())
                        .field(error.getPropertyPath().toString())
                        .build())
                .toList();

        throw new InvalidTransactionException("Invalid Input Data", validations);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<InvalidParams> validationResponse = errors.stream().map(
                err -> InvalidParams.builder()
                        .cause(err.getDefaultMessage())
                        .field(err.getField())
                        .build())
                .toList();

        throw new InvalidTransactionException("Invalid Input Data", validationResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        try {
            List<? extends MessageSourceResolvable> errors = ex.getAllErrors();

            List<InvalidParams> validationResponse = errors.stream().map(
                    err -> InvalidParams.builder()
                            .cause(err.getDefaultMessage())
                            .field(err.getCodes()[1])
                            .build())
                    .toList(); 
            throw new InvalidTransactionException("Invalid Input Data", validationResponse);
        } catch (InvalidTransactionException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                InvalidTransactionResponse.builder()
                .title(exception.getMessage())
                .invalidData(exception.getVoilatedConstraints())
                .build()
            );
        }
    }
}
