package org.testtask.CoffeeMachine.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "drink_status")
@Getter
@Setter
public class DrinkStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "drink_id")
    private UUID drinkId;
    @Column(name = "can_create")
    private boolean canCreate;
    @Column(name = "text_status")
    private String textStatus;

}
