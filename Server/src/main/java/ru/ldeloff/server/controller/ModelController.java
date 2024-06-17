package ru.ldeloff.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ldeloff.server.model.Model;

@RestController
public class ModelController {
    @PostMapping(value = "/models")
    public Model test2(Model model) {
        int id = model.getId();
        model.setId(id + 1000000);
        return model;
    }
}
