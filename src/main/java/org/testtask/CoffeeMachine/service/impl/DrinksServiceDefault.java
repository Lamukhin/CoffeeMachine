package org.testtask.CoffeeMachine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testtask.CoffeeMachine.MappingUtil;
import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.db.entity.DrinkStatusEntity;
import org.testtask.CoffeeMachine.db.entity.UsageCountersEntity;
import org.testtask.CoffeeMachine.db.repo.DrinkRepo;
import org.testtask.CoffeeMachine.db.repo.DrinkStatusRepo;
import org.testtask.CoffeeMachine.db.repo.UsageCountersRepo;
import org.testtask.CoffeeMachine.dto.AvailableDrinkDto;
import org.testtask.CoffeeMachine.service.interfaces.DrinksService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrinksServiceDefault implements DrinksService {

    private final DrinkRepo drinkRepo;
    private final UsageCountersRepo usagesRepo;
    private final DrinkStatusRepo drinkStatusRepo;

    @Override
    public List<AvailableDrinkDto> refreshAvailableDrinks() {

        List<DrinkEntity> availableNowDrinks = new ArrayList<>();
        List<DrinkEntity> allDrinks = drinkRepo.findAll();
        UsageCountersEntity usageStatus = usagesRepo.findAll().get(0);
        if(usageStatus != null) {
            allDrinks.forEach(currentDrink ->
                    checkDrinkStatus(currentDrink, usageStatus, availableNowDrinks));
        }
        return availableNowDrinks.stream().map(MappingUtil::drinkEntityToDto).toList();
    }

    private void checkDrinkStatus(
            DrinkEntity currentDrink,
            UsageCountersEntity usageStatus,
            List<DrinkEntity> availableNowDrinks) {

        StringBuilder status = new StringBuilder();
        if (usageStatus.getBeansAvailable() - currentDrink.getCoffeeBean() < 0) {
            status.append("недостаточно зёрен; ");
        }
        if (usageStatus.getWaterAvailable() - currentDrink.getWater() < 0) {
            status.append("недостаточно воды; ");
        }
        if (usageStatus.getMilkAvailable() - currentDrink.getMilk() < 0) {
            status.append("недостаточно молока; ");
        }
        if (!status.isEmpty()) {
            updateStatusByDrinkId(
                    currentDrink.getId(),
                    status.toString(),
                    false);
        } else {
            updateStatusByDrinkId(
                    currentDrink.getId(),
                    "можно создать;",
                    true);
            availableNowDrinks.add(currentDrink);
        }
    }

    @Override
    public boolean updateStatusByDrinkId(UUID drinkId, String newStatus, boolean canCreate) {
        DrinkStatusEntity drinkStatusEntity = drinkStatusRepo.findByDrinkId(drinkId);
        if (drinkStatusEntity != null) {
            drinkStatusEntity.setCanCreate(canCreate);
            drinkStatusEntity.setTextStatus(newStatus);
            drinkStatusRepo.save(drinkStatusEntity);
            return true;
        }
        return false;
    }

    @Override
    public DrinkEntity findDrinkByName(String name) {
        return drinkRepo.findDrinkEntityByName(name);
    }

    @Override
    public DrinkStatusEntity findDrinkStatusEntityByName(String name) {
        DrinkEntity drinkEntity = drinkRepo.findDrinkEntityByName(name);
        if (drinkEntity != null) {
            DrinkStatusEntity drinkStatusEntity = drinkStatusRepo.findByDrinkId(drinkEntity.getId());
            if (drinkStatusEntity != null) {
                return drinkStatusEntity;
            }
            throw new RuntimeException("Для данного напитка нет записи о статусе в базе");
        }
        throw new RuntimeException("Данный напиток не найден в базе");
    }
}
