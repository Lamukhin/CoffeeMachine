package org.testtask.CoffeeMachine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;
import org.testtask.CoffeeMachine.exception.IncorrectNameException;
import org.testtask.CoffeeMachine.service.interfaces.CoffeeMachineService;
import org.testtask.CoffeeMachine.service.interfaces.CreateDrinkService;
import org.testtask.CoffeeMachine.service.interfaces.DrinksService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateDrinkServiceDefault implements CreateDrinkService {

    private final CoffeeMachineService coffeeMachineService;
    private final DrinksService drinksService;
    private long nextDrinkCanCreateTimestamp = 0; //обновляется в момент начала создания: текущая метка + время создания напитка * 1000

    @Override
    public String makeADrink(String name) {

        String responseMessage;


        List<String> drinkNames = getAvailableDrinksNames();
        if (!drinkNames.isEmpty()) {

            DrinkEntity chosenDrinkEntity = drinksService.findDrinkByName(name);
            if (chosenDrinkEntity == null) {
                responseMessage =
                        "Данный напиток не найден в системе! Список доступных сейчас: "
                                + drinkNames;
                throw new IncorrectNameException(responseMessage);
            } else if (!drinkNames.contains(name)) {
                String reason = drinksService.checkWhyCantCreate(name);
                responseMessage =
                        "В данный момент кофемашина не может приготовить данный напиток: " + reason;
                throw new RuntimeException(responseMessage);
            }

            if (machineIsFreeToBrew()) {
                try {
                    return createNewCoffee(name);
                } catch (RuntimeException ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            } else {
                throw new RuntimeException("В данный момент машина уже создает другой напиток, " +
                        "осталось " + secondsLeft() + " секунд");
            }
        }
        /*else {
            String status = coffeeMachineService.getMachineStatus();
            responseMessage = "С машиной проблема: " + status;
        }*/
        return null;
    }

    private String createNewCoffee(String name) {
        if ()
    }

    private List<String> getAvailableDrinksNames() {
        return drinksService.refreshAvailableDrinks()
                .stream()
                .map(AvailableDrinkDto::name)
                .toList();
    }

    private long secondsLeft() {
        return (nextDrinkCanCreateTimestamp - System.currentTimeMillis()) / 1000;
    }

    private boolean machineIsFreeToBrew() {
        return System.currentTimeMillis() > nextDrinkCanCreateTimestamp;
    }
}
