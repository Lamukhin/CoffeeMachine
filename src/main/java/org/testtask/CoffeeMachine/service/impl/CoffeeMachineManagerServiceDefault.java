package org.testtask.CoffeeMachine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testtask.CoffeeMachine.db.entity.*;
import org.testtask.CoffeeMachine.db.repo.CoffeeMachineStateRepo;
import org.testtask.CoffeeMachine.db.repo.OrderHistoryRepo;
import org.testtask.CoffeeMachine.db.repo.UsageCountersRepo;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;
import org.testtask.CoffeeMachine.exception.CoffeeMachineException;
import org.testtask.CoffeeMachine.exception.IncorrectNameException;
import org.testtask.CoffeeMachine.service.interfaces.CoffeeMachineManagerService;
import org.testtask.CoffeeMachine.service.interfaces.CreateDrinkService;
import org.testtask.CoffeeMachine.service.interfaces.DrinksService;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CoffeeMachineManagerServiceDefault implements CoffeeMachineManagerService {

    private final DrinksService drinksService;
    private final CoffeeMachineStateRepo coffeeMachineStateRepo;
    private final CreateDrinkService createDrinkService;
    //обновляется в момент начала создания: текущая метка + продолжительность создания напитка * 1000
    private Long nextDrinkCanBeCreatedTimestamp = 0L;

    @Override
    public String makeADrink(String name) {

        CoffeeMachineStateEntity coffeeMachineStateEntity = coffeeMachineStateRepo.findAll().get(0);

        if(coffeeMachineStateEntity != null) {

            if (coffeeMachineStateEntity.isTrayFull()) {
                throw new CoffeeMachineException("Невозможно приготовить напиток, прочистите поддон.");
            }
            if (coffeeMachineStateEntity.isRepairRequired()) {
                throw new CoffeeMachineException("Невозможно приготовить напиток, обратитесь в сервисный центр.");
            }
        }

        List<AvailableDrinkDto> availableNowDrinks = drinksService.refreshAvailableDrinks();
        String responseMessage;

        List<String> drinkNames = getAvailableDrinksNames(availableNowDrinks);

        DrinkEntity chosenDrinkEntity = drinksService.findDrinkByName(name);

        if ((chosenDrinkEntity == null) && (!drinkNames.isEmpty())) {
            responseMessage =
                    "Данный напиток не найден в системе! Список доступных сейчас: "
                            + drinkNames;
            throw new IncorrectNameException(responseMessage);
        } else if (!drinkNames.contains(name)) {
            DrinkStatusEntity drinkStatusEntity = drinksService.findDrinkStatusEntityByName(name);
            responseMessage =
                    "Сейчас кофемашина не может приготовить данный напиток: "
                            + drinkStatusEntity.getTextStatus();
            throw new CoffeeMachineException(responseMessage);
        }

        if (machineIsFreeToBrew()) {
            try {
                return createDrinkService.createNewCoffee(chosenDrinkEntity, nextDrinkCanBeCreatedTimestamp);
            } catch (Exception ex) {
                responseMessage = "Во время приготовления произошёл сбой: " + ex.getMessage();
                throw new CoffeeMachineException(responseMessage);
            }
        } else {
            responseMessage = "В данный момент машина уже создает другой напиток, " +
                    "осталось дождаться" + secondsLeft() + " секунд.";
            throw new CoffeeMachineException(responseMessage);
        }
    }

    private List<String> getAvailableDrinksNames(List<AvailableDrinkDto> availableDrinks) {
        return availableDrinks
                .stream()
                .map(AvailableDrinkDto::name)
                .toList();
    }

    private long secondsLeft() {
        return (nextDrinkCanBeCreatedTimestamp - System.currentTimeMillis()) / 1000;
    }

    private boolean machineIsFreeToBrew() {
        return System.currentTimeMillis() > nextDrinkCanBeCreatedTimestamp;
    }
}
