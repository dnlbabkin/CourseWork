package com.example.aeronautics.controller;

import com.example.aeronautics.DAO.AeronauticsModel;
import com.example.aeronautics.service.AeronauticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/aeronautics",
        produces = "application/xml")
@RequiredArgsConstructor
@Slf4j
public class AeronauticsController {

    private final AeronauticsService service;

    @PostMapping("/create")
    public AeronauticsModel createAeronauticObject(@RequestBody AeronauticsModel model) {
        log.info(model.toString());
        return service.createAircraft(model.getAeronauticsId(), model);
    }

    @GetMapping("/get/{id}")
    public AeronauticsModel getAeronauticObject(@PathVariable Long id) {
        return service.getAircraft(id);
    }

    @DeleteMapping("/delete/{id}")
    public AeronauticsModel deleteAeronauticObject(@PathVariable Long id) {
        return service.deleteAircraft(id);
    }

    @PutMapping("/update/{id}")
    public AeronauticsModel updateAeronauticObject(@PathVariable Long id,
                                                   @RequestBody AeronauticsModel model) {
        return service.updateAircraft(id, model);
    }
}
