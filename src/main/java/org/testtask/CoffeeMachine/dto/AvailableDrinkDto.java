package org.testtask.CoffeeMachine.dto;

public record AvailableDrinkDto(
    String name,
    //для упрощения в контексте ТЗ использую массу, а не объем напитка
    String finalWeight,
    String composition
) {}
