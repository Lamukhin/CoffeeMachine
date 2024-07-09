package org.testtask.CoffeeMachine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.testtask.CoffeeMachine.exception.CoffeeMachineException;
import org.testtask.CoffeeMachine.exception.IncorrectNameException;
import org.testtask.CoffeeMachine.service.interfaces.CreateDrinkService;
import org.testtask.CoffeeMachine.service.interfaces.DrinksService;

@Controller
@RequestMapping("/drink/")
@RequiredArgsConstructor
public class CreateDrinkController {

    private final CreateDrinkService createDrinkService;
    private final DrinksService drinksService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<String> getAvailableDrinks() {
        try {
            return ResponseEntity.ok(
                    objectMapper.writeValueAsString(
                            drinksService.refreshAvailableDrinks()
                    )
            );
        } catch (RuntimeException | JsonProcessingException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<String> createNewDrink(
            @RequestParam(name = "name") String name) {
        try {
            return ResponseEntity.ok(createDrinkService.makeADrink(name));
        } catch (IncorrectNameException ex) {
            return new ResponseEntity<>(ex.getMessageForUser(), HttpStatus.BAD_REQUEST);
        } catch (CoffeeMachineException ex) {
            return new ResponseEntity<>(ex.getMessageForUser(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
