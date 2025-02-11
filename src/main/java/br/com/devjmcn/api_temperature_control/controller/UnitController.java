package br.com.devjmcn.api_temperature_control.controller;

import br.com.devjmcn.api_temperature_control.domain.UnitModel;
import br.com.devjmcn.api_temperature_control.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {
    @Autowired
    private UnitRepository unitRepository;

    @PostMapping
    public UnitModel insertUnit(@RequestBody UnitModel unitModel){
        return unitRepository.save(unitModel);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<UnitModel> getAllUnits(){
        return unitRepository.findAll();
    }
}
