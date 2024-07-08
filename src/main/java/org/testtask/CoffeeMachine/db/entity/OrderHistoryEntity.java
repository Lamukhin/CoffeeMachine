package org.testtask.CoffeeMachine.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "order_history")
@Getter
@Setter
public class OrderHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "drink_id")
    private UUID drinkId;
    @Column(name = "timestamp")
    private Timestamp orderTimestamp;
}
