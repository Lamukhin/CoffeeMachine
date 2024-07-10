package org.testtask.CoffeeMachine.service.interfaces;

import org.testtask.CoffeeMachine.db.entity.DrinkEntity;

public interface CreateDrinkService {

    String createNewCoffee(DrinkEntity chosenDrinkEntity, long nextDrinkCanBeCreatedTimestamp);
}
