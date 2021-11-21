package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private  static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet){
        petModel.add(pet, newId.getAndIncrement());
        if(petModel.getAll().size() == 1){
            return "Поздравляю! Вы создали первого домашнего питомца :)";
        }
        else return "Вы создали домашнего питомца!";
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public void delete(@RequestBody Map<String, Integer> id) {
        petModel.delete(id.get("id"));
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public String update(@PathVariable("id") int id, @RequestBody Pet pet){
        petModel.add(pet, id);
        return "Поздравляю! Вы успешно изменили данные питомца :)";
    }
}

// Выполнила Маркина Ксения Александровна 
