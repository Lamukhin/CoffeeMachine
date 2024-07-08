package org.testtask.CoffeeMachine.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "usage_counters")
@Getter
@Setter
public class UsageCountersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "coffmach_fact_numb_id")
    private UUID coffeeMachineId;
    @Column(name = "water_available_gr")
    private int waterAvailable;
    @Column(name = "milk_available_gr")
    private int milkAvailable;
    @Column(name = "beans_available_gr")
    private int beansAvailable;
    @Column(name = "drink_made_counter")
    private int drinkMadeCounter;
    @Column(name = "liquid_in_tray_gr")
    private int liquidInTray;
}
