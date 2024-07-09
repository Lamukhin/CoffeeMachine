package org.testtask.CoffeeMachine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testtask.CoffeeMachine.db.entity.CoffeeMachineStateEntity;
import org.testtask.CoffeeMachine.db.repo.CoffeeMachineStateRepo;
import org.testtask.CoffeeMachine.service.interfaces.CoffeeMachineService;

@Service
@RequiredArgsConstructor
public class CoffeeMachineServiceDefault implements CoffeeMachineService {

    private final CoffeeMachineStateRepo coffeeMachineStateRepo;

    @Override
    public String getMachineStatus() {
        CoffeeMachineStateEntity state = coffeeMachineStateRepo.findAll().get(0);
        if(state.)

    }
}
