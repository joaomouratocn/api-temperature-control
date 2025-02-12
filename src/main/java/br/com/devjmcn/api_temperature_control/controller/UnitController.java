package br.com.devjmcn.api_temperature_control.controller;

import br.com.devjmcn.api_temperature_control.domain.unit.UnitDto;
import br.com.devjmcn.api_temperature_control.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @PostMapping
    public ResponseEntity<UnitDto> insertUnit(@RequestBody @Valid UnitDto unitDto) {
        UnitDto unitDtoResponse = unitService.insertUnit(unitDto);
        return ResponseEntity.ok(unitDtoResponse);
    }

    @PutMapping()
    public ResponseEntity<UnitDto> updateUnit(@RequestBody @Valid UnitDto unitDto) {
        UnitDto unitDtoResponse = unitService.updateUnit(unitDto);
        return ResponseEntity.ok(unitDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable String id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<UnitDto>> getAllUnits() {
        List<UnitDto> unitDtoList = unitService.findAllUnits();
        return ResponseEntity.ok(unitDtoList);
    }
}
