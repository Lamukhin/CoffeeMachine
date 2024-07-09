package org.testtask.CoffeeMachine.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testtask.CoffeeMachine.db.entity.DrinkStatusEntity;

import java.util.UUID;

public interface DrinkStatusRepo extends JpaRepository<DrinkStatusEntity, UUID> {

    DrinkStatusEntity findByDrinkId(UUID id);
}
