package org.testtask.CoffeeMachine.service.interfaces;

import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;

import java.util.List;

public interface DrinksService {

    List<AvailableDrinkDto> refreshAvailableDrinks();

    DrinkEntity findDrinkByName(String name);

    String checkWhyCantCreate(String name);
}
