package com.auto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ResourceAlreadyExists ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdeasNumberException.class)
    public ResponseEntity<ExceptionResponse> ideasExceedNumberException(IdeasNumberException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("IDEAS_EXCEED_ALLOWED_NUMBER");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedException(UnauthorizedException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("UNAUTHORIZED");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedClientException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedClientException(UnauthorizedClientException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("UNAUTHORIZED_CLIENT");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCommunicationException.class)
    public ResponseEntity<ExceptionResponse> badCommunicationException(BadCommunicationException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("BAD_COMMUNICATION_CLIENT");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentClientException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentClientException(IllegalArgumentClientException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("ILLEGAL_ARGUMENT_CLIENT");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StageOverlappingException.class)
    public ResponseEntity<ExceptionResponse> StageOverlappingException(StageOverlappingException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("STAGE_OVERLAPPING_EXCEPTION");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StageOrderException.class)
    public ResponseEntity<ExceptionResponse> StageOrderException(StageOrderException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("STAGE_TIME_ORDER_EXCEPTION");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StagesAllClosedException.class)
    public ResponseEntity<ExceptionResponse> StagesAllClosedException(StagesAllClosedException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("STAGE_ARE_ALL_CLOSED");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ExceptionResponse> conflict(PersistenceException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("DATA_INTEGRITY_VIOLATION");
        response.setErrorMessage(ex.getCause().getCause().getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> GlobalException(UnauthorizedException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
