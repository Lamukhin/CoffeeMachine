package org.testtask.CoffeeMachine.service.interfaces;

import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.db.entity.DrinkStatusEntity;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;

import java.util.List;
import java.util.UUID;

public interface DrinksService {

    List<AvailableDrinkDto> refreshAvailableDrinks();

    DrinkEntity findDrinkByName(String name);

    boolean updateStatusByDrinkId(UUID drinkId, String newStatus, boolean canCreate);

    DrinkStatusEntity findDrinkStatusEntityByName(String name);

}
