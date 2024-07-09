package org.testtask.CoffeeMachine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testtask.CoffeeMachine.MappingUtil;
import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.db.entity.UsageCountersEntity;
import org.testtask.CoffeeMachine.db.repo.DrinkRepo;
import org.testtask.CoffeeMachine.db.repo.DrinkStatusRepo;
import org.testtask.CoffeeMachine.db.repo.UsageCountersRepo;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;
import org.testtask.CoffeeMachine.service.interfaces.DrinksService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinksServiceDefault implements DrinksService {

    private final DrinkRepo drinkRepo;
    private final UsageCountersRepo usagesRepo;
    private final DrinkStatusRepo drinkStatusRepo;
    private final DrinkStatusService drinkStatusService;

    @Override
    public List<AvailableDrinkDto> refreshAvailableDrinks() {
        List<DrinkEntity> availableNowDrinks = new ArrayList<>();
        List<DrinkEntity> allDrinks = drinkRepo.findAll();
        UsageCountersEntity usageStatus = usagesRepo.findAll().get(0);
        allDrinks.forEach(currentDrink -> {
            if(usageStatus.getBeansAvailable() - currentDrink.getCoffeeBean() < 0){
                String newStatus = "Недостаточно зёрен";
                drinkStatusService.updateStatusByDrinkId(currentDrink.getId(), newStatus);
            }
            сделать полностью так, чтобы рефрешилась таблица статусов, а потом создавать кофе уже на ее основании
            if (!((usageStatus.getBeansAvailable() - currentDrink.getCoffeeBean() <= 0) ||
                    (usageStatus.getMilkAvailable() - currentDrink.getMilk() <= 0) ||
                    (usageStatus.getWaterAvailable() - currentDrink.getWater() <= 0)))
                availableNowDrinks.add(currentDrink);
        });
        return availableNowDrinks.stream().map(MappingUtil::drinkEntityToDto).toList();
    }

    @Override
    public DrinkEntity findDrinkByName(String name) {
        return drinkRepo.findDrinkEntityByName(name);
    }

    @Override
    public String checkWhyCantCreate(String name) {
        DrinkEntity drinkEntity = drinkRepo.findDrinkEntityByName(name);
        UsageCountersEntity usageStatus = usagesRepo.findAll().get(0);
        if ((drinkEntity != null)&&(usageStatus != null)) {
            if

        }
    }
}
