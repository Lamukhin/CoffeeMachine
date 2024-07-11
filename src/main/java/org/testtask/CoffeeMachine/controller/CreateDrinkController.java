package org.testtask.CoffeeMachine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.testtask.CoffeeMachine.service.interfaces.CoffeeMachineManagerService;
import org.testtask.CoffeeMachine.service.interfaces.DrinksService;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CreateDrinkController {

    private final CoffeeMachineManagerService coffeeMachineManagerService;
    private final DrinksService drinksService;
    private final ObjectMapper objectMapper;

    @GetMapping("/drinks")
    public ResponseEntity<String> getAvailableDrinks() throws JsonProcessingException {
        return ResponseEntity.ok(
                objectMapper.writeValueAsString(
                        drinksService.refreshAvailableDrinks()
                )
        );
    }

    @GetMapping("/drink")
    public ResponseEntity<String> createNewDrink(
            @RequestParam(name = "name") String name) throws JsonProcessingException {
        return ResponseEntity.ok(
                objectMapper.writeValueAsString(
                        coffeeMachineManagerService.makeADrink(name)
                )
        );
    }
}
