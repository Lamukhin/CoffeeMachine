package org.testtask.CoffeeMachine.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "order_history")
@Getter
@Setter
@NoArgsConstructor
public class OrderHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "drink_id")
    private UUID drinkId;
    @Column(name = "timestamp")
    private Timestamp orderTimestamp;

    public OrderHistoryEntity(UUID drinkId) {
        this.drinkId = drinkId;
        this.orderTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
