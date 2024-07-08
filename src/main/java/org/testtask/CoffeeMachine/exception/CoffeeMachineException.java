package org.testtask.CoffeeMachine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoffeeMachineException extends RuntimeException {

    private final String messageForUser;
}