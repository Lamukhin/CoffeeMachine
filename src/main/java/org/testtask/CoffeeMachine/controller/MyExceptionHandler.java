package org.testtask.CoffeeMachine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.testtask.CoffeeMachine.dto.ErrorDto;
import org.testtask.CoffeeMachine.exception.CoffeeMachineException;
import org.testtask.CoffeeMachine.exception.IncorrectNameException;

@RestController
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CoffeeMachineException.class)
    public ResponseEntity<ErrorDto> handleCoffeeMachineException(CoffeeMachineException ex) {
        ErrorDto errorResponse = new ErrorDto(ex.getMessageForUser());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectNameException.class)
    public ResponseEntity<ErrorDto> handleIncorrectNameException(IncorrectNameException ex) {
        ErrorDto errorResponse = new ErrorDto(ex.getMessageForUser());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorDto> handleJsonProcessingExceptionException(JsonProcessingException ex) {
        ErrorDto errorResponse = new ErrorDto("Произошел сбой при отправке ответа. Попробуйте выполнить запрос повторно.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}