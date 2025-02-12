package br.com.devjmcn.api_temperature_control.repository;

import br.com.devjmcn.api_temperature_control.domain.unit.UnitModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<UnitModel, String> {}
