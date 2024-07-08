package org.testtask.CoffeeMachine.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testtask.CoffeeMachine.db.entity.CoffeeMachineStateEntity;
import org.testtask.CoffeeMachine.db.entity.DrinkEntity;

import java.util.UUID;

public interface CoffeeMachineStateRepo extends JpaRepository<CoffeeMachineStateEntity, UUID> {
}
