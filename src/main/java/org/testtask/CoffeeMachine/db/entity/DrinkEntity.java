package org.testtask.CoffeeMachine.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "drink")
@Getter
@Setter
public class DrinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "water_gr")
    private int water;
    @Column(name = "milk_gr")
    private int milk;
    @Column(name = "coffee_bean_gr")
    private int coffeeBean;

}
