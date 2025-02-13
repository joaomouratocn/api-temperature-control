package br.com.devjmcn.api_temperature_control.service;

import br.com.devjmcn.api_temperature_control.domain.unit.UnitDto;
import br.com.devjmcn.api_temperature_control.domain.unit.UnitModel;
import br.com.devjmcn.api_temperature_control.infra.exception.custom.NotDataFoundException;
import br.com.devjmcn.api_temperature_control.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public UnitDto insertUnit(UnitDto unitDto) {
        UnitModel unitModel = new UnitModel(unitDto.name());
        UnitModel unitInserted = unitRepository.save(unitModel);
        return new UnitDto(unitInserted.getId(), unitInserted.getName());
    }

    public UnitDto updateUnit(UnitDto unitDto) {
        UnitModel unitModel = unitRepository.findById(unitDto.id())
                .orElseThrow(() -> new NotDataFoundException("No unit found!"));
        unitModel.setId(unitDto.id());
        unitModel.setName(unitDto.name());
        unitRepository.save(unitModel);
        return unitDto;
    }

    public void deleteUnit(String id) {
        UnitModel unitModel = unitRepository.findById(id)
                .orElseThrow(() -> new NotDataFoundException("No unit Found!"));
        unitModel.setEnable(false);
        unitRepository.save(unitModel);
    }

    public List<UnitDto> findAllUnits() {
        List<UnitModel> unitListModel = unitRepository.findAll();

        if (unitListModel.isEmpty()) {
            throw new NotDataFoundException("No units found database!");
        }

        return unitListModel.stream()
                .map(unitModel -> new UnitDto(unitModel.getId(), unitModel.getName()))
                .collect(Collectors.toList());
    }
}
