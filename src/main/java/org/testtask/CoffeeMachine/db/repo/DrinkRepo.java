package org.testtask.CoffeeMachine.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testtask.CoffeeMachine.db.entity.DrinkEntity;

import java.util.UUID;

public interface DrinkRepo extends JpaRepository<DrinkEntity, UUID> {
}
