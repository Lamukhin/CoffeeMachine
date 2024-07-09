package org.testtask.CoffeeMachine;

import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;

public class MappingUtil {

    public static AvailableDrinkDto drinkEntityToDto(DrinkEntity drinkEntity) {

        return new AvailableDrinkDto(
                drinkEntity.getName(),
                "" + drinkEntity.getMilk() + drinkEntity.getWater(),
                getDrinkComposition(drinkEntity)
        );
    }

    private static String getDrinkComposition(DrinkEntity drinkEntity) {
        StringBuilder sb = new StringBuilder();

        if (drinkEntity.getCoffeeBean() > 7) {
            sb.append("Double espresso");
        } else {
            sb.append("Espresso");
        }

        if (drinkEntity.getWater() != 0) {
            sb.append(", water");
        }

        if (drinkEntity.getMilk() != 0) {
            sb.append(", milk");
        }
        return sb.toString();
    }
}
