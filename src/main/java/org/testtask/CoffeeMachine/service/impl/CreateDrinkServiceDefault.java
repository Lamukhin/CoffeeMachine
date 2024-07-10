package org.testtask.CoffeeMachine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testtask.CoffeeMachine.db.entity.CoffeeMachineStateEntity;
import org.testtask.CoffeeMachine.db.entity.DrinkEntity;
import org.testtask.CoffeeMachine.db.entity.OrderHistoryEntity;
import org.testtask.CoffeeMachine.db.entity.UsageCountersEntity;
import org.testtask.CoffeeMachine.db.repo.CoffeeMachineStateRepo;
import org.testtask.CoffeeMachine.db.repo.OrderHistoryRepo;
import org.testtask.CoffeeMachine.db.repo.UsageCountersRepo;
import org.testtask.CoffeeMachine.service.interfaces.CreateDrinkService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreateDrinkServiceDefault implements CreateDrinkService {

    private final UsageCountersRepo usageCountersRepo;
    private final OrderHistoryRepo orderHistoryRepo;
    private final CoffeeMachineStateRepo coffeeMachineStateRepo;

    @Override
    @Transactional
    public String createNewCoffee(DrinkEntity chosenDrinkEntity, Long nextDrinkCanBeCreatedTimestamp) {

        StringBuilder response = new StringBuilder();
        response.append("Ваш напиток готовится, время приготовления: ");
        response.append(chosenDrinkEntity.getMakeDuration());
        response.append(" секунд.");

        UsageCountersEntity usageCountersEntity;
        try {
            usageCountersEntity = updateUsageCounters(chosenDrinkEntity);
        } catch (RuntimeException ex){
            throw new RuntimeException();
        }

        orderHistoryRepo.save(new OrderHistoryEntity(chosenDrinkEntity.getId()));

        CoffeeMachineStateEntity coffeeMachineStateEntity = coffeeMachineStateRepo.findAll().get(0);
        if(coffeeMachineStateEntity != null) {

            if (usageCountersEntity.getDrinkMadeCounter() >= 50) {
                coffeeMachineStateEntity.setCleaningRequired(true);
                response.append("ВНИМАНИЕ! Кофемашине требуется чистка!");
            }

            if (usageCountersEntity.getLiquidInTray() >= 300)
                coffeeMachineStateEntity.setTrayFull(true);

            //введём условную вероятность поломки машины
            if (cmIsBroken())
                coffeeMachineStateEntity.setRepairRequired(true);

            coffeeMachineStateRepo.save(coffeeMachineStateEntity);
        } else {
            throw new RuntimeException();
        }
        nextDrinkCanBeCreatedTimestamp = System.currentTimeMillis() + chosenDrinkEntity.getMakeDuration() * 1000L;
        return response.toString();
    }

    private UsageCountersEntity updateUsageCounters(DrinkEntity chosenDrinkEntity) {

        UsageCountersEntity usageCountersEntity = usageCountersRepo.findAll().get(0);
        if(usageCountersEntity != null) {
            usageCountersEntity.setBeansAvailable(usageCountersEntity.getBeansAvailable() - chosenDrinkEntity.getCoffeeBean());
            usageCountersEntity.setWaterAvailable(usageCountersEntity.getWaterAvailable() - chosenDrinkEntity.getWater());
            usageCountersEntity.setMilkAvailable(usageCountersEntity.getMilkAvailable() - chosenDrinkEntity.getMilk());
            usageCountersEntity.setLiquidInTray(usageCountersEntity.getLiquidInTray() + 10);
            usageCountersEntity.setDrinkMadeCounter(usageCountersEntity.getDrinkMadeCounter() + 1);
            return usageCountersRepo.save(usageCountersEntity);
        }
        throw new RuntimeException("В базе нет информации по использованию кофемашины.");
    }

    private boolean cmIsBroken() {
        Random random = new Random();
        int randomInt = random.nextInt(10000) + 1;
        return randomInt == 1;
    }
}
