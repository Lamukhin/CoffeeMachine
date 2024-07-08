package org.testtask.CoffeeMachine.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testtask.CoffeeMachine.db.entity.OrderHistoryEntity;
import org.testtask.CoffeeMachine.db.entity.UsageCountersEntity;

import java.util.UUID;

public interface UsageCountersRepo extends JpaRepository<UsageCountersEntity, UUID> {
}
