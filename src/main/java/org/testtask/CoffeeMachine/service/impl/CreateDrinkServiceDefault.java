package org.testtask.CoffeeMachine.service.impl;

import org.springframework.stereotype.Service;
import org.testtask.CoffeeMachine.exception.CoffeeMachineException;
import org.testtask.CoffeeMachine.exception.IncorrectNameException;
import org.testtask.CoffeeMachine.service.interfaces.CreateDrinkService;

@Service
public class CreateDrinkServiceDefault implements CreateDrinkService {

    private final CoffeeMachineService coffeeMachineService;
    @Override
    public String makeADrink(String name) {

        if(!nameIsCorrect) {
            throw new IncorrectNameException("Данный напиток не найден в системе! Убедитесь в корректности ввода");
        }

        String status = coffeeMachineService.getMachineStatus();
        if(!machineStatus.equals("OK")) {
            throw new CoffeeMachineException(status);
        }
        String responseWithDuration = null;
        return responseWithDuration;
    }
}
