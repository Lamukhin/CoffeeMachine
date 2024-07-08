package org.testtask.CoffeeMachine.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testtask.CoffeeMachine.db.entity.CoffeeMachineStateEntity;
import org.testtask.CoffeeMachine.db.entity.OrderHistoryEntity;

import java.util.UUID;

public interface OrderHistoryRepo extends JpaRepository<OrderHistoryEntity, UUID> {
}
