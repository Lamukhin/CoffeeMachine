package org.testtask.CoffeeMachine.controller;

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

@Controller
@RequestMapping("/drink/")
@RequiredArgsConstructor
public class CreateDrinkController {

    private final CreateDrinkService createDrinkService;

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
