package org.testtask.CoffeeMachine.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "coffee_machine_state")
@Getter
@Setter
public class CoffeeMachineStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "factory_number")
    private String factoryNumber;
    @Column(name = "no_water")
    private boolean noWater;
    @Column(name = "no_milk")
    private boolean noMilk;
    @Column(name = "no_beans")
    private boolean noBeans;
    @Column(name = "cleaning_required")
    private boolean cleaningRequired;
    @Column(name = "tray_full")
    private boolean trayFull;
    @Column(name = "repair_required")
    private boolean repairRequired;
}
